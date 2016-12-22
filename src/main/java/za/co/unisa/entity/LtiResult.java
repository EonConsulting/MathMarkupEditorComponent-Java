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
@Table(name = "lti_result", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiResult.findAll", query = "SELECT l FROM LtiResult l")
    , @NamedQuery(name = "LtiResult.findByResultId", query = "SELECT l FROM LtiResult l WHERE l.resultId = :resultId")
    , @NamedQuery(name = "LtiResult.findByGrade", query = "SELECT l FROM LtiResult l WHERE l.grade = :grade")
    , @NamedQuery(name = "LtiResult.findByServerGrade", query = "SELECT l FROM LtiResult l WHERE l.serverGrade = :serverGrade")
    , @NamedQuery(name = "LtiResult.findByEntityVersion", query = "SELECT l FROM LtiResult l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiResult.findByCreatedAt", query = "SELECT l FROM LtiResult l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiResult.findByUpdatedAt", query = "SELECT l FROM LtiResult l WHERE l.updatedAt = :updatedAt")
    , @NamedQuery(name = "LtiResult.findByRetrievedAt", query = "SELECT l FROM LtiResult l WHERE l.retrievedAt = :retrievedAt")})
public class LtiResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "result_id")
    private Integer resultId;
    @Lob
    @Size(max = 65535)
    @Column(name = "result_url")
    private String resultUrl;
    @Lob
    @Size(max = 65535)
    @Column(name = "sourcedid")
    private String sourcedid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grade")
    private Float grade;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @Column(name = "server_grade")
    private Float serverGrade;
    @Lob
    @Size(max = 65535)
    @Column(name = "json")
    private String json;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entity_version")
    private int entityVersion;
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
    @Column(name = "retrieved_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retrievedAt;
    @JoinColumn(name = "link_id", referencedColumnName = "link_id")
    @ManyToOne(optional = false)
    private LtiLink linkId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private LtiUser userId;
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    @ManyToOne
    private LtiService serviceId;

    public LtiResult() {
    }

    public LtiResult(Integer resultId) {
        this.resultId = resultId;
    }

    public LtiResult(Integer resultId, int entityVersion, Date createdAt, Date updatedAt) {
        this.resultId = resultId;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }

    public String getSourcedid() {
        return sourcedid;
    }

    public void setSourcedid(String sourcedid) {
        this.sourcedid = sourcedid;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getServerGrade() {
        return serverGrade;
    }

    public void setServerGrade(Float serverGrade) {
        this.serverGrade = serverGrade;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(int entityVersion) {
        this.entityVersion = entityVersion;
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

    public Date getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(Date retrievedAt) {
        this.retrievedAt = retrievedAt;
    }

    public LtiLink getLinkId() {
        return linkId;
    }

    public void setLinkId(LtiLink linkId) {
        this.linkId = linkId;
    }

    public LtiUser getUserId() {
        return userId;
    }

    public void setUserId(LtiUser userId) {
        this.userId = userId;
    }

    public LtiService getServiceId() {
        return serviceId;
    }

    public void setServiceId(LtiService serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultId != null ? resultId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiResult)) {
            return false;
        }
        LtiResult other = (LtiResult) object;
        if ((this.resultId == null && other.resultId != null) || (this.resultId != null && !this.resultId.equals(other.resultId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiResult[ resultId=" + resultId + " ]";
    }
    
}
