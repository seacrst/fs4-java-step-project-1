package dev.flight_app.entities;

import dev.flight_app.dao.Identifiable;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Booking implements Serializable, Identifiable<Integer> {
    private static final long serialVersionUID = 1L;
    private final Integer id;
    private final Flight flight;
    private final List<Passenger> passenger;
    public Booking(Integer id, Flight flight, List<Passenger> passenger) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        flight.addPassenger(passenger.size());
    }
    @Override
    public String toString() {
        return "Booking ID: " + id +
                "\nFlight: " + flight.toString() +"\n"+ passengerPrettyFormat();
    }
    private String passengerPrettyFormat(){
        return IntStream.range(0, passenger.size())
                .mapToObj(i -> String.format("%12d. %s", i + 1, passenger.get(i).toString()))
                .collect(Collectors.joining(System.lineSeparator(), "Passengers:" + System.lineSeparator(), ""));
    }

    public Integer getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }
    public void cancelBooking(){
        flight.removePassenger(passenger.size());
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || !(that instanceof Booking)) return false;
        Booking booking = (Booking) that;
        return Objects.equals(flight, booking.flight) && Objects.equals(passenger, booking.passenger);
    }
    public boolean similarPassengerOnFlight(Object that) {
        Booking booking = (Booking) that;
        return Objects.equals(flight, booking.flight) && booking.getPassenger().stream().anyMatch(passenger -> getPassenger().stream().anyMatch(p-> p.equals(passenger)));
    }
    @Override
    public int hashCode() {
        return Objects.hash(flight, passenger);
    }
}
