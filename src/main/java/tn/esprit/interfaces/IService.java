package tn.esprit.interfaces;

import tn.esprit.models.article;
import tn.esprit.models.comment;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IService<T> {
    void add(T t) throws SQLException;

    ArrayList<T> getAll() throws SQLException;




    void update(T t);
    boolean delete(T t);

}
