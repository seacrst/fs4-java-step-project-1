package dev.flight_app.entities;

import dev.flight_app.controllers.MenuController;
import dev.flight_app.events.Event;
import dev.flight_app.events.Selector;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;
import dev.flight_app.services.Selectors;

import java.util.function.Function;

public class Menu {
    private final MenuController menu = new MenuController();
    public Menu(String msg) {
        Console.output(msg);
    }

    public void open(Selectors sel) {
        menu.switchTo(sel.getState());
    }
}
