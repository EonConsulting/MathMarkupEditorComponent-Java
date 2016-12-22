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
@Table(name = "blob_file", catalog = "tsugi", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlobFile.findAll", query = "SELECT b FROM BlobFile b")
    , @NamedQuery(name = "BlobFile.findByFileId", query = "SELECT b FROM BlobFile b WHERE b.fileId = :fileId")
    , @NamedQuery(name = "BlobFile.findByFileSha256", query = "SELECT b FROM BlobFile b WHERE b.fileSha256 = :fileSha256")
    , @NamedQuery(name = "BlobFile.findByFileName", query = "SELECT b FROM BlobFile b WHERE b.fileName = :fileName")
    , @NamedQuery(name = "BlobFile.findByDeleted", query = "SELECT b FROM BlobFile b WHERE b.deleted = :deleted")
    , @NamedQuery(name = "BlobFile.findByContenttype", query = "SELECT b FROM BlobFile b WHERE b.contenttype = :contenttype")
    , @NamedQuery(name = "BlobFile.findByPath", query = "SELECT b FROM BlobFile b WHERE b.path = :path")
    , @NamedQuery(name = "BlobFile.findByCreatedAt", query = "SELECT b FROM BlobFile b WHERE b.createdAt = :createdAt")
    , @NamedQuery(name = "BlobFile.findByAccessedAt", query = "SELECT b FROM BlobFile b WHERE b.accessedAt = :accessedAt")})
public class BlobFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Integer fileId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "file_sha256")
    private String fileSha256;
    @Size(max = 2048)
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "deleted")
    private Boolean deleted;
    @Size(max = 256)
    @Column(name = "contenttype")
    private String contenttype;
    @Size(max = 2048)
    @Column(name = "path")
    private String path;
    @Lob
    @Column(name = "content")
    private byte[] content;
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
    @Column(name = "accessed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessedAt;
    @JoinColumn(name = "context_id", referencedColumnName = "context_id")
    @ManyToOne
    private LtiContext contextId;

    public BlobFile() {
    }

    public BlobFile(Integer fileId) {
        this.fileId = fileId;
    }

    public BlobFile(Integer fileId, String fileSha256, Date createdAt, Date accessedAt) {
        this.fileId = fileId;
        this.fileSha256 = fileSha256;
        this.createdAt = createdAt;
        this.accessedAt = accessedAt;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileSha256() {
        return fileSha256;
    }

    public void setFileSha256(String fileSha256) {
        this.fileSha256 = fileSha256;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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

    public Date getAccessedAt() {
        return accessedAt;
    }

    public void setAccessedAt(Date accessedAt) {
        this.accessedAt = accessedAt;
    }

    public LtiContext getContextId() {
        return contextId;
    }

    public void setContextId(LtiContext contextId) {
        this.contextId = contextId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlobFile)) {
            return false;
        }
        BlobFile other = (BlobFile) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.co.unisa.entity.BlobFile[ fileId=" + fileId + " ]";
    }
    
}
