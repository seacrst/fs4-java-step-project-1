package dev.flight_app.Dao;

import dev.flight_app.Database.BookingDB;
import dev.flight_app.entity.Booking;

import java.util.HashMap;
import java.util.Map;
public class BookingDao implements DAO<Integer, Booking>{
    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final BookingDB BDB = BookingDB.getBDB();
    @Override
    public Map<Integer, Booking> getAll() {
        return bookings;
    }

    @Override
    public Booking getById(Integer id) {
        return bookings.get(id);
    }

    @Override
    public boolean delete(Integer id) {
        Booking deleteBooking = bookings.remove(id);
        return deleteBooking != null;
    }
    @Override
    public void save(Booking booking) {
        bookings.put(booking.id(), booking);
    }

    @Override
    public void load() {
        bookings.putAll(BDB.read());
    }

    @Override
    public boolean saveToFile() {
        return BDB.write(bookings);
    }
}
