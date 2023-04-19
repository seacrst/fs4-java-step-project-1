package dev.flight_app.Service;

import dev.flight_app.Dao.BookingDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;
import dev.flight_app.entity.User;

import java.util.ArrayList;
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
    public Booking createNewBooking(Flight flight,List<Passenger> passengers, User user){
        Booking newBooking = new Booking(bookingDao.generateId(), flight, passengers, user);
        user.addBookings(newBooking);
        bookingDao.save(newBooking);
        return newBooking;
    }
    public boolean addPassenger(Booking booking, String name, String surname){
        Optional<Booking> byId = bookingDao.getById(booking.id());
        if (byId.isEmpty()) return false;
        Passenger passenger = new Passenger(name, surname);
        byId.get().addPassenger(passenger);
        return true;
    }
    public List<Map.Entry<Integer, Booking>> myFlights(String name, String surname){
        Passenger passenger = new Passenger(name, surname);
        return bookingDao.getAll()
                .entrySet()
                .stream()
                .filter( b -> b.getValue().getPassenger().stream()
                        .anyMatch(e->e.getFirstName().equals(name) &&
                                e.getLastName().equals(surname)))
                .collect(Collectors.toList());
    }
    public List<Map.Entry<Integer, Booking>> myFlights(User user){
        return bookingDao.getAll()
                .entrySet()
                .stream()
                .filter( b -> b.getValue().getUser().equals(user))
                .collect(Collectors.toList());
    }
    public boolean cancelBooking(Integer id){
        return bookingDao.delete(id);
    }
    public void loadData(){
        bookingDao.load();
    }
    public boolean saveData() {
        return bookingDao.saveToFile();
    }
}
