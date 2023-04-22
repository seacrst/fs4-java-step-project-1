package dev.flight_app.events;

import dev.flight_app.entities.Console;
import dev.flight_app.entities.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Event<T> extends Console {

    public static String readLine() {
        return Console.input();
    }

    public static String readLine(String msg) {
        Console.output(msg);
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

    public static Passenger collectPassengersData(Function<String, Void> handler, String... steps) {
        ArrayList<String> data = new ArrayList<>();

        Stream.of(steps).forEach(step -> {
            handler.apply(step);
            String value = Event.readLine();
            if (data.size() != 2) {
                data.add(value);
            }
        });

        return new Passenger(data.get(0), data.get(1));
    }

    public static Void print(String msg) {
        System.out.print(msg + " ");
        return null;
    }

}
