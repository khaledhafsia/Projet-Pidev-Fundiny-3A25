package tn.esprit.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IService<T> {
    void add(T t) throws SQLException;
    ArrayList<T> getAll();
    void update(T t);
    boolean delete(T t);

}
