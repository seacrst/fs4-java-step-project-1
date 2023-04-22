package dev.flight_app.services;

import dev.flight_app.controllers.BookingController;
import dev.flight_app.controllers.MenuController;
import dev.flight_app.controllers.UserController;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Console;
import dev.flight_app.controllers.FlightController;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import dev.flight_app.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String id = Event.readLine();
        Optional<Flight> flt = findFlightById(id);
        List<Passenger> passengers = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        for (int i = 1; i <= Integer.parseInt(seats); i ++) {
            passengers.add(Event.collectPassengersData(
                    Event::print, String.format("Enter name of passenger: %s", i),
                    String.format("Enter surname of passenger: %s", i)
            ));
        }

        flt.ifPresent(f -> {
            Booking bkg = booking.createNewBooking(f, passengers);

            Console.output(bkg);
        });
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
