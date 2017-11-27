/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author james
 */
@Entity
@Table(name = "baggageitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baggageitem.findAll", query = "SELECT b FROM Baggageitem b")
    , @NamedQuery(name = "Baggageitem.findByBaggageItemId", query = "SELECT b FROM Baggageitem b WHERE b.baggageItemId = :baggageItemId")
    , @NamedQuery(name = "Baggageitem.findByBaggageItemWeightKg", query = "SELECT b FROM Baggageitem b WHERE b.baggageItemWeightKg = :baggageItemWeightKg")
    , @NamedQuery(name = "Baggageitem.findByAircraftAircraftId", query = "SELECT b FROM Baggageitem b WHERE b.aircraftAircraftId = :aircraftAircraftId")})
public class Baggageitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BaggageItemId")
    private Integer baggageItemId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BaggageItemWeightKg")
    private int baggageItemWeightKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Aircraft_AircraftId")
    private int aircraftAircraftId;
    @JoinColumn(name = "Booking_BookingId", referencedColumnName = "BookingId")
    @ManyToOne(optional = false)
    private Booking bookingBookingId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baggageItemBaggageItemId")
    private Collection<Passenger> passengerCollection;

    public Baggageitem() {
    }

    public Baggageitem(Integer baggageItemId) {
        this.baggageItemId = baggageItemId;
    }

    public Baggageitem(Integer baggageItemId, int baggageItemWeightKg, int aircraftAircraftId) {
        this.baggageItemId = baggageItemId;
        this.baggageItemWeightKg = baggageItemWeightKg;
        this.aircraftAircraftId = aircraftAircraftId;
    }

    public Integer getBaggageItemId() {
        return baggageItemId;
    }

    public void setBaggageItemId(Integer baggageItemId) {
        this.baggageItemId = baggageItemId;
    }

    public int getBaggageItemWeightKg() {
        return baggageItemWeightKg;
    }

    public void setBaggageItemWeightKg(int baggageItemWeightKg) {
        this.baggageItemWeightKg = baggageItemWeightKg;
    }

    public int getAircraftAircraftId() {
        return aircraftAircraftId;
    }

    public void setAircraftAircraftId(int aircraftAircraftId) {
        this.aircraftAircraftId = aircraftAircraftId;
    }

    public Booking getBookingBookingId() {
        return bookingBookingId;
    }

    public void setBookingBookingId(Booking bookingBookingId) {
        this.bookingBookingId = bookingBookingId;
    }

    @XmlTransient
    public Collection<Passenger> getPassengerCollection() {
        return passengerCollection;
    }

    public void setPassengerCollection(Collection<Passenger> passengerCollection) {
        this.passengerCollection = passengerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (baggageItemId != null ? baggageItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baggageitem)) {
            return false;
        }
        Baggageitem other = (Baggageitem) object;
        if ((this.baggageItemId == null && other.baggageItemId != null) || (this.baggageItemId != null && !this.baggageItemId.equals(other.baggageItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Baggageitem[ baggageItemId=" + baggageItemId + " ]";
    }
    
}
