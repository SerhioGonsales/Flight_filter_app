package main.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.model.Flight;

import java.util.List;

/**
 * The FlightFilter interface defines a filter method that accepts a list of flights and returns
 * the filtered list of flights.
 */
public interface FlightFilter {
    /**
     * Filters the list of flights according to certain criteria.
     *
     * @param flights the list of flights to filter
     * @return the filtered list of flights
     */
    List<Flight> filter(List<Flight> flights);
}
