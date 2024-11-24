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
@Table(name = "conferencecalls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conferencecalls.findAll", query = "SELECT c FROM Conferencecalls c"),
    @NamedQuery(name = "Conferencecalls.findByCallId", query = "SELECT c FROM Conferencecalls c WHERE c.callId = :callId"),
    @NamedQuery(name = "Conferencecalls.findByCallTitle", query = "SELECT c FROM Conferencecalls c WHERE c.callTitle = :callTitle"),
    @NamedQuery(name = "Conferencecalls.findByScheduledAt", query = "SELECT c FROM Conferencecalls c WHERE c.scheduledAt = :scheduledAt"),
    @NamedQuery(name = "Conferencecalls.findByStartedAt", query = "SELECT c FROM Conferencecalls c WHERE c.startedAt = :startedAt"),
    @NamedQuery(name = "Conferencecalls.findByEndedAt", query = "SELECT c FROM Conferencecalls c WHERE c.endedAt = :endedAt"),
    @NamedQuery(name = "Conferencecalls.findByCreatedAt", query = "SELECT c FROM Conferencecalls c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Conferencecalls.findByUpdatedAt", query = "SELECT c FROM Conferencecalls c WHERE c.updatedAt = :updatedAt")})
public class Conferencecalls implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "call_id")
    private Integer callId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "call_title")
    private String callTitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scheduled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledAt;
    @Column(name = "started_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;
    @Column(name = "ended_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endedAt;
    @Lob
    @Size(max = 65535)
    @Column(name = "call_notes")
    private String callNotes;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "callId")
    private Collection<Conferencecallparticipants> conferencecallparticipantsCollection;

    public Conferencecalls() {
    }

    public Conferencecalls(Integer callId) {
        this.callId = callId;
    }

    public Conferencecalls(Integer callId, String callTitle, Date scheduledAt) {
        this.callId = callId;
        this.callTitle = callTitle;
        this.scheduledAt = scheduledAt;
    }

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public String getCallTitle() {
        return callTitle;
    }

    public void setCallTitle(String callTitle) {
        this.callTitle = callTitle;
    }

    public Date getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Date scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public String getCallNotes() {
        return callNotes;
    }

    public void setCallNotes(String callNotes) {
        this.callNotes = callNotes;
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

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public Collection<Conferencecallparticipants> getConferencecallparticipantsCollection() {
        return conferencecallparticipantsCollection;
    }

    public void setConferencecallparticipantsCollection(Collection<Conferencecallparticipants> conferencecallparticipantsCollection) {
        this.conferencecallparticipantsCollection = conferencecallparticipantsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (callId != null ? callId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conferencecalls)) {
            return false;
        }
        Conferencecalls other = (Conferencecalls) object;
        if ((this.callId == null && other.callId != null) || (this.callId != null && !this.callId.equals(other.callId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Conferencecalls[ callId=" + callId + " ]";
    }
    
}
