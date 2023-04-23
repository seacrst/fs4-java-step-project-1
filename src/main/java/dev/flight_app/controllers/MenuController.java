package dev.flight_app.controllers;

import java.util.*;

import dev.flight_app.DuplicateBookingException;
import dev.flight_app.InvalidMenuOptionException;
import dev.flight_app.Validation;
import dev.flight_app.entities.*;
import dev.flight_app.services.*;

public class MenuController {
    private String terminatingMsg;
    private final EventService events;
    private static final Map<String, String> actions = new HashMap<>();

    static {
        actions.put("0", Menu.Selectors.Home.getState());
        actions.put("1", Menu.Selectors.AllFlights.getState());
        actions.put("2", Menu.Selectors.MyFlights.getState());
        actions.put("3", Menu.Selectors.CreateBooking.getState());
        actions.put("4", Menu.Selectors.FindFlight.getState());
        actions.put("5", Menu.Selectors.CancelBooking.getState());
        actions.put("!0",Menu.Selectors.Exit.getState());
    }

    private boolean closed = false;

    public MenuController(EventController eventController) {
        events = eventController.events();
    }

    private static final String homePrompt = """
________________________
1. Display all flights
2. My flights
3. Booking
4. Search flight
5. Cancel booking
0. Exit
________________________
""";
    private static final String enterMenuNumber = "Enter menu number: ";
    private static final String createBookingArrivalPrompt = "Destination: ";
    private static final String createBookingDatePrompt = "Date in dd-MM-yyyy format : ";
    private static final String createBookingPassengersPrompt = "Amount of passengers: ";
    private static final String readPassengerNamePrompt = "Name: ";
    private static final String readPassengerSurnamePrompt = "Surname: ";
    private static final String searchFlightByIdPrompt = "Enter flight ID: ";
    private static final String cancelBookingById = "Enter booking ID: ";
    private static final String selectFlightByIndex = "Select flight by line number or 0 to exit: ";
    private static final String getBackPrompt = "0. Back: ";
    private static final String getFlightFailure = "Not found flight";
    private static final String incorrectInputPrompt = "Incorrect input. Please try again";


    public String selectMenu(String option, int maxOptions) throws InvalidMenuOptionException {
        try {
            int optNumber = Integer.parseInt(option);

            if (optNumber > maxOptions || optNumber < 0) {
                throw  new InvalidMenuOptionException(incorrectInputPrompt);
            }

            return actions.get(option.equals("0") ? String.format("!%s", option) : option);
        } catch (NumberFormatException ex) {
            throw new InvalidMenuOptionException(incorrectInputPrompt);
        }
    }
    public void toHomeMenu() {
        Console.output(homePrompt);
        String s = Event.readLine(enterMenuNumber);
        try {
            switchTo(selectMenu(s, 5));
        } catch (InvalidMenuOptionException ex) {
            Console.output(ex.getMessage());
            toHomeMenu();
        }
    }

    public void toAllFlightsMenu() {
        events.displayAllFlights();
        Console.output("");
        toHomeMenu();
    }

    public void toMyFlightsMenu() {
        ArrayList<String> passengerData = Event.collectData(Event::print, readPassengerNamePrompt, readPassengerSurnamePrompt);
        List<Booking> bookings = events.findBookingByPassengerData(passengerData);
        if(bookings.isEmpty()){
            Console.output("Bookings not found.");
        }else{
            bookings.forEach(Console::output);
        }
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

        events.enterCity(flightData, createBookingArrivalPrompt);
        events.enterDate(flightData, createBookingDatePrompt);
        events.enterSeats(flightData, createBookingPassengersPrompt);

        List<Flight> flightList = events.selectFlight(flightData);
        if (flightList.size() == 0) {
            Console.output("Flight not found");
            toHomeMenu();
        } else {
            flightList.forEach(flt -> {
                int i = flightList.indexOf(flt) + 1;
                System.out.println(i + ". " + flt.toString());
            });


            String idx = events.enterIndexOfFlight(selectFlightByIndex, flightList.size(), (x) -> {
                toHomeMenu();
                return null;
            });

            try {
                Booking booking = events.createBooking(Integer.parseInt(flightData.getSeatsAmount()), flightList.get(Integer.parseInt(idx) - 1));
                Console.output(booking);
            } catch (DuplicateBookingException ex){
                Console.output(ex.getMessage());
            }
            toHomeMenu();
        }
    }

    public void toFlight() {

    }

    public void toCancelBookingMenu() {
        boolean result = events.enterBookingId(cancelBookingById);

        if (result) {
            Console.output("Deleted!");
        } else {
            Console.output("Such booking doesn't exist.");
        }
        toHomeMenu();
    }

    public String toWarningMenu(String msg) {
        return Event.readLine(msg);
    }

    public void switchTo(String state) {
        while (!closed) {
            switch (state) {
                case "/index" -> toHomeMenu();
                case "/all-flights" -> toAllFlightsMenu();
                case "/my-flights" -> toMyFlightsMenu();
                case "/new-booking" -> toBookingMenu();
                case "/find-flight" -> toFlightSearchMenu();
                case "/get-flight" -> toFlight();
                case "/cancel-flight" -> toCancelBookingMenu();
                case "/exit" -> terminate();
                default -> toWarningMenu(incorrectInputPrompt);
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
