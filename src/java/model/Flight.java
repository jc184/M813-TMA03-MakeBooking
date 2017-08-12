/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.persistence.ManyToMany;
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
 * @author james
 */
@Entity
@Table(name = "flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f")
    , @NamedQuery(name = "Flight.findByFlightId", query = "SELECT f FROM Flight f WHERE f.flightId = :flightId")
    , @NamedQuery(name = "Flight.findByFlightDate", query = "SELECT f FROM Flight f WHERE f.flightDate = :flightDate")
    , @NamedQuery(name = "Flight.findByLeaveDateTime", query = "SELECT f FROM Flight f WHERE f.leaveDateTime = :leaveDateTime")
    , @NamedQuery(name = "Flight.findByArrivalDateTime", query = "SELECT f FROM Flight f WHERE f.arrivalDateTime = :arrivalDateTime")
    , @NamedQuery(name = "Flight.findByFlightStatus", query = "SELECT f FROM Flight f WHERE f.flightStatus = :flightStatus")
    , @NamedQuery(name = "Flight.findByGateNumber", query = "SELECT f FROM Flight f WHERE f.gateNumber = :gateNumber")
    , @NamedQuery(name = "Flight.findByStops", query = "SELECT f FROM Flight f WHERE f.stops = :stops")
    , @NamedQuery(name = "Flight.findByAircraftAircraftId", query = "SELECT f FROM Flight f WHERE f.aircraftAircraftId = :aircraftAircraftId")})
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FlightId")
    private Integer flightId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FlightDate")
    @Temporal(TemporalType.DATE)
    private Date flightDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LeaveDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArrivalDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FlightStatus")
    private String flightStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GateNumber")
    private int gateNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Stops")
    private int stops;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Aircraft_AircraftId")
    private int aircraftAircraftId;
    @ManyToMany(mappedBy = "flightCollection")
    private Collection<Booking> bookingCollection;
    @JoinColumn(name = "Route_RouteId", referencedColumnName = "RouteId")
    @ManyToOne(optional = false)
    private Route routeRouteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightFlightId")
    private Collection<Passenger> passengerCollection;

    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Flight(Integer flightId, Date flightDate, Date leaveDateTime, Date arrivalDateTime, String flightStatus, int gateNumber, int stops, int aircraftAircraftId) {
        this.flightId = flightId;
        this.flightDate = flightDate;
        this.leaveDateTime = leaveDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.flightStatus = flightStatus;
        this.gateNumber = gateNumber;
        this.stops = stops;
        this.aircraftAircraftId = aircraftAircraftId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Date getLeaveDateTime() {
        return leaveDateTime;
    }

    public void setLeaveDateTime(Date leaveDateTime) {
        this.leaveDateTime = leaveDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getAircraftAircraftId() {
        return aircraftAircraftId;
    }

    public void setAircraftAircraftId(int aircraftAircraftId) {
        this.aircraftAircraftId = aircraftAircraftId;
    }

    @XmlTransient
    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    public Route getRouteRouteId() {
        return routeRouteId;
    }

    public void setRouteRouteId(Route routeRouteId) {
        this.routeRouteId = routeRouteId;
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
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Flight[ flightId=" + flightId + " ]";
    }
    
}
