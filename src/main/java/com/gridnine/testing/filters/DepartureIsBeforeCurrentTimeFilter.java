package main.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The DepartureIsBeforeCurrentTimeFilter class implements the FlightFilter
 * interface to check flights by departure time.
 */
public class DepartureIsBeforeCurrentTimeFilter implements FlightFilter {
    /**
     * A filter that returns a list of flights whose first segments
     * depart at a time later than the current time.
     *
     * @param flights the list of flights to filter
     * @return the filtered list of flight
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        var currentTime = LocalDateTime.now();

        return flights.stream()
                .filter(flight -> flight.getSegments().get(0)
                        .getDepartureDate().isAfter(currentTime))
                .collect(Collectors.toList());
    }
}
