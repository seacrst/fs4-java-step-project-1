    package dev.flight_app.controllers;

    import dev.flight_app.services.BookingService;
    import dev.flight_app.entities.Booking;
    import dev.flight_app.entities.Flight;
    import dev.flight_app.entities.Passenger;
    import dev.flight_app.entities.User;

    import java.util.List;
    import java.util.Map;

    public class BookingController {
        private final BookingService bookingService;

        public BookingController(BookingService bookingService) {
            this.bookingService = bookingService;
        }
        public List<Map.Entry<Integer, Booking>> myFlights(String name, String surname){
            return bookingService.myFlights(name, surname);
    }

        public List<Map.Entry<Integer, Booking>> myFlights(User user){
            return bookingService.myFlights(user);
        }
        public Booking createNewBooking(Flight flight,List<Passenger> passengers, User user) {
            return bookingService.createNewBooking(flight, passengers,user);
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
