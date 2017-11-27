/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@Entity
@Table(name = "passenger")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p")
    , @NamedQuery(name = "Passenger.findByPassengerId", query = "SELECT p FROM Passenger p WHERE p.passengerId = :passengerId")
    , @NamedQuery(name = "Passenger.findByPassengerName", query = "SELECT p FROM Passenger p WHERE p.passengerName = :passengerName")
    , @NamedQuery(name = "Passenger.findByPassengerType", query = "SELECT p FROM Passenger p WHERE p.passengerType = :passengerType")})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PassengerId")
    private Integer passengerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PassengerName")
    private String passengerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PassengerType")
    private String passengerType;
    @JoinColumn(name = "BaggageItem_BaggageItemId", referencedColumnName = "BaggageItemId")
    @ManyToOne(optional = false)
    private Baggageitem baggageItemBaggageItemId;
    @JoinColumn(name = "Booking_BookingId", referencedColumnName = "BookingId")
    @ManyToOne(optional = false)
    private Booking bookingBookingId;
    @JoinColumn(name = "Flight_FlightId", referencedColumnName = "FlightId")
    @ManyToOne(optional = false)
    private Flight flightFlightId;
    @JoinColumns({
        @JoinColumn(name = "Seat_SeatNo", referencedColumnName = "SeatNo")
        , @JoinColumn(name = "Seat_Aircraft_AircraftId", referencedColumnName = "Aircraft_AircraftId")})
    @ManyToOne(optional = false)
    private Seat seat;

    public Passenger() {
    }

    public Passenger(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Passenger(Integer passengerId, String passengerName, String passengerType) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.passengerType = passengerType;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public Baggageitem getBaggageItemBaggageItemId() {
        return baggageItemBaggageItemId;
    }

    public void setBaggageItemBaggageItemId(Baggageitem baggageItemBaggageItemId) {
        this.baggageItemBaggageItemId = baggageItemBaggageItemId;
    }

    public Booking getBookingBookingId() {
        return bookingBookingId;
    }

    public void setBookingBookingId(Booking bookingBookingId) {
        this.bookingBookingId = bookingBookingId;
    }

    public Flight getFlightFlightId() {
        return flightFlightId;
    }

    public void setFlightFlightId(Flight flightFlightId) {
        this.flightFlightId = flightFlightId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passengerId != null ? passengerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passenger)) {
            return false;
        }
        Passenger other = (Passenger) object;
        if ((this.passengerId == null && other.passengerId != null) || (this.passengerId != null && !this.passengerId.equals(other.passengerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Passenger[ passengerId=" + passengerId + " ]";
    }
    
}
