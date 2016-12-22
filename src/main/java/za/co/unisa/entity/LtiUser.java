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
@Table(name = "lti_user", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiUser.findAll", query = "SELECT l FROM LtiUser l")
    , @NamedQuery(name = "LtiUser.findByUserId", query = "SELECT l FROM LtiUser l WHERE l.userId = :userId")
    , @NamedQuery(name = "LtiUser.findByUserSha256", query = "SELECT l FROM LtiUser l WHERE l.userSha256 = :userSha256")
    , @NamedQuery(name = "LtiUser.findByProfileId", query = "SELECT l FROM LtiUser l WHERE l.profileId = :profileId")
    , @NamedQuery(name = "LtiUser.findByLocale", query = "SELECT l FROM LtiUser l WHERE l.locale = :locale")
    , @NamedQuery(name = "LtiUser.findBySubscribe", query = "SELECT l FROM LtiUser l WHERE l.subscribe = :subscribe")
    , @NamedQuery(name = "LtiUser.findByLoginAt", query = "SELECT l FROM LtiUser l WHERE l.loginAt = :loginAt")
    , @NamedQuery(name = "LtiUser.findByEntityVersion", query = "SELECT l FROM LtiUser l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiUser.findByCreatedAt", query = "SELECT l FROM LtiUser l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiUser.findByUpdatedAt", query = "SELECT l FROM LtiUser l WHERE l.updatedAt = :updatedAt")})
public class LtiUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "user_sha256")
    private String userSha256;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "user_key")
    private String userKey;
    @Column(name = "profile_id")
    private Integer profileId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<KeyRequest> keyRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<LtiResult> ltiResultList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<MailBulk> mailBulkList;
    @JoinColumn(name = "key_id", referencedColumnName = "key_id")
    @ManyToOne(optional = false)
    private LtiKey keyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<LtiMembership> ltiMembershipList;
    @OneToMany(mappedBy = "userTo")
    private List<MailSent> mailSentList;
    @OneToMany(mappedBy = "userFrom")
    private List<MailSent> mailSentList1;

    public LtiUser() {
    }

    public LtiUser(Integer userId) {
        this.userId = userId;
    }

    public LtiUser(Integer userId, String userSha256, String userKey, int entityVersion, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.userSha256 = userSha256;
        this.userKey = userKey;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserSha256() {
        return userSha256;
    }

    public void setUserSha256(String userSha256) {
        this.userSha256 = userSha256;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
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

    @XmlTransient
    public List<KeyRequest> getKeyRequestList() {
        return keyRequestList;
    }

    public void setKeyRequestList(List<KeyRequest> keyRequestList) {
        this.keyRequestList = keyRequestList;
    }

    @XmlTransient
    public List<LtiResult> getLtiResultList() {
        return ltiResultList;
    }

    public void setLtiResultList(List<LtiResult> ltiResultList) {
        this.ltiResultList = ltiResultList;
    }

    @XmlTransient
    public List<MailBulk> getMailBulkList() {
        return mailBulkList;
    }

    public void setMailBulkList(List<MailBulk> mailBulkList) {
        this.mailBulkList = mailBulkList;
    }

    public LtiKey getKeyId() {
        return keyId;
    }

    public void setKeyId(LtiKey keyId) {
        this.keyId = keyId;
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

    @XmlTransient
    public List<MailSent> getMailSentList1() {
        return mailSentList1;
    }

    public void setMailSentList1(List<MailSent> mailSentList1) {
        this.mailSentList1 = mailSentList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiUser)) {
            return false;
        }
        LtiUser other = (LtiUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiUser[ userId=" + userId + " ]";
    }
    
}
