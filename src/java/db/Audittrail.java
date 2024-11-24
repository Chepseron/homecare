/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "audittrail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Audittrail.findAll", query = "SELECT a FROM Audittrail a"),
    @NamedQuery(name = "Audittrail.findByAuditId", query = "SELECT a FROM Audittrail a WHERE a.auditId = :auditId"),
    @NamedQuery(name = "Audittrail.findByTableName", query = "SELECT a FROM Audittrail a WHERE a.tableName = :tableName"),
    @NamedQuery(name = "Audittrail.findByRecordId", query = "SELECT a FROM Audittrail a WHERE a.recordId = :recordId"),
    @NamedQuery(name = "Audittrail.findByActionType", query = "SELECT a FROM Audittrail a WHERE a.actionType = :actionType"),
    @NamedQuery(name = "Audittrail.findByActionTimestamp", query = "SELECT a FROM Audittrail a WHERE a.actionTimestamp = :actionTimestamp")})
public class Audittrail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "audit_id")
    private Integer auditId;
    @Size(max = 50)
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "record_id")
    private Integer recordId;
    @Size(max = 6)
    @Column(name = "action_type")
    private String actionType;
    @Lob
    @Size(max = 65535)
    @Column(name = "old_values")
    private String oldValues;
    @Lob
    @Size(max = 65535)
    @Column(name = "new_values")
    private String newValues;
    @Column(name = "action_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTimestamp;
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToOne
    private Users users;

    public Audittrail() {
    }

    public Audittrail(Integer auditId) {
        this.auditId = auditId;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getOldValues() {
        return oldValues;
    }

    public void setOldValues(String oldValues) {
        this.oldValues = oldValues;
    }

    public String getNewValues() {
        return newValues;
    }

    public void setNewValues(String newValues) {
        this.newValues = newValues;
    }

    public Date getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Date actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditId != null ? auditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Audittrail)) {
            return false;
        }
        Audittrail other = (Audittrail) object;
        if ((this.auditId == null && other.auditId != null) || (this.auditId != null && !this.auditId.equals(other.auditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Audittrail[ auditId=" + auditId + " ]";
    }
    
}
