/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.math.BigDecimal;
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
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "commissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commissions.findAll", query = "SELECT c FROM Commissions c"),
    @NamedQuery(name = "Commissions.findByCommissionId", query = "SELECT c FROM Commissions c WHERE c.commissionId = :commissionId"),
    @NamedQuery(name = "Commissions.findByCommissionPercentage", query = "SELECT c FROM Commissions c WHERE c.commissionPercentage = :commissionPercentage"),
    @NamedQuery(name = "Commissions.findByCommissionAmount", query = "SELECT c FROM Commissions c WHERE c.commissionAmount = :commissionAmount"),
    @NamedQuery(name = "Commissions.findByPaymentDate", query = "SELECT c FROM Commissions c WHERE c.paymentDate = :paymentDate"),
    @NamedQuery(name = "Commissions.findByIsPaid", query = "SELECT c FROM Commissions c WHERE c.isPaid = :isPaid"),
    @NamedQuery(name = "Commissions.findByCreatedAt", query = "SELECT c FROM Commissions c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Commissions.findByUpdatedAt", query = "SELECT c FROM Commissions c WHERE c.updatedAt = :updatedAt")})
public class Commissions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commission_id")
    private Integer commissionId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "commission_percentage")
    private BigDecimal commissionPercentage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commission_amount")
    private BigDecimal commissionAmount;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(optional = false)
    private Payments paymentId;

    public Commissions() {
    }

    public Commissions(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Commissions(Integer commissionId, BigDecimal commissionPercentage, BigDecimal commissionAmount) {
        this.commissionId = commissionId;
        this.commissionPercentage = commissionPercentage;
        this.commissionAmount = commissionAmount;
    }

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(BigDecimal commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
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

    public Payments getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Payments paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commissionId != null ? commissionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commissions)) {
            return false;
        }
        Commissions other = (Commissions) object;
        if ((this.commissionId == null && other.commissionId != null) || (this.commissionId != null && !this.commissionId.equals(other.commissionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Commissions[ commissionId=" + commissionId + " ]";
    }
    
}
