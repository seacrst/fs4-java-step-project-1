package dev.flight_app.events;

import dev.flight_app.entities.Console;
import java.util.function.Function;

public class Event<T> {

    boolean message = false;
    Actions action;

    public Event(Actions action) {
        this.action = action;
    }
    public Event(Actions action, boolean message) {
        this(action);
        this.message = message;
    }

    public T listen(Function<String, T> listener) {
        if (message) Console.output(action.name());
        return listener.apply(Console.input());
    }
}
