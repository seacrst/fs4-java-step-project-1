package dev.flight_app.entities;

import dev.flight_app.dao.Identifiable;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable, Identifiable<Integer> {
    private static final long serialVersionUID = 1L;
    private final Integer id;
    private final Flight flight;
    private final List<Passenger> passenger;
    private final User user;
    public Booking(Integer id, Flight flight, List<Passenger> passenger, User user) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        this.user = user;
    }
    @Override
    public String toString() {
        return "Booking ID: " + id +
                "\nFlight: " + flight.toString() +
                "\nPassengers:" + passenger.toString()+
                "\n"
                ;
    }
    private String passengerPrettyFormat(){
        return passenger.stream()
                .map(passenger -> String.format("%d: %s", this.passenger.indexOf(passenger)+1, passenger.toString()))
                .toList().toString();
    }

    public Integer getId() {
        return id;
    }
    public User getUser(){
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }
    public void addPassenger(Passenger P){
        passenger.add(P);
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
        return id == booking.id && Objects.equals(flight, booking.flight) && Objects.equals(passenger, booking.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passenger);
    }
}
