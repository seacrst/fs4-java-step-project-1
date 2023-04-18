package dev.flight_app.Dao;

import java.util.Map;

public interface DAO<ID, E extends Identifiable<ID>> {
    Map<ID, E> getAll();
    E getById(ID id);
    boolean delete(ID id);
    default boolean delete(E o){
       return delete((ID) o.id());
    }
    void save(E e);
    void load();

    boolean saveToFile();
}
