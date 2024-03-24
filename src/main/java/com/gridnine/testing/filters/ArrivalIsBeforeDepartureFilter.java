package main.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ArrivalIsBeforeDepartureTimeFilter class implements the FlightFilter
 * interface to check flights for inconsistencies in departure and arrival times.
 */
public class ArrivalIsBeforeDepartureFilter implements FlightFilter {
    /**
     * A filter that returns a list of flights where the arrival time
     * of each segment is not later than its departure time.
     *
     * @param flights the list of flights to filter
     * @return the filtered list of flights
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
