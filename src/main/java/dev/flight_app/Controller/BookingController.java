    package dev.flight_app.Controller;

    import dev.flight_app.Service.BookingService;
    import dev.flight_app.entity.Booking;
    import dev.flight_app.entity.Flight;
    import dev.flight_app.entity.Passenger;
    import dev.flight_app.entity.User;

    import java.util.ArrayList;
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
