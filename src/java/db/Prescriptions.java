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
@Table(name = "prescriptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescriptions.findAll", query = "SELECT p FROM Prescriptions p"),
    @NamedQuery(name = "Prescriptions.findByPrescriptionId", query = "SELECT p FROM Prescriptions p WHERE p.prescriptionId = :prescriptionId"),
    @NamedQuery(name = "Prescriptions.findByMedication", query = "SELECT p FROM Prescriptions p WHERE p.medication = :medication"),
    @NamedQuery(name = "Prescriptions.findByDosage", query = "SELECT p FROM Prescriptions p WHERE p.dosage = :dosage"),
    @NamedQuery(name = "Prescriptions.findByFrequency", query = "SELECT p FROM Prescriptions p WHERE p.frequency = :frequency"),
    @NamedQuery(name = "Prescriptions.findByPharmacy", query = "SELECT p FROM Prescriptions p WHERE p.pharmacy = :pharmacy"),
    @NamedQuery(name = "Prescriptions.findByPrescriptionStatus", query = "SELECT p FROM Prescriptions p WHERE p.prescriptionStatus = :prescriptionStatus"),
    @NamedQuery(name = "Prescriptions.findByIssuedAt", query = "SELECT p FROM Prescriptions p WHERE p.issuedAt = :issuedAt")})
public class Prescriptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prescription_id")
    private Integer prescriptionId;
    @Size(max = 255)
    @Column(name = "medication")
    private String medication;
    @Size(max = 100)
    @Column(name = "dosage")
    private String dosage;
    @Size(max = 100)
    @Column(name = "frequency")
    private String frequency;
    @Size(max = 100)
    @Column(name = "pharmacy")
    private String pharmacy;
    @Size(max = 9)
    @Column(name = "prescription_status")
    private String prescriptionStatus;
    @Column(name = "issued_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedAt;
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users patientId;
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users doctorId;
    @JoinColumn(name = "filled_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users filledBy;

    public Prescriptions() {
    }

    public Prescriptions(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
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

    public Users getFilledBy() {
        return filledBy;
    }

    public void setFilledBy(Users filledBy) {
        this.filledBy = filledBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prescriptionId != null ? prescriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescriptions)) {
            return false;
        }
        Prescriptions other = (Prescriptions) object;
        if ((this.prescriptionId == null && other.prescriptionId != null) || (this.prescriptionId != null && !this.prescriptionId.equals(other.prescriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Prescriptions[ prescriptionId=" + prescriptionId + " ]";
    }
    
}
