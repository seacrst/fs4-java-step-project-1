import dev.flight_app.Dao.BookingDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDaoTest {
   private BookingDao dao;

    @BeforeEach
    void setUp(){
        dao = new BookingDao();
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
        Booking booking = new Booking(1, new Flight(), new Passenger("Kira", "Smith"), null);
        dao.save(booking);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void testGetById(){
        Booking booking = new Booking(1, new Flight(), new Passenger("Kira", "Smith"), null);
        dao.save(booking);
        Optional<Booking> res = dao.getById(1);
        assertTrue(res.isPresent());
        assertEquals(booking, res.get());
    }
    @Test
    public void testDelete(){
        Booking booking = new Booking(1, new Flight(), new Passenger("Kira", "Smith"), null);
        dao.save(booking);
        assertEquals(1, dao.getAll().size());
        boolean res = dao.delete(1);
        assertTrue(res);
        Optional<Booking> delB = dao.getById(1);
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){
        Booking booking = new Booking(1, new Flight(), new Passenger("Kira", "Smith"), null);
        dao.save(booking);
        boolean result = dao.saveToFile();
        assertTrue(result);
    }

    @Test
    public void testLoad(){
        Booking booking = new Booking(2, new Flight(), new Passenger("Kira", "Smith"), null);
        dao.save(booking);
        boolean saveRes = dao.saveToFile();
        assertTrue(saveRes);

        BookingDao dao2 = new BookingDao();
        dao2.load();
        Map<Integer, Booking> resultDao = dao.getAll();
        assertEquals(1, resultDao.size());
        assertTrue(resultDao.containsKey(2));
        assertEquals(booking, resultDao.get(2));
    }
}
