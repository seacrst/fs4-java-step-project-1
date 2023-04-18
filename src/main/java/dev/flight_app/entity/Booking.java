package dev.flight_app.entity;

import dev.flight_app.Dao.Identifiable;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable, Identifiable<Integer> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Integer id;
    private final Flight flight;
    private final List<Passenger> passengers;
    public Booking(Integer id, Flight flight, List<Passenger> passenger) {
        this.id = id;
        this.flight = flight;
        this.passengers = passenger;}
    @Override
    public String toString() {
        return "Booking ID: " + id +
                "\nFlight: " + flight.toString() +
                "\nPassengers:" + passengerPrettyFormat()+
                "\n"
                ;
    }
    private String passengerPrettyFormat(){
        return passengers.stream()
                .map(passenger -> String.format("%d: %s", passengers.indexOf(passenger)+1, passenger.toString()))
                .toList().toString();
    }

    public Integer getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
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
        return id == booking.id && Objects.equals(flight, booking.flight) && Objects.equals(passengers, booking.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passengers);
    }
}
