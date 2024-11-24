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
@Table(name = "sensordata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensordata.findAll", query = "SELECT s FROM Sensordata s"),
    @NamedQuery(name = "Sensordata.findBySensorDataId", query = "SELECT s FROM Sensordata s WHERE s.sensorDataId = :sensorDataId"),
    @NamedQuery(name = "Sensordata.findByDeviceId", query = "SELECT s FROM Sensordata s WHERE s.deviceId = :deviceId"),
    @NamedQuery(name = "Sensordata.findByTemperature", query = "SELECT s FROM Sensordata s WHERE s.temperature = :temperature"),
    @NamedQuery(name = "Sensordata.findByBloodPressure", query = "SELECT s FROM Sensordata s WHERE s.bloodPressure = :bloodPressure"),
    @NamedQuery(name = "Sensordata.findBySugarLevel", query = "SELECT s FROM Sensordata s WHERE s.sugarLevel = :sugarLevel"),
    @NamedQuery(name = "Sensordata.findByIsAnomalous", query = "SELECT s FROM Sensordata s WHERE s.isAnomalous = :isAnomalous"),
    @NamedQuery(name = "Sensordata.findByRecordedAt", query = "SELECT s FROM Sensordata s WHERE s.recordedAt = :recordedAt"),
    @NamedQuery(name = "Sensordata.findByPulseRate", query = "SELECT s FROM Sensordata s WHERE s.pulseRate = :pulseRate"),
    @NamedQuery(name = "Sensordata.findByRespiratoryRate", query = "SELECT s FROM Sensordata s WHERE s.respiratoryRate = :respiratoryRate"),
    @NamedQuery(name = "Sensordata.findByOxygenSaturation", query = "SELECT s FROM Sensordata s WHERE s.oxygenSaturation = :oxygenSaturation")})
public class Sensordata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sensor_data_id")
    private Integer sensorDataId;
    @Size(max = 50)
    @Column(name = "device_id")
    private String deviceId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "temperature")
    private BigDecimal temperature;
    @Size(max = 20)
    @Column(name = "blood_pressure")
    private String bloodPressure;
    @Column(name = "sugar_level")
    private BigDecimal sugarLevel;
    @Column(name = "is_anomalous")
    private Boolean isAnomalous;
    @Column(name = "recorded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordedAt;
    @Column(name = "pulse_rate")
    private BigDecimal pulseRate;
    @Column(name = "respiratory_rate")
    private BigDecimal respiratoryRate;
    @Column(name = "oxygen_saturation")
    private BigDecimal oxygenSaturation;
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users patientId;

    public Sensordata() {
    }

    public Sensordata(Integer sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    public Integer getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(Integer sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public BigDecimal getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(BigDecimal sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public Boolean getIsAnomalous() {
        return isAnomalous;
    }

    public void setIsAnomalous(Boolean isAnomalous) {
        this.isAnomalous = isAnomalous;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    public BigDecimal getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(BigDecimal pulseRate) {
        this.pulseRate = pulseRate;
    }

    public BigDecimal getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(BigDecimal respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public BigDecimal getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(BigDecimal oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
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
        hash += (sensorDataId != null ? sensorDataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensordata)) {
            return false;
        }
        Sensordata other = (Sensordata) object;
        if ((this.sensorDataId == null && other.sensorDataId != null) || (this.sensorDataId != null && !this.sensorDataId.equals(other.sensorDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Sensordata[ sensorDataId=" + sensorDataId + " ]";
    }
    
}
