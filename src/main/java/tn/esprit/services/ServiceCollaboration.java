package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceCollaboration implements IService<Collaboration> {
    private Connection cnx ;
    public ServiceCollaboration(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
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

    @Override
    public ArrayList<Collaboration> getAll() {
        ArrayList<Collaboration> collaborations = new ArrayList();
        String qry = "SELECT * FROM `collaboration`" ;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Collaboration cl = new Collaboration();
                cl.setId_collaboration(rs.getInt(1));
                cl.setNomColl(rs.getString(2));
                cl.setTypeColl(rs.getString(3));
                cl.setDateColl(rs.getDate(4));

                Collaboration c = new Collaboration();
                collaborations.add(cl);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return collaborations;
    }

    @Override
    public void update(Collaboration collaboration) {
        String qry = "UPDATE `collaboration` SET `nomColl`=?, `typeColl`=?, `dateColl`=? WHERE `id_collaboration`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, collaboration.getNomColl());
            stm.setString(2, collaboration.getTypeColl());
            stm.setString(3, String.valueOf(collaboration.getDateColl()));
            stm.setInt(4, collaboration.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour la collaboration avec l'ID : " + collaboration.getId());
            } else {
                System.out.println("Mise à jour réussie pour la collaboration avec l'ID : " + collaboration.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la collaboration : " + e.getMessage());
        }

    }

    @Override
    public boolean delete(Collaboration collaboration) {
        String qry = "DELETE FROM `collaboration` WHERE `id_collaboration`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, collaboration.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour la collaboration avec l'ID : " + collaboration.getId());
                return false; // Aucune ligne supprimée
            } else {
                System.out.println("Collaboration supprimée avec succès, ID : " + collaboration.getId());
                return true; // Suppression réussie
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la collaboration : " + e.getMessage());
            return false; // Erreur lors de la suppression
        }
    }
}
