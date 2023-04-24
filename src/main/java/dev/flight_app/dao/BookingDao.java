package dev.flight_app.dao;

import dev.flight_app.database.BookingDB;
import dev.flight_app.entities.Booking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookingDao implements DAO<Integer, Booking>{
    private Map<Integer, Booking> bookings = new HashMap<>();
    private final BookingDB BDB = BookingDB.getBDB();
    public BookingDao(){
        this.bookings = BDB.read();
    }
    @Override
    public Map<Integer, Booking> getAll() {
        return bookings;
    }

    @Override
    public Optional<Booking> getById(Integer id) {
        return Optional.ofNullable(bookings.get(id));
    }

    @Override
    public boolean delete(Integer id) {
        return Optional.ofNullable(bookings.remove(id)).isPresent();
    }

    public void add(Booking booking) {
        bookings.put(booking.id(), booking);
    }

    @Override
    public boolean save() {
        return BDB.write(bookings);
    }

    public Integer generateId(){
        return bookings
                .keySet()
                .stream()
                .max(Integer::compare).orElse(0)+1;
    }
}
