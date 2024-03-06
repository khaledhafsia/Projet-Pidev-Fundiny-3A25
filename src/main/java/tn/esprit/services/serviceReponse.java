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
}
