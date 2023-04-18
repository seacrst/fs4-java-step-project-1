package dev.flight_app.Controller;

import dev.flight_app.Service.BookingService;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;

import java.util.List;
import java.util.Map;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public Map<Integer, Booking>  getAllBookings() {
        return bookingService.getAllBookings();
    }
    public void displayAllBookings() {
        bookingService.displayAllBookings();
    }
    public List<Map.Entry<Integer, Booking>> getBookings(Passenger passenger){
        return bookingService.searchBookings(passenger);
}
    public Booking createNewBooking(Flight flight, List<Passenger> passenger) {
        return bookingService.createNewBooking(flight, passenger);
    }
    public boolean cancelBooking(int id) {
        return bookingService.cancelBooking(id);
    }
    public int getNextId(){
        return bookingService.getNextId();
    }
    public void loadData() {
        bookingService.loadData();
    }
    public boolean saveData() {
        return bookingService.saveData();
    }
}
