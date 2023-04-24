package dev.flight_app.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

import dev.flight_app.common.Validation;
import dev.flight_app.controllers.*;
import dev.flight_app.entities.*;

public class EventService {
    private final FlightController flight;
    private final BookingController booking;
    public EventService(FlightController flight, BookingController booking) {
        this.flight = flight;
        this.booking = booking;
    }

    public void displayAllFlights() {
        flight.allFlights().forEach(Console::output);
    }

    public List<Flight> selectFlight(FlightDataCollector flightData) {
        return flight.selectByCityDateSeats(flightData.getDestination(), flightData.getDepartureDate(), flightData.getSeatsAmount());
    }

    public Booking createBooking(int seats, Flight flt) {
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i <= seats; i ++) {
            Passenger p = new Passenger(
                    enterPassengerName(String.format("Enter name of passenger #%d: ", i)),
                    enterPassengerName(String.format("Enter surname of passenger #%d: ", i))
            );
            passengers.add(p);
        }

        return booking.createNewBooking(flt, passengers);
    }

    public String enterPassengerName(String prompt) {
        String name = Event.readLine(prompt);
        if(Validation.isValidName(name)){
            return name;
        } else{
            return enterPassengerName("Incorrect name format. Try again: ");
        }

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
//        enterBookingId(id)
    }


    public void enterCity(FlightDataCollector flightData, String prompt) {
        String city = Event.readLine(prompt);
        if (!Validation.validateCity(city)) {
            enterCity(flightData, prompt);
        } else {
            flightData.setDestination(city);
        }
    }

    public void enterDate(FlightDataCollector flightData, String prompt) {
        String date = Event.readLine(prompt);
        if (!Validation.validateDate(date)) {
            enterDate(flightData, prompt);
        } else {
            flightData.setDepartureDate(date);
        }
    }

    public void enterSeats(FlightDataCollector flightData, String prompt) {
        String seats = Event.readLine(prompt);
        if (!Validation.validateSeatsQuantity(seats)) {
            enterSeats(flightData, prompt);
        } else {
            flightData.setSeatsAmount(seats);
        }
    }

    public String enterIndexOfFlight(String prompt, int size, Function<Void, Void> fallback) {
        String idx = Event.readLine(prompt);
        if (Validation.isValidIndex(idx, size)) {
            if (idx.equals("0")) {
                fallback.apply(null);
            }
            return idx;
        } else {
            return enterIndexOfFlight("Try again or 0 to exit: ", size, fallback);
        }
    }

    public boolean enterBookingId(String prompt) {
        String id = Event.readLine(prompt);
        if (!Validation.validateId(id)) {
            enterBookingId(prompt);
            return false;
        } else {
            return booking.cancelBooking(Integer.parseInt(id));
        }
    }

    public void saveData() {
        flight.saveData();
        booking.saveData();
    }
}
