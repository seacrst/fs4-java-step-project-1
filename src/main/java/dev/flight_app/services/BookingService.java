package dev.flight_app.services;

import dev.flight_app.Console;
import dev.flight_app.DuplicateBookingException;
import dev.flight_app.dao.BookingDao;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import dev.flight_app.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDao bookingDao;
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
    public Map<Integer, Booking> getAllBookings() {
        return bookingDao.getAll();
    }
    public Optional<Booking> getBookingById(Integer id) {
        return bookingDao.getById(id);
    }
    public Booking createNewBooking(Flight flight, List<Passenger> passengers, User user){
        Booking newBooking = new Booking(bookingDao.generateId(), flight, passengers, user);
        checkForDuplicateBooking(newBooking);
        user.addBookings(newBooking);
        bookingDao.save(newBooking);
        return newBooking;
    }
    public Booking createNewBooking(Flight flight, List<Passenger> passengers){
        Booking newBooking = new Booking(bookingDao.generateId(), flight, passengers);
        checkForDuplicateBooking(newBooking);
        bookingDao.save(newBooking);
        return newBooking;
    }
    private void checkForDuplicateBooking(Booking newBooking) {
        if(getAllBookings().values().stream().anyMatch(b -> b.equals(newBooking))){
            throw new DuplicateBookingException("The booking you are trying to make already exists. Please choose a different date/time or cancel the existing booking before making a new one.");
        };
        if(getAllBookings().values().stream().anyMatch(b -> b.similarPassengerOnFlight(newBooking))){
            throw new DuplicateBookingException("Booking is already exists for one of the passenger. Please choose a different date/time or cancel the existing booking before making a new one.");
        };
    }
    public List<Booking> myFlights(String name, String surname){
        List<Booking> result = bookingDao.getAll()
                .values()
                .stream()
                .filter(b -> b.getPassenger().stream()
                        .anyMatch(e -> e.getFirstName().equals(name) &&
                                e.getLastName().equals(surname)))
                .collect(Collectors.toList());
        result.stream().forEach(x -> System.out.println(x.toString()));
        return result;
    }
    public List<Booking> myFlights(User user){
        List<Booking> result = bookingDao.getAll()
                .values()
                .stream()
                .filter( b -> b.getUser().equals(user))
                .collect(Collectors.toList());
        result.stream().forEach(x -> System.out.println((result.indexOf(x)+1) + ": "+ x.toString()));
        return result;
    }
    public boolean cancelBooking(Integer id){
        if (!bookingDao.delete(id)){
            Console.println("Such booking don't exist.");
            return false;
        }
        return true;
    }
    public void loadData(){
        bookingDao.load();
    }
    public boolean saveData() {
        return bookingDao.saveToFile();
    }
}
