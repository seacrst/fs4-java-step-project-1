package dev.flight_app.database;

import dev.flight_app.FlightGenerator;
import dev.flight_app.entities.Flight;

import java.io.File;
import java.util.Map;

public class FlightDB implements DB<Integer,Flight> {
    private static FlightDB FDB;
    private final File file =  new File("./DB", "flight.txt");
    private FlightDB(){}
    public static FlightDB getFDB(){
        if(FDB == null) FDB = new FlightDB();
        return FDB;
    }
    @Override
    public boolean write(Map<Integer, Flight> B) {
        return write(B, file);
    }

    @Override
    public Map<Integer, Flight> read() {
        if(!file.exists()) write(new FlightGenerator().getFlights(), file);
        return read(file);
    }
}