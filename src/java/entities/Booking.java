/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findByBookingId", query = "SELECT b FROM Booking b WHERE b.bookingId = :bookingId")
    , @NamedQuery(name = "Booking.findByNoOfAdults", query = "SELECT b FROM Booking b WHERE b.noOfAdults = :noOfAdults")
    , @NamedQuery(name = "Booking.findByNoOfChildren", query = "SELECT b FROM Booking b WHERE b.noOfChildren = :noOfChildren")
    , @NamedQuery(name = "Booking.findByNoOfInfants", query = "SELECT b FROM Booking b WHERE b.noOfInfants = :noOfInfants")
    , @NamedQuery(name = "Booking.findByOutboundFlightID", query = "SELECT b FROM Booking b WHERE b.outboundFlightID = :outboundFlightID")
    , @NamedQuery(name = "Booking.findByReturnFlightID", query = "SELECT b FROM Booking b WHERE b.returnFlightID = :returnFlightID")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BookingId")
    private Integer bookingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfAdults")
    private int noOfAdults;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfChildren")
    private int noOfChildren;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfInfants")
    private int noOfInfants;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OutboundFlightID")
    private int outboundFlightID;
    @Column(name = "ReturnFlightID")
    private int returnFlightID;
    @JoinTable(name = "flightbooking", joinColumns = {
        @JoinColumn(name = "Booking_BookingId", referencedColumnName = "BookingId")}, inverseJoinColumns = {
        @JoinColumn(name = "Flight_FlightId", referencedColumnName = "FlightId")})
    @ManyToMany
    private Collection<Flight> flightCollection;
    @OneToMany(mappedBy = "bookingBookingId")
    private Collection<List<Seat>> seatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingBookingId")
    private Collection<Baggageitem> baggageitemCollection;
    @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId")
    @ManyToOne(optional = false)
    private int customerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingBookingId")
    private Collection<Passenger> passengerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingBookingId")
    private Collection<Payment> paymentCollection;

    public Booking() {
    }

    public Booking(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Booking(Integer bookingId, int noOfAdults, int noOfChildren, int noOfInfants, int outboundFlightID) {
        this.bookingId = bookingId;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfInfants = noOfInfants;
        this.outboundFlightID = outboundFlightID;
    }

    public Booking(Integer bookingId, int noOfAdults, int noOfChildren, int noOfInfants, int outboundFlightID, int returnFlightID, int customerId) {
        this.bookingId = bookingId;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfInfants = noOfInfants;
        this.outboundFlightID = outboundFlightID;
        this.returnFlightID = returnFlightID;
        this.customerId = customerId;
    }

    public Booking(Integer bookingId, int noOfAdults, int noOfChildren, int noOfInfants, int outboundFlightID, int returnFlightID, Collection<List<Seat>> seatCollection, int customerId) {
        this.bookingId = bookingId;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfInfants = noOfInfants;
        this.outboundFlightID = outboundFlightID;
        this.returnFlightID = returnFlightID;
        this.seatCollection = seatCollection;
        this.customerId = customerId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public int getNoOfInfants() {
        return noOfInfants;
    }

    public void setNoOfInfants(int noOfInfants) {
        this.noOfInfants = noOfInfants;
    }

    public int getOutboundFlightID() {
        return outboundFlightID;
    }

    public void setOutboundFlightID(int outboundFlightID) {
        this.outboundFlightID = outboundFlightID;
    }

    public Integer getReturnFlightID() {
        return returnFlightID;
    }

    public void setReturnFlightID(Integer returnFlightID) {
        this.returnFlightID = returnFlightID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Collection<Flight> getFlightCollection() {
        return flightCollection;
    }

    public void setFlightCollection(Collection<Flight> flightCollection) {
        this.flightCollection = flightCollection;
    }

    public Collection<List<Seat>> getSeatCollection() {
        return seatCollection;
    }

    public void setSeatCollection(Collection<List<Seat>> seatCollection) {
        this.seatCollection = seatCollection;
    }

    public Collection<Baggageitem> getBaggageitemCollection() {
        return baggageitemCollection;
    }

    public void setBaggageitemCollection(Collection<Baggageitem> baggageitemCollection) {
        this.baggageitemCollection = baggageitemCollection;
    }

    public Collection<Passenger> getPassengerCollection() {
        return passengerCollection;
    }

    public void setPassengerCollection(Collection<Passenger> passengerCollection) {
        this.passengerCollection = passengerCollection;
    }

    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Booking[ bookingId=" + bookingId + " ]";
    }

}
