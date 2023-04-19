package dev.flight_app.Controller;

import dev.flight_app.Service.BookingService;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;
import dev.flight_app.entity.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public List<Map.Entry<Integer, Booking>> myFlights(String name, String surname, User user){
        return bookingService.myFlights(name, surname, user);
}
    public List<Map.Entry<Integer, Booking>> myBookings(User user){
        return bookingService.myBookings( user);
    }
    public Booking createNewBooking(Flight flight, String name, String surname, User user) {
        return bookingService.createNewBooking(flight, name, surname, user);
    }
    public boolean cancelBooking(int id) {
        return bookingService.cancelBooking(id);
    }
    public void loadData() {
        bookingService.loadData();
    }
    public boolean saveData() {
        return bookingService.saveData();
    }
}
