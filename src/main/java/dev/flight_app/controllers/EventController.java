package dev.flight_app.controllers;
import dev.flight_app.services.EventService;
public class EventController {

    private final FlightController flightController = new FlightController();
    private final BookingController bookingController = new BookingController();
    private final EventService eventService = new EventService(flightController, bookingController);
    public EventController() { }

    public BookingController bookings() {
        return bookingController;
    }

    public FlightController flights() {
        return flightController;
    }

    public EventService events() { return eventService; }
}
