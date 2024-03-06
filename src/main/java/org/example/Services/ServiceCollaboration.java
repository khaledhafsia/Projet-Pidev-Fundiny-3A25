package org.example.Services;

import org.example.interfaces.Iservice;
import org.example.Entities.Collaboration;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCollaboration {}
 /*
    private Connection cnx ;
    public ServiceCollaboration(){
        cnx = MyDataBase.getInstance().getCnx();
    }

    public void add(Collaboration collaboration) {
        String qry = "INSERT INTO `collaboration`( `nomColl`, `TypeColl`, `dateColl`,`id`) VALUES (?,?,?,?)" ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,collaboration.getNomColl());
            stm.setString(2,collaboration.getTypeColl());
            stm.setString(3, String.valueOf(collaboration.getDateColl()));
            stm.setInt(4,collaboration.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

public ArrayList<Collaboration> getAll() {
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String qry = "SELECT * FROM collaboration" ;
        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(qry)) {

            while (rs.next()) {
                int id_collaboration = rs.getInt("id_collaboration");
                String nomColl = rs.getString("nomColl");
                String TypeColl = rs.getString("TypeColl");
                Date dateColl = rs.getDate("dateColl");
                int id = rs.getInt("id");

                Collaboration sc = new Collaboration(id_collaboration, nomColl, TypeColl,dateColl, id);
                collaborations.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collaborations;
    }




    public void update(Collaboration collaboration) {
        String qry = "UPDATE collaboration SET `nomColl`=?, `typeColl`=?, `dateColl`=? WHERE `id_collaboration`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, collaboration.getNomColl());
            stm.setString(2, collaboration.getTypeColl());
            stm.setString(3, String.valueOf(collaboration.getDateColl()));
            stm.setInt(4, collaboration.getId_collaboration());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour la collaboration avec l'ID : " + collaboration.getId_collaboration());
            } else {
                System.out.println("Mise à jour réussie pour la collaboration avec l'ID : " + collaboration.getId_collaboration());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la collaboration : " + e.getMessage());
        }

    }


    public boolean delete(Collaboration collaboration) {
        String qry = "DELETE FROM collaboration WHERE `id_collaboration`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, collaboration.getId_collaboration());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour la collaboration avec l'ID : " + collaboration.getId_collaboration());
                return false;
            } else {
                System.out.println("Collaboration supprimée avec succès, ID : " + collaboration.getId_collaboration());
                return true; //
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la collaboration : " + e.getMessage());
            return false;
        }
    }

    public List<Collaboration> afficherListe() throws SQLException {
        String req = "SELECT * FROM collaboration";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Collaboration> users = new ArrayList<Collaboration>();
        while (rs.next()) {
            Collaboration p = new Collaboration(
                    rs.getInt("id_collaboration"),
                    rs.getString("nomColl"),
                    rs.getString("TypeColl"),
                    rs.getDate("dateColl"),
                    rs.getInt("id")
            );
            users.add(p);
        }

        return users;
    }
}

  */