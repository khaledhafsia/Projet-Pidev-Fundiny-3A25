package tn.esprit.services;

import tn.esprit.interfaces.IServices;
import tn.esprit.models.Reclamation;

import java.sql.*;
import java.util.ArrayList;

public class serviceReclamation implements IServices<Reclamation> {
    private Connection cnx;
    @Override
    public void add(Reclamation reclamation) {
        String qry = "INSERT INTO `reclamations`(`email`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `objet`, `texte`) VALUES VALUES (?, ?, ?, ?, ?, ?)";
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
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String qry = "SELECT * FROM `Reclamation`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setEmail(rs.getString("email"));
                r.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                r.setID_Projet(rs.getInt("ID_Projet"));
                r.setID_Type_Reclamation(rs.getInt("ID_Type_Reclamation"));
                r.setID_Admin(rs.getInt("ID_Admin"));
                r.setObjet(rs.getString("objet"));
                r.setTexte(rs.getString("texte"));
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
                "`email`=?, " +
                "`ID_Projet`=?, " +
                "`ID_Type_Reclamation`=?, " +
                "`ID_Admin`=?, " +
                "`Statut_Reclamation`=?, " +
                "`Description_Reclamation`=?, "+
                "WHERE `ID_Reclamation`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, reclamation.getEmail());
            stm.setInt(2, reclamation.getID_Projet());
            stm.setInt(3, reclamation.getID_Type_Reclamation());
            stm.setInt(4, reclamation.getID_Admin());
            stm.setString(5, reclamation.getObjet());
            stm.setString(6, reclamation.getTexte());

            //stm.setString(9, reclamation.getemail());

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
            stm.setString(1, reclamation.getEmail());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
