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
import java.util.List;
import model.Flight;

/**
 * Alba Airways application M813-TMA02-ChooseFlight
  * https://github.com/jc184/M813-TMA02-ChooseFlight
 * @author james chalmers Open University F6418079
 * @version 1.0
 */
public class FlightDataStore {

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

    /**
     * Retrieves a Flight record from the database and creates a corresponding instance of Flight
     * @param id int id
     * @return an instance of Flight
     */
    public Flight getRecord(int id) {
        Flight flight = null;
        Connection connection = getConnection();
        try {
            PreparedStatement get = (PreparedStatement) connection.prepareStatement("SELECT * FROM flight WHERE FlightId=?");

            get.setInt(1, id);

            ResultSet results = get.executeQuery();

            while (results.next()) {
                flight = new Flight(
                        results.getInt("FlightId"),
                        results.getDate("FlightDate"),
                        results.getDate("LeaveDateTime"),
                        results.getDate("ArrivalDateTime"),
                        results.getString("FlightStatus"),
                        results.getInt("GateNumber"),
                        results.getInt("Stops"),
                        results.getInt("Aircraft_AircraftId"));
            }

            get.close();

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to find Record: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (flight);
    }

    /**
     *
     * @return a list of Flight instances
     */
    public List<Flight> getAllRecords() {

        Connection connection = getConnection();
        List<Flight> allFlights = new ArrayList<>();

        try {
            try (PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                    "SELECT * FROM flight")) {
                ResultSet results = get.executeQuery();

                Flight resultBean = null;

                while (results.next()) {

                    resultBean = new Flight(
                            results.getInt("FlightId"),
                            results.getDate("FlightDate"),
                            results.getDate("LeaveDateTime"),
                            results.getDate("ArrivalDateTime"),
                            results.getString("FlightStatus"),
                            results.getInt("GateNumber"),
                            results.getInt("Stops"),
                            results.getInt("Aircraft_AircraftId"));

                    allFlights.add(resultBean);
                }
            }

            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get all records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return (allFlights);
    }

}
