package dev.flight_app.controllers;

import dev.flight_app.entities.Console;
import dev.flight_app.entities.Menu;
import dev.flight_app.events.Event;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;
import dev.flight_app.services.Selectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class MenuController {
    private static MenuController sharedController = null;
    private final MenuService menus = new MenuService();
    private final EventService events = EventService.use();
    private static final Map<String, String> actions = new HashMap<>();

    static {
        actions.put("0", Selectors.Home.getState());
        actions.put("1", Selectors.Register.getState());
        actions.put("2", Selectors.AllFlights.getState());
        actions.put("3", Selectors.MyFlights.getState());
        actions.put("4", Selectors.CreateBooking.getState());
        actions.put("5", Selectors.SearchBooking.getState());
        actions.put("6", Selectors.GetFlight.getState());
        actions.put("7", Selectors.CancelBooking.getState());
        actions.put("!0", Selectors.Exit.getState());
    }

    private boolean closed = false;

    public MenuController() {

    }

    private static final String homePrompt = """
    1. Зареєструватися
    2. Показати всі рейси
    3. Мої рейси
    4. Забронювати
    5. Пошук
    6. Показати певний рейс
    7. Відмінити
    0. Вихід
""";

    private static final String homePromptEng = """
    1. Register
    2. Display all flights
    3. My flights
    4. Booking
    5. Search
    6. Display flight
    7. Cancel booking
    0. Exit
""";

    private static final String createBookingArrivalPrompt = "Місце призначення: ";
    private static final String createBookingArrivalPromptEng = "Destination: ";
    private static final String createBookingDatePrompt = "Дата: ";
    private static final String createBookingDatePromptEng = "Date: ";
    private static final String createBookingPassengersPrompt = "Кількість пасажирів: ";
    private static final String createBookingPassengersPromptEng = "Amount of passengers: ";
    private static final String readPassengerNamePrompt = "Ім'я: ";
    private static final String readPassengerNamePromptEng = "Name: ";
    private static final String readPassengerSurnamePrompt = "Прізвище: ";
    private static final String readPassengerSurnamePromptEng = "Surname: ";
    private static final String searchBookingByIdPrompt = "Введіть ID рейсу: ";
    private static final String searchBookingByIdPromptEng = "Enter flight ID: ";
    private static final String cancelBookingById = "Введіть ID бронювання: ";
    private static final String cancelBookingByIdEng = "Enter booking ID: ";
    private static final String getBackPrompt = "0. Назад: ";
    private static final String getBackPromptEng = "0. Back: ";


    public void toHomeMenu() {
        clear();
        menus.displayMessage(MenuController.homePromptEng);
        String s = Event.readLine();

        switchTo(actions.get(s.equals("0") ? String.format("!%s", s) : s));
    }

    public void toRegisterMenu() {

    }

    public void toAllFlightsMenu() {
        clear();
        events.displayAllFlights();
        Event.print(getBackPromptEng);
        switchTo(actions.get(Event.readLine()));
    }

    public void toMyFlightsMenu() {
        clear();
        ArrayList<String> passengerData = Event.collectData(Event::print, readPassengerNamePromptEng, readPassengerSurnamePromptEng);
        String response = events.findBookingByPassengerData(passengerData);
        menus.displayMessage(response);
        Event.print(getBackPrompt);
        switchTo(actions.get(Event.readLine()));
    }

    public void toBookingSearchMenu() {
        clear();
        menus.displayMessage(searchBookingByIdPromptEng);
        String response = events.findBookingById(Event.readLine());
        menus.displayMessage(response);
        Event.print(getBackPrompt);
        switchTo(actions.get(Event.readLine()));
    }

    public void toBookingMenu() {
        clear();
        ArrayList<String> bookingData = Event.collectData(Event::print, createBookingArrivalPromptEng, createBookingDatePromptEng, createBookingPassengersPromptEng);
        events.createBooking(bookingData);
        switchTo("/index");
    }

    public void toFlight() {

    }

    public void toCancelBookingMenu() {
        clear();
        menus.displayMessage(cancelBookingByIdEng);
        events.cancelBooking(actions.get(Event.readLine()));
    }

    public void toPreviousMenu() {
        toHomeMenu();
    }

    public void toWarningMenu() {

    }

    public void switchTo(String state) {
        while (!closed) {
            switch (state) {
                case "/index" -> toHomeMenu();
                case "/register" -> toRegisterMenu();
                case "/all-flights" -> toAllFlightsMenu();
                case "/my-flights" -> toMyFlightsMenu();
                case "/new-booking" -> toBookingMenu();
                case "/find-booking" -> toBookingSearchMenu();
                case "/get-flight" -> toFlight();
                case "/cancel-flight" -> toCancelBookingMenu();
                case "/exit" -> terminate();
                default -> toWarningMenu();
            }
        }
    }

    private void terminate() {
        clear();
        Console.output("Good bye");
        closed = true;
    }

    public void clear() {
     Console.output("\033[H\033[2J");
     System.out.flush();
    }

    public boolean isClosed() {return closed;}
    public static MenuController get() {
        if (!Objects.isNull(sharedController)) {
            return sharedController;
        }

        sharedController = new MenuController();

        return sharedController;
    }
}
