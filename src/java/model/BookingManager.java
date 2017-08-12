/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author james
 */
public class BookingManager implements Serializable {

    private static final long serialVersionUID = 1L;

    BookingDataStore bookingStore;

    public BookingManager() {
        bookingStore = new BookingDataStore();
    }

    public void addBooking(int customerId, int noOfAdults, int noOfChildren, int noOfInfants, int outboundFlightId, int returnFlightId) {
        // 'Creates a unique ID then creates booking record'.
        for (Integer newId = 1; newId < Integer.MAX_VALUE; newId++) {
            if (bookingStore.getRecord(newId) == null) {
                Booking booking = new Booking(newId, customerId, noOfAdults, noOfChildren, noOfInfants, outboundFlightId, returnFlightId);
                bookingStore.createRecord(booking);
                return;
            }
        }
    }
}