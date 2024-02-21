package org.example.Services;

import org.example.Entities.User;
import org.example.utils.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser {

    private static Connection cnx;

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
}