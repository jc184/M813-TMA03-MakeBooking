/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
    private Integer returnFlightID;
    @JoinColumn(name = "Customer_CustomerId", referencedColumnName = "CustomerId")
    @ManyToOne(optional = false)
    private int customerId;

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

    public Booking(Integer bookingId, int noOfAdults, int noOfChildren, int noOfInfants, int outboundFlightID, Integer returnFlightID, int customerId) {
        this.bookingId = bookingId;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfInfants = noOfInfants;
        this.outboundFlightID = outboundFlightID;
        this.returnFlightID = returnFlightID;
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
