package dev.flight_app.controllers;

import dev.flight_app.services.FlightService;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // метод принимает место отправления, место назначения, дата вылета, количество человек (сколько необходимо купить билетов). Возвращает список рейсов удовлетворяющих условиям.



}
