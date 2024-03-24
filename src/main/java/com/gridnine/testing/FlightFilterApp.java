package main.java.com.gridnine.testing;

import main.java.com.gridnine.testing.filters.ArrivalIsBeforeDepartureFilter;
import main.java.com.gridnine.testing.filters.DepartureIsBeforeCurrentTimeFilter;
import main.java.com.gridnine.testing.filters.FlightFilter;
import main.java.com.gridnine.testing.filters.TransferTimeMoreThanTwoHoursFilter;
import main.java.com.gridnine.testing.model.Flight;
import main.java.com.gridnine.testing.model.FlightBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The FlightFilterApp class represents an example of using flight filters for different scenarios.
 */
public class FlightFilterApp {
    public static void main(String[] args) {
        // Create a list of flights
        List<Flight> flights = FlightBuilder.createFlights();
        // Print the list of all flights
        System.out.print("Flights:\n");
        flights.forEach(System.out::println);

        // Create instances of filter by current time
        FlightFilter deptBeforeNow = new DepartureIsBeforeCurrentTimeFilter();
        // Get the current time for clarity
        var currentTime = LocalDateTime.now();
        var now = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        // Apply filter
        System.out.println("\nFlights where departure is after current time [" + now + "]:");
        deptBeforeNow.filter(flights).forEach(System.out::println);

        // Create instances of filter by departure time
        FlightFilter arrBeforeDept = new ArrivalIsBeforeDepartureFilter();
        // Apply filter for flights without segments where arrival is before departure time
        System.out.println("\nFlights without segments where arrival is before departure time:");
        arrBeforeDept.filter(flights).forEach(System.out::println);

        // Create instances of filter by transfer time
        FlightFilter transMoreTwoHours = new TransferTimeMoreThanTwoHoursFilter();
        // Apply filter for flights without a transfer time of more than two hours
        System.out.println("\nFlights without a transfer time of more than two hours:");
        transMoreTwoHours.filter(flights).forEach(System.out::println);
    }
}
