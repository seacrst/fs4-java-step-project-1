package dev.flight_app.services;

import dev.flight_app.controllers.MenuController;

public enum Selectors {
    Home(),
    Flights(),
    BookingCreate(),
    BookingSearch(),
    BookingCancel();

    Selectors() {
        MenuController menu = MenuController.get();

        switch (this) {
            case Home: menu.toHomeMenu();
            break;
            case Flights: menu.toMyFlightsMenu();
            case BookingCreate: menu.toBookingMenu();
            break;
            case BookingSearch: menu.toBookingSearchMenu();
            break;
            case BookingCancel: menu.toCancelBookingMenu();
            break;
            default: break;
        }
    }
}
