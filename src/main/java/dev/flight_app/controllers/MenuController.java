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
    private final Map<String, String> actions = new HashMap<>();

    private boolean closed = false;

    public MenuController() {
        actions.put("1", "/register");
        actions.put("2", "/all-flights");
        actions.put("3", "/my-flights");
        actions.put("4", "/new-booking");
        actions.put("5", "/find-booking");
        actions.put("6", "/get-flight");
        actions.put("7", "/cancel-flight");
        actions.put("0", "/get-back");
        actions.put("!0", "/exit");
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

    private static final String createBookingArrivalPrompt = "Місце призначення: ";
    private static final String createBookingDatePrompt = "Дата: ";
    private static final String createBookingPassengersPrompt = "Кількість пасажирів: ";
    private static final String readPassengerNamePrompt = "Ім'я: ";
    private static final String readPassengerSurnamePrompt = "Прізвище: ";
    private static final String searchBookingByIdPrompt = "Введіть ID рейсу: ";
    private static final String cancelBookingById = "Введіть ID бронювання: ";
    private static final String getBackPrompt = "0. Назад: ";


    public void toHomeMenu() {
        clear();
        menus.displayMessage(MenuController.homePrompt);
        menus.switchTo(actions.get("!" + Event.readLine()));
    }

    public void toRegisterMenu() {

    }

    public void toAllFlightsMenu() {
        clear();
        events.displayAllFlights();
        Event.print(getBackPrompt);
        menus.switchTo(actions.get(Event.readLine()));
    }

    public void toMyFlightsMenu() {
        clear();
        ArrayList<String> passengerData = Event.collectData(Event::print, readPassengerNamePrompt, readPassengerSurnamePrompt);
        String response = events.findBookingByPassengerData(passengerData);
        menus.displayMessage(response);
        Event.print(getBackPrompt);
        menus.switchTo(actions.get(Event.readLine()));
    }

    public void toBookingSearchMenu() {
        clear();
        menus.displayMessage(searchBookingByIdPrompt);
        String response = events.findBookingById(Event.readLine());
        menus.displayMessage(response);
        Event.print(getBackPrompt);
        menus.switchTo(actions.get(Event.readLine()));
    }

    public void toBookingMenu() {
        clear();
        ArrayList<String> bookingData = Event.collectData(Event::print, createBookingArrivalPrompt, createBookingDatePrompt, createBookingPassengersPrompt);
        events.createBooking(bookingData);
    }

    public void toFlight() {

    }

    public void toCancelBookingMenu() {
        clear();
        menus.displayMessage(cancelBookingById);
        events.cancelBooking(actions.get(Event.readLine()));
    }

    public void toPreviousMenu() {
        toHomeMenu();
    }

    public void toWarningMenu() {

    }

    public void switchTo(String state) {
        switch (state) {
            case "/index" -> toHomeMenu();
            case "/register" -> toRegisterMenu();
            case "/all-flights" -> toAllFlightsMenu();
            case "/my-flights" -> toMyFlightsMenu();
            case "/new-booking" -> toBookingMenu();
            case "/find-booking" -> toBookingSearchMenu();
            case "/get-flight" -> toFlight();
            case "/cancel-flight" -> toCancelBookingMenu();
            case "/get-back" -> toPreviousMenu();
            case "/exit" -> terminate();
            default -> toWarningMenu();
        }
    }

    private void terminate() {
        clear();
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
