package dev.flight_app.entities;

import dev.flight_app.controllers.EventController;
import dev.flight_app.controllers.MenuController;
import dev.flight_app.events.Event;
import dev.flight_app.events.Selector;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;
import dev.flight_app.services.Selectors;

import java.util.function.Function;

public class Menu {

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
