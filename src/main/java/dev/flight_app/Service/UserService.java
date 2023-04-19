package dev.flight_app.Service;

import dev.flight_app.Dao.UserDao;
import dev.flight_app.entity.Booking;
import dev.flight_app.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao =userDao;
    }
    public User createNewUser(String login, String password, String name, String surname){
        User newUser = new User(getNextId(), login, password, name, surname);
        userDao.save(newUser);
        return newUser;
    }
    public boolean checkLogin(String login){
        return userDao.getAll().keySet().stream()
                .anyMatch(k -> k.equals(login));
    }
    public Optional<User> getUser(String login){
        return userDao.getById(login);
    }

    public List<Booking> getBookings(String login){
        return userDao.getById(login).get().getUserBookings();
    }

    public boolean deleteUser(String login){
        return userDao.delete(login);
    }
    private Integer getNextId(){
        return userDao.getAll()
                .values()
                .stream()
                .mapToInt(User::getId)
                .boxed()
                .max(Integer::compare).orElse(0)+1;
    }
    public void loadData(){
       userDao.load();
    }
    public boolean saveData() {
        return userDao.saveToFile();
    }
    public boolean islogIn(String login, String password){
        Optional<User> user = userDao.getById(login);
        return user.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}
