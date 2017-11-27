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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Alba Airways application M813-TMA02-ChooseSeat
 *
 * @author james chalmers Open University F6418079
 */
@Entity
@IdClass(SeatPK.class)
@Table(name = "seat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seat.findAll", query = "SELECT s FROM Seat s")
    , @NamedQuery(name = "Seat.findBySeatNo", query = "SELECT s FROM Seat s WHERE s.seatPK.seatNo = :seatNo")
    , @NamedQuery(name = "Seat.findByAircraftAircraftId", query = "SELECT s FROM Seat s WHERE s.seatPK.aircraftAircraftId = :aircraftAircraftId")
    , @NamedQuery(name = "Seat.findBySeatType", query = "SELECT s FROM Seat s WHERE s.seatType = :seatType")})
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int seatNumber;
    @Id
    private int aircraftId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "SeatType")
    private String seatType;
    @JoinColumn(name = "BookingId", referencedColumnName = "BookingId")
    @ManyToOne
    private Booking bookingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SeatBooked")
    private boolean booked;


    public Seat(int seatNumber, int aircraftId, String seatType, Booking bookingId, boolean booked) {
        this.seatNumber = seatNumber;
        this.aircraftId = aircraftId;
        this.seatType = seatType;
        this.bookingId = bookingId;
        this.booked = booked;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
    
    public Seat() {
    }

//    public Seat(SeatPK seatPK) {
//        this.seatPK = seatPK;
//    }
//
//    public Seat(SeatPK seatPK, SeatTypeEnum seatType, Booking bookingId, boolean booked) {
//        this.seatPK = seatPK;
//        this.seatType = seatType;
//        this.bookingId = bookingId;
//        this.booked = booked;
//    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

//    public Seat(int seatNo, int aircraftId, SeatTypeEnum seatType, boolean booked) {
//        this.seatPK = new SeatPK(seatNo, aircraftId);
//        this.seatType = seatType;
//        this.booked = booked;
//    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }


//    public SeatPK getSeatPK() {
//        return seatPK;
//    }
//
//    public void setSeatPK(SeatPK seatPK) {
//        this.seatPK = seatPK;
//    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (seatPK != null ? seatPK.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Seat)) {
//            return false;
//        }
//        Seat other = (Seat) object;
//        if ((this.seatPK == null && other.seatPK != null) || (this.seatPK != null && !this.seatPK.equals(other.seatPK))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entities.Seat[ seatPK=" + seatPK + " ]";
//    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
