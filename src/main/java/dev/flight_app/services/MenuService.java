package dev.flight_app.services;

import dev.flight_app.entities.Console;
import dev.flight_app.events.Event;

public class MenuService {

    public void switchTo(String s) {
        if (s.equals("0")) {

        }
    }

    public void displayMessage(String msg) {
        Console.output(msg);
    }

    public static enum Selectors {
        Home,
        Flights,
        BookingCreate,
        BookingSearch,
        BookingCancel,
    }
}
