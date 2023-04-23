package dev.flight_app.database;

import dev.flight_app.entities.Booking;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BookingDB implements DB<Integer,Booking> {
    private static BookingDB BDB;
    private final File file =  new File("./DB", "booking.txt");
    private BookingDB(){}
    public static BookingDB getBDB(){
        if(BDB == null) BDB = new BookingDB();
        return BDB;
    }
    @Override
    public boolean write(Map<Integer,Booking> B) {
        return write(B, file);
    }

    @Override
    public Map<Integer,Booking> read() {
        if(!file.exists()) write(new HashMap<>(), file);
        return read(file);
    }
}
