package dev.flight_app.controllers;
import dev.flight_app.services.EventService;
public class EventController {

    private final UserController userController = new UserController();
    private final FlightController flightController = new FlightController();
    private final BookingController bookingController = new BookingController();
    private final EventService eventService = new EventService(flightController, bookingController, userController);
    public EventController() { }

    public BookingController bookings() {
        return bookingController;
    }

    public UserController users() {
        return userController;
    }

    public FlightController flights() {
        return flightController;
    }

    public EventService events() { return eventService; }
}
