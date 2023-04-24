package dev.flight_app.controllers;

import dev.flight_app.common.Validation;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;
import dev.flight_app.services.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightController {
    public final FlightService flightService = new FlightService();

    public FlightController() {
    }

    public Optional<Flight> selectById(String idString) {
        int id = Validation.stringToFlightId(idString);
        return flightService.selectById(id);
    }

    public List<Flight> selectByCityDateSeats(String cityString, String dateString, String seatsString) {
        City city = Validation.stringToCity(cityString);
        LocalDate date = Validation.stringToDate(dateString);
        int seatsQuantity = Validation.stringToSeatsQuantity(seatsString);
        List<Flight> byCityDeparture = flightService.selectByDepartureCity(City.KYIV);
        List<Flight> plusByCityArrival = flightService.selectByArrivalCity(byCityDeparture, city);
        List<Flight> plusByDate = flightService.selectByDepartureDate(plusByCityArrival, date);
        return  flightService.selectBySeatsQuantity(plusByDate, seatsQuantity);
    }

    public List<Flight> allFlights() {
        List<Flight> byCityDeparture = flightService.selectByDepartureCity(City.KYIV);
        return flightService.selectByDepartureDateTime(byCityDeparture, LocalDateTime.now().plusHours(24));
    }
    public void saveData() {
        flightService.saveData();
    }

}
