package dev.flight_app.services;

import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {

    private final FlightDao flightDao = new FlightDao();

    public FlightService() {
    }

    public List<Flight> getAll() {
        return flightDao.getAll()
                .values()
                .stream()
                .toList();
    }

    public Optional<Flight> selectById(int id) {
        return flightDao.getById(id);
    }

    public List<Flight> selectByFlightCode(String flightCode) {
        return getAll().stream()
                .filter(x -> x.getFlightCode().equals(flightCode))
                .collect(Collectors.toList());
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

    public List<Flight> selectByArrivalCity(List<Flight> flights, City arrivalCity) {
        return flights.stream()
                .filter(x -> x.getArrivalCity().equals(arrivalCity))
                .collect(Collectors.toList());
    }

    public List<Flight> selectByDepartureDate(LocalDate departureDate) {
        return getAll().stream()
                .filter(x -> x.getDepartureDateTime().toLocalDate().equals(departureDate))
                .collect(Collectors.toList());
    }

    public List<Flight> selectByDepartureDate(List<Flight> flights, LocalDate departureDate) {
        return flights.stream()
                .filter(x -> x.getDepartureDateTime().toLocalDate().equals(departureDate))
                .collect(Collectors.toList());
    }

    public List<Flight> selectBySeatsQuantity(int seatsQuantity) {
        return getAll().stream()
                .filter(x -> x.getSeatsQuantity() >= seatsQuantity)
                .collect(Collectors.toList());
    }

    public List<Flight> selectBySeatsQuantity(List<Flight> flights, int seatsQuantity) {
        return flights.stream()
                .filter(x -> x.getSeatsQuantity() >= seatsQuantity)
                .collect(Collectors.toList());
    }

    public List<Flight> selectByDepartureDateTime(List<Flight> flights, LocalDateTime localDateTime) {
        return flights.stream()
                .filter(x -> x.getDepartureDateTime().isBefore(localDateTime))
                .collect(Collectors.toList());
    }

    public boolean saveData() {
        return flightDao.save();
    }

}


