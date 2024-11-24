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
@Table(name = "medicalimages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicalimages.findAll", query = "SELECT m FROM Medicalimages m"),
    @NamedQuery(name = "Medicalimages.findByImageId", query = "SELECT m FROM Medicalimages m WHERE m.imageId = :imageId"),
    @NamedQuery(name = "Medicalimages.findByImageType", query = "SELECT m FROM Medicalimages m WHERE m.imageType = :imageType"),
    @NamedQuery(name = "Medicalimages.findByImageUrl", query = "SELECT m FROM Medicalimages m WHERE m.imageUrl = :imageUrl"),
    @NamedQuery(name = "Medicalimages.findByUploadedAt", query = "SELECT m FROM Medicalimages m WHERE m.uploadedAt = :uploadedAt")})
public class Medicalimages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    @Size(max = 5)
    @Column(name = "image_type")
    private String imageType;
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;
    @Lob
    @Size(max = 65535)
    @Column(name = "image_description")
    private String imageDescription;
    @Column(name = "uploaded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users patientId;
    @JoinColumn(name = "uploaded_by", referencedColumnName = "user_id")
    @ManyToOne
    private Users uploadedBy;

    public Medicalimages() {
    }

    public Medicalimages(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Users getPatientId() {
        return patientId;
    }

    public void setPatientId(Users patientId) {
        this.patientId = patientId;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicalimages)) {
            return false;
        }
        Medicalimages other = (Medicalimages) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Medicalimages[ imageId=" + imageId + " ]";
    }
    
}
