package tn.esprit.services;

import tn.esprit.interfaces.IserviceINV;
import tn.esprit.models.taches;
import tn.esprit.utils.MyDataBase;
import tn.esprit.interfaces.Iservice;
import tn.esprit.models.investissements;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class serviceInvestissements implements Iservice<investissements>, IserviceINV {
    private Connection cnx;
    public serviceInvestissements(){
        cnx=MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(investissements investissements) {
        String qry ="INSERT INTO `investissements`(`userID`, `projetID`, `montant`,`description`, `date`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, investissements.getUserID());
            stm.setInt(2, investissements.getProjetID());
            stm.setDouble(3, investissements.getMontant());
            stm.setString(4, investissements.getDescription());

            // Generate current timestamp
            Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
            investissements.setDate(currentTimestamp); // Set the timestamp
            stm.setTimestamp(5, currentTimestamp); // Set timestamp in the prepared statement

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<investissements> getAll() {
        ArrayList<investissements> invs = new ArrayList<>();
        String qry ="SELECT * FROM `investissements`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                investissements i = new investissements();

                i.setInvID(rs.getInt(1));
                i.setUserID(rs.getInt(2));
                i.setProjetID(rs.getInt(3));
                i.setMontant(rs.getDouble(4));
                i.setDescription(rs.getString(5));

                // Retrieve the Timestamp from the result set and set it to the investissements object
                Timestamp timestamp = rs.getTimestamp(6);
                i.setDate(timestamp);

                invs.add(i);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invs;
    }

    @Override
    public void update(investissements investissements) {
        String qry = "UPDATE `investissements` SET `userID`=?, `projetID`=?, `montant`=?, `description`=?, `date`=? WHERE `invID`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, investissements.getUserID());
            stm.setInt(2, investissements.getProjetID());
            stm.setDouble(3, investissements.getMontant());
            stm.setString(4, investissements.getDescription());
            stm.setTimestamp(5, investissements.getDate()); // Use the existing date value
            stm.setInt(6, investissements.getInvID()); // Use the existing invID value

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour l'investissement avec l'ID : " + investissements.getInvID());
            } else {
                System.out.println("Mise à jour réussie pour l'investissement avec l'ID : " + investissements.getInvID());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'investissement : " + e.getMessage());
        }
    }

    @Override
    public boolean delete(investissements investissements) {
        String qry = "DELETE FROM investissements WHERE `invID`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, investissements.getInvID());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour l'investissement' l'ID : " + investissements.getInvID());
                return false; // Aucune ligne supprimée
            } else {
                System.out.println("investissement supprimé avec succès, ID : " + investissements.getInvID());
                return true; // Suppression réussie
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'investissement : " + e.getMessage());
            return false; // Erreur lors de la suppression
        }
    }

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
    public List<investissements> getInvestissementByUserId(int id) {
        List<investissements> invs = new ArrayList<>();
        String qry = "SELECT * FROM investissements WHERE userID = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                investissements inv = new investissements();
                inv.setInvID(rs.getInt("invID"));
                inv.setUserID(rs.getInt("userID"));
                inv.setProjetID(rs.getInt("projetID"));
                inv.setMontant(rs.getDouble("montant"));
                inv.setDescription(rs.getString("description"));
                inv.setDate(rs.getTimestamp("date")); // Convert SQL Date to LocalDate
                // Add more properties as needed
                invs.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error getting tasks by investment ID: " + e.getMessage());
        }
        return invs;
    }




}
