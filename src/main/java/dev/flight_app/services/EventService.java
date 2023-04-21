package dev.flight_app.services;

import java.util.ArrayList;

public class EventService {

    private String value = "";
    private static EventService instance = null;
    private EventService() {
    }

    public static EventService use() {
        if (instance != null) {
            return instance;
        }

        instance = new EventService();

        return  instance;
    }

    public String read() {return value;}

    public void displayAllFlights() {

    }

    public void createBooking(ArrayList<String> bookingData) {

    }

    public void findBookingByPassengerData(ArrayList<String> passengerData) {

    }

    public void findBookingById(String id) {

    }

    public void cancelBooking(String id) {
        findBookingById(id);
    }
}
