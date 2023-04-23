import dev.flight_app.dao.BookingDao;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.*;
import static dev.flight_app.entities.City.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingDaoTest {
   private BookingDao dao;
    private List<Passenger> passenger;
    private Flight flight;

    @BeforeEach
    void setUp(){
        dao = new BookingDao();
        passenger = new ArrayList<>();
        passenger.add(new Passenger("Nina", "Smith"));
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
    }
    @Test
    public void testGetAll(){
        Map<Integer, Booking> result = dao.getAll();
        assertTrue(result.isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testSave(){
        assertEquals(0, dao.getAll().size());
        Booking booking = new Booking(1, flight, passenger);
        dao.add(booking);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void testGetById(){
        Booking booking = new Booking(1, flight, passenger);
        dao.add(booking);
        Optional<Booking> res = dao.getById(1);
        assertTrue(res.isPresent());
        assertEquals(booking, res.get());
    }
    @Test
    public void testDelete(){
        Booking booking = new Booking(1, flight, passenger);
        dao.add(booking);
        assertEquals(1, dao.getAll().size());
        boolean res = dao.delete(1);
        assertTrue(res);
        Optional<Booking> delB = dao.getById(1);
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){
        Booking booking = new Booking(1, flight, passenger);
        dao.add(booking);
        boolean result = dao.save();
        assertTrue(result);
    }

    @Test
    public void testLoad(){
        Booking booking = new Booking(2, flight, passenger);
        dao.add(booking);
        boolean saveRes = dao.save();
        assertTrue(saveRes);

        BookingDao dao2 = new BookingDao();
        dao2.load();
        Map<Integer, Booking> resultDao = dao.getAll();
        assertEquals(1, resultDao.size());
        assertTrue(resultDao.containsKey(2));
        assertEquals(booking, resultDao.get(2));
    }
}
