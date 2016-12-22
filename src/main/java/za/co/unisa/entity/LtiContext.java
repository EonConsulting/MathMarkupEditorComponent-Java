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
import javax.persistence.CascadeType;
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
@Table(name = "lti_context", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiContext.findAll", query = "SELECT l FROM LtiContext l")
    , @NamedQuery(name = "LtiContext.findByContextId", query = "SELECT l FROM LtiContext l WHERE l.contextId = :contextId")
    , @NamedQuery(name = "LtiContext.findByContextSha256", query = "SELECT l FROM LtiContext l WHERE l.contextSha256 = :contextSha256")
    , @NamedQuery(name = "LtiContext.findByEntityVersion", query = "SELECT l FROM LtiContext l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiContext.findByCreatedAt", query = "SELECT l FROM LtiContext l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiContext.findByUpdatedAt", query = "SELECT l FROM LtiContext l WHERE l.updatedAt = :updatedAt")})
public class LtiContext implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "context_id")
    private Integer contextId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "context_sha256")
    private String contextSha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "context_key")
    private String contextKey;
    @Lob
    @Size(max = 65535)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "json")
    private String json;
    @Lob
    @Size(max = 65535)
    @Column(name = "settings")
    private String settings;
    @Lob
    @Size(max = 65535)
    @Column(name = "settings_url")
    private String settingsUrl;
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
    @JoinColumn(name = "key_id", referencedColumnName = "key_id")
    @ManyToOne(optional = false)
    private LtiKey keyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contextId")
    private List<MailBulk> mailBulkList;
    @OneToMany(mappedBy = "contextId")
    private List<BlobFile> blobFileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contextId")
    private List<LtiLink> ltiLinkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contextId")
    private List<LtiMembership> ltiMembershipList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contextId")
    private List<MailSent> mailSentList;

    public LtiContext() {
    }

    public LtiContext(Integer contextId) {
        this.contextId = contextId;
    }

    public LtiContext(Integer contextId, String contextSha256, String contextKey, int entityVersion, Date createdAt, Date updatedAt) {
        this.contextId = contextId;
        this.contextSha256 = contextSha256;
        this.contextKey = contextKey;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getContextId() {
        return contextId;
    }

    public void setContextId(Integer contextId) {
        this.contextId = contextId;
    }

    public String getContextSha256() {
        return contextSha256;
    }

    public void setContextSha256(String contextSha256) {
        this.contextSha256 = contextSha256;
    }

    public String getContextKey() {
        return contextKey;
    }

    public void setContextKey(String contextKey) {
        this.contextKey = contextKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getSettingsUrl() {
        return settingsUrl;
    }

    public void setSettingsUrl(String settingsUrl) {
        this.settingsUrl = settingsUrl;
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

    public LtiKey getKeyId() {
        return keyId;
    }

    public void setKeyId(LtiKey keyId) {
        this.keyId = keyId;
    }

    @XmlTransient
    public List<MailBulk> getMailBulkList() {
        return mailBulkList;
    }

    public void setMailBulkList(List<MailBulk> mailBulkList) {
        this.mailBulkList = mailBulkList;
    }

    @XmlTransient
    public List<BlobFile> getBlobFileList() {
        return blobFileList;
    }

    public void setBlobFileList(List<BlobFile> blobFileList) {
        this.blobFileList = blobFileList;
    }

    @XmlTransient
    public List<LtiLink> getLtiLinkList() {
        return ltiLinkList;
    }

    public void setLtiLinkList(List<LtiLink> ltiLinkList) {
        this.ltiLinkList = ltiLinkList;
    }

    @XmlTransient
    public List<LtiMembership> getLtiMembershipList() {
        return ltiMembershipList;
    }

    public void setLtiMembershipList(List<LtiMembership> ltiMembershipList) {
        this.ltiMembershipList = ltiMembershipList;
    }

    @XmlTransient
    public List<MailSent> getMailSentList() {
        return mailSentList;
    }

    public void setMailSentList(List<MailSent> mailSentList) {
        this.mailSentList = mailSentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contextId != null ? contextId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiContext)) {
            return false;
        }
        LtiContext other = (LtiContext) object;
        if ((this.contextId == null && other.contextId != null) || (this.contextId != null && !this.contextId.equals(other.contextId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiContext[ contextId=" + contextId + " ]";
    }
    
}
