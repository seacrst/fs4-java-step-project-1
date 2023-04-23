package dev.flight_app.services;

public interface DataCollector<T> {
    T collect(String str);
}
