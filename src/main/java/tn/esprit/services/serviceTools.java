package tn.esprit.services;

import tn.esprit.models.Reclamation;
import tn.esprit.models.Reponse;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serviceTools {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/reclamation";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection connection = null;

}
