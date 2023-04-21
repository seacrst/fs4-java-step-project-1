package dev.flight_app.services;

import dev.flight_app.Validation;
import dev.flight_app.dao.FlightDao;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import dev.flight_app.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public List<Flight> selectByUser(User user) {
        return selectByPassenger(new Passenger(user.getName(), user.getSurname()));
    }

    public List<Flight> selectByPassenger(Passenger passenger) {
        return getAll().stream()
                .filter(x -> x.getPassengers().size() > 0)
                .filter(y -> y.getPassengers().contains(passenger))
                .collect(Collectors.toList());
    }

    public void addPassengerOnboard(Flight flight, Passenger passenger) {
        flightDao.getById(flight.id()).ifPresent(value ->  value.addPassengerOnBoard(passenger));
    }


}
