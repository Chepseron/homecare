/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "permissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
    @NamedQuery(name = "Permissions.findByPermissionId", query = "SELECT p FROM Permissions p WHERE p.permissionId = :permissionId"),
    @NamedQuery(name = "Permissions.findByCanCreate", query = "SELECT p FROM Permissions p WHERE p.canCreate = :canCreate"),
    @NamedQuery(name = "Permissions.findByCanRead", query = "SELECT p FROM Permissions p WHERE p.canRead = :canRead"),
    @NamedQuery(name = "Permissions.findByCanUpdate", query = "SELECT p FROM Permissions p WHERE p.canUpdate = :canUpdate"),
    @NamedQuery(name = "Permissions.findByCanDelete", query = "SELECT p FROM Permissions p WHERE p.canDelete = :canDelete")})
public class Permissions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "permission_id")
    private Integer permissionId;
    @Column(name = "can_create")
    private Boolean canCreate;
    @Column(name = "can_read")
    private Boolean canRead;
    @Column(name = "can_update")
    private Boolean canUpdate;
    @Column(name = "can_delete")
    private Boolean canDelete;
    @JoinColumn(name = "page_id", referencedColumnName = "page_id")
    @ManyToOne
    private Pages pageId;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
    private Roles roleId;

    public Permissions() {
    }

    public Permissions(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Pages getPageId() {
        return pageId;
    }

    public void setPageId(Pages pageId) {
        this.pageId = pageId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionId != null ? permissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.permissionId == null && other.permissionId != null) || (this.permissionId != null && !this.permissionId.equals(other.permissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Permissions[ permissionId=" + permissionId + " ]";
    }
    
}
