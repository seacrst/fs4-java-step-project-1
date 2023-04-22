package dev.flight_app.controllers;

import dev.flight_app.Validation;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Console;
import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.Flight;
import dev.flight_app.services.FlightService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FlightController {

    private static FlightController fc = null;
    private final FlightService flightService = new FlightService();

    //    private final FlightService flightService;
    private FlightDao flightDao;

//    public FlightController() {
//        this.flightDao = new FlightDao();
//        this.flightService = new FlightService();
//    }
//    public FlightController(FlightService flightService) {
//        this.flightService = flightService;
//    }

    private FlightController() {
    }

    public static FlightController create() {
        if (!Objects.isNull(fc)) {
            return fc;
        }
        fc = new FlightController();
        return fc;
    }

    public Optional<Flight> selectById(String idString) {
        int id = Validation.stringToFlightId(idString);
        return flightService.selectById(id);
    }

    public List<Flight> selectByFlightCode(String flightCode) {
        return flightService.selectByFlightCode(flightCode);
    }

    public List<Flight> selectByCityDateSeats(String cityString, String dateString, String seatsString) {
        City city = Validation.stringToCity(cityString);
        LocalDate date = Validation.stringToDate(dateString);
        int seatsQuantity = Validation.stringToSeatsQuantity(seatsString);
        List<Flight> byCityDeparture = flightService.selectByDepartureCity(City.KYIV);
        List<Flight> plusByCityArrival = flightService.selectByArrivalCity(byCityDeparture, city);
        List<Flight> plusByDate = flightService.selectByDepartureDate(plusByCityArrival, date);
        List<Flight> plusBySeatsQuantity = flightService.selectBySeatsQuantity(plusByDate, seatsQuantity);
        return plusBySeatsQuantity;
    }

    public List<Flight> selectByDepartureCity(String cityString) {
        City city = Validation.stringToCity(cityString);
        return flightService.selectByDepartureCity(city);
    }

    public List<Flight> selectByDepartureDate(String dateString) {
        LocalDate date = Validation.stringToDate(dateString);
        return flightService.selectByDepartureDate(date);
    }

    public List<Flight> selectBySeatsQuantity(String seatsString) {
        int seatsQuantity = Validation.stringToSeatsQuantity(seatsString);
        return flightService.selectBySeatsQuantity(seatsQuantity);
    }

    public List<Flight> allFlights() {
        return flightService.getAll();
    }

    public void loadData() {
        flightService.loadData();
    }

    public boolean saveData() {
        return flightService.saveData();
    }

    // метод принимает место отправления, место назначения, дата вылета, количество человек (сколько необходимо купить билетов). Возвращает список рейсов удовлетворяющих условиям.


}
