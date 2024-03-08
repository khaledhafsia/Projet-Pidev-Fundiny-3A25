package org.example.Services;

//import org.example.Entities.Collaboration;
//import tn.esprit.models.Projet;
//import tn.esprit.interfaces.IService;
//import tn.esprit.utils.MyDataBase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Entities.Projet;
import org.example.Entities.investissements;
import org.example.interfaces.Iservice;
import org.example.utils.MyDataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.swing.*;
import java.sql.*;
import java.sql.Date;





import java.util.ArrayList;
import java.util.List;

public class ServiceProjet implements Iservice<Projet> {
    private Connection cnx ;
    public ServiceProjet(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void importProjetFromExcel(String filePath) {
        try (FileInputStream excelFile = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheetAt(0); // Suppose que vos données sont sur la première feuille
            for (Row row : sheet) {
                int user_id = (int) row.getCell(0).getNumericCellValue();
                String nomPr = row.getCell(1).getStringCellValue(); // NomPr est dans la première colonne du fichier Excel
                String nomPo = row.getCell(2).getStringCellValue(); // NomPo est dans la deuxième colonne du fichier Excel
                Date dateD = (Date) row.getCell(3).getDateCellValue();
                int CA = (int) row.getCell(4).getNumericCellValue();

                // Insérer les données dans la table Projet uniquement
                add(new Projet(user_id,nomPr,nomPo,dateD,CA)); // Vous devez implémenter cette méthode
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void add(Projet projet) {
        String qry = "INSERT INTO `projet`( `user_id`,`nomPr`, `nomPo`, `dateD`, `CA`) VALUES (?,?,?,?,?)" ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,projet.getUser_id());
            stm.setString(2,projet.getNomPr());
            stm.setString(3, projet.getNomPo());
            stm.setDate(4, projet.getDateD());
            stm.setInt(5,projet.getCA());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Projet> getAll() {
        ArrayList<Projet> projets = new ArrayList<>();
        String qry = "SELECT * FROM projet";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(qry)) {

            while (rs.next()) {
                int id = rs.getInt(1);
                int user_id = rs.getInt(2);
                String nomPr = rs.getString(3);
                String nomPo = rs.getString(4);
                Date dateD = rs.getDate("dateD");
                int CA = rs.getInt(6);

                Projet pr = new Projet(id,user_id, nomPr, nomPo,dateD, CA);
                projets.add(pr);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return projets;
    }
    @Override
    public void update(Projet projet) {
        String qry = "UPDATE `projet` SET `user_id`=?, `nomPr`=?, `nomPo`=?, `dateD`=?, `CA`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, projet.getUser_id());
            stm.setString(2, projet.getNomPr());
            stm.setString(3, projet.getNomPo());
            stm.setString(4, String.valueOf(projet.getDateD()));
            stm.setInt(5, projet.getCA());
            stm.setInt(6, projet.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour le projet avec l'ID : " + projet.getId());
            } else {
                System.out.println("Mise à jour réussie pour le projet avec l'ID : " + projet.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }


    @Override
    public boolean delete(Projet projet) {
        String qry = "DELETE FROM `projet` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, projet.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour le projet avec l'ID : " + projet.getId());
                return false;
            } else {
                System.out.println("Projet supprimé avec succès, ID : " + projet.getId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet : " + e.getMessage());
            return false;
        }}
    public List<Projet> afficherListe() throws SQLException {
        String req = "SELECT * FROM projet";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Projet> lv = new ArrayList<Projet>();
        while (rs.next()) {
            Projet p = new Projet(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("nomPr"),
                    rs.getString("nomPo"),
                    rs.getDate("dateD"),
                    rs.getInt("CA")
            );
            lv.add(p);
        }

        return lv;
    }
//    public ArrayList<Projet> getProjectsByUserId(int userId) {
//        ArrayList<Projet> projets = new ArrayList<>();
//        String qry = "SELECT * FROM projet";
//
//        try (Statement stm = cnx.createStatement();
//             ResultSet rs = stm.executeQuery(qry)) {
//
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                int user_id = rs.getInt(2);
//                String nomPr = rs.getString(3);
//                String nomPo = rs.getString(4);
//                Date dateD = rs.getDate("dateD");
//                int CA = rs.getInt(6);
//
//                Projet pr = new Projet(1,MyDataBase.getInstance().getIdenvoi(), nomPr, nomPo,dateD, CA);
//                projets.add(pr);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return projets;
//    }


    public ArrayList<Projet> getProjectsByUserId(int userId) {
        ArrayList<Projet> projets = new ArrayList<>();
        String qry = "SELECT * FROM projet WHERE user_id = ?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, userId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int user_id = rs.getInt(2);
                    String nomPr = rs.getString(3);
                    String nomPo = rs.getString(4);
                    Date dateD = rs.getDate("dateD");
                    int CA = rs.getInt(6);
                    Projet pr = new Projet(id, user_id, nomPr, nomPo, dateD, CA);
                    projets.add(pr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }


//    @Override
//    public List<Projet> getProjectsByUserId(int id) {
//        List<Projet> prs = new ArrayList<>();
//        String qry = "SELECT * FROM projet WHERE user_id = ?";
//        try {
//            PreparedStatement stm = cnx.prepareStatement(qry);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Projet pr = new Projet();
//                pr.set(rs.getInt("id"));
//                pr.setUserID(rs.getInt("userID"));
//                pr.setProjetID(rs.getInt("projetID"));
//                pr.setMontant(rs.getDouble("montant"));
//                pr.setDescription(rs.getString("description"));
//                pr.setDate(rs.getTimestamp("date")); // Convert SQL Date to LocalDate
//                // Add more properties as needed
//                prs.add(pr);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error getting investments by user ID: " + e.getMessage());
//        }
//        return invs;
//    }

}



