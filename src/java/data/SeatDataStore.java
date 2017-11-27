/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Class;
import entities.Booking;
import entities.Booking;
import entities.Seat;
import entities.Seat;
import model.SeatTypeEnum;
import model.SeatTypeEnum;

/**
 *
 * @author james
 */
public class SeatDataStore {

    private SeatTypeEnum seatType;

    public void createRecord(Seat seat) {
        Connection myConnection = getConnection();

        try {
            PreparedStatement create = (PreparedStatement) myConnection.prepareStatement("INSERT INTO seat VALUES (?, ?, ?, ?, ?)");

            create.setInt(1, seat.getSeatNumber());
            create.setInt(2, seat.getAircraftId());
            create.setString(3, seat.getSeatType());
            create.setObject(4, seat.getBookingId());//????
            create.setBoolean(5, seat.isBooked());

            create.executeUpdate();

            myConnection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to create record: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
    }


    /*
     * 
     */
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Database driver not found: " + cnfe.getMessage());
        }

        String dbUrl = "jdbc:mysql://localhost:3306/flightbookingsystem";
        String dbUser = "root";
        String dbPass = "stcallans";
        Connection connection = null;

        try {
            connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException sqle) {
            System.err.println("Unable to connect to Database: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
        return (connection);
    }

    public Seat getRecord(int seatNo) throws ClassNotFoundException {
        Seat seat = null;
        Connection connection = getConnection();
        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement("SELECT * FROM seat WHERE SeatNo=?");

            get.setInt(1, seatNo);

            ResultSet results = get.executeQuery();

            while (results.next()) {
                seat = new Seat(
                        results.getInt("SeatNo"),
                        results.getInt("AircraftId"),
                        results.getString("SeatType"),
                        (Booking) results.getObject("BookingId"),
                        results.getBoolean("SeatBooked"));
            }

            get.close();

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to find Record: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (seat);
    }

    public List<Seat> getAllRecords() throws ClassNotFoundException {

        Connection connection = getConnection();
        List<Seat> allSeats = new ArrayList<>();
        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                    "SELECT * FROM seat");
            ResultSet results = get.executeQuery();

            Seat resultBean = null;

            while (results.next()) {

                resultBean = new Seat(
                        results.getInt("SeatNo"),
                        results.getInt("AircraftId"),
                        results.getString("SeatType"),
                        (Booking) results.getObject("BookingId"),
                        results.getBoolean("SeatBooked"));

                allSeats.add(resultBean);

            }

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get all records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        } catch (ClassCastException cce) {
            System.err.println(cce.getMessage());

        }
        return (allSeats);
    }

    public List<Seat> getAllRecordsByBookingId(int bookingID) throws ClassNotFoundException {

        Connection connection = getConnection();
        List<Seat> bookingSeats = new ArrayList<>();
        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                    "SELECT * FROM seat WHERE BookingId=?");
            ResultSet results = get.executeQuery();

            Seat resultBean = null;

            while (results.next()) {

                resultBean = new Seat(
                        results.getInt("SeatNo"),
                        results.getInt("AircraftId"),
                        results.getString("SeatType"),
                        (Booking) results.getObject("BookingId"),
                        results.getBoolean("SeatBooked"));

                bookingSeats.add(resultBean);

            }

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get all records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        } catch (ClassCastException cce) {
            System.err.println(cce.getMessage());

        }
        return (bookingSeats);
    }

}
