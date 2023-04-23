package dev.flight_app.common;

public class DuplicateBookingException extends RuntimeException{
    public DuplicateBookingException(String message) {
        super(message);
    }
}
