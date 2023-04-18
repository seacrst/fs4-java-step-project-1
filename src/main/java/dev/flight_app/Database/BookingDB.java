package dev.flight_app.Database;

import dev.flight_app.entity.Booking;

import java.io.File;
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
        return read(file);
    }
}
