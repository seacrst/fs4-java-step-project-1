package dev.flight_app.controllers;

import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.Flight;
import dev.flight_app.services.FlightService;

import java.util.List;
import java.util.Objects;

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

    public List<Flight> selectById(int id) {
        return flightService.selectById(id);
    }

    public List<Flight> selectByFlightCode(String flightCode) {
        return flightService.selectByFlightCode(flightCode);
    }



    public List<Flight> allFlights() {
        return flightService.getAll();
    }

    public void displayAllFlights() {
        System.out.println(allFlights());
    }

    public void loadData() {
        flightService.loadData();
    }

    public boolean saveData() {
        return flightService.saveData();
    }

    // метод принимает место отправления, место назначения, дата вылета, количество человек (сколько необходимо купить билетов). Возвращает список рейсов удовлетворяющих условиям.


}
