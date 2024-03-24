package test.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.filters.DepartureIsBeforeCurrentTimeFilter;
import main.java.com.gridnine.testing.filters.FlightFilter;
import main.java.com.gridnine.testing.model.Flight;
import main.java.com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartureIsBeforeCurrentTimeFilterTest {

    @Test
    // Testing filtering of flights whose departure time was before the current date
    void testFilterNoDepartureBeforeCurrentTime() {
        // Create valid segment with departure after current date,
        // while increasing the departure time by 2 seconds,
        // since when calling the tested method its current time is about a second later
        List<Segment> validSegment =
                List.of(new Segment(LocalDateTime.now().plusSeconds(2), LocalDateTime.now().plusHours(1)));
        // Create invalid segments with departure date before current date
        List<Segment> invalidSegment =
                List.of(new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now()));
        // Create set of flights
        Flight validFlight = new Flight(validSegment);
        Flight invalidFlight = new Flight(invalidSegment);
        List<Flight> flights = List.of(validFlight, invalidFlight);

        // when
        FlightFilter deptBeforeNow = new DepartureIsBeforeCurrentTimeFilter();
        List<Flight> actualList = deptBeforeNow.filter(flights);

        // then
        // Expecting only one valid flight in the list
        assertEquals(1, actualList.size());
        assertEquals(validFlight, actualList.get(0));
    }
}
