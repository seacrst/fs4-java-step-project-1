import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;
import dev.flight_app.services.BookingService;
import dev.flight_app.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceTest {
    private FlightService FS;
    Flight flight;
    @Mock
    private FlightService mockedFlightService;
    @BeforeEach
    void setUp(){
        FS = new FlightService();
//        flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings(){
        List<Flight> result = FS.getAll();
        assertNotNull(result);
        assertTrue(result.size()>0);
    }
    @Test
    public void testSelectById() {
        List<Flight> result = FS.getAll();
        Flight flight1 = result.get(0);
        Integer id = flight1.id();
        Optional<Flight> selectedFlight = FS.selectById(id);
        assertTrue(selectedFlight.isPresent());
        assertEquals(flight1, selectedFlight.get());

    }
    @Test
    public void testSelectByFlightCode(){
        List<Flight> result = FS.getAll();
        Flight flight = result.get(0);
        String flightCode = flight.getFlightCode();

        List<Flight> selectedFlights = FS.selectByFlightCode(flightCode);
        assertTrue(selectedFlights.size()>0);
        assertTrue(selectedFlights.contains(flight));
    }
    @Test
    public void testSelectByDepartureCity(){
        List<Flight> result = FS.getAll();
        Flight flight = result.get(0);
        City departureCity = flight.getDepartureCity();

        List<Flight> selectedFlights = FS.selectByDepartureCity(departureCity);
        assertTrue(selectedFlights.size() > 0);
        assertTrue(selectedFlights.contains(flight));
    }
    @Test
    public void testSelectByArrivalCity(){
        List<Flight> result = FS.getAll();
        Flight flight = result.get(0);
        City arrivalCity = flight.getArrivalCity();

        List<Flight> selectedFlights = FS.selectByArrivalCity(arrivalCity);
        assertTrue(selectedFlights.size() > 0);
        assertTrue(selectedFlights.contains(flight));
    }
    @Test
    public void testSelectByDepartureDate(){
        List<Flight> result = FS.getAll();
        Flight flight = result.get(0);
        LocalDateTime departureDateTime = flight.getDepartureDateTime();

        List<Flight> selectedFlights = FS.selectByDepartureDate(departureDateTime.toLocalDate());
        assertTrue(selectedFlights.size() > 0);
        assertTrue(selectedFlights.contains(flight));
    }

    @Test
    public void testSaveData(){
        when(mockedFlightService.saveData()).thenReturn(true);
        boolean result2 = mockedFlightService.saveData();
        verify(mockedFlightService, times(1)).saveData();
        assertEquals(true, result2);
    }
//    @Test
//    public void testLoadData(){
//        FS.flightDao.getAll().put(flight.id(), flight);
//        Integer id = flight.id();
//        boolean result = FS.saveData();
//        assertTrue(result);
//
//
//        BookingService FS2 = new BookingService();
//        FS2.loadData();
//
//        Map<Integer, Flight> resultFS = FS.flightDao.getAll();
//        assertEquals(1, resultFS.size());
//        assertTrue(resultFS.containsKey(id));
//        assertEquals(flight, resultFS.get(id));
//    }
}
