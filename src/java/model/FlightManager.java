/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Flight;
import data.FlightDataStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Alba Airways application M813-TMA02-ChooseFlight
 * https://github.com/jc184/M813-TMA02-ChooseFlight
 *
 * @author james chalmers Open University F6418079
 * @version 1.0
 */
public class FlightManager {

    FlightDataStore flightStore;

    /**
     * Creates an empty FlightManager which allows Flights to be stored and
     * retrieved.
     */
    public FlightManager() {
        flightStore = new FlightDataStore();
    }

    /**
     * Returns a subset of all the Flights held
     *
     * @param flightDate identifier for Flight
     * @return rtnList (a list of Flights)
     */
    public List<Flight> getFilteredFlights(Date flightDate) {
        List<Flight> rtnList = new ArrayList<>();
        for (Flight flight : flightStore.getAllRecords()) {
            if (flight.getFlightDate().equals(flightDate)) {
                rtnList.add(flight);
            }
        }
        return (rtnList);
    }

    /**
     * Returns all the flights held
     *
     * @return allFlights (a list of Flights)
     */
    public List<Flight> getAllFlights() {
        return flightStore.getAllRecords();
    }

    /**
     * Gets a Flight from the Flight Store referenced by the flight ID
     *
     * @param id int id
     * @return Flight
     */
    public Flight getFlight(int id) {
        return flightStore.getRecord(id);
    }

}
