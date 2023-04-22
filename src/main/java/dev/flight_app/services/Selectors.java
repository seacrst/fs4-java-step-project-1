package dev.flight_app.services;

public enum Selectors {
    Home("/index", "0"),
    Register("/register", "1"),
    AllFlights("/all-flights", "2"),
    MyFlights("/my-flights", "3"),
    CreateBooking("/new-booking", "4"),
    SearchBooking("/find-booking", "5"),
    GetFlight("/get-flight", "6"),
    CancelBooking("/cancel-flight", "7"),
    Exit("/exit", "!0");

    private final String sel;
    private final String id;

    Selectors(String s, String id) {
        sel = s;
        this.id = id;
    }

    public String getState() {return sel;}
    public String getId() {return id;}
}
