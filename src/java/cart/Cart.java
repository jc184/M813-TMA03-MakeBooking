/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.ArrayList;
import entities.Booking;

/**
 *
 * @author james
 */
public class Cart implements Serializable {

    private ArrayList<Booking> bookings;

    public Cart(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public int getCount() {
        return bookings.size();
    }
    

}
