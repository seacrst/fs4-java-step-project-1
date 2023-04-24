import dev.flight_app.controllers.BookingController;
import dev.flight_app.services.BookingService;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.BERN;
import static dev.flight_app.entities.City.BRATISLAVA;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BookingControllerTest {
    private BookingService BSMock;
    private BookingController BC;
    private Flight flight;
    private List<Passenger> passenger;

    @BeforeEach
    void setUp(){
        BSMock = Mockito.mock(BookingService.class);
        BC = new BookingController();
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        passenger = new ArrayList<>();
        passenger.add(new Passenger("Nina", "Smith"));

    }
    @Test
    public void testMyFlightsWithPassenger(){
        Booking b1 = new Booking(1, flight, passenger);
        List<Booking> expected = new ArrayList<>();
        expected.add(b1);

        when(BSMock.myFlights("Nina", "Smith")).thenReturn(expected);
        BC.createNewBooking(flight, passenger);

        List<Booking> actual = BC.myFlights("Nina", "Smith");

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateNewBooking(){
        Booking expected = new Booking(1, flight, passenger);

        when(BSMock.createNewBooking(flight, passenger)).thenReturn(expected);

        Booking actual = BC.createNewBooking(flight, passenger);
        assertEquals(expected, actual);

    }
    @Test
    public void testCancelBooking(){
        int id = 1;
        when(BSMock.cancelBooking(id)).thenReturn(false);
        boolean result = BC.cancelBooking(id);
        assertFalse(result);
    }
    @Test void testSaveData(){
        boolean expect = true;
        when(BSMock.saveData()).thenReturn(expect);
        boolean actual = BC.saveData();
        assertTrue(actual);
    }
}
