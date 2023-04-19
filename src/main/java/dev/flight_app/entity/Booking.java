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
    private final Passenger passenger;
    private final User user;
    public Booking(Integer id, Flight flight, Passenger passenger, User user) {
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
//    private String passengerPrettyFormat(){
//        return passenger.stream()
//                .map(passenger -> String.format("%d: %s", this.passenger.indexOf(passenger)+1, passenger.toString()))
//                .toList().toString();
//    }

    public Integer getId() {
        return id;
    }
    public User getUser(){
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
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
