package dev.flight_app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import dev.flight_app.controllers.*;
import dev.flight_app.entities.*;

public class EventService {
    private final FlightController flight;
    private final BookingController booking;
    private final UserController user;
    public EventService(FlightController flight, BookingController booking, UserController user) {
        this.flight = flight;
        this.booking = booking;
        this.user = user;
    }

    public void displayAllFlights() {
        flight.allFlights().forEach(Console::output);
    }

    public List<Flight> selectFlight(FlightDataCollector flightData) {
        return flight.selectByCityDateSeats(flightData.getDestination(), flightData.getDepartureDate(), flightData.getSeatsAmount());
    }

    public void createBooking(int seats, Flight flt) {
        List<Passenger> passengers = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        for (int i = 1; i <= seats; i ++) {
            Passenger p = (Event.collectPassengersData(
                    Event::print, String.format("Enter name of passenger: %s", i),
                    String.format("Enter surname of passenger: %s", i)
            ));

            passengers.add(p);
        }

        booking.createNewBooking(flt, passengers);
    }

    public List<Booking> findBookingByPassengerData(ArrayList<String> passengerData) {
        String name = "";
        String surname = "";

        if (passengerData.size() == 2) {
            name = passengerData.get(0);
            surname = passengerData.get(1);
        }
        return booking.myFlights(name, surname);
    }

    public Optional<Flight> findFlightById(String id) {

        return flight.selectById(id);
    }

    public void cancelBooking(String id) {
        booking.cancelBooking(Integer.parseInt(id));
    }

    public void saveData() {
        flight.saveData();
        booking.saveData();
        user.saveData();
    }
}
