package uts.isd.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    // Returns a single instance from database or null
    T get(String id) throws SQLException;

    // Returns a List of all items
    List<T> getAll() throws SQLException;

    // Writes an object to the database
    void save(T t) throws SQLException;

    // Updates an existing instance in the database
    void update(T t, String[] params) throws SQLException;

    // Deletes an existing instance in the database
    void delete(T t) throws SQLException;
}