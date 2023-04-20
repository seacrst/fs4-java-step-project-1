package dev.flight_app.events;

import dev.flight_app.entities.Console;
import dev.flight_app.services.EventService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Event<T> {

    private boolean prompted = false;
    public final Actions action;

    public static final EventService service = EventService.use();

    public Event(Actions action) {
        this.action = action;
    }
    public Event(Actions action, boolean prompted) {
        this(action);
        this.prompted = prompted;
    }

    public T listen(Function<String, T> listener) {
        prompt();
        return listener.apply(Console.input());
    }

    public void prompt() {
        if (prompted) Console.output(action.get());
    }
    /* public T bind(Actions action, boolean prompt, Event... events) {
        if (prompt) {
            ArrayList<Actions> actions = new ArrayList<>();
            Stream.of(events).forEach(ev -> actions.add(ev.action));
            Console.output(Console.formatRows(actions));
        }
        new Event<>(action).listen(Event.service::handle);
    } */

}
