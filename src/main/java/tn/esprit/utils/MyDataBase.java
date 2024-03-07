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

    public List<String> getAllAdminNames() {
        List<String> adminNames = new ArrayList<>();
        try {
            connection = getInstance().getCnx(); // Use getInstance to get the instance
            String query = "SELECT Nom FROM admin";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                adminNames.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminNames;
    }

    public List<String> getAllusersNames() {
        List<String> usersNames = new ArrayList<>();
        try {
            connection = getInstance().getCnx(); // Use getInstance to get the instance
            String query = "SELECT Nom FROM utilisateur";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                usersNames.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersNames;
    }


    public List<String> getAllProjectsNames() {
        List<String> ProjectName = new ArrayList<>();
        try {
            connection = getInstance().getCnx(); // Use getInstance to get the instance
            String query = "SELECT Nom FROM projet";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                ProjectName.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProjectName;
    }

    public List<String> getAllProblemsNames() {
        List<String> ProblemsName = new ArrayList<>();
        try {
            connection = getInstance().getCnx(); // Use getInstance to get the instance
            String query = "SELECT Nom_Type_Reclamation FROM typesreclamation";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nom = resultSet.getString("Nom_Type_Reclamation");
                ProblemsName.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProblemsName;
    }

    public int getProjectIDByName(String projectName) {
        int projectID = -1;  // Default value if not found

        try {
            String query = "SELECT ID_Projet FROM projet WHERE Nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, projectName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                projectID = resultSet.getInt("ID_Projet");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception based on your application's needs
        }

        return projectID;
    }

    public int getAllusersNames(String userName) {
        int userID = -1;  // Default value if not found

        try {
            String query = "SELECT ID_utilisateur FROM utilisateur WHERE Nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userID = resultSet.getInt("ID_utilisateur");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception based on your application's needs
        }

        return userID;
    }

    public int getreclamationIDByName(String Nom_Type_Reclamation) {
        int ID_Type_Reclamation = -1;  // Default value if not found

        try {
            String query = "SELECT ID_Type_Reclamation FROM typesreclamation WHERE Nom_Type_Reclamation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Nom_Type_Reclamation);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ID_Type_Reclamation = resultSet.getInt("ID_Type_Reclamation");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception based on your application's needs
        }

        return ID_Type_Reclamation;
    }

    public int getadminIDByName(String AdminName) {
        int AdminID = -1;  // Default value if not found

        try {
            String query = "SELECT ID_Admin FROM admin WHERE Nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, AdminName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                AdminID = resultSet.getInt("ID_Admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception based on your application's needs
        }

        return AdminID;
    }

    public List<Reclamation> getAllReclamations() {
        List<Reclamation> reclamationsList = new ArrayList<>();

        try {
            connection = getInstance().getCnx();
            String query = "SELECT * FROM reclamations";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setID_Reclamation(Integer.parseInt(resultSet.getString("ID_Reclamation")));
                reclamation.setEmail(resultSet.getString("email"));
                reclamation.setID_Projet(Integer.parseInt(resultSet.getString("ID_Projet")));
                reclamation.setID_Type_Reclamation(Integer.parseInt(resultSet.getString("ID_Type_Reclamation")));
                reclamation.setID_Admin(Integer.parseInt(resultSet.getString("ID_Admin")));
                reclamation.setObjet(resultSet.getString("objet"));
                reclamation.setTexte(resultSet.getString("texte"));

                reclamationsList.add(reclamation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamationsList;
    }

    public void deleteReclamation(Reclamation reclamation) {
        try {
            connection = getInstance().getCnx();
            String query = "DELETE FROM reclamations WHERE ID_Reclamation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(reclamation.getID_Reclamation()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Reclamation deleted successfully from the database.");
            } else {
                System.out.println("Failed to delete reclamation from the database.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reponse> getAllReponse() {
        List<Reponse> reponsesList = new ArrayList<>();

        try {
            connection = getInstance().getCnx();
            String query = "SELECT * FROM reponses";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reponse reponse = new Reponse(
                        resultSet.getInt("ID_Reponse"),
                        resultSet.getInt("ID_Utilisateur"),
                        resultSet.getString("email"),
                        resultSet.getString("objet"),
                        resultSet.getString("texte")
                );                reponse.setID_Reponse(Integer.parseInt(resultSet.getString("ID_Reponse")));
                reponse.setemail(resultSet.getString("email"));
                reponse.setID_Utilisateur(Integer.parseInt(resultSet.getString("ID_Utilisateur")));
                reponse.setobjet(resultSet.getString("objet"));
                reponse.settexte(resultSet.getString("texte"));

                reponsesList.add(reponse);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reponsesList;
    }

    public void deleteReponse(Reponse reponse) {
        try {
            connection = getInstance().getCnx();
            String query = "DELETE FROM reponses WHERE ID_Reponse = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(reponse.getID_Reponse()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Reclamation deleted successfully from the database.");
            } else {
                System.out.println("Failed to delete reclamation from the database.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reclamation> searchReclamations(String searchText) {
        List<Reclamation> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM reclamations " +
                             "WHERE LOWER(email) LIKE ? OR " +
                             "LOWER(objet) LIKE ? OR " +
                             "LOWER(texte) LIKE ?")) {

            String searchString = "%" + searchText.toLowerCase() + "%";
            preparedStatement.setString(1, searchString);
            preparedStatement.setString(2, searchString);
            preparedStatement.setString(3, searchString);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID_Reclamation");
                    String email = resultSet.getString("email");
                    //int userId = resultSet.getInt("ID_Utilisateur");
                    int projectId = resultSet.getInt("ID_Projet");
                    int typeId = resultSet.getInt("ID_Type_Reclamation");
                    int adminId = resultSet.getInt("ID_Admin");
                    String objet = resultSet.getString("objet");
                    String texte = resultSet.getString("texte");

                    Reclamation reclamation = new Reclamation(id, email, projectId, typeId, adminId, objet, texte);
                    result.add(reclamation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return result;
    }

    public List<Reponse> searchResponses(String searchText) {
        List<Reponse> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM reponses " +
                             "WHERE LOWER(email) LIKE ? OR " +
                             "LOWER(objet) LIKE ? OR " +
                             "LOWER(texte) LIKE ?")) {

            String searchString = "%" + searchText.toLowerCase() + "%";
            preparedStatement.setString(1, searchString);
            preparedStatement.setString(2, searchString);
            preparedStatement.setString(3, searchString);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID_Reponse");
                    String email = resultSet.getString("email");
                    int userId = resultSet.getInt("ID_utilisateur");
                    String objet = resultSet.getString("objet");
                    String texte = resultSet.getString("texte");

                    Reponse reponse = new Reponse(id, userId, email, objet, texte);
                    result.add(reponse);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return result;
    }


}
