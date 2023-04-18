package dev.flight_app.entity;

import java.io.Serial;
import java.io.Serializable;

public record Passenger(String name, String surname) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
