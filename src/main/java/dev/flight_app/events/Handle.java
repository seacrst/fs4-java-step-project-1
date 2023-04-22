package dev.flight_app.events;
@FunctionalInterface
public interface Handle<T> {
    T handle();
}
