package org.example.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IServiceblog<T> {
    void add(T t) throws SQLException;

    ArrayList<T> getAll() throws SQLException;




    void update(T t);
    boolean delete(T t);


}
