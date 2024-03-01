package tn.esprit.interfaces;

import tn.esprit.models.article;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IService<T> {
    void add(T t) throws SQLException;

//    article getById(int id) throws SQLException;


    ArrayList<article> getAll() throws SQLException;



//    article getById(int id) throws SQLException;

//    ArrayList<comment> getById();

    void update(T t);
    boolean delete(T t);

}
