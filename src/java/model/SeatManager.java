/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import model.SeatEnum;
import model.SeatDataStore;
import model.Booking;
import model.Seat;
import model.SeatTypeEnum;
import static model.SeatTypeEnum.FIRSTCLASS;

/**
 *
 * @author james
 */
public class SeatManager implements Serializable {

    private static final long serialVersionUID = 1L;

    SeatDataStore seatStore;

    private final int NUMBER_OF_SEATS = 24;
    private boolean[] seats = new boolean[NUMBER_OF_SEATS];
    private int firstClassCounter;//counter for first class
    private int economyCounter;//counter for economy class
    private boolean isSeatBooked;
    private String msg;
    private String url;

    public SeatManager() {
        seatStore = new SeatDataStore();
        //seats = getAllSeatBookings();
    }

    public int getNUMBER_OF_SEATS() {
        return NUMBER_OF_SEATS;
    }

    public void setSeats(boolean[] seats) {
        this.seats = seats;

    }

    public boolean[] getSeats() {

        return seats;
    }

    public int getFirstClassCounter() {
        return firstClassCounter;
    }

    public int getEconomyCounter() {
        return economyCounter;
    }

    public boolean isIsSeatBooked() {
        return isSeatBooked;
    }

    public void addSeatBooking(int seatNumber, int aircraftId, String seatType, Booking bookingId, boolean booked) throws ClassNotFoundException {
        try {
            for (SeatEnum seatEnum : SeatEnum.values()) {
                if (seatStore.getRecord(seatEnum.ordinal()) == null) {
                    Seat seat = new Seat(seatNumber, aircraftId, seatType, bookingId, booked);
                    seatStore.createRecord(seat);
                } else {
                    msg = "That seat is already booked.";
                }
            }
        } catch (ClassCastException cce) {
            cce.getMessage();
        }
    }

    public Seat addSeatReservation(int seatNumber, int aircraftId, String seatType, Booking bookingId, boolean booked) throws ClassNotFoundException {
        try {
            for (SeatEnum seatEnum : SeatEnum.values()) {
                if (seatStore.getRecord(seatEnum.ordinal()) == null) {
                    Seat seat = new Seat(seatNumber, aircraftId, seatType, bookingId, booked);
                    seatStore.createRecord(seat);
                    return seat;
                } else {
                    msg = "That seat is already booked.";
                }
            }
        } catch (ClassCastException cce) {
            cce.getMessage();
        }
        return null;
    }
//    public boolean[] assignSeat(int seatNumber, SeatTypeEnum seatType) throws ClassNotFoundException {
//
////        if (seatType == SeatTypeEnum.ECONOMY) {
////            economyCounter++;
////            if (economyCounter >= 12) {
////                msg = "All the Economy seats have been used up.";
////                url = "/booked.jsp";
////            }
////        } else if (seatType == SeatTypeEnum.FIRSTCLASS) {
////            firstClassCounter++;
////            if (firstClassCounter >= 12) {
////                msg = "All the First Class seats have been used up.";
////                url = "/chooseeconomy.jsp";
////            } else {
////
////           }
////        }
//        this.getSeats()[seatNumber] = true;
//        return seats;
//    }
//    public boolean[] allocateEconomySeat(SeatTypeEnum seatType) {
//
//        if (economyCounter < 12) {
//            Random random = new Random();
//            int economySeat = random.nextInt(23 - 12 + 1) + 12;
//            int seatNumber = economySeat;
//            if (this.getSeats()[seatNumber] == false) {
//                this.getSeats()[seatNumber] = true;
//                economyCounter++;
//                return seats;
//            } else {
//                msg = "This seat is already booked.";
//            }
//            isSeatBooked = true;
//        } else if (economyCounter >= 12) {
//            msg = "All the Economy seats have been used up.";
//            url = "/booked.jsp";
//        }
//        
//        return seats;
//    }

    public void saveSeatingLayout(boolean[] seats) {
        int seatNumber = 0;
        for (SeatEnum seatEnum : SeatEnum.values()) {
            if (seatNumber == seatEnum.ordinal()) {
                this.setSeats(seats);
                Arrays.toString(seats);
            }
        }
    }

    public boolean isSeatBooked(int seatNumber) {
        for (SeatEnum seatEnum : SeatEnum.values()) {
            if (seatNumber == seatEnum.ordinal()) {
                return seats[seatNumber];//WRONG. ALWAYS RETURNS FALSE
            }
        }
        return false;
    }

    public boolean seatsContainsTrue() {
        for (boolean seat : seats) {
            if (seat) {
                return true;
            }
        }
        return false;
    }


    public List<Seat> getAllSeats() throws ClassNotFoundException {
        return seatStore.getAllRecords();
    }

    public List<Seat> getFilteredSeats(int bookingId) throws ClassNotFoundException {
        List<Seat> rtnList = new ArrayList<>();
        for (Seat seat : seatStore.getAllRecords()) {
            if (seat.getBookingId().equals(bookingId)) {
                rtnList.add(seat);
            }
        }
        return (rtnList);
    }

//    public Object[] getAllSeatBookings() {
//        return seatStore.getAllRecords().subList(3, 4).toArray();
//    }
    public Object[] getAllSeatBookings() throws ClassNotFoundException {
        Object[] seatObjects = seatStore.getAllRecords().subList(3, 4).toArray();
        return seatObjects;
    }

    public Seat getSeat(int seatNumber) throws ClassNotFoundException {
        return seatStore.getRecord(seatNumber);
    }

    public boolean getSeatBooking(int seatNumber) throws ClassNotFoundException {
        return seatStore.getRecord(seatNumber).isBooked();
    }

}
