package dev.flight_app.Service;

import dev.flight_app.Dao.UserDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.User;

import java.util.*;

public class UserService {
    private final UserDao userDao;
    private final Map<String, User> loggedInUser = new HashMap<>();

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public Map<String, User> getAllUsers() {
        return userDao.getAll();
    }
    public User createNewUser(String login, String password, String name, String surname){
        User newUser = new User(userDao.generateId(), login, password, name, surname);
        userDao.save(newUser);
        return newUser;
    }
    public boolean checkLogin(String login){
        return userDao.getAll().containsKey(login);
    }
    public Optional<User> getUser(String login){
        return userDao.getById(login);
    }

    public List<Booking> myBookings(String login){
        return userDao.getById(login)
                .map(User::getUserBookings)
                .orElse(Collections.emptyList());
    }
    public void loadData(){
       userDao.load();
    }
    public boolean saveData() {
        return userDao.saveToFile();
    }
    public Optional<User> logIn(String login, String password){
        return userDao.getAll()
                .values()
                .stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .map(user -> {
                    loggedInUser.put(login, user);
                    return user;
                });
    }
    public boolean logout(User user){
        loggedInUser.remove(user.id());
        return loggedInUser.isEmpty();
    }
}
