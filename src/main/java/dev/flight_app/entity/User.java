package dev.flight_app.entity;

import dev.flight_app.Dao.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User implements Serializable, Identifiable<String> {
    private static final long serialVersionUID = 1L;
    private final Integer id;
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final List<Booking> userBookings;


    public User(Integer id, String login, String password, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userBookings = new ArrayList<>();
    }
    public Integer getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Booking> getUserBookings() {
        return userBookings;
    }
    public void addBookings(Booking booking){
        userBookings.add(booking);
    }
    @Override
    public String toString() {
        return String.format("%s %s (%s)\nBookings: %s", name, surname, login, userBookings);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || !(that instanceof User)) return false;
        User user = (User) that;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String id() {
        return login;
    }
}
