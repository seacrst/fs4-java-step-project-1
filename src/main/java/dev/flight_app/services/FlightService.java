package dev.flight_app.services;

import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightService {

    public List<Flight> flights;

    public FlightService(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> selectByDepartureCity (City departureCity){
        return new ArrayList<>();
    }

    public List<Flight> selectByArrivalCity (City arrivalCity){
        return new ArrayList<>();
    }

    public List<Flight> selectByDepartureDate (String departureDate){
        return new ArrayList<>();
    }

    public List<Flight> selectBySeatsQuantity (int seatsQuantity){
        return new ArrayList<>();
    }


}
