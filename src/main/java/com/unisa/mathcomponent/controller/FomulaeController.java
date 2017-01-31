/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.mathcomponent.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.unisa.mathcomponent.controller.util.JsfUtil;
import com.unisa.mathcomponent.controller.util.PagingInfo;
import com.unisa.mathcomponent.ejb.FomulaeFacade;
import com.unisa.mathcomponent.entity.Fomulae;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Mbaka Motale
 */
public class FomulaeController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FomulaeController.class.getName());

    public FomulaeController() {
        pagingInfo = new PagingInfo();
        converter = new FomulaeConverter();
    }
    private Fomulae fomulae = new Fomulae();
    private Fomulae selectedFoumula = new Fomulae();
    private List<Fomulae> fomulaeItems = null;
    private FomulaeFacade jpaController = null;
    private FomulaeConverter converter = null;
    private PagingInfo pagingInfo = null;
    private String markup;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MathComponentPU")
    private EntityManagerFactory emf = null;
    private String markupValue = null;
    private String formulaID = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public FomulaeFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (FomulaeFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "fomulaeJpa");
        }
        return jpaController;
    }

    public SelectItem[] getFomulaeItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getFomulaeItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Fomulae getSelectedFoumula() {
        return selectedFoumula;
    }

    public void setSelectedFoumula(Fomulae selectedFoumula) {
        this.selectedFoumula = selectedFoumula;
    }

    public Fomulae getFomulae() {
        if (fomulae == null) {
            fomulae = (Fomulae) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentFomulae", converter, null);
        }
        if (fomulae == null) {
            fomulae = new Fomulae();
        }
        return fomulae;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public String getMarkupValue() {
        return markupValue;
    }

    public void setMarkupValue(String markupValue) {
        this.markupValue = markupValue;
    }

    public String getFormulaID() {
        return formulaID;
    }

    public void setFormulaID(String formulaID) {
        this.formulaID = formulaID;
    }

    public void displayMarkup() {
        System.out.println("Markup Value: " + markup);
        //return null;
    }

    public String defaultMarkup() {
        try {
            Integer id = Integer.valueOf(formulaID);
            Fomulae initMarkup = getJpaController().find(id);
            markupValue = initMarkup.getMarkup();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            fomulae = new Fomulae();
            return "'<math><mtext>X+Y</mtext></math>'";
        }
        return "'" + markupValue + "'";
    }

    public String editFomula() {
        fomulae = selectedFoumula;
        System.out.println("listing view");
        return "/MathML.xhtml";
    }

    public String listSetup() {
        reset(true);
        return "/List.xhtml";
    }

    public String prapareEditor() {
        fomulae = new Fomulae();
        formulaID = null;
        return "/MathML.xhtml";
    }

    public String createSetup() {
        reset(false);
        fomulae = new Fomulae();
        return "/crud/fomulae/New.jsp";
    }

    public boolean updateCreate() {
        if (fomulae == null) {
            return true;
        } else {
            return false;
        }
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            fomulae.setActiveInd(false);
            fomulae.setSubjectId(1);
            fomulae.setMarkup(markup);
            System.out.println("marku up value: " + markup);
            getJpaController().create(fomulae);
            try {
                utx.commit();
                formulaID = String.valueOf(fomulae.getId());
            } catch (javax.transaction.RollbackException ex) {
                LOGGER.log(Level.SEVERE, "Exception occur ", ex);
                transactionException = ex;
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Fomulae was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
                LOGGER.log(Level.SEVERE, "Exception occur ", transactionException);
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            }
            LOGGER.log(Level.SEVERE, "Exception occur ", e);
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return "";
    }

    public String detailSetup() {
        return scalarSetup("MathML.xhtml");
    }

    public String editSetup() {
        return scalarSetup("MathML.xhtml");
    }

    private String scalarSetup(String destination) {
        reset(false);
        fomulae = (Fomulae) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentFomulae", converter, null);
        if (fomulae == null) {
            String requestFomulaeString = JsfUtil.getRequestParameter("jsfcrud.currentFomulae");
            JsfUtil.addErrorMessage("The fomulae with id " + requestFomulaeString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {

        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            fomulae.setMarkup(markup);
            getJpaController().edit(fomulae);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Fomulae was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return "";
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentFomulae");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Fomulae was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        /*String relatedControllerOutcome = relatedControllerOutcome();
        if ((ERROR)) {
            return relatedControllerOutcome;
        }*/
        return listSetup();
    }

    public List<Fomulae> getFomulaeItems() {
        if (fomulaeItems == null) {
            getPagingInfo();
            fomulaeItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return fomulaeItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "fomulae_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "fomulae_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        fomulae = null;
        fomulaeItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Fomulae newFomulae = new Fomulae();
        String newFomulaeString = converter.getAsString(FacesContext.getCurrentInstance(), null, newFomulae);
        String fomulaeString = converter.getAsString(FacesContext.getCurrentInstance(), null, fomulae);
        if (!newFomulaeString.equals(fomulaeString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
