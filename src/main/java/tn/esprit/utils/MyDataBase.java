package tn.esprit.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    private static MyDataBase instance;
    private final String URL ="jdbc:mysql://127.0.0.1:3306/PiDEV";
    private final String username="root";
    private final String password ="";
    Connection cnx;
    private MyDataBase() {
        try {
            cnx = DriverManager.getConnection(URL,username,password);
            System.out.println("connected...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("___not connected___");
        }

    }
    public static MyDataBase getInstance(){
        if(instance == null)
            instance = new MyDataBase();
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
}

