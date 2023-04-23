    package dev.flight_app.controllers;

    import dev.flight_app.services.BookingService;
    import dev.flight_app.entities.Booking;
    import dev.flight_app.entities.Flight;
    import dev.flight_app.entities.Passenger;

    import java.util.List;
    import java.util.Objects;

    public class BookingController {
        private static BookingController bc;
        private final BookingService bookingService = new BookingService();
        public BookingController() {
        }
        public static BookingController create() {
            if (!Objects.isNull(bc)) {
                return bc;
            }
            bc = new BookingController();
            return bc;
        }
        public List<Booking> myFlights(String name, String surname){
            return bookingService.myFlights(name, surname);
    }

        public Booking createNewBooking(Flight flight,List<Passenger> passengers) {
            return bookingService.createNewBooking(flight, passengers);
        }
        public boolean cancelBooking(int id) {
            return bookingService.cancelBooking(id);
        }
        public void loadData() {
            bookingService.loadData();
        }
        public boolean saveData() {
            return bookingService.saveData();
        }
    }
