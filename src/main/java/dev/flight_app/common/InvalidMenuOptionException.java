package dev.flight_app.common;

public class InvalidMenuOptionException extends Exception {
    public InvalidMenuOptionException(String message){
        super(message);
    }
}
