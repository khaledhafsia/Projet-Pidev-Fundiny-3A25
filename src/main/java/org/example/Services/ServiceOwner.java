package org.example.Services;

import org.example.Entities.Funder;
import org.example.Entities.User;
import org.example.Entities.Owner;
import org.example.utils.MyDataBase;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ServiceOwner {

    private Connection cnx;

    public ServiceOwner() {
        cnx = MyDataBase.getInstance().getCnx();
    }
/*
    public void Insert(Owner p) {

        try {

            String req = "INSERT INTO `patient` (`nom`, `prenom`, `email`, `password`, `roles`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getPassword());
            String[] roles = {"ROLE_PATIENT"};
            ps.setString(7, Arrays.toString(roles));
            ps.executeUpdate();
            System.out.println("Owner ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du patient: " + ex.getMessage());
        }
    }


    public User Verifier(String email) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        User user = new Owner();
        try {
            // String req = "SELECT * FROM owner where email = ? and password = ?";
            String req = "SELECT * FROM `user` where `email` = '" + email + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                User.role.valueOf(rs.getString("role"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

     */
}

