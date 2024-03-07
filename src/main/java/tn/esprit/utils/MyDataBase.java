package tn.esprit.utils;

import tn.esprit.models.Reclamation;
import tn.esprit.models.Reponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDataBase {
    private static MyDataBase instance;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/reclamation";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    Connection cnx;
    private Connection connection = null;

    private MyDataBase() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Your connection is Connected successfully !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Your connection is not connected !!!");
        }
    }

    public static MyDataBase getInstance() {
        if (instance == null)
            instance = new MyDataBase();

        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }



}
