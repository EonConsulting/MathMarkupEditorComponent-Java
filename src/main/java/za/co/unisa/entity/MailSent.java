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
@Table(name = "mail_sent", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MailSent.findAll", query = "SELECT m FROM MailSent m")
    , @NamedQuery(name = "MailSent.findBySentId", query = "SELECT m FROM MailSent m WHERE m.sentId = :sentId")
    , @NamedQuery(name = "MailSent.findBySubject", query = "SELECT m FROM MailSent m WHERE m.subject = :subject")
    , @NamedQuery(name = "MailSent.findByCreatedAt", query = "SELECT m FROM MailSent m WHERE m.createdAt = :createdAt")})
public class MailSent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sent_id")
    private Integer sentId;
    @Size(max = 256)
    @Column(name = "subject")
    private String subject;
    @Lob
    @Size(max = 65535)
    @Column(name = "body")
    private String body;
    @Lob
    @Size(max = 65535)
    @Column(name = "json")
    private String json;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "context_id", referencedColumnName = "context_id")
    @ManyToOne(optional = false)
    private LtiContext contextId;
    @JoinColumn(name = "link_id", referencedColumnName = "link_id")
    @ManyToOne
    private LtiLink linkId;
    @JoinColumn(name = "user_to", referencedColumnName = "user_id")
    @ManyToOne
    private LtiUser userTo;
    @JoinColumn(name = "user_from", referencedColumnName = "user_id")
    @ManyToOne
    private LtiUser userFrom;

    public MailSent() {
    }

    public MailSent(Integer sentId) {
        this.sentId = sentId;
    }

    public MailSent(Integer sentId, Date createdAt) {
        this.sentId = sentId;
        this.createdAt = createdAt;
    }

    public Integer getSentId() {
        return sentId;
    }

    public void setSentId(Integer sentId) {
        this.sentId = sentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public LtiContext getContextId() {
        return contextId;
    }

    public void setContextId(LtiContext contextId) {
        this.contextId = contextId;
    }

    public LtiLink getLinkId() {
        return linkId;
    }

    public void setLinkId(LtiLink linkId) {
        this.linkId = linkId;
    }

    public LtiUser getUserTo() {
        return userTo;
    }

    public void setUserTo(LtiUser userTo) {
        this.userTo = userTo;
    }

    public LtiUser getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(LtiUser userFrom) {
        this.userFrom = userFrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sentId != null ? sentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MailSent)) {
            return false;
        }
        MailSent other = (MailSent) object;
        if ((this.sentId == null && other.sentId != null) || (this.sentId != null && !this.sentId.equals(other.sentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.MailSent[ sentId=" + sentId + " ]";
    }
    
}
