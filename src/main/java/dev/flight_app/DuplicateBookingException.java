package dev.flight_app;

public class DuplicateBookingException extends RuntimeException{
    public DuplicateBookingException(String message) {
        super(message);
    }
}
