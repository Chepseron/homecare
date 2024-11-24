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
@Table(name = "patientprofiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patientprofiles.findAll", query = "SELECT p FROM Patientprofiles p"),
    @NamedQuery(name = "Patientprofiles.findByProfileId", query = "SELECT p FROM Patientprofiles p WHERE p.profileId = :profileId"),
    @NamedQuery(name = "Patientprofiles.findByEmergencyContact", query = "SELECT p FROM Patientprofiles p WHERE p.emergencyContact = :emergencyContact"),
    @NamedQuery(name = "Patientprofiles.findByInsuranceNumber", query = "SELECT p FROM Patientprofiles p WHERE p.insuranceNumber = :insuranceNumber"),
    @NamedQuery(name = "Patientprofiles.findByCreatedAt", query = "SELECT p FROM Patientprofiles p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Patientprofiles.findByUpdatedAt", query = "SELECT p FROM Patientprofiles p WHERE p.updatedAt = :updatedAt")})
public class Patientprofiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "profile_id")
    private Integer profileId;
    @Size(max = 100)
    @Column(name = "emergency_contact")
    private String emergencyContact;
    @Size(max = 50)
    @Column(name = "insurance_number")
    private String insuranceNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "medical_history")
    private String medicalHistory;
    @Lob
    @Size(max = 65535)
    @Column(name = "health_conditions")
    private String healthConditions;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "primary_doctor_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users primaryDoctorId;

    public Patientprofiles() {
    }

    public Patientprofiles(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(String healthConditions) {
        this.healthConditions = healthConditions;
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

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Users getPrimaryDoctorId() {
        return primaryDoctorId;
    }

    public void setPrimaryDoctorId(Users primaryDoctorId) {
        this.primaryDoctorId = primaryDoctorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patientprofiles)) {
            return false;
        }
        Patientprofiles other = (Patientprofiles) object;
        if ((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Patientprofiles[ profileId=" + profileId + " ]";
    }
    
}
