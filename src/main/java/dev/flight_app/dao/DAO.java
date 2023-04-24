package dev.flight_app.dao;

import java.util.Map;
import java.util.Optional;

public interface DAO<ID, E extends Identifiable<ID>> {
    Map<ID, E> getAll();

    Optional<E> getById(ID id);

    boolean delete(ID id);

    default boolean delete(E o) {
        return delete(o.id());
    }
    boolean save();
}
