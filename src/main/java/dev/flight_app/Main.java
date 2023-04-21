package dev.flight_app;

import dev.flight_app.controllers.MenuController;
import dev.flight_app.entities.Menu;
import dev.flight_app.events.Event;
import dev.flight_app.services.EventService;
import dev.flight_app.services.Selectors;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("1. Рейси\n2. Реєстрація");
        Event<?> displayFlights = new Event<>(() -> {
            EventService.use().displayAllFlights();
            return null;
        });
        Event<?> exit = new Event<>(() -> {
            menu.close();
            return null;
        });
        Event<?> register = new Event<>((value) -> {
            EventService.use().createBooking(value);
            return null;
        });
        menu.addSelector("1", displayFlights);
        menu.addSelector("2", register);
        menu.addSelector("0", exit);


        Menu<Void> appMenu = new Menu<>("<<< ПОЛЕТІЛИ! >>>");

        Menu<String> home = new Menu<>(Selectors.Home);
//        Menu booking = new Menu(Selectors.B);

        appMenu.open(home);
    }
}
