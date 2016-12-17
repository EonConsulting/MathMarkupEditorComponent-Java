/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.mathcomponent.ejb;

import com.unisa.mathcomponent.entity.Fomulae;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mbaka Motale
 */
@Stateless
public class FomulaeFacade extends AbstractFacade<Fomulae> {

    @PersistenceContext(unitName = "MathComponentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FomulaeFacade() {
        super(Fomulae.class);
    }
    
}
