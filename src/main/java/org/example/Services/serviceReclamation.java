package org.example.Services;

import org.example.interfaces.IServices;
import org.example.Entities.Reclamation;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serviceReclamation implements IServices<Reclamation> {
    private Connection cnx; // Ensure that cnx is declared and initialized appropriately.
    public serviceReclamation(){cnx= MyDataBase.getInstance().getCnx();}
    // Other methods...

    @Override
    public void add(Reclamation reclamation) {
        String qry = "INSERT INTO `Reclamations` (`email`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `objet`, `texte`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, reclamation.getEmail());
            stm.setInt(2, reclamation.getID_Projet());
            stm.setInt(3, reclamation.getID_Type_Reclamation());
            stm.setInt(4, reclamation.getID_Admin());
            stm.setString(5, reclamation.getObjet());
            stm.setString(6, reclamation.getTexte());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Reclamation> getAll() {
        ArrayList<Reclamation> Reclamations = new ArrayList<>();
        String qry = "SELECT * FROM `Reclamations`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setEmail(rs.getString("email"));
                r.setID_Projet(rs.getInt("ID_Projet"));
                r.setID_Type_Reclamation(rs.getInt("ID_Type_Reclamation"));
                r.setID_Admin(rs.getInt("ID_Admin"));
                r.setObjet(rs.getString("objet"));
                r.setTexte(rs.getString("texte"));
                Reclamations.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Reclamations;
    }

    @Override
    public void update(Reclamation reclamation) {
        String qry = "UPDATE `reclamations` SET " +
                "`email`=?, " +
                "`ID_Projet`=?, " +
                "`ID_Type_Reclamation`=?, " +
                "`ID_Admin`=?, " +
                "`objet`=?, " +
                "`texte`=? " +
                "WHERE `ID_Reclamation`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, reclamation.getEmail());
            stm.setInt(2, reclamation.getID_Projet());
            stm.setInt(3, reclamation.getID_Type_Reclamation());
            stm.setInt(4, reclamation.getID_Admin());
            stm.setString(5, reclamation.getObjet());
            stm.setString(6, reclamation.getTexte());
            stm.setInt(7, reclamation.getID_Reclamation());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Reclamation reclamation) {
        //serviceReclamation serviceReclamation = new serviceReclamation();
        //serviceReclamation.setConnection(cnx);

        String qry = "DELETE FROM `reclamations` WHERE `ID_Reclamation`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reclamation.getID_Reclamation());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<String> getAllProjectsNames() {
        List<String> projectNames = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT Nom FROM projet";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nom = resultSet.getString("Nom");
                    projectNames.add(nom);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projectNames;
    }

    public List<String> getAllProblemsNames() {
        List<String> problemsNames = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT Nom_Type_Reclamation FROM typesreclamation";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nom = resultSet.getString("Nom_Type_Reclamation");
                    problemsNames.add(nom);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return problemsNames;
    }

    public int getProjectIDByName(String projectName) {
        int projectID = -1;  // Default value if not found
        Connection connection = MyDataBase.getInstance().getCnx();
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

    public List<String> getAllAdminNames() {
        List<String> adminNames = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT Nom FROM admin";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nom = resultSet.getString("Nom");
                    adminNames.add(nom);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminNames;
    }

    public List<Reclamation> getAllReclamations() {
        List<Reclamation> reclamationsList = new ArrayList<>();

        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM reclamations";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Reclamation reclamation = new Reclamation();
                    reclamation.setID_Reclamation(resultSet.getInt("ID_Reclamation"));
                    reclamation.setEmail(resultSet.getString("email"));
                    reclamation.setID_Projet(resultSet.getInt("ID_Projet"));
                    reclamation.setID_Type_Reclamation(resultSet.getInt("ID_Type_Reclamation"));
                    reclamation.setID_Admin(resultSet.getInt("ID_Admin"));
                    reclamation.setObjet(resultSet.getString("objet"));
                    reclamation.setTexte(resultSet.getString("texte"));

                    reclamationsList.add(reclamation);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamationsList;
    }

    public List<Reclamation> searchReclamations(String searchText) {
        List<Reclamation> result = new ArrayList<>();

        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM reclamations " +
                    "WHERE LOWER(email) LIKE ? OR " +
                    "LOWER(objet) LIKE ? OR " +
                    "LOWER(texte) LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                String searchString = "%" + searchText.toLowerCase() + "%";
                preparedStatement.setString(1, searchString);
                preparedStatement.setString(2, searchString);
                preparedStatement.setString(3, searchString);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Reclamation reclamation = new Reclamation();
                        reclamation.setID_Reclamation(resultSet.getInt("ID_Reclamation"));
                        reclamation.setEmail(resultSet.getString("email"));
                        reclamation.setID_Projet(resultSet.getInt("ID_Projet"));
                        reclamation.setID_Type_Reclamation(resultSet.getInt("ID_Type_Reclamation"));
                        reclamation.setID_Admin(resultSet.getInt("ID_Admin"));
                        reclamation.setObjet(resultSet.getString("objet"));
                        reclamation.setTexte(resultSet.getString("texte"));

                        result.add(reclamation);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int getreclamationIDByName(String Nom_Type_Reclamation) {
        int ID_Type_Reclamation = -1;  // Default value if not found
        Connection connection = MyDataBase.getInstance().getCnx();

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
        Connection connection = MyDataBase.getInstance().getCnx();

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

    public void deleteReclamation(Reclamation reclamation) {
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            connection = MyDataBase.getInstance().getCnx();
            String query = "DELETE FROM reclamations WHERE ID_Reclamation = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, reclamation.getID_Reclamation());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Reclamation deleted successfully from the database.");
                } else {
                    System.out.println("Failed to delete reclamation from the database.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
