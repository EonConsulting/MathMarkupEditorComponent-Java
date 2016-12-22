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
import javax.persistence.Lob;
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
@Table(name = "lti_key", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiKey.findAll", query = "SELECT l FROM LtiKey l")
    , @NamedQuery(name = "LtiKey.findByKeyId", query = "SELECT l FROM LtiKey l WHERE l.keyId = :keyId")
    , @NamedQuery(name = "LtiKey.findByKeySha256", query = "SELECT l FROM LtiKey l WHERE l.keySha256 = :keySha256")
    , @NamedQuery(name = "LtiKey.findByUserId", query = "SELECT l FROM LtiKey l WHERE l.userId = :userId")
    , @NamedQuery(name = "LtiKey.findByEntityVersion", query = "SELECT l FROM LtiKey l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiKey.findByCreatedAt", query = "SELECT l FROM LtiKey l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiKey.findByUpdatedAt", query = "SELECT l FROM LtiKey l WHERE l.updatedAt = :updatedAt")})
public class LtiKey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "key_id")
    private Integer keyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "key_sha256")
    private String keySha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "key_key")
    private String keyKey;
    @Lob
    @Size(max = 65535)
    @Column(name = "secret")
    private String secret;
    @Lob
    @Size(max = 65535)
    @Column(name = "new_secret")
    private String newSecret;
    @Lob
    @Size(max = 65535)
    @Column(name = "ack")
    private String ack;
    @Column(name = "user_id")
    private Integer userId;
    @Lob
    @Size(max = 65535)
    @Column(name = "consumer_profile")
    private String consumerProfile;
    @Lob
    @Size(max = 65535)
    @Column(name = "new_consumer_profile")
    private String newConsumerProfile;
    @Lob
    @Size(max = 65535)
    @Column(name = "tool_profile")
    private String toolProfile;
    @Lob
    @Size(max = 65535)
    @Column(name = "new_tool_profile")
    private String newToolProfile;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "keyId")
    private List<LtiContext> ltiContextList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "keyId")
    private List<LtiService> ltiServiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "keyId")
    private List<LtiUser> ltiUserList;

    public LtiKey() {
    }

    public LtiKey(Integer keyId) {
        this.keyId = keyId;
    }

    public LtiKey(Integer keyId, String keySha256, String keyKey, int entityVersion, Date createdAt, Date updatedAt) {
        this.keyId = keyId;
        this.keySha256 = keySha256;
        this.keyKey = keyKey;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getKeySha256() {
        return keySha256;
    }

    public void setKeySha256(String keySha256) {
        this.keySha256 = keySha256;
    }

    public String getKeyKey() {
        return keyKey;
    }

    public void setKeyKey(String keyKey) {
        this.keyKey = keyKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getNewSecret() {
        return newSecret;
    }

    public void setNewSecret(String newSecret) {
        this.newSecret = newSecret;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsumerProfile() {
        return consumerProfile;
    }

    public void setConsumerProfile(String consumerProfile) {
        this.consumerProfile = consumerProfile;
    }

    public String getNewConsumerProfile() {
        return newConsumerProfile;
    }

    public void setNewConsumerProfile(String newConsumerProfile) {
        this.newConsumerProfile = newConsumerProfile;
    }

    public String getToolProfile() {
        return toolProfile;
    }

    public void setToolProfile(String toolProfile) {
        this.toolProfile = toolProfile;
    }

    public String getNewToolProfile() {
        return newToolProfile;
    }

    public void setNewToolProfile(String newToolProfile) {
        this.newToolProfile = newToolProfile;
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
    public List<LtiContext> getLtiContextList() {
        return ltiContextList;
    }

    public void setLtiContextList(List<LtiContext> ltiContextList) {
        this.ltiContextList = ltiContextList;
    }

    @XmlTransient
    public List<LtiService> getLtiServiceList() {
        return ltiServiceList;
    }

    public void setLtiServiceList(List<LtiService> ltiServiceList) {
        this.ltiServiceList = ltiServiceList;
    }

    @XmlTransient
    public List<LtiUser> getLtiUserList() {
        return ltiUserList;
    }

    public void setLtiUserList(List<LtiUser> ltiUserList) {
        this.ltiUserList = ltiUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyId != null ? keyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiKey)) {
            return false;
        }
        LtiKey other = (LtiKey) object;
        if ((this.keyId == null && other.keyId != null) || (this.keyId != null && !this.keyId.equals(other.keyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiKey[ keyId=" + keyId + " ]";
    }
    
}
