package dev.flight_app;
import dev.flight_app.entities.Menu;
import dev.flight_app.services.Selectors;

public class Main {
    public static void main(String[] args) {
        Menu appMenu = new Menu("<<< ПОЛЕТІЛИ! >>>");
        appMenu.open(Selectors.Home);
    }
}
