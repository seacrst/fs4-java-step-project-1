package dev.flight_app.events;

import dev.flight_app.entities.Console;
import dev.flight_app.services.EventService;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

public class Event<T> extends Console {

    private Function<String, T> handles;

    public static final EventService service = EventService.use();

    public Event(Handle<?> handler) {
        this.handler = handler;
    }

    public T listen(Function<String, T> listener) {
        prompt();
        return listener.apply(Console.input());
    }

    public void handle() {
        handler.handle();
    }

    public Function<String, T> getHandles() {
        return handles;
    }

    public static String readLine() {
        return Console.input();
    }

    public static ArrayList<String> collectData(String... steps) {
        ArrayList<String> data = new ArrayList<>();
        Stream.of(steps).forEach(step -> {
            String value = Event.readLine();
            data.add(value);
        });
        return  data;
    }
    public static ArrayList<String> collectData(Function<String, Void> handler, String... steps) {
        ArrayList<String> data = new ArrayList<>();
        Stream.of(steps).forEach(step -> {
            handler.apply(step);
            String value = Event.readLine();
            data.add(value);
        });
        return  data;
    }

    public static Void print(String msg) {
        Console.output(msg);
        return null;
    }

}
