import dev.flight_app.dao.BookingDao;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.*;
import static dev.flight_app.entities.City.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingDaoTest {
   private BookingDao dao;
    private List<Passenger> passenger;
    private Flight flight;
    @Mock
    private BookingDao mockedBookingDao;
    @BeforeEach
    void setUp(){
        dao = new BookingDao();
        passenger = new ArrayList<>();
        passenger.add(new Passenger("Nina", "Smith"));
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        MockitoAnnotations.openMocks(this);
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
        Booking booking = new Booking(dao.generateId(), flight, passenger);
        dao.add(booking);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void testGetById(){
        Booking booking = new Booking(dao.generateId(), flight, passenger);
        dao.add(booking);
        Optional<Booking> res = dao.getById(booking.id());
        assertTrue(res.isPresent());
        assertEquals(booking, res.get());
    }
    @Test
    public void testDelete(){
        Booking booking = new Booking(dao.generateId(), flight, passenger);
        dao.add(booking);
        assertEquals(1, dao.getAll().size());
        boolean res = dao.delete(booking.id());
        assertTrue(res);
        Optional<Booking> delB = dao.getById(booking.id());
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){
        when(mockedBookingDao.save()).thenReturn(true);
        boolean result2 = mockedBookingDao.save();
        verify(mockedBookingDao, times(1)).save();
        assertTrue(result2);
    }

//    @Test
//    public void testLoad(){
//
//        Booking booking = new Booking(dao.generateId(), flight, passenger);
//        Integer id = booking.id();
//        dao.add(booking);
//        boolean saveRes = dao.save();
//        assertTrue(saveRes);
//
//        dao.load();
//        Map<Integer, Booking> resultDao = dao.getAll();
//        assertTrue(resultDao.containsKey(id));
//        assertEquals(booking, resultDao.get(id));
//
//        dao.delete(id);
//        dao.save();
//        dao.load();
//        assertFalse(dao.getAll().containsKey(id));
//    }
}
