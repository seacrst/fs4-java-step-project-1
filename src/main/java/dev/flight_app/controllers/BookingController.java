    package dev.flight_app.controllers;

    import dev.flight_app.services.BookingService;
    import dev.flight_app.entities.Booking;
    import dev.flight_app.entities.Flight;
    import dev.flight_app.entities.Passenger;

    import java.util.List;

    public class BookingController {
        private final BookingService bookingService = new BookingService();
        public BookingController() {
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
        public boolean saveData() {
            return bookingService.saveData();
        }
    }
