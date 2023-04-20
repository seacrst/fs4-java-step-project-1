package dev.flight_app.entities;

import dev.flight_app.events.Actions;
import dev.flight_app.events.Event;
import dev.flight_app.events.Selector;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;

import java.util.ArrayList;
import java.util.LinkedList;

public class Menu extends Console {

    private final MenuService menus = new MenuService();

    public Menu() {
        super();
    }

    public static void createSelector(Selector sel) {

    }

    public void select(Selector sel, Menu menu) {
        if (menus.getCurrent().equals(sel.triggerValue())) {
            menu.open();
        }
    }

    private boolean closed = false;

    public void open() {
       while (!closed) {

       }
    }

    public void close() {
        closed = true;
    }

    public void create(String name) {
        Event<String> ev = new Event<>(Actions.Search);
        String listen = ev.listen(s -> s + "\n");

    }

}
