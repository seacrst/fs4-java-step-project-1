package dev.flight_app.Controller;

import dev.flight_app.AuthenticationException;
import dev.flight_app.Service.UserService;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.User;

import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public Optional<User> logIn(String login, String password){
        if (userService.logIn(login, password).isEmpty()){
            throw new AuthenticationException("Your login or password is incorrect.");
        }
        return userService.logIn(login, password);
    }
    public boolean logout(User user){
        return userService.logout(user);
    }
}
