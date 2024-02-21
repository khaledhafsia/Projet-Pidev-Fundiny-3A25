package org.example.Services;

import org.example.Entities.Owner;
import org.example.Entities.User;

import org.example.utils.MyDataBase;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ServiceFunder {

    private Connection cnx;

    public ServiceFunder() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    /*
        public void insertOwner(Owner owner) {
            try {
                String req = "INSERT INTO `user` (`nom`, `prenom`, `email`, `password`, `role`, `capital`) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, owner.getNom());
                ps.setString(2, owner.getPrenom());
                ps.setString(3, owner.getEmail());
                ps.setString(4, owner.getPassword());
                ps.setString(5, owner.getRole().toString());
                ps.setString(6, owner.getCapital());
                ps.executeUpdate();
            } catch (SQLException ex) {
                // Log and handle the exception
            }
        }

        public Owner verifyOwner(String email) {
            Owner owner = null;
            try {
                String req = "SELECT * FROM `user` where `email` = ?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Assuming the constructor User(id, nom, prenom, email, password, role) initializes all its fields correctly
                    owner = new Owner(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("password"),
                            User.role.valueOf(rs.getString("role")),
                            rs.getString("capital")
                    );
                }
            } catch (SQLException ex) {
                // Log and handle the exception
            }
            return owner;

        }

     */
    }