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
@Table(name = "homecareplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Homecareplan.findAll", query = "SELECT h FROM Homecareplan h"),
    @NamedQuery(name = "Homecareplan.findByPlanId", query = "SELECT h FROM Homecareplan h WHERE h.planId = :planId"),
    @NamedQuery(name = "Homecareplan.findByStatus", query = "SELECT h FROM Homecareplan h WHERE h.status = :status"),
    @NamedQuery(name = "Homecareplan.findByCreatedAt", query = "SELECT h FROM Homecareplan h WHERE h.createdAt = :createdAt"),
    @NamedQuery(name = "Homecareplan.findByUpdatedAt", query = "SELECT h FROM Homecareplan h WHERE h.updatedAt = :updatedAt")})
public class Homecareplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "plan_id")
    private Integer planId;
    @Lob
    @Size(max = 65535)
    @Column(name = "care_plan")
    private String carePlan;
    @Size(max = 9)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users patientId;
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users doctorId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users createdBy;
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users updatedBy;

    public Homecareplan() {
    }

    public Homecareplan(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getCarePlan() {
        return carePlan;
    }

    public void setCarePlan(String carePlan) {
        this.carePlan = carePlan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Users getPatientId() {
        return patientId;
    }

    public void setPatientId(Users patientId) {
        this.patientId = patientId;
    }

    public Users getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Users doctorId) {
        this.doctorId = doctorId;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Homecareplan)) {
            return false;
        }
        Homecareplan other = (Homecareplan) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Homecareplan[ planId=" + planId + " ]";
    }
    
}
