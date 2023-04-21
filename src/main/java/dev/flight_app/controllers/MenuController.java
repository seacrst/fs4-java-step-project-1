package dev.flight_app.controllers;

import dev.flight_app.events.Event;
import dev.flight_app.services.EventService;
import dev.flight_app.services.MenuService;
import dev.flight_app.services.Selectors;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

public class MenuController {
    private static MenuController sharedController = null;
    private final MenuService menus = new MenuService();
    private final EventService events = EventService.use();

    private static final String homePrompt = """
    1. Показати всі рейси
    2. Мої рейси
    3. Забронювати
    4. Пошук
    5. Показати певний рейс
    6. Відмінити
    0. Вихід
""";

    private static final String createBookingArrivalPrompt = "Місце призначення: ";
    private static final String createBookingDatePrompt = "Дата: ";
    private static final String createBookingPassengersPrompt = "Кількість пасажирів: ";
    private static final String readPassengerNamePrompt = "Ім'я: ";
    private static final String readPassengerSurnamePrompt = "Прізвище: ";
    private static final String searchBookingByIdPrompt = "Введіть ID рейсу: ";
    private static final String cancelBookingById = "Введіть ID бронювання: ";


    public void toHomeMenu() {
        menus.displayMessage(MenuController.homePrompt);
        menus.switchTo(Event.readLine());
    }

    public void toMyFlightsMenu() {
        ArrayList<String> passengerData = Event.collectData(Event::print, readPassengerNamePrompt, readPassengerSurnamePrompt);
        events.findBookingByPassengerData(passengerData);
    }

    public void toBookingSearchMenu() {
        menus.displayMessage(searchBookingByIdPrompt);
        events.findBookingById(Event.readLine());

    }

    public void toBookingMenu() {
         ArrayList<String> bookingData = Event.collectData(Event::print, createBookingArrivalPrompt, createBookingDatePrompt, createBookingPassengersPrompt);
         events.createBooking(bookingData);
    }

    public void toCancelBookingMenu() {
        menus.displayMessage(cancelBookingById);
        events.cancelBooking(Event.readLine());
    }


    public void index(Selectors selector) {
        toHomeMenu();
    }

    public static MenuController get() {
        if (!Objects.isNull(sharedController)) {
            return sharedController;
        }

        sharedController = new MenuController();

        return sharedController;
    }
}
