package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {

    public static void importFromExcel(String filePath) throws IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_données", "utilisateur", "mot_de_passe");
             FileInputStream excelFile = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet sheet = workbook.getSheetAt(0); // Suppose que vos données sont sur la première feuille
            for (Row row : sheet) {
                String nom = row.getCell(0).getStringCellValue(); // Suppose que le nom est dans la première colonne
                // Extraire d'autres données selon la structure de votre fichier Excel

                // Insérer les données dans la base de données
                String sql = "INSERT INTO votre_table (nom, ...) VALUES (?, ...)";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, nom);
                    // Définir les autres paramètres pour les autres colonnes
                    statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

