import dev.flight_app.Service.BookingService;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.User;
import org.junit.jupiter.api.BeforeEach;
import dev.flight_app.Dao.BookingDao;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {
    private BookingDao BD;
    private BookingService BS;
    @BeforeEach
    void setUp(){
        BD = new BookingDao();
        BS = new BookingService(BD);
    }

    @Test
    public void testGetAllBookings(){
        Map<Integer, Booking> result = BS.getAllBookings();
        assertTrue(result.isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testCreateNewBooking(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Booking newBooking2 =  BS.createNewBooking(flight, "Peter", "Smith", user);
        assertEquals(newBooking, BS.getBookingById(1).orElse(null));
        assertEquals(newBooking2, BS.getBookingById(newBooking2.id()).orElse(null));
    }
    @Test
    public void testGetBookingById(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Optional<Booking> res = BS.getBookingById(newBooking.id());
        assertTrue(res.isPresent());
        assertEquals(newBooking, res.get());
    }
    @Test
    public void testMyFlights(){
        Flight flight = new Flight();
        Flight flight1 = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        User user1 = new User(2, "xxxq", "qwert1234", "Jane", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Booking newBooking2 =  BS.createNewBooking(flight, "Peter", "Smith", user1);
        Booking newBooking3 =  BS.createNewBooking(flight1, "Nina", "Smith", user1);

        assertEquals(1, BS.myFlights("Peter", "Smith").size());
        assertEquals(2, BS.myFlights(user1).size());
    }
    @Test
    public void testCancelBooking(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        User user1 = new User(2, "xxxq", "qwert1234", "Jane", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Booking newBooking2 =  BS.createNewBooking(flight, "Peter", "Smith", user1);
        Booking newBooking3 =  BS.createNewBooking(flight, "Nina", "Smith", user1);
        Integer id = newBooking.id();
        assertTrue(BS.cancelBooking(newBooking.id()));
        assertEquals(2, BS.getAllBookings().size());
        assertTrue(BS.getBookingById(id).isEmpty());
    }
    @Test
    public void testGetNextId(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        User user1 = new User(2, "xxxq", "qwert1234", "Jane", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Booking newBooking2 =  BS.createNewBooking(flight, "Peter", "Smith", user1);
        Booking newBooking3 =  BS.createNewBooking(flight, "Nina", "Smith", user1);
        Integer id = newBooking2.id();
        assertEquals(id+1, newBooking3.id());
    }

    @Test
    public void testSaveData(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        boolean result = BS.saveData();
        assertTrue(result);
    }
    @Test
    public void testLoadData(){
        Flight flight = new Flight();
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        Booking newBooking =  BS.createNewBooking(flight, "Nina", "Smith", user);
        Integer id = newBooking.id();
        boolean result = BS.saveData();
        assertTrue(result);

        BookingDao dao2 = new BookingDao();
        BookingService BS2 = new BookingService(dao2);
        BS2.loadData();

        Map<Integer, Booking> resultBS = BS.getAllBookings();
        assertEquals(1, resultBS.size());
        assertTrue(resultBS.containsKey(id));
        assertEquals(newBooking, resultBS.get(id));
    }
    }


