package dev.flight_app.services;

import dev.flight_app.Validation;
import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightService {

    public final FlightDao flightDao;

    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> getAll() {
        return flightDao.getAll()
                .values()
                .stream()
                .toList();
    }

    public List<Flight> selectByDepartureCity(City departureCity) {
        return getAll().stream()
                .filter(x -> x.getDepartureCity().equals(departureCity))
                .collect(Collectors.toList());
    }

    public List<Flight> selectByArrivalCity(City arrivalCity) {
        return getAll().stream()
                .filter(x -> x.getArrivalCity().equals(arrivalCity))
                .collect(Collectors.toList());
    }

    public List<Flight> selectByDepartureDate(String departureDate) {
        return getAll().stream()
                .filter(x -> x.getDepartureDateTime().toLocalDate().equals(Validation.stringToDate(departureDate)))
                .collect(Collectors.toList());
    }

    public List<Flight> selectBySeatsQuantity(int seatsQuantity) {
        return getAll().stream()
                .filter(x -> x.getSeatsQuantity() >= seatsQuantity)
                .collect(Collectors.toList());
    }


}
