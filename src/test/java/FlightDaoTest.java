import dev.flight_app.dao.BookingDao;
import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.BERN;
import static dev.flight_app.entities.City.BRATISLAVA;
import static org.junit.jupiter.api.Assertions.*;

public class FlightDaoTest {
    private FlightDao dao;
    private Flight flight;

    @BeforeEach
    void setUp(){
        dao = new FlightDao();
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
    }
    @Test
    public void testGetAll(){
        Map<Integer, Flight> result = dao.getAll();
        assertTrue(result.isEmpty());
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetById(){
        dao.getAll().put(flight.id(), flight);
        int id = flight.id();
        Optional<Flight> res = dao.getById(id);
        assertTrue(res.isPresent());
        assertEquals(flight, res.get());
    }
    @Test
    public void testDelete(){
         dao.getAll().put(flight.id(), flight);
        int id = flight.id();
        assertEquals(1, dao.getAll().size());
        boolean res = dao.delete(id);
        assertTrue(res);
        Optional<Flight> delB = dao.getById(id);
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){
        dao.getAll().put(flight.id(), flight);
        boolean result = dao.save();
        assertTrue(result);
        assertEquals(flight, dao.getAll().get(flight.id()));
    }

    @Test
    public void testLoad(){
        dao.getAll().put(flight.id(), flight);
        boolean saveRes = dao.save();
        assertTrue(saveRes);
        int id = flight.id();
        BookingDao dao2 = new BookingDao();
        dao2.load();
        Map<Integer, Flight> resultDao = dao.getAll();
        assertEquals(1, resultDao.size());
        assertTrue(resultDao.containsKey(id));
        assertEquals(flight, resultDao.get(id));
    }
}
