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
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Mufaro Benedict
 * @author mbaka motale
 */
@Entity
@Table(name = "lti_service", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiService.findAll", query = "SELECT l FROM LtiService l")
    , @NamedQuery(name = "LtiService.findByServiceId", query = "SELECT l FROM LtiService l WHERE l.serviceId = :serviceId")
    , @NamedQuery(name = "LtiService.findByServiceSha256", query = "SELECT l FROM LtiService l WHERE l.serviceSha256 = :serviceSha256")
    , @NamedQuery(name = "LtiService.findByFormat", query = "SELECT l FROM LtiService l WHERE l.format = :format")
    , @NamedQuery(name = "LtiService.findByEntityVersion", query = "SELECT l FROM LtiService l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiService.findByCreatedAt", query = "SELECT l FROM LtiService l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiService.findByUpdatedAt", query = "SELECT l FROM LtiService l WHERE l.updatedAt = :updatedAt")})
public class LtiService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "service_id")
    private Integer serviceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "service_sha256")
    private String serviceSha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "service_key")
    private String serviceKey;
    @Size(max = 1024)
    @Column(name = "format")
    private String format;
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
    @OneToMany(mappedBy = "serviceId")
    private List<LtiResult> ltiResultList;
    @JoinColumn(name = "key_id", referencedColumnName = "key_id")
    @ManyToOne(optional = false)
    private LtiKey keyId;

    public LtiService() {
    }

    public LtiService(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public LtiService(Integer serviceId, String serviceSha256, String serviceKey, int entityVersion, Date createdAt, Date updatedAt) {
        this.serviceId = serviceId;
        this.serviceSha256 = serviceSha256;
        this.serviceKey = serviceKey;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceSha256() {
        return serviceSha256;
    }

    public void setServiceSha256(String serviceSha256) {
        this.serviceSha256 = serviceSha256;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    @XmlTransient
    public List<LtiResult> getLtiResultList() {
        return ltiResultList;
    }

    public void setLtiResultList(List<LtiResult> ltiResultList) {
        this.ltiResultList = ltiResultList;
    }

    public LtiKey getKeyId() {
        return keyId;
    }

    public void setKeyId(LtiKey keyId) {
        this.keyId = keyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiService)) {
            return false;
        }
        LtiService other = (LtiService) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiService[ serviceId=" + serviceId + " ]";
    }
    
}
