package dev.flight_app.services;

import dev.flight_app.entities.Console;
import dev.flight_app.events.Event;

public class MenuService {

    public void displayMessage(String msg) {
        Console.output(msg);
    }

}
