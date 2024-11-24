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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "paymentdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymentdetails.findAll", query = "SELECT p FROM Paymentdetails p"),
    @NamedQuery(name = "Paymentdetails.findByPaymentDetailId", query = "SELECT p FROM Paymentdetails p WHERE p.paymentDetailId = :paymentDetailId"),
    @NamedQuery(name = "Paymentdetails.findByServiceType", query = "SELECT p FROM Paymentdetails p WHERE p.serviceType = :serviceType")})
public class Paymentdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_detail_id")
    private Integer paymentDetailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "service_type")
    private String serviceType;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "service_id", referencedColumnName = "appointment_id")
    @ManyToOne(optional = false)
    private Appointments serviceId;
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(optional = false)
    private Payments paymentId;

    public Paymentdetails() {
    }

    public Paymentdetails(Integer paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public Paymentdetails(Integer paymentDetailId, String serviceType) {
        this.paymentDetailId = paymentDetailId;
        this.serviceType = serviceType;
    }

    public Integer getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Integer paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Appointments getServiceId() {
        return serviceId;
    }

    public void setServiceId(Appointments serviceId) {
        this.serviceId = serviceId;
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
        hash += (paymentDetailId != null ? paymentDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentdetails)) {
            return false;
        }
        Paymentdetails other = (Paymentdetails) object;
        if ((this.paymentDetailId == null && other.paymentDetailId != null) || (this.paymentDetailId != null && !this.paymentDetailId.equals(other.paymentDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Paymentdetails[ paymentDetailId=" + paymentDetailId + " ]";
    }
    
}
