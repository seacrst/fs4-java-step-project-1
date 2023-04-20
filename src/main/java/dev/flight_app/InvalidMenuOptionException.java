package dev.flight_app;

public class InvalidMenuOptionException extends Exception {
    public InvalidMenuOptionException(String message){
        super(message);
    }
}
