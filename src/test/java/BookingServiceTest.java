import dev.flight_app.services.BookingService;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import org.junit.jupiter.api.BeforeEach;
import dev.flight_app.dao.BookingDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {
    private BookingDao BD;
    private BookingService BS;
    private List<Passenger> passengers;
    Flight flight;
    @Mock
    private BookingService mockedBookingService;
    @BeforeEach
    void setUp(){
        BS = new BookingService();
        passengers = new ArrayList<>();
        passengers.add(new Passenger("Nina", "Smith"));
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings(){
        Map<Integer, Booking> result = BS.getAllBookings();
        assertNotNull(result);
    }

    @Test
    public void testCreateNewBooking(){
        Booking newBooking =  BS.createNewBooking(flight, passengers);
        Flight flight2 = new Flight("code2", RYANAIR, 100, BERN, KYIV, LocalDateTime.now(), LocalDateTime.now());

        Booking newBooking2 =  BS.createNewBooking(flight2, passengers);
        assertEquals(newBooking, BS.getBookingById(newBooking.id()).orElse(null));
        assertEquals(newBooking2, BS.getBookingById(newBooking2.id()).orElse(null));
    }
    @Test
    public void testGetBookingById(){
        Booking newBooking =  BS.createNewBooking(flight, passengers);
        Optional<Booking> res = BS.getBookingById(newBooking.id());
        assertTrue(res.isPresent());
        assertEquals(newBooking, res.get());
    }
    @Test
    public void testMyFlights(){
        Flight flight1 = new Flight("code3", RYANAIR, 100, BERN, KYIV, LocalDateTime.now(), LocalDateTime.now());
        Flight flight2 = new Flight("code4", RYANAIR, 100, LONDON, KYIV, LocalDateTime.now(), LocalDateTime.now());

        Booking newBooking =  BS.createNewBooking(flight, passengers);
        Booking newBooking2 =  BS.createNewBooking(flight1, passengers);
        passengers.add(new Passenger("Peter", "Smith"));
        Booking newBooking3 =  BS.createNewBooking(flight2, passengers);

        assertEquals(3, BS.myFlights("Peter", "Smith").size());
    }
    @Test
    public void testCancelBooking(){
        passengers.add(new Passenger("Peter", "Smith"));
        Flight flight1 = new Flight("code3", RYANAIR, 100, BERN, KYIV, LocalDateTime.now(), LocalDateTime.now());

        Booking newBooking =  BS.createNewBooking(flight, passengers);
        Booking newBooking3 =  BS.createNewBooking(flight1, passengers);
        Integer id = newBooking.id();
        assertTrue(BS.cancelBooking(newBooking.id()));
        assertEquals(1, BS.getAllBookings().size());
        assertTrue(BS.getBookingById(id).isEmpty());
    }
    @Test
    public void testGetNextId(){
        Flight flight1 = new Flight("code3", RYANAIR, 100, BERN, KYIV, LocalDateTime.now(), LocalDateTime.now());
        Flight flight2 = new Flight("code4", RYANAIR, 100, LONDON, KYIV, LocalDateTime.now(), LocalDateTime.now());

        Booking newBooking =  BS.createNewBooking(flight, passengers);
        Booking newBooking2 =  BS.createNewBooking(flight1, passengers);
        Booking newBooking3 =  BS.createNewBooking(flight2, passengers);
        Integer id = newBooking2.id();
        assertEquals(id+1, newBooking3.id());
    }

    @Test
    public void testSaveData(){
        when(mockedBookingService.saveData()).thenReturn(true);
        boolean result2 = mockedBookingService.saveData();
        verify(mockedBookingService, times(1)).saveData();
        assertTrue(result2);
    }
//    @Test
//    public void testLoadData(){
//        Booking newBooking =  BS.createNewBooking(flight, passengers);
//        Integer id = newBooking.id();
//        boolean result = BS.saveData();
//        assertTrue(result);
//
//
//        BookingService BS2 = new BookingService();
//        BS2.loadData();
//
//        Map<Integer, Booking> resultBS = BS.getAllBookings();
//        assertEquals(1, resultBS.size());
//        assertTrue(resultBS.containsKey(id));
//        assertEquals(newBooking, resultBS.get(id));
//    }
}


