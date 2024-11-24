/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "Users.findByLastLogin", query = "SELECT u FROM Users u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 6)
    @Column(name = "sex")
    private String sex;
    @Lob
    @Size(max = 65535)
    @Column(name = "address")
    private String address;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "patientId")
    private Collection<Appointments> appointmentsCollection;
    @OneToMany(mappedBy = "doctorId")
    private Collection<Appointments> appointmentsCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Appointments> appointmentsCollection2;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Appointments> appointmentsCollection3;
    @OneToMany(mappedBy = "users")
    private Collection<Audittrail> audittrailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Documents> documentsCollection;
    @OneToMany(mappedBy = "verifiedBy")
    private Collection<Documents> documentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Payments> paymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Payments> paymentsCollection1;
    @OneToMany(mappedBy = "patientId")
    private Collection<Sensordata> sensordataCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
    private Roles roleId;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Users> usersCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users createdBy;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Users> usersCollection1;
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users updatedBy;
    @OneToMany(mappedBy = "patientId")
    private Collection<Billing> billingCollection;
    @OneToMany(mappedBy = "patientId")
    private Collection<Homecareplan> homecareplanCollection;
    @OneToMany(mappedBy = "doctorId")
    private Collection<Homecareplan> homecareplanCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Homecareplan> homecareplanCollection2;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Homecareplan> homecareplanCollection3;
    @OneToMany(mappedBy = "patientId")
    private Collection<Medicalimages> medicalimagesCollection;
    @OneToMany(mappedBy = "uploadedBy")
    private Collection<Medicalimages> medicalimagesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<Conferencecalls> conferencecallsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Conferencecallparticipants> conferencecallparticipantsCollection;
    @OneToMany(mappedBy = "patientId")
    private Collection<Medicalrecords> medicalrecordsCollection;
    @OneToMany(mappedBy = "doctorId")
    private Collection<Medicalrecords> medicalrecordsCollection1;
    @OneToMany(mappedBy = "updatedBy")
    private Collection<Medicalrecords> medicalrecordsCollection2;
    @OneToMany(mappedBy = "patientId")
    private Collection<Prescriptions> prescriptionsCollection;
    @OneToMany(mappedBy = "doctorId")
    private Collection<Prescriptions> prescriptionsCollection1;
    @OneToMany(mappedBy = "filledBy")
    private Collection<Prescriptions> prescriptionsCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Notifications> notificationsCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Patientprofiles> patientprofilesCollection;
    @OneToMany(mappedBy = "primaryDoctorId")
    private Collection<Patientprofiles> patientprofilesCollection1;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
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

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection1() {
        return appointmentsCollection1;
    }

    public void setAppointmentsCollection1(Collection<Appointments> appointmentsCollection1) {
        this.appointmentsCollection1 = appointmentsCollection1;
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection2() {
        return appointmentsCollection2;
    }

    public void setAppointmentsCollection2(Collection<Appointments> appointmentsCollection2) {
        this.appointmentsCollection2 = appointmentsCollection2;
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection3() {
        return appointmentsCollection3;
    }

    public void setAppointmentsCollection3(Collection<Appointments> appointmentsCollection3) {
        this.appointmentsCollection3 = appointmentsCollection3;
    }

    @XmlTransient
    public Collection<Audittrail> getAudittrailCollection() {
        return audittrailCollection;
    }

    public void setAudittrailCollection(Collection<Audittrail> audittrailCollection) {
        this.audittrailCollection = audittrailCollection;
    }

    @XmlTransient
    public Collection<Documents> getDocumentsCollection() {
        return documentsCollection;
    }

    public void setDocumentsCollection(Collection<Documents> documentsCollection) {
        this.documentsCollection = documentsCollection;
    }

    @XmlTransient
    public Collection<Documents> getDocumentsCollection1() {
        return documentsCollection1;
    }

    public void setDocumentsCollection1(Collection<Documents> documentsCollection1) {
        this.documentsCollection1 = documentsCollection1;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection1() {
        return paymentsCollection1;
    }

    public void setPaymentsCollection1(Collection<Payments> paymentsCollection1) {
        this.paymentsCollection1 = paymentsCollection1;
    }

    @XmlTransient
    public Collection<Sensordata> getSensordataCollection() {
        return sensordataCollection;
    }

    public void setSensordataCollection(Collection<Sensordata> sensordataCollection) {
        this.sensordataCollection = sensordataCollection;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection1() {
        return usersCollection1;
    }

    public void setUsersCollection1(Collection<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    @XmlTransient
    public Collection<Billing> getBillingCollection() {
        return billingCollection;
    }

    public void setBillingCollection(Collection<Billing> billingCollection) {
        this.billingCollection = billingCollection;
    }

    @XmlTransient
    public Collection<Homecareplan> getHomecareplanCollection() {
        return homecareplanCollection;
    }

    public void setHomecareplanCollection(Collection<Homecareplan> homecareplanCollection) {
        this.homecareplanCollection = homecareplanCollection;
    }

    @XmlTransient
    public Collection<Homecareplan> getHomecareplanCollection1() {
        return homecareplanCollection1;
    }

    public void setHomecareplanCollection1(Collection<Homecareplan> homecareplanCollection1) {
        this.homecareplanCollection1 = homecareplanCollection1;
    }

    @XmlTransient
    public Collection<Homecareplan> getHomecareplanCollection2() {
        return homecareplanCollection2;
    }

    public void setHomecareplanCollection2(Collection<Homecareplan> homecareplanCollection2) {
        this.homecareplanCollection2 = homecareplanCollection2;
    }

    @XmlTransient
    public Collection<Homecareplan> getHomecareplanCollection3() {
        return homecareplanCollection3;
    }

    public void setHomecareplanCollection3(Collection<Homecareplan> homecareplanCollection3) {
        this.homecareplanCollection3 = homecareplanCollection3;
    }

    @XmlTransient
    public Collection<Medicalimages> getMedicalimagesCollection() {
        return medicalimagesCollection;
    }

    public void setMedicalimagesCollection(Collection<Medicalimages> medicalimagesCollection) {
        this.medicalimagesCollection = medicalimagesCollection;
    }

    @XmlTransient
    public Collection<Medicalimages> getMedicalimagesCollection1() {
        return medicalimagesCollection1;
    }

    public void setMedicalimagesCollection1(Collection<Medicalimages> medicalimagesCollection1) {
        this.medicalimagesCollection1 = medicalimagesCollection1;
    }

    @XmlTransient
    public Collection<Conferencecalls> getConferencecallsCollection() {
        return conferencecallsCollection;
    }

    public void setConferencecallsCollection(Collection<Conferencecalls> conferencecallsCollection) {
        this.conferencecallsCollection = conferencecallsCollection;
    }

    @XmlTransient
    public Collection<Conferencecallparticipants> getConferencecallparticipantsCollection() {
        return conferencecallparticipantsCollection;
    }

    public void setConferencecallparticipantsCollection(Collection<Conferencecallparticipants> conferencecallparticipantsCollection) {
        this.conferencecallparticipantsCollection = conferencecallparticipantsCollection;
    }

    @XmlTransient
    public Collection<Medicalrecords> getMedicalrecordsCollection() {
        return medicalrecordsCollection;
    }

    public void setMedicalrecordsCollection(Collection<Medicalrecords> medicalrecordsCollection) {
        this.medicalrecordsCollection = medicalrecordsCollection;
    }

    @XmlTransient
    public Collection<Medicalrecords> getMedicalrecordsCollection1() {
        return medicalrecordsCollection1;
    }

    public void setMedicalrecordsCollection1(Collection<Medicalrecords> medicalrecordsCollection1) {
        this.medicalrecordsCollection1 = medicalrecordsCollection1;
    }

    @XmlTransient
    public Collection<Medicalrecords> getMedicalrecordsCollection2() {
        return medicalrecordsCollection2;
    }

    public void setMedicalrecordsCollection2(Collection<Medicalrecords> medicalrecordsCollection2) {
        this.medicalrecordsCollection2 = medicalrecordsCollection2;
    }

    @XmlTransient
    public Collection<Prescriptions> getPrescriptionsCollection() {
        return prescriptionsCollection;
    }

    public void setPrescriptionsCollection(Collection<Prescriptions> prescriptionsCollection) {
        this.prescriptionsCollection = prescriptionsCollection;
    }

    @XmlTransient
    public Collection<Prescriptions> getPrescriptionsCollection1() {
        return prescriptionsCollection1;
    }

    public void setPrescriptionsCollection1(Collection<Prescriptions> prescriptionsCollection1) {
        this.prescriptionsCollection1 = prescriptionsCollection1;
    }

    @XmlTransient
    public Collection<Prescriptions> getPrescriptionsCollection2() {
        return prescriptionsCollection2;
    }

    public void setPrescriptionsCollection2(Collection<Prescriptions> prescriptionsCollection2) {
        this.prescriptionsCollection2 = prescriptionsCollection2;
    }

    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }

    @XmlTransient
    public Collection<Patientprofiles> getPatientprofilesCollection() {
        return patientprofilesCollection;
    }

    public void setPatientprofilesCollection(Collection<Patientprofiles> patientprofilesCollection) {
        this.patientprofilesCollection = patientprofilesCollection;
    }

    @XmlTransient
    public Collection<Patientprofiles> getPatientprofilesCollection1() {
        return patientprofilesCollection1;
    }

    public void setPatientprofilesCollection1(Collection<Patientprofiles> patientprofilesCollection1) {
        this.patientprofilesCollection1 = patientprofilesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Users[ userId=" + userId + " ]";
    }
    
}
