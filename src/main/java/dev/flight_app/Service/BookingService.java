package dev.flight_app.Service;

import dev.flight_app.Dao.BookingDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.Flight;
import dev.flight_app.entity.Passenger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDao bookingDao;
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
    public Map<Integer, Booking> getAllBookings(){
        return bookingDao.getAll();
    }

    public void displayAllBookings(){
        getAllBookings().values()
                .stream()
                .forEach(System.out::println);
    }
    public List<Map.Entry<Integer, Booking>> searchBookings(Passenger passenger){
        return bookingDao.getAll()
                .entrySet()
                .stream()
                .filter( b -> b.getValue().getPassengers().contains(passenger))
                .collect(Collectors.toList());
    }

    public Booking createNewBooking(Flight flight, List<Passenger> passenger){
        Booking newBooking = new Booking(getNextId(), flight, passenger);
        bookingDao.save(newBooking);
        return newBooking;
    }

    public boolean cancelBooking(Integer id){
        return bookingDao.delete(id);
    }
    public Integer getNextId(){
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
