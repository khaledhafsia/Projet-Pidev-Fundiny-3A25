package tn.esprit.services;

import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.interfaces.IService;
import tn.esprit.utils.MyDataBase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class ServiceProjet implements IService<Projet> {
    private Connection cnx ;
    public ServiceProjet(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void importProjetFromExcel(String filePath) {
        try (FileInputStream excelFile = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheetAt(0); // Suppose que vos données sont sur la première feuille
            for (Row row : sheet) {
                String nomPr = row.getCell(0).getStringCellValue(); // NomPr est dans la première colonne du fichier Excel
                String nomPo = row.getCell(1).getStringCellValue(); // NomPo est dans la deuxième colonne du fichier Excel
                Date dateD = (Date) row.getCell(2).getDateCellValue();
                int CA = (int) row.getCell(3).getNumericCellValue();

                // Insérer les données dans la table Projet uniquement
                add(new Projet(nomPr,nomPo,dateD,CA)); // Vous devez implémenter cette méthode
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void add(Projet projet) {
        String qry = "INSERT INTO `projet`( `nomPr`, `nomPo`, `dateD`, `CA`) VALUES (?,?,?,?)" ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,projet.getNomPr());
            stm.setString(2, projet.getNomPo());
            stm.setString(3, projet.getDateD().toString());
            stm.setInt(4,projet.getCA());

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
                String nomPr = rs.getString(2);
                String nomPo = rs.getString(3);
                Date dateD = rs.getDate("dateD");
                int CA = rs.getInt(5);

                Projet pr = new Projet(id, nomPr, nomPo,dateD, CA);
                projets.add(pr);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return projets;
    }
    @Override
    public void update(Projet projet) {
        String qry = "UPDATE `projet` SET `nomPr`=?, `nomPo`=?, `dateD`=?, `CA`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, projet.getNomPr());
            stm.setString(2, projet.getNomPo());
            stm.setString(3, String.valueOf(projet.getDateD()));
            stm.setInt(4, projet.getCA());
            stm.setInt(5, projet.getId());

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
                    rs.getString("nomPr"),
                    rs.getString("nomPo"),
                    rs.getDate("dateD"),
                    rs.getInt("CA")
            );
            lv.add(p);
        }

        return lv;
    }
}



