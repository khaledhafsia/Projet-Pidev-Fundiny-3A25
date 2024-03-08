package org.example.Services;

import org.example.interfaces.IServices;
import org.example.Entities.Reponse;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serviceReponse implements IServices<Reponse> {
    private Connection cnx;
    public serviceReponse(){cnx= MyDataBase.getInstance().getCnx();}

    @Override
    public void add(Reponse reponse) {
        String qry = "INSERT INTO `reponses`(`ID_Utilisateur`, `Objet`, `tete`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(2, reponse.getID_Utilisateur());
            stm.setString(3, reponse.getobjet());
            stm.setString(4, reponse.gettexte());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Reponse> getAll() {
        ArrayList<Reponse> reponses = new ArrayList<>();
        String qry = "SELECT * FROM `reponses`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Reponse reponse = new Reponse();
                reponse.setID_Reponse(Integer.parseInt(rs.getString("ID_Reponse")));
                reponse.setemail(rs.getString("email"));
                reponse.setID_Utilisateur(Integer.parseInt(rs.getString("ID_Utilisateur")));
                reponse.setobjet(rs.getString("objet"));
                reponse.settexte(rs.getString("texte"));
                reponses.add(reponse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reponses;
    }

    @Override
    public void update(Reponse reponse) {
        String qry = "UPDATE `reponses` SET " +
                "`email`=?, " +
                "`ID_Utilisateur`=?, " +
                "`Objet`=?, " +
                "`texte`=? " +
                "WHERE `ID_Reponse`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, reponse.getemail());
            stm.setInt(2, reponse.getID_Utilisateur());
            stm.setString(3, reponse.getobjet());
            stm.setString(4, reponse.gettexte());
            stm.setInt(5, reponse.getID_Reponse());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Reponse reponse) {
        String qry = "DELETE FROM `reponses` WHERE `ID_Reponse`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reponse.getID_Reponse());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getUserIdByName(String userName) {
        int userId = -1;
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            String query = "SELECT ID_utilisateur FROM utilisateur WHERE Nom = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("ID_utilisateur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    public List<String> getAllUsersNames() {
        List<String> usersNames = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx(); // Use getInstance to get the instance
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

    public int getUserIDByName(String userName) {
        int userID = -1;
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            String query = "SELECT ID_utilisateur FROM utilisateur WHERE Nom = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userID = resultSet.getInt("ID_utilisateur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }


    public List<Reponse> getAllReponses() {
        List<Reponse> reponsesList = new ArrayList<>();
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM reponses";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Reponse reponse = new Reponse();
                    reponse.setID_Reponse(resultSet.getInt("ID_Reponse"));
                    reponse.setID_Utilisateur(resultSet.getInt("ID_Utilisateur"));
                    reponse.setemail(resultSet.getString("email"));
                    reponse.setobjet(resultSet.getString("objet"));
                    reponse.settexte(resultSet.getString("texte"));

                    reponsesList.add(reponse);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceTools.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reponsesList;
    }

    public void deleteReponse(Reponse reponse) {
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            connection = MyDataBase.getInstance().getCnx();
            String query = "DELETE FROM reponses WHERE ID_Reponse = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, reponse.getID_Reponse());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Reponse deleted successfully from the database.");
                } else {
                    System.out.println("Failed to delete reponse from the database.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reponse> searchReponses(String searchText) {
        List<Reponse> result = new ArrayList<>();
        Connection connection = MyDataBase.getInstance().getCnx();

        try {
            connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM reponses " +
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
                        Reponse reponse = new Reponse();
                        reponse.setID_Reponse(resultSet.getInt("ID_Reponse"));
                        reponse.setID_Utilisateur(resultSet.getInt("ID_Utilisateur"));
                        reponse.setemail(resultSet.getString("email"));
                        reponse.setobjet(resultSet.getString("objet"));
                        reponse.settexte(resultSet.getString("texte"));

                        result.add(reponse);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
