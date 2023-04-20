package dev.flight_app.dao;

import dev.flight_app.database.BookingDB;
import dev.flight_app.entities.Booking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookingDao implements DAO<Integer, Booking>{
    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final BookingDB BDB = BookingDB.getBDB();
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

    public Integer generateId(){
        return bookings
                .keySet()
                .stream()
                .max(Integer::compare).orElse(0)+1;
    }
}