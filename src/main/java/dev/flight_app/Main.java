package dev.flight_app;
import dev.flight_app.entities.Menu;
import dev.flight_app.entities.Console;
import dev.flight_app.services.Selectors;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("<<< ПОЛЕТІЛИ! >>>");
        menu.open(Selectors.Home);
    }
}
