package tn.esprit.services;

import tn.esprit.interfaces.IServices;
import tn.esprit.models.Reponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class serviceReponse implements IServices<Reponse> {
    private Connection cnx;

    @Override
    public void add(Reponse reponse) {
        String qry = "INSERT INTO `reponses`(`ID_Reclamation`, `ID_Administrateur`, `Contenu_Reponse`, `Date_Reponse`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reponse.getID_Reclamation());
            stm.setInt(2, reponse.getID_Administrateur());
            stm.setString(3, reponse.getContenu_Reponse());
            stm.setDate(4, new java.sql.Date(reponse.getDate_Reponse().getTime()));

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
                reponse.setID_Reponse(rs.getInt("ID_Reponse"));
                reponse.setID_Reclamation(rs.getInt("ID_Reclamation"));
                reponse.setID_Administrateur(rs.getInt("ID_Administrateur"));
                reponse.setContenu_Reponse(rs.getString("Contenu_Reponse"));
                reponse.setDate_Reponse(rs.getDate("Date_Reponse"));
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
                "`ID_Reclamation`=?, " +
                "`ID_Administrateur`=?, " +
                "`Contenu_Reponse`=?, " +
                "`Date_Reponse`=? " +
                "WHERE `ID_Reponse`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reponse.getID_Reclamation());
            stm.setInt(2, reponse.getID_Administrateur());
            stm.setString(3, reponse.getContenu_Reponse());
            stm.setDate(4, new java.sql.Date(reponse.getDate_Reponse().getTime()));
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
}
