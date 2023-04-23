package dev.flight_app.entities;

import dev.flight_app.controllers.EventController;
import dev.flight_app.controllers.MenuController;

public class Menu {

    public enum Selectors {
        Home("/index", "0"),
        Register("/register", "1"),
        AllFlights("/all-flights", "2"),
        MyFlights("/my-flights", "3"),
        CreateBooking("/new-booking", "4"),
        FindFlight("/find-flight", "5"),
        CancelBooking("/cancel-flight", "6"),
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

    public final EventController events = new EventController();
    private final MenuController menu = new MenuController(events);
    public Menu(String initialMsg) {
        Console.output(initialMsg);
    }
    public Menu(String initialMsg, String finalMsg) {
        this(initialMsg);
        menu.setFinalMsg(finalMsg);
    }

    public void open(Selectors sel) {
        menu.switchTo(sel.getState());
    }

}
