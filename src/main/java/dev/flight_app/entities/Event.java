package dev.flight_app.entities;

import dev.flight_app.common.Validation;
import dev.flight_app.services.DataCollector;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

public class Event<T extends DataCollector<T>> extends Console {

    T collector;
    Validation validator;

    public Event(T collector, Validation validator) {
        this.collector = collector;
        this.validator = validator;
    }

    public static String readLine() {
        return Console.input();
    }
    public static int readNumber() {
        return Integer.parseInt(Console.input());
    }

    public static String readLine(String msg) {
        print(msg);
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

    public void handle(DataCollector<T> collector, String... steps) {
        Stream.of(steps).forEach(step -> {
            String data = Event.readLine(step);
                collector.collect(data);
            }
        );
    }
}
