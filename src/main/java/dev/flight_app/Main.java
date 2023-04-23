package dev.flight_app;

import dev.flight_app.entities.Menu;
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("<<< Welcome! >>>", "Good bye");

        menu.events.bookings().loadData();
        menu.events.flights().loadData();
        menu.events.users().loadData();

        menu.open(Menu.Selectors.Home);
    }
}
