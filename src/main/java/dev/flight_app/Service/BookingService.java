package dev.flight_app.Service;

import dev.flight_app.Dao.BookingDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;
import dev.flight_app.entity.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDao bookingDao;
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
    public List<Map.Entry<Integer, Booking>> myFlights(String name, String surname, User user){
        Passenger passenger = new Passenger(name, surname);
        return bookingDao.getAll()
                .entrySet()
                .stream()
                .filter( b -> b.getValue().getPassenger().equals(passenger) || b.getValue().getUser().equals(user))
                .collect(Collectors.toList());
    }
    public List<Map.Entry<Integer, Booking>> myBookings(User user){
        return bookingDao.getAll()
                .entrySet()
                .stream()
                .filter( b -> b.getValue().getUser().equals(user))
                .collect(Collectors.toList());
    }

    public Booking createNewBooking(Flight flight, String name, String surname, User user){
        Passenger newPassenger = new Passenger(name, surname);
        Booking newBooking = new Booking(getNextId(), flight, newPassenger, user);
        user.addBookings(newBooking);
        bookingDao.save(newBooking);
        return newBooking;
    }

    public boolean cancelBooking(Integer id){
        return bookingDao.delete(id);
    }
    private Integer getNextId(){
        return bookingDao.getAll()
                .keySet()
                .stream()
                .max(Integer::compare).orElse(0)+1;
    }
    public void loadData(){
        bookingDao.load();
    }
    public boolean saveData() {
        return bookingDao.saveToFile();
    }
}
