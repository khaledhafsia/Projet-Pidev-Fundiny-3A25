package tn.esprit.services;

import tn.esprit.interfaces.Iservice;
import tn.esprit.interfaces.IserviceINV;
import tn.esprit.interfaces.IserviceTache;
import tn.esprit.models.investissements;
import tn.esprit.models.taches;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class serviceTaches implements Iservice<taches>, IserviceINV, IserviceTache {
    private Connection cnx;
    public serviceTaches(){
        cnx= MyDataBase.getInstance().getCnx();
    }


    @Override
    public void add(taches taches) {
        String qry ="INSERT INTO `taches`(`invID`, `titre`, `priorite`,`statut`, `echeanceD`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, taches.getInvID());
            stm.setString(2, taches.getTitre());
            stm.setString(3, taches.getPriorite());
            stm.setString(4, taches.getStatut());
            stm.setDate(5, taches.getEcheanceD());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<taches> getAll() {
        ArrayList<taches> tch = new ArrayList<>();
        String qry ="SELECT * FROM `taches`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                taches i = new taches();

                i.setTacheID(rs.getInt(1));
                i.setInvID(rs.getInt(2));
                i.setTitre(rs.getString(3));
                i.setPriorite(rs.getString(4));
                i.setStatut(rs.getString(5));
                i.setEcheanceD(rs.getDate(6));


                tch.add(i);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tch;
    }

    @Override
    public void update(taches taches) {
        String qry = "UPDATE `taches` SET `invID`=?, `titre`=?, `priorite`=?, `statut`=?, `echeanceD`=? WHERE `tacheID`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, taches.getInvID());
            stm.setString(2, taches.getTitre());
            stm.setString(3, taches.getPriorite());
            stm.setString(4, taches.getStatut());
            stm.setDate(5, taches.getEcheanceD()); // Use the existing date value
            stm.setInt(6, taches.getTacheID()); // Use the existing invID value

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour la tache avec l'ID : " + taches.getTacheID());
            } else {
                System.out.println("Mise à jour réussie pour la tache avec l'ID : " + taches.getTacheID());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la tache : " + e.getMessage());
        }

    }

    @Override
    public boolean delete(taches taches) {
        String qry = "DELETE FROM taches WHERE `tacheID`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, taches.getTacheID());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour la tache' l'ID : " + taches.getTacheID());
                return false; // Aucune ligne supprimée
            } else {
                System.out.println("tache supprimé avec succès, ID : " + taches.getTacheID());
                return true; // Suppression réussie
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la tache : " + e.getMessage());
            return false; // Erreur lors de la suppression
        }
    }


    @Override
    public investissements getInvestissementById(int id) {
        String qry = "SELECT * FROM `investissements` WHERE `invID` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                investissements i = new investissements();
                i.setInvID(rs.getInt("invID"));
                i.setUserID(rs.getInt("userID"));
                i.setProjetID(rs.getInt("projetID"));
                i.setMontant(rs.getDouble("montant"));
                i.setDescription(rs.getString("description"));
                i.setDate(rs.getTimestamp("date"));
                return i;
            }
        } catch (SQLException e) {
            System.out.println("Error getting investissement by ID: " + e.getMessage());
        }
        return null; // Return null if no investissement with the given ID is found
    }

    @Override
    public List<taches> getTasksByInvestissementID(int invID) {
        List<taches> tasks = new ArrayList<>();
        String qry = "SELECT * FROM taches WHERE invID = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, invID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                taches task = new taches();
                task.setTacheID(rs.getInt("tacheID"));
                task.setInvID(rs.getInt("invID"));
                task.setTitre(rs.getString("titre"));
                task.setPriorite(rs.getString("priorite"));
                task.setStatut(rs.getString("statut"));
                task.setEcheanceD(rs.getDate("echeanceD")); // Convert SQL Date to LocalDate
                // Add more properties as needed
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error getting tasks by investment ID: " + e.getMessage());
        }
        return tasks;
    }

}
