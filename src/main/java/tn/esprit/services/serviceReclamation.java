package tn.esprit.services;

import tn.esprit.interfaces.IServices;
import tn.esprit.models.Reclamation;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

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
}
