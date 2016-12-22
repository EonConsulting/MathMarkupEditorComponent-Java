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
import javax.persistence.Lob;
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
@Table(name = "profile", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findByProfileId", query = "SELECT p FROM Profile p WHERE p.profileId = :profileId")
    , @NamedQuery(name = "Profile.findByProfileSha256", query = "SELECT p FROM Profile p WHERE p.profileSha256 = :profileSha256")
    , @NamedQuery(name = "Profile.findByKeyId", query = "SELECT p FROM Profile p WHERE p.keyId = :keyId")
    , @NamedQuery(name = "Profile.findByLocale", query = "SELECT p FROM Profile p WHERE p.locale = :locale")
    , @NamedQuery(name = "Profile.findBySubscribe", query = "SELECT p FROM Profile p WHERE p.subscribe = :subscribe")
    , @NamedQuery(name = "Profile.findByLoginAt", query = "SELECT p FROM Profile p WHERE p.loginAt = :loginAt")
    , @NamedQuery(name = "Profile.findByEntityVersion", query = "SELECT p FROM Profile p WHERE p.entityVersion = :entityVersion")
    , @NamedQuery(name = "Profile.findByCreatedAt", query = "SELECT p FROM Profile p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Profile.findByUpdatedAt", query = "SELECT p FROM Profile p WHERE p.updatedAt = :updatedAt")})
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "profile_id")
    private Integer profileId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "profile_sha256")
    private String profileSha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "profile_key")
    private String profileKey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "key_id")
    private int keyId;
    @Lob
    @Size(max = 65535)
    @Column(name = "displayname")
    private String displayname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 65535)
    @Column(name = "email")
    private String email;
    @Size(max = 63)
    @Column(name = "locale")
    private String locale;
    @Column(name = "subscribe")
    private Short subscribe;
    @Lob
    @Size(max = 65535)
    @Column(name = "json")
    private String json;
    @Column(name = "login_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAt;
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

    public Profile() {
    }

    public Profile(Integer profileId) {
        this.profileId = profileId;
    }

    public Profile(Integer profileId, String profileSha256, String profileKey, int keyId, int entityVersion, Date createdAt, Date updatedAt) {
        this.profileId = profileId;
        this.profileSha256 = profileSha256;
        this.profileKey = profileKey;
        this.keyId = keyId;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getProfileSha256() {
        return profileSha256;
    }

    public void setProfileSha256(String profileSha256) {
        this.profileSha256 = profileSha256;
    }

    public String getProfileKey() {
        return profileKey;
    }

    public void setProfileKey(String profileKey) {
        this.profileKey = profileKey;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Short getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Short subscribe) {
        this.subscribe = subscribe;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.Profile[ profileId=" + profileId + " ]";
    }
    
}
