package dev.flight_app.controllers;

import java.util.*;

import dev.flight_app.Validation;
import dev.flight_app.entities.*;
import dev.flight_app.services.*;

public class MenuController {
    private String terminatingMsg;
    private final EventService events;
    private final EventController services;
    private final Validation validation = new Validation();
    private static final Map<String, String> actions = new HashMap<>();

    static {
        actions.put("0", Menu.Selectors.Home.getState());
        actions.put("1", Menu.Selectors.Register.getState());
        actions.put("2", Menu.Selectors.AllFlights.getState());
        actions.put("3", Menu.Selectors.MyFlights.getState());
        actions.put("4", Menu.Selectors.CreateBooking.getState());
        actions.put("5", Menu.Selectors.FindFlight.getState());
        actions.put("6", Menu.Selectors.CancelBooking.getState());
        actions.put("!0",Menu.Selectors.Exit.getState());
    }

    private boolean closed = false;

    public MenuController(EventController eventController) {
        events = eventController.events();
        services = eventController;
    }

    private static final String homePrompt = """
    1. Register
    2. Display all flights
    3. My flights
    4. Booking
    5. Search flight
    6. Cancel booking
    0. Exit
""";
    private static final String createBookingArrivalPrompt = "Destination: ";
    private static final String createBookingDatePrompt = "Date: ";
    private static final String createBookingPassengersPrompt = "Amount of passengers: ";
    private static final String readPassengerNamePrompt = "Name: ";
    private static final String readPassengerSurnamePrompt = "Surname: ";
    private static final String searchFlightByIdPrompt = "Enter flight ID: ";
    private static final String cancelBookingById = "Enter booking ID: ";
    private static final String getBackPrompt = "0. Back: ";
    private static final String getFlightFailure = "Not found flight";


    public void toHomeMenu() {
        String s = Event.readLine(homePrompt);
        switchTo(actions.get(s.equals("0") ? String.format("!%s", s) : s));
    }

    public void toRegisterMenu() {}

    public void toAllFlightsMenu() {
        events.displayAllFlights();
        String sel = Event.readLine(getBackPrompt);
        switchTo(actions.get(sel));
    }

    public void toMyFlightsMenu() {
        ArrayList<String> passengerData = Event.collectData(Event::print, readPassengerNamePrompt, readPassengerSurnamePrompt);
        events.findBookingByPassengerData(passengerData).forEach(Console::output);
        Event.print(getBackPrompt);
        switchTo(actions.get(Event.readLine()));
    }

    public void toFlightSearchMenu() {
        Console.output(searchFlightByIdPrompt);

        String id = Event.readLine();
        if (!Validation.validateFlightId(id)) {
            toFlightSearchMenu();
        } else {
            Optional<Flight> response = events.findFlightById(id);
            if (response.isPresent()) {
                Console.output(response.get());
                String s = Event.readLine(getBackPrompt);
                switchTo(actions.get(s));
            } else {
                toWarningMenu(getFlightFailure);
            }
        }
    }

    public void toBookingMenu() {
        Validation validator = new Validation();
        FlightDataCollector flightData = new FlightDataCollector();
        Event<FlightDataCollector> event = new Event<>(flightData, validator);

        event.handle(flightData, createBookingArrivalPrompt, createBookingDatePrompt, createBookingPassengersPrompt);
//        ArrayList<String> bookingData = Event.collectData(Event::print, createBookingArrivalPromptEng, createBookingDatePromptEng, createBookingPassengersPromptEng);
        List<Flight> flightList = events.selectFlight(flightData);
        flightList.forEach(Console::output);

        Optional<Flight> flt = events.findFlightById(Event.readLine(searchFlightByIdPrompt));
        flt.ifPresent(flight -> events.createBooking(Integer.parseInt(flightData.getSeatsAmount()), flight));
        switchTo("/index");
    }

    public void toFlight() {

    }

    public void toCancelBookingMenu() {
        Console.output(cancelBookingById);
        events.cancelBooking(actions.get(Event.readLine()));
    }

    public void toPreviousMenu() {
        toHomeMenu();
    }

    public void toWarningMenu(String msg) {
//        Event.readLine();
    }

    public void switchTo(String state) {
        while (!closed) {
            switch (state) {
                case "/index" -> toHomeMenu();
                case "/register" -> toRegisterMenu();
                case "/all-flights" -> toAllFlightsMenu();
                case "/my-flights" -> toMyFlightsMenu();
                case "/new-booking" -> toBookingMenu();
                case "/find-flight" -> toFlightSearchMenu();
                case "/get-flight" -> toFlight();
                case "/cancel-flight" -> toCancelBookingMenu();
                case "/exit" -> terminate();
                default -> toWarningMenu("Please choose correct command");
            }
        }
    }

    private void terminate() {
        if (!terminatingMsg.isEmpty()) {
            Console.output(terminatingMsg);
        }
        closed = true;
        events.saveData();
    }
    public void setFinalMsg(String msg) {
        terminatingMsg = msg;
    }

    public boolean isClosed() {return closed;}
}
