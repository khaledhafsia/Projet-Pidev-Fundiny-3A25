package tn.esprit.services;

import tn.esprit.interfaces.IServices;
import tn.esprit.models.Reclamation;

import java.sql.*;
import java.util.ArrayList;

public class serviceReclamation implements IServices<Reclamation> {
    private Connection cnx;
    @Override
    public void add(Reclamation reclamation) {
        String qry = "INSERT INTO `Reclamation`(`ID_Utilisateur`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `Statut_Reclamation`, `Description_Reclamation`, `Date_Soumission`, `Derniere_Mise_a_Jour`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reclamation.getID_Utilisateur());
            stm.setInt(2, reclamation.getID_Projet());
            stm.setInt(3, reclamation.getID_Type_Reclamation());
            stm.setInt(4, reclamation.getID_Admin());
            stm.setString(5, reclamation.getStatut_Reclamation());
            stm.setString(6, reclamation.getDescription_Reclamation());
            stm.setDate(7, new java.sql.Date(reclamation.getDate_Soumission().getTime()));
            stm.setDate(8, new java.sql.Date(reclamation.getDerniere_Mise_a_Jour().getTime()));

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public ArrayList<Reclamation> getAll() {
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String qry = "SELECT * FROM `Reclamation`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setID_Reclamation(rs.getInt("ID_Reclamation"));
                r.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                r.setID_Projet(rs.getInt("ID_Projet"));
                r.setID_Type_Reclamation(rs.getInt("ID_Type_Reclamation"));
                r.setID_Admin(rs.getInt("ID_Admin"));
                r.setStatut_Reclamation(rs.getString("Statut_Reclamation"));
                r.setDescription_Reclamation(rs.getString("Description_Reclamation"));
                r.setDate_Soumission(rs.getDate("Date_Soumission"));
                r.setDerniere_Mise_a_Jour(rs.getDate("Derniere_Mise_a_Jour"));
                reclamations.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reclamations;
    }


    @Override
    public void update(Reclamation reclamation) {
        String qry = "UPDATE `Reclamation` SET " +
                "`ID_Utilisateur`=?, " +
                "`ID_Projet`=?, " +
                "`ID_Type_Reclamation`=?, " +
                "`ID_Admin`=?, " +
                "`Statut_Reclamation`=?, " +
                "`Description_Reclamation`=?, " +
                "`Date_Soumission`=?, " +
                "`Derniere_Mise_a_Jour`=? " +
                "WHERE `ID_Reclamation`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reclamation.getID_Utilisateur());
            stm.setInt(2, reclamation.getID_Projet());
            stm.setInt(3, reclamation.getID_Type_Reclamation());
            stm.setInt(4, reclamation.getID_Admin());
            stm.setString(5, reclamation.getStatut_Reclamation());
            stm.setString(6, reclamation.getDescription_Reclamation());
            stm.setDate(7, new java.sql.Date(reclamation.getDate_Soumission().getTime()));
            stm.setDate(8, new java.sql.Date(reclamation.getDerniere_Mise_a_Jour().getTime()));
            stm.setInt(9, reclamation.getID_Reclamation());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public boolean delete(Reclamation reclamation) {
        String qry = "DELETE FROM `Reclamation` WHERE `ID_Reclamation`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reclamation.getID_Reclamation());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
