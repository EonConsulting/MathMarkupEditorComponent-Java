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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Mufaro Benedict
 * @author mbaka motale
 */
@Entity
@Table(name = "lti_membership", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LtiMembership.findAll", query = "SELECT l FROM LtiMembership l")
    , @NamedQuery(name = "LtiMembership.findByMembershipId", query = "SELECT l FROM LtiMembership l WHERE l.membershipId = :membershipId")
    , @NamedQuery(name = "LtiMembership.findByRole", query = "SELECT l FROM LtiMembership l WHERE l.role = :role")
    , @NamedQuery(name = "LtiMembership.findByRoleOverride", query = "SELECT l FROM LtiMembership l WHERE l.roleOverride = :roleOverride")
    , @NamedQuery(name = "LtiMembership.findByEntityVersion", query = "SELECT l FROM LtiMembership l WHERE l.entityVersion = :entityVersion")
    , @NamedQuery(name = "LtiMembership.findByCreatedAt", query = "SELECT l FROM LtiMembership l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "LtiMembership.findByUpdatedAt", query = "SELECT l FROM LtiMembership l WHERE l.updatedAt = :updatedAt")})
public class LtiMembership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "membership_id")
    private Integer membershipId;
    @Column(name = "role")
    private Short role;
    @Column(name = "role_override")
    private Short roleOverride;
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
    @JoinColumn(name = "context_id", referencedColumnName = "context_id")
    @ManyToOne(optional = false)
    private LtiContext contextId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private LtiUser userId;

    public LtiMembership() {
    }

    public LtiMembership(Integer membershipId) {
        this.membershipId = membershipId;
    }

    public LtiMembership(Integer membershipId, int entityVersion, Date createdAt, Date updatedAt) {
        this.membershipId = membershipId;
        this.entityVersion = entityVersion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Integer membershipId) {
        this.membershipId = membershipId;
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public Short getRoleOverride() {
        return roleOverride;
    }

    public void setRoleOverride(Short roleOverride) {
        this.roleOverride = roleOverride;
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

    public LtiContext getContextId() {
        return contextId;
    }

    public void setContextId(LtiContext contextId) {
        this.contextId = contextId;
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
        hash += (membershipId != null ? membershipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LtiMembership)) {
            return false;
        }
        LtiMembership other = (LtiMembership) object;
        if ((this.membershipId == null && other.membershipId != null) || (this.membershipId != null && !this.membershipId.equals(other.membershipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.LtiMembership[ membershipId=" + membershipId + " ]";
    }
    
}
