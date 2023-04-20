package dev.flight_app.dao;

import dev.flight_app.database.UserDB;
import dev.flight_app.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDao implements DAO<String, User>{
    private final Map<String, User> users = new HashMap<>();
    private final UserDB UDB = UserDB.getUDB();
    @Override
    public Map<String, User> getAll() {
        return users;
    }

    @Override
    public Optional<User> getById(String login) {
        return Optional.ofNullable(users.get(login));
    }

    @Override
    public boolean delete(String login) {
        return Optional.ofNullable(users.remove(login))
                .isPresent();
    }

    @Override
    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void load() {
        users.putAll(UDB.read());
    }

    @Override
    public boolean saveToFile() {
        return UDB.write(users);
    }
    public Integer generateId(){
        return users
                .values()
                .stream()
                .mapToInt(User::getId)
                .boxed()
                .max(Integer::compare).orElse(0)+1;
    }

}
