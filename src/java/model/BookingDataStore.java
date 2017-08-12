/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author james
 */
class BookingDataStore {

    private Connection getConnection() {//IDENTICAL TO METHOD IN CUSTDATASTORE
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

    public void createRecord(Booking booking) {

        Connection myConnection = getConnection();

        try {
            try (PreparedStatement create = (PreparedStatement) myConnection.prepareStatement("INSERT INTO booking VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                create.setInt(1, booking.getBookingId());
                create.setInt(2, booking.getCustomerId());
                create.setInt(3, booking.getNoOfAdults());
                create.setInt(4, booking.getNoOfChildren());
                create.setInt(5, booking.getNoOfInfants());
                create.setInt(6, booking.getOutboundFlightID());
                create.setInt(7, booking.getReturnFlightID());

                create.executeUpdate();
            }

            myConnection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to create record: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
    }

    public Booking getRecord(int id) {
        Booking booking = null;
        Connection connection = getConnection();
        try {
            try (PreparedStatement get = (PreparedStatement) connection.prepareStatement("SELECT * FROM booking WHERE idBooking=?")) {
                get.setInt(1, id);

                ResultSet results = get.executeQuery();

                while (results.next()) {

                    booking = new Booking(
                            results.getInt("BookingId"),
                            results.getInt("Customer_CustomerId"),
                            results.getInt("NoOfAdults"),
                            results.getInt("NoOfChildren"),
                            results.getInt("NoOfInfants"),
                            results.getInt("OutboundFlightId"),
                            results.getInt("ReturnFlightId"));

                }
            }

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to find Record: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (booking);
    }

    public List<Booking> getAllRecords() {

        Connection connection = getConnection();
        List<Booking> allBookings = new ArrayList<>();

        try {
            try (PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                    "SELECT * FROM booking")) {
                ResultSet results = get.executeQuery();

                Booking resultBean = null;

                while (results.next()) {
                    resultBean = new Booking(
                            results.getInt("BookingId"),
                            results.getInt("Customer_CustomerId"),
                            results.getInt("NoOfAdults"),
                            results.getInt("NoOfChildren"),
                            results.getInt("NoOfInfants"),
                            results.getInt("OutboundFlightId"),
                            results.getInt("ReturnFlightId"));

                    allBookings.add(resultBean);
                }
            }

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get all records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (allBookings);
    }
}
