/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.mathcomponent.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mbaka Motale
 */
@Entity
@Cacheable(false)
@Table(catalog = "mathcomponent", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fomulae.findAll", query = "SELECT f FROM Fomulae f"),
    @NamedQuery(name = "Fomulae.findById", query = "SELECT f FROM Fomulae f WHERE f.id = :id"),
    @NamedQuery(name = "Fomulae.findBySubjectId", query = "SELECT f FROM Fomulae f WHERE f.subjectId = :subjectId"),
    @NamedQuery(name = "Fomulae.findByActiveInd", query = "SELECT f FROM Fomulae f WHERE f.activeInd = :activeInd"),
    @NamedQuery(name = "Fomulae.findByDescription", query = "SELECT f FROM Fomulae f WHERE f.description = :description"),})
public class Fomulae implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "base_string")
    private String baseString;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subject_id")
    private int subjectId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active_ind")
    private boolean activeInd;
    @Basic(optional = true)
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name="markup")
    private String markup;

    public Fomulae() {
    }

    public Fomulae(Integer id) {
        this.id = id;
    }

    public Fomulae(Integer id, String baseString, int subjectId, boolean activeInd, String description) {
        this.id = id;
        this.baseString = baseString;
        this.subjectId = subjectId;
        this.activeInd = activeInd;
        this.description = description;
        
    }

    public boolean isActiveInd() {
        return activeInd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseString() {
        return baseString;
    }

    public void setBaseString(String baseString) {
        this.baseString = baseString;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(boolean activeInd) {
        this.activeInd = activeInd;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fomulae)) {
            return false;
        }
        Fomulae other = (Fomulae) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unisa.mathcomponent.entity.Fomulae[ id=" + id + " ]";
    }
    
}
