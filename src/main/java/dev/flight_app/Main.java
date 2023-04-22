package dev.flight_app;
import dev.flight_app.controllers.BookingController;
import dev.flight_app.controllers.FlightController;
import dev.flight_app.controllers.UserController;
import dev.flight_app.dao.BookingDao;
import dev.flight_app.dao.FlightDao;
import dev.flight_app.dao.UserDao;
import dev.flight_app.entities.Menu;
import dev.flight_app.entities.Console;
import dev.flight_app.services.BookingService;
import dev.flight_app.services.FlightService;
import dev.flight_app.services.Selectors;
import dev.flight_app.services.UserService;

public class Main {
    public static void main(String[] args) {

        BookingDao BD = new BookingDao();
        FlightDao FD = new FlightDao();

        BookingService BS = new BookingService(BD);
        FlightService FS = new FlightService(FD);

        BookingController BC = new BookingController(BS);
        FlightController FC = new FlightController(FS);


        UserDao UD = new UserDao();
        UserService US = new UserService(UD);
        UserController UC = new UserController(US);




        Menu menu = new Menu("<<< ПОЛЕТІЛИ! >>>");
        menu.open(Selectors.Home);
    }
}
