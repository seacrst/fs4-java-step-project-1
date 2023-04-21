package dev.flight_app.entities;

import dev.flight_app.controllers.MenuController;
import dev.flight_app.events.Event;
import dev.flight_app.events.Selector;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;
import dev.flight_app.services.Selectors;

import java.util.function.Function;

public class Menu<T> {
    private final MenuController menus = new MenuController();
    private final EventService events = EventService.use();
    private Selectors selector;


    public Menu(Selectors sel) {
        selector = sel;
    }
    public Menu(String msg) {
        Event.print(msg);
    }

    public static void select() {

    }

    public void addSelector(String sel, Event<?> event) {
        if (events.read().equals(sel)) {
            event.handle();
        }
    }

    public void display(Selectors selector) {
        new Event<T>(selector)
    }

    private boolean closed = false;

    public void open(Menu menu) {
        menus.index(selector);

        while (!menu.closed) {
           menu.display(selector);
        }
    }

    public void close() {
        closed = true;
    }
}
