/*
 * Copyright (c) 2016, University of South Africa and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package za.co.unisa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Mufaro Benedict
 * @author mbaka motale
 */
@Entity
@Table(name = "key_request", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeyRequest.findAll", query = "SELECT k FROM KeyRequest k")
    , @NamedQuery(name = "KeyRequest.findByRequestId", query = "SELECT k FROM KeyRequest k WHERE k.requestId = :requestId")
    , @NamedQuery(name = "KeyRequest.findByTitle", query = "SELECT k FROM KeyRequest k WHERE k.title = :title")
    , @NamedQuery(name = "KeyRequest.findByState", query = "SELECT k FROM KeyRequest k WHERE k.state = :state")
    , @NamedQuery(name = "KeyRequest.findByLti", query = "SELECT k FROM KeyRequest k WHERE k.lti = :lti")
    , @NamedQuery(name = "KeyRequest.findByCreatedAt", query = "SELECT k FROM KeyRequest k WHERE k.createdAt = :createdAt")
    , @NamedQuery(name = "KeyRequest.findByUpdatedAt", query = "SELECT k FROM KeyRequest k WHERE k.updatedAt = :updatedAt")})
public class KeyRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id")
    private Integer requestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "notes")
    private String notes;
    @Lob
    @Size(max = 65535)
    @Column(name = "admin")
    private String admin;
    @Column(name = "state")
    private Short state;
    @Column(name = "lti")
    private Short lti;
    @Lob
    @Size(max = 65535)
    @Column(name = "json")
    private String json;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private LtiUser userId;

    public KeyRequest() {
    }

    public KeyRequest(Integer requestId) {
        this.requestId = requestId;
    }

    public KeyRequest(Integer requestId, String title, Date createdAt, Date updatedAt) {
        this.requestId = requestId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Short getLti() {
        return lti;
    }

    public void setLti(Short lti) {
        this.lti = lti;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LtiUser getUserId() {
        return userId;
    }

    public void setUserId(LtiUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeyRequest)) {
            return false;
        }
        KeyRequest other = (KeyRequest) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.KeyRequest[ requestId=" + requestId + " ]";
    }
    
}
