package dev.flight_app.entities;

import dev.flight_app.events.Actions;
import dev.flight_app.events.Event;
import dev.flight_app.events.Selector;
import dev.flight_app.services.EventService;

import java.util.ArrayList;
import java.util.LinkedList;

public class Menu extends Console {

    LinkedList<Menu> menus = new LinkedList<>();
    private final EventService event = EventService.use();

    private final LinkedList<Event> eventList = new LinkedList<>();

    public Menu(ArrayList<Event> events) {
        super();

        eventList.addAll(events);
    }

    public static void createSelector(Selector sel) {

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
