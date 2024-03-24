package test.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.filters.ArrivalIsBeforeDepartureFilter;
import main.java.com.gridnine.testing.filters.FlightFilter;
import main.java.com.gridnine.testing.model.Flight;
import main.java.com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrivalIsBeforeDepartureFilterTest {

    @Test
    // Testing filtering flights that have segments with an arrival date earlier than the departure date
    void testFilterNoArrivalBeforeDeparture() {
        // Create valid segment with departure date before arrival date
        List<Segment> validSegment =
                List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        // Create invalid segment with arrival date before departure date
        List<Segment> invalidSegment =
                List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().minusHours(1)));
        // Create set of flights
        Flight validFlight = new Flight(validSegment);
        Flight invalidFlight = new Flight(invalidSegment);
        List<Flight> flights = List.of(validFlight, invalidFlight);

        // when
        FlightFilter arrBeforeDept = new ArrivalIsBeforeDepartureFilter();
        List<Flight> actualList = arrBeforeDept.filter(flights);

        // then
        // Expecting only one valid flight in the list
        assertEquals(1, actualList.size());
        assertEquals(validFlight, actualList.get(0));
    }
}
