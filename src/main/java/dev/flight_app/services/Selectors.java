package dev.flight_app.services;

import dev.flight_app.controllers.MenuController;

public enum Selectors {
    Register("/register", "1"),
    Home("/index", "2"),
    AllFlights("/all-flights", "3"),
    MyFlights("/my-flights", "4"),
    BookingCreate("/new-booking", "5"),
    BookingSearch("/find-booking", "6"),
    GetBooking("/get-flight", "7"),
    BookingCancel("/cancel-flight", "8");

    private String sel;
    private String id;

    Selectors(String s, String id) {
        sel = s;
        this.id = id;
    }

    public String getState() {return sel;}
    public String getId() {return id;}
}
