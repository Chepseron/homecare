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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "billing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billing.findAll", query = "SELECT b FROM Billing b"),
    @NamedQuery(name = "Billing.findByBillId", query = "SELECT b FROM Billing b WHERE b.billId = :billId"),
    @NamedQuery(name = "Billing.findByAmount", query = "SELECT b FROM Billing b WHERE b.amount = :amount"),
    @NamedQuery(name = "Billing.findByPaymentStatus", query = "SELECT b FROM Billing b WHERE b.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Billing.findByPaymentMethod", query = "SELECT b FROM Billing b WHERE b.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "Billing.findByPaidDate", query = "SELECT b FROM Billing b WHERE b.paidDate = :paidDate"),
    @NamedQuery(name = "Billing.findByBillGeneratedAt", query = "SELECT b FROM Billing b WHERE b.billGeneratedAt = :billGeneratedAt"),
    @NamedQuery(name = "Billing.findByForwardedTo", query = "SELECT b FROM Billing b WHERE b.forwardedTo = :forwardedTo")})
public class Billing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bill_id")
    private Integer billId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Size(max = 7)
    @Column(name = "payment_status")
    private String paymentStatus;
    @Size(max = 9)
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    private Date paidDate;
    @Column(name = "bill_generated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billGeneratedAt;
    @Size(max = 10)
    @Column(name = "forwarded_to")
    private String forwardedTo;
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users patientId;

    public Billing() {
    }

    public Billing(Integer billId) {
        this.billId = billId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getBillGeneratedAt() {
        return billGeneratedAt;
    }

    public void setBillGeneratedAt(Date billGeneratedAt) {
        this.billGeneratedAt = billGeneratedAt;
    }

    public String getForwardedTo() {
        return forwardedTo;
    }

    public void setForwardedTo(String forwardedTo) {
        this.forwardedTo = forwardedTo;
    }

    public Users getPatientId() {
        return patientId;
    }

    public void setPatientId(Users patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billing)) {
            return false;
        }
        Billing other = (Billing) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Billing[ billId=" + billId + " ]";
    }
    
}
