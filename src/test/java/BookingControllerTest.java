import dev.flight_app.controllers.BookingController;
import dev.flight_app.services.BookingService;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import dev.flight_app.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.BERN;
import static dev.flight_app.entities.City.BRATISLAVA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class BookingControllerTest {
    private BookingService BSMock;
    private BookingController BC;
    private Flight flight;
    private User user;
    private List<Passenger> passenger;

    @BeforeEach
    void setUp(){
        BSMock = mock(BookingService.class);
        BC = new BookingController(BSMock);
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        passenger = new ArrayList<>();
        passenger.add(new Passenger("Nina", "Smith"));

    }
    @Test
    public void testMyFlightsWithPassenger(){
        Booking b1 = new Booking(1, flight, passenger, user);
        Booking b2 =  new Booking(2, flight, passenger, user);
        Map<Integer, Booking> m = new HashMap<>();
        m.put(1, b1);
        m.put(2, b1);
        List<Map.Entry<Integer, Booking>> expected = m.entrySet().stream().toList();

        when(BSMock.myFlights("Nina", "Smith")).thenReturn(expected);

        List<Map.Entry<Integer, Booking>> actual = BC.myFlights("Nina", "Smith");

        assertEquals(expected, actual);
    }

    @Test
    public void testMyFlightsWithUser(){
        Booking b1 =  new Booking(1, flight, passenger, user);
        Booking b2 =  new Booking(2, flight, passenger, user);
        Map<Integer, Booking> m = new HashMap<>();
        m.put(1, b1);
        m.put(2, b2);
        List<Map.Entry<Integer, Booking>> expected = m.entrySet().stream().toList();

        when(BSMock.myFlights(user)).thenReturn(expected);

        List<Map.Entry<Integer, Booking>> actual = BC.myFlights(user);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateNewBooking(){
        Booking expected = new Booking(1, flight, passenger, user);

        when(BSMock.createNewBooking(flight, passenger, user)).thenReturn(expected);

        Booking actual = BC.createNewBooking(flight, passenger, user);
        assertEquals(expected, actual);

    }
    @Test
    public void testCancelBooking(){
        int id = 32;
        boolean expect = true;
        when(BC.cancelBooking(id)).thenReturn(expect);
        boolean actual = BC.cancelBooking(id);
        verify(BSMock, times(1)).cancelBooking(id);
        assertTrue(actual);
    }
    @Test
    public void testLoadData() {
        BC.loadData();
        verify(BSMock).loadData();
    }

    @Test void testSaveData(){
        boolean expect = true;
        when(BC.saveData()).thenReturn(expect);
        boolean actual = BC.saveData();
        verify(BSMock, times(1)).saveData();
        assertTrue(actual);
    }
}
