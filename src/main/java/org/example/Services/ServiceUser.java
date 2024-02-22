package org.example.Services;

import org.example.Entities.Funder;
import org.example.Entities.Owner;
import org.example.Entities.User;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    private static Connection cnx;
    Statement stm;

    public ServiceUser() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public static void insertUser(User user, String attributeValue, String attributeType) throws SQLException {
        String req = "INSERT INTO `user` (`nom`, `prenom`, `email`, `password`, `role`, `" + attributeType + "`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, user.getNom());
        ps.setString(2, user.getPrenom());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getRole().name());
        ps.setString(6, attributeValue);
        ps.executeUpdate();
    }

    public static User verifyUser(String email) throws SQLException {
        User user = null;
        String req = "SELECT * FROM `user` WHERE `email` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("password"),
                    User.role.valueOf(rs.getString("role"))
            );
        }
        return user;
    }

    public User validateUser(String email, String password) {
        User user = null;
        try {
            user = verifyUser(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return null;
    }

    public User.role getUserRole(String email) {
        try {
            User user = verifyUser(email);
            if (user != null) {
                return user.getRole();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return User.role.ADMIN; // Return a default role, like "AUTRE", when user not found or an error occurs
    }

    // Add this method to ServiceUser.java
    public List<User> getAllUsers() {
        User user = null;
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM `user`";

        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User.role userRole = User.role.valueOf(resultSet.getString("role"));

                // Assuming the role determines the type of user, handle accordingly
                if (userRole == User.role.Funder) {
                    float participation = resultSet.getFloat("participation");
                    userList.add(new Funder(id, nom, prenom, email, password, userRole, participation));
                } else if (userRole == User.role.Owner) {
                    float capital = resultSet.getFloat("capital");
                    userList.add(new Owner(id, nom, prenom, email, password, userRole, capital));
                } else {
                    userList.add(new User(id, nom, prenom, email, password, userRole));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            System.out.println("aaaaaaaaaaaaaaaa");
        }
        return userList;

    }
    public void updateUserAttribute(int userId, String attributeType, String attributeValue) throws SQLException {
        String query = "UPDATE `user` SET " + attributeType + " = ? WHERE id = ?";
        try(PreparedStatement statement = cnx.prepareStatement(query)){
            statement.setString(1, attributeValue);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }


    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM `user` WHERE id = ?";
        stm = cnx.createStatement();
        stm.executeUpdate(query);
        }



}

/*

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM `user` WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }


    public void deleteUser(int id) throws SQLException {
        String req = "DELETE FROM `user` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted == 0) {
            System.out.println("No rows were deleted for ID " + id);
        } else {
            System.out.println(rowsDeleted + " rows were deleted for ID " + id);
        }
    }
 */


