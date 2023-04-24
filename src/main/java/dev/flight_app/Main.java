package dev.flight_app;

import dev.flight_app.entities.Menu;
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("<<< Welcome! >>>", "Good bye");
        menu.open(Menu.Selectors.Home);
    }
}
