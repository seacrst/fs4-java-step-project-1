package dev.flight_app.services;

import dev.flight_app.controllers.BookingController;
import dev.flight_app.controllers.MenuController;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Console;
import dev.flight_app.controllers.FlightController;
import dev.flight_app.entities.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventService {

    private final FlightController flight = FlightController.create();
    private final BookingController booking = BookingController.create();
    private final MenuController menu = new MenuController();
    private String value = "";
    private static EventService instance = null;
    private EventService() {
    }

    public static EventService use() {
        if (instance != null) {
            return instance;
        }

        instance = new EventService();

        return  instance;
    }

    public String read() {return value;}

    public void displayAllFlights() {
        flight.allFlights().forEach(Console::output);
    }

    public void createBooking(ArrayList<String> bookingData) {
        String city = "";
        String date = "";
        String seats = "";

        if (bookingData.size() == 3) {
            city = bookingData.get(0);
            date = bookingData.get(1);
            seats = bookingData.get(2);
        }

        List<Flight> flights = flight.selectByCityDateSeats(city, date, seats);
        flights.forEach(Console::output);
        menu.toFlightSearchMenu();
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
        findFlightById(id);
    }
}
