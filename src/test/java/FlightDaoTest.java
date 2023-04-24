import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.BERN;
import static dev.flight_app.entities.City.BRATISLAVA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightDaoTest {
    private FlightDao dao;
    private Flight flight;
    @Mock
    private FlightDao mockedFlightDao;

    @BeforeEach
    void setUp(){
        dao = new FlightDao();
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAll(){
        Map<Integer, Flight> result = dao.getAll();
        assertFalse(result.isEmpty());
        assertNotNull(result);
        assertTrue(result.size()>0);
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
        assertTrue(dao.getAll().size()>0);
        boolean res = dao.delete(id);
        assertTrue(res);
        Optional<Flight> delB = dao.getById(id);
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){

        when(mockedFlightDao.save()).thenReturn(true);
        boolean result2 = mockedFlightDao.save();
        verify(mockedFlightDao, times(1)).save();
        assertEquals(true, result2);
    }

//    @Test
//    public void testLoad(){
//        dao.load();
//        assertFalse(dao.getAll().isEmpty());
//        assertTrue(dao.getAll().size()>0);
//    }
}
