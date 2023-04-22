package dev.flight_app.entities;

import dev.flight_app.dao.Identifiable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight implements Serializable, Identifiable<Integer> {
    private static final long flightUID = 1_0L;
    private static int flightCounter = 1;
    private final int flightID;
    private final String flightCode;
    private final Airline airline;
    private int seatsQuantity;
    private final City departureCity;
    private final City arrivalCity;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;

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
    }

    public Flight(int flightID,
                  String flightCode,
                  Airline airline,
                  int seatsQuantity,
                  City departureCity,
                  City arrivalCity,
                  LocalDateTime departureDateTime,
                  LocalDateTime arrivalDateTime) {
        this.flightID = flightID;
        this.flightCode = flightCode;
        this.airline = airline;
        this.seatsQuantity = seatsQuantity;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
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


    public boolean addPassenger(int bookingSeats) {
        int seatsQuantityOld = seatsQuantity;
        seatsQuantity -= bookingSeats;
        return seatsQuantity + bookingSeats == seatsQuantityOld;
    }

    public boolean removePassenger(int bookingSeats) {
        int seatsQuantityOld = seatsQuantity;
        seatsQuantity += bookingSeats;
        return seatsQuantity - bookingSeats == seatsQuantityOld;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %s | %s |\u001B[34m %-11s \u001B[0m---\u001B[33m %11s \u001B[0m| %s | %3d | %-16s",
                flightID,
                flightCode,
                departureDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                departureCity,
                arrivalCity,
                arrivalDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                seatsQuantity,
                airline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return flightCode.equals(flight.flightCode);
    }

    @Override
    public int hashCode() {
        return (id()+flightCode).hashCode();
    }

    @Override
    public Integer id() {
        return flightID;
    }

}

