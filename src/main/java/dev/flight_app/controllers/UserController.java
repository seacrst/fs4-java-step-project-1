package dev.flight_app.controllers;

import dev.flight_app.AuthenticationException;
import dev.flight_app.DuplicateLoginException;
import dev.flight_app.services.BookingService;
import dev.flight_app.services.UserService;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserController {
    private static UserController uc = null;
    private final UserService userService = new UserService();
    private UserController() {
    }
    public static UserController create() {
        if (!Objects.isNull(uc)) {
            return uc;
        }
        uc = new UserController();
        return uc;
    }
    public User createNewUser(String login, String password, String name, String surname){

        return userService.createNewUser(login, password, name, surname);
    }
    public boolean checkLogin(String login){
        return userService.checkLogin(login);
    }
    public Optional<User> getUser(String login){
        if(!checkLogin(login)){
            System.out.println("There is no such user.");
            return Optional.empty();
        }
        return userService.getUser(login);
    }
    public List<Booking> myBookings(String login){
        return userService.myBookings(login);
    }
    public void loadData(){
        userService.loadData();
    }
    public boolean saveData() {
        return userService.saveData();
    }
    public User logIn(String login, String password){
        if (userService.logIn(login, password).isEmpty()){
            throw new AuthenticationException("Your login or password is incorrect.");
        }
        return userService.logIn(login, password).orElseThrow(()-> new AuthenticationException("Your login or password is incorrect."));
    }
    public boolean logout(User user){
        return userService.logout(user);
    }
}
