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
@Table(name = "medicalrecords")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicalrecords.findAll", query = "SELECT m FROM Medicalrecords m"),
    @NamedQuery(name = "Medicalrecords.findByRecordId", query = "SELECT m FROM Medicalrecords m WHERE m.recordId = :recordId"),
    @NamedQuery(name = "Medicalrecords.findByVisitDate", query = "SELECT m FROM Medicalrecords m WHERE m.visitDate = :visitDate"),
    @NamedQuery(name = "Medicalrecords.findByRecordStatus", query = "SELECT m FROM Medicalrecords m WHERE m.recordStatus = :recordStatus"),
    @NamedQuery(name = "Medicalrecords.findByCreatedAt", query = "SELECT m FROM Medicalrecords m WHERE m.createdAt = :createdAt"),
    @NamedQuery(name = "Medicalrecords.findByUpdatedAt", query = "SELECT m FROM Medicalrecords m WHERE m.updatedAt = :updatedAt")})
public class Medicalrecords implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "record_id")
    private Integer recordId;
    @Lob
    @Size(max = 65535)
    @Column(name = "diagnosis")
    private String diagnosis;
    @Lob
    @Size(max = 65535)
    @Column(name = "prescriptions")
    private String prescriptions;
    @Lob
    @Size(max = 65535)
    @Column(name = "referrals")
    private String referrals;
    @Lob
    @Size(max = 65535)
    @Column(name = "health_plan")
    private String healthPlan;
    @Lob
    @Size(max = 65535)
    @Column(name = "short_term_goals")
    private String shortTermGoals;
    @Lob
    @Size(max = 65535)
    @Column(name = "long_term_goals")
    private String longTermGoals;
    @Column(name = "visit_date")
    @Temporal(TemporalType.DATE)
    private Date visitDate;
    @Size(max = 8)
    @Column(name = "record_status")
    private String recordStatus;
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
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users updatedBy;

    public Medicalrecords() {
    }

    public Medicalrecords(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getReferrals() {
        return referrals;
    }

    public void setReferrals(String referrals) {
        this.referrals = referrals;
    }

    public String getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(String healthPlan) {
        this.healthPlan = healthPlan;
    }

    public String getShortTermGoals() {
        return shortTermGoals;
    }

    public void setShortTermGoals(String shortTermGoals) {
        this.shortTermGoals = shortTermGoals;
    }

    public String getLongTermGoals() {
        return longTermGoals;
    }

    public void setLongTermGoals(String longTermGoals) {
        this.longTermGoals = longTermGoals;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
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

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicalrecords)) {
            return false;
        }
        Medicalrecords other = (Medicalrecords) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Medicalrecords[ recordId=" + recordId + " ]";
    }
    
}
