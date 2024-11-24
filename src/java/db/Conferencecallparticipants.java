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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon Sabul
 */
@Entity
@Table(name = "conferencecallparticipants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conferencecallparticipants.findAll", query = "SELECT c FROM Conferencecallparticipants c"),
    @NamedQuery(name = "Conferencecallparticipants.findByParticipantId", query = "SELECT c FROM Conferencecallparticipants c WHERE c.participantId = :participantId"),
    @NamedQuery(name = "Conferencecallparticipants.findByRole", query = "SELECT c FROM Conferencecallparticipants c WHERE c.role = :role"),
    @NamedQuery(name = "Conferencecallparticipants.findByJoinedAt", query = "SELECT c FROM Conferencecallparticipants c WHERE c.joinedAt = :joinedAt"),
    @NamedQuery(name = "Conferencecallparticipants.findByLeftAt", query = "SELECT c FROM Conferencecallparticipants c WHERE c.leftAt = :leftAt")})
public class Conferencecallparticipants implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "participant_id")
    private Integer participantId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "role")
    private String role;
    @Column(name = "joined_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt;
    @Column(name = "left_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leftAt;
    @JoinColumn(name = "call_id", referencedColumnName = "call_id")
    @ManyToOne(optional = false)
    private Conferencecalls callId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public Conferencecallparticipants() {
    }

    public Conferencecallparticipants(Integer participantId) {
        this.participantId = participantId;
    }

    public Conferencecallparticipants(Integer participantId, String role) {
        this.participantId = participantId;
        this.role = role;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(Date leftAt) {
        this.leftAt = leftAt;
    }

    public Conferencecalls getCallId() {
        return callId;
    }

    public void setCallId(Conferencecalls callId) {
        this.callId = callId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participantId != null ? participantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conferencecallparticipants)) {
            return false;
        }
        Conferencecallparticipants other = (Conferencecallparticipants) object;
        if ((this.participantId == null && other.participantId != null) || (this.participantId != null && !this.participantId.equals(other.participantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Conferencecallparticipants[ participantId=" + participantId + " ]";
    }
    
}
