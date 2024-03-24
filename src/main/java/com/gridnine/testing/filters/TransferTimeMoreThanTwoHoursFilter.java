package main.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.model.Flight;
import main.java.com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TransferTimeMoreThanTwoHoursFilter class implements the FlightFilter
 * interface to check flights for exceeding time on the ground.
 */
public class TransferTimeMoreThanTwoHoursFilter implements FlightFilter {
    /**
     * A filter that returns a list of flights in which the total interval
     * time between the arrival of one segment and the departure of the next one
     * does not exceed two hours
     *
     * @param flights the list of flights to filter
     * @return the filtered list of flights
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() > 1) {
                        var totalTransferTime = 0;
                        for (int i = 1; i < segments.size(); i++) {
                            var arrivalTime = segments.get(i - 1).getArrivalDate();
                            var departureTime = segments.get(i).getDepartureDate();
                            Duration duration = Duration.between(arrivalTime, departureTime);
                            totalTransferTime += duration.toHoursPart();
                        }
                        return totalTransferTime <= 2;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
