package test.java.com.gridnine.testing.filters;

import main.java.com.gridnine.testing.filters.FlightFilter;
import main.java.com.gridnine.testing.filters.TransferTimeMoreThanTwoHoursFilter;
import main.java.com.gridnine.testing.model.Flight;
import main.java.com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTimeMoreThanTwoHoursFilterTest {

    @Test
    // Testing the filtering of flights where transfer time exceeds two hours
    void testFilterNoTransfersMoreThan2Hours() {
        // Create valid set of segments where the total transfer time is 2 hours
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5));
        List<Segment> validSegment = List.of(segment1, segment2, segment3);
        // Create invalid set of segments where the total transfer time is 3 hours
        Segment segment4 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment5 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        Segment segment6 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5));
        Segment segment7 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7));
        List<Segment> invalidSegment = List.of(segment4, segment5, segment6, segment7);
        // Create set of flights
        Flight validFlight = new Flight(validSegment);
        Flight invalidFlight = new Flight(invalidSegment);
        List<Flight> flights = List.of(validFlight, invalidFlight);

        // when
        FlightFilter transMoreTwoHours = new TransferTimeMoreThanTwoHoursFilter();
        List<Flight> actualList = transMoreTwoHours.filter(flights);

        // then
        // Expecting only one valid flight in the list
        assertEquals(1, actualList.size());
        assertEquals(validFlight, actualList.get(0));
    }
}
