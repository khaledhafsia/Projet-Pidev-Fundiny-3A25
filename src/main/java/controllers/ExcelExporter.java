package controllers;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public static void exportToExcel(ListView<Projet> projetListView, ListView<Collaboration> collaborationListView, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet projetSheet = workbook.createSheet("Projets");
            Sheet collaborationSheet = workbook.createSheet("Collaborations");

            exportProjetListViewToSheet(projetListView, projetSheet);
            exportCollaborationListViewToSheet(collaborationListView, collaborationSheet);

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    private static void exportProjetListViewToSheet(ListView<Projet> projetListView, Sheet sheet) {
        ObservableList<Projet> items = projetListView.getItems();
        for (int i = 0; i < items.size(); i++) {
            Row row = sheet.createRow(i + 1); // Start from row 1 to leave the first row for headers
            Projet projet = items.get(i);
            Cell cellNomPr = row.createCell(0);
            cellNomPr.setCellValue(projet.getNomPr());
            Cell cellNomPo = row.createCell(1);
            cellNomPo.setCellValue(projet.getNomPo());
            Cell cellDateD = row.createCell(2);
            cellDateD.setCellValue(projet.getDateD());
            Cell cellCA = row.createCell(3);
            cellCA.setCellValue(projet.getCA());
        }
        // Add headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("NomPr");
        headerRow.createCell(1).setCellValue("NomPo");
        headerRow.createCell(2).setCellValue("DateD");
        headerRow.createCell(3).setCellValue("CA");
    }

    private static void exportCollaborationListViewToSheet(ListView<Collaboration> collaborationListView, Sheet sheet) {
        ObservableList<Collaboration> items = collaborationListView.getItems();
        for (int i = 0; i < items.size(); i++) {
            Row row = sheet.createRow(i + 1); // Start from row 1 to leave the first row for headers
            Collaboration collaboration = items.get(i);
            Cell cellNomColl = row.createCell(0);
            cellNomColl.setCellValue(collaboration.getNomColl());
            Cell cellTypeColl = row.createCell(1);
            cellTypeColl.setCellValue(collaboration.getTypeColl());
            Cell cellDateColl = row.createCell(2);
            cellDateColl.setCellValue(collaboration.getDateColl());
        }
        // Add headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("NomColl");
        headerRow.createCell(1).setCellValue("TypeColl");
        headerRow.createCell(2).setCellValue("DateColl");
    }
}

