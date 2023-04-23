import dev.flight_app.entities.Flight;
import dev.flight_app.services.BookingService;
import dev.flight_app.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {
    private FlightService FS;
    Flight flight;
    @BeforeEach
    void setUp(){
        FS = new FlightService();
        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testGetAllBookings(){
        List<Flight> result = FS.getAll();
        assertTrue(result.isEmpty());
        assertNotNull(result);

        FS.flightDao.getAll().put(1, flight);

        result = FS.getAll();
        assertEquals(1, result.size());
        assertEquals(flight, result.get(0));
    }
    @Test
    public void testSelectById() {
        FS.flightDao.getAll().put(1, flight);

        Optional<Flight> selectedFlight = FS.selectById(1);
        assertTrue(selectedFlight.isPresent());
        assertEquals(flight, selectedFlight.get());

        selectedFlight = FS.selectById(2);
        assertFalse(selectedFlight.isPresent());
    }
    @Test
    public void testSelectByFlightCode(){

        Flight flight2 = new Flight("code1", RYANAIR, 100, KYIV, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        FS.flightDao.getAll().put(flight.id(), flight);
        FS.flightDao.getAll().put(flight2.id(), flight2);

        List<Flight> selectedFlights = FS.selectByFlightCode("code");
        assertEquals(1, selectedFlights.size());
        assertEquals(flight, selectedFlights.get(0));

        selectedFlights = FS.selectByFlightCode("code1");
        assertEquals(1, selectedFlights.size());
        assertEquals(flight2, selectedFlights.get(0));

        selectedFlights = FS.selectByFlightCode("code3");
        assertEquals(0, selectedFlights.size());
    }
    @Test
    public void testSelectByDepartureCity(){
        Flight flight2 = new Flight("code1", RYANAIR, 100, KYIV, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        FS.flightDao.getAll().put(1, flight2);
        FS.flightDao.getAll().put(2, flight);

        // select by departure city
        List<Flight> selectedFlights = FS.selectByDepartureCity(BERN);
        assertEquals(1, selectedFlights.size());
        assertEquals(flight, selectedFlights.get(0));

        selectedFlights = FS.selectByDepartureCity(KYIV);
        assertEquals(1, selectedFlights.size());
        assertEquals(flight2, selectedFlights.get(0));

        selectedFlights = FS.selectByDepartureCity(LONDON);
        assertEquals(0, selectedFlights.size());

    }
    @Test
    public void testSelectByArrivalCity(){
        Flight flight2 = new Flight("code1", RYANAIR, 100, KYIV, TALLINN, LocalDateTime.now(), LocalDateTime.now());
        FS.flightDao.getAll().put(flight2.id(), flight2);
        FS.flightDao.getAll().put(flight.id(), flight);

        List<Flight> selectedFlights = FS.selectByArrivalCity(BRATISLAVA);
        assertEquals(1, selectedFlights.size());
        assertEquals(flight, selectedFlights.get(0));

        selectedFlights = FS.selectByArrivalCity(TALLINN);
        assertEquals(1, selectedFlights.size());
        assertEquals(flight2, selectedFlights.get(0));

        selectedFlights = FS.selectByArrivalCity(LONDON);
        assertEquals(0, selectedFlights.size());
    }
    @Test
    public void testSelectByDepartureDate(){
        FS.flightDao.getAll().put(flight.id(), flight);
        List<Flight> selectedFlights = FS.selectByDepartureDate(LocalDate.now());
        assertEquals(1, selectedFlights.size());
        assertEquals(flight, selectedFlights.get(0));
    }

    @Test
    public void testSaveData(){
        FS.flightDao.getAll().put(flight.id(), flight);
        boolean result = FS.saveData();
        assertTrue(result);
    }
    @Test
    public void testLoadData(){
        FS.flightDao.getAll().put(flight.id(), flight);
        Integer id = flight.id();
        boolean result = FS.saveData();
        assertTrue(result);


        BookingService FS2 = new BookingService();
        FS2.loadData();

        Map<Integer, Flight> resultFS = FS.flightDao.getAll();
        assertEquals(1, resultFS.size());
        assertTrue(resultFS.containsKey(id));
        assertEquals(flight, resultFS.get(id));
    }
}
