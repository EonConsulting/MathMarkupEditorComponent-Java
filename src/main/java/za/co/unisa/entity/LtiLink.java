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
@Table(name = "lti_link", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiLink.findAll", query = "SELECT l FROM LtiLink l")
    , @NamedQuery(name = "LtiLink.findByLinkId", query = "SELECT l FROM LtiLink l WHERE l.linkId = :linkId")
    , @NamedQuery(name = "LtiLink.findByLinkSha256", query = "SELECT l FROM LtiLink l WHERE l.linkSha256 = :linkSha256")
    , @NamedQuery(name = "LtiLink.findByEntityVersion", query = "SELECT l FROM LtiLink l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiLink.findByCreatedAt", query = "SELECT l FROM LtiLink l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiLink.findByUpdatedAt", query = "SELECT l FROM LtiLink l WHERE l.updatedAt = :updatedAt")})
public class LtiLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "link_id")
    private Integer linkId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "link_sha256")
    private String linkSha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "link_key")
    private String linkKey;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linkId")
    private List<LtiResult> ltiResultList;
    @JoinColumn(name = "context_id", referencedColumnName = "context_id")
    @ManyToOne(optional = false)
    private LtiContext contextId;
    @OneToMany(mappedBy = "linkId")
    private List<MailSent> mailSentList;

    public LtiLink() {
    }

    public LtiLink(Integer linkId) {
        this.linkId = linkId;
    }

    public LtiLink(Integer linkId, String linkSha256, String linkKey, int entityVersion, Date createdAt, Date updatedAt) {
        this.linkId = linkId;
        this.linkSha256 = linkSha256;
        this.linkKey = linkKey;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkSha256() {
        return linkSha256;
    }

    public void setLinkSha256(String linkSha256) {
        this.linkSha256 = linkSha256;
    }

    public String getLinkKey() {
        return linkKey;
    }

    public void setLinkKey(String linkKey) {
        this.linkKey = linkKey;
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

    @XmlTransient
    public List<LtiResult> getLtiResultList() {
        return ltiResultList;
    }

    public void setLtiResultList(List<LtiResult> ltiResultList) {
        this.ltiResultList = ltiResultList;
    }

    public LtiContext getContextId() {
        return contextId;
    }

    public void setContextId(LtiContext contextId) {
        this.contextId = contextId;
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
        hash += (linkId != null ? linkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiLink)) {
            return false;
        }
        LtiLink other = (LtiLink) object;
        if ((this.linkId == null && other.linkId != null) || (this.linkId != null && !this.linkId.equals(other.linkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiLink[ linkId=" + linkId + " ]";
    }
    
}
