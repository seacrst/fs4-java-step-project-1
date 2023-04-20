package dev.flight_app.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Serializable {
    private static final long flightUID = 1_0L;
    private static int flightCounter = 1;

    private final int flightID;
    private final String flightCode;
    private final Airline airline;
    private final int seatsQuantity;
    private final City departureCity;
    private final City arrivalCity;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
    private final List<Passenger> passengers;

    public Flight(String flightCode,
                  Airline airline,
                  int seatsQuantity,
                  City departureCity,
                  City arrivalCity,
                  LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime) {
        this.flightID = flightCounter++;
        this.flightCode = flightCode;
        this.airline = airline;
        this.seatsQuantity = seatsQuantity;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.passengers = new ArrayList<>();
    }

    public Flight(int flightID,
                  String flightCode,
                  Airline airline,
                  int seatsQuantity,
                  City departureCity,
                  City arrivalCity,
                  LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime,
                  List<Passenger> passengers) {
        this.flightID = flightID;
        this.flightCode = flightCode;
        this.airline = airline;
        this.seatsQuantity = seatsQuantity;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.passengers = passengers;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getSeatsQuantity() {
        return seatsQuantity;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %s | %s |  %s --- %s | %s | %-3d | %s |\n",
                flightID,
                flightCode,
                departureDateTime,
                departureCity,
                arrivalCity,
                arrivalDateTime,
                seatsQuantity,
                airline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        return flightCode.equals(flight.flightCode);
    }

    @Override
    public int hashCode() {
        return flightCode.hashCode();
    }
}

