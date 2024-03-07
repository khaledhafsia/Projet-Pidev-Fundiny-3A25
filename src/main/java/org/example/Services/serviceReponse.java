package org.example.Services;

import  org.example.interfaces.IServices;
import  org.example.Entities.Reponse;

import java.sql.*;
import java.util.ArrayList;

public class serviceReponse implements IServices<Reponse> {
    private Connection cnx;

    @Override
    public void add(Reponse reponse) {
        String qry = "INSERT INTO `reponses`(`ID_Utilisateur`, `emailUser`, `objet`, `texte`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, reponse.getID_Utilisateur());
            stm.setString(2, reponse.getemail());
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
                reponse.setID_Reponse(rs.getInt("ID_Reponse"));
                reponse.setID_Utilisateur(rs.getInt("ID_Utilisateur"));
                reponse.setemail(rs.getString("email"));
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
                "`ID_Reclamation`=?, " +
                "`ID_Administrateur`=?, " +
                "`Contenu_Reponse`=?, " +
                "`Date_Reponse`=? " +
                "WHERE `ID_Reponse`=?";

        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, reponse.getemail());
            stm.setString(2, reponse.getemail());
            stm.setString(3, reponse.getobjet());
            stm.setString(4, reponse.gettexte());

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
