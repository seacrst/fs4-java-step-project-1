import dev.flight_app.controllers.FlightController;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;
import dev.flight_app.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightControllerTest {

    private FlightController FC;
    @Mock
    private FlightController mockedFlightController;

    @BeforeEach
    void setUp() {
        FC = new FlightController();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllFlight() {
        List<Flight> flights = FC.allFlights();
        assertTrue(flights.size() > 0);
        Flight flight = flights.get(0);
        assertEquals(City.KYIV, flight.getDepartureCity());
    }

    @Test
    void testSelectById() {
        List<Flight> flights = FC.allFlights();
        Flight flight = flights.get(0);
        String id = flight.id().toString();
        Optional<Flight> flight1 = FC.selectById(id);
        assertTrue(flight1.isPresent());
    }

    @Test
    void testSelectByCityDateSeats() {
        List<Flight> flights = FC.allFlights();
        Flight flight = flights.get(0);
        City arrivalCity = flight.getArrivalCity();

        int seatsQuantity = flight.getSeatsQuantity();
        LocalDateTime departureDateTime = flight.getDepartureDateTime();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data = departureDateTime.format(dateTimeFormatter);
        List<Flight> selectors = FC.selectByCityDateSeats(arrivalCity.toString(), data, Integer.toString(seatsQuantity));

        assertEquals(arrivalCity, selectors.get(0).getArrivalCity());
        assertTrue(selectors.get(0).getSeatsQuantity() >= seatsQuantity);
        assertEquals(departureDateTime.toLocalDate(), selectors.get(0).getDepartureDateTime().toLocalDate());
        assertTrue(selectors.contains(flight));


        List<Flight> checkseats = FC.selectByCityDateSeats(arrivalCity.toString(), data, "401");
        assertEquals(0, checkseats.size());

        LocalDate d2 = LocalDate.now().minusDays(1);
        String data2 = d2.format(dateTimeFormatter);
        List<Flight> checkData = FC.selectByCityDateSeats(arrivalCity.toString(), data2, "1");
        assertEquals(0, checkData.size());

    }

}

