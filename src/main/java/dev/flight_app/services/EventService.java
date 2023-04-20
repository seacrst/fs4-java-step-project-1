package dev.flight_app.services;

public class EventService implements EventInteract {

    private static EventService instance = null;
    private EventService() {
    }

    public static EventService use() {
        if (instance != null) {
            return instance;
        }

        return new EventService();
    }

    @Override
    public void register() {

    }

    public void createBooking() {

    }

    @Override
    public void search() {

    }

    @Override
    public void display() {

    }

    @Override
    public void cancel() {

    }
}
