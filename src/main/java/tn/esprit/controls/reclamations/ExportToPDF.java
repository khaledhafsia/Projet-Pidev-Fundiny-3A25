package tn.esprit.controls.reclamations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.io.File;
import java.io.IOException;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import tn.esprit.models.Reclamation;

public class ExportToPDF {

    public static void exportToPDF(ObservableList<Reclamation> dataList, String outputPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Load the custom font
            PDType0Font font = PDType0Font.load(document, new File("C:/Users/Amine/Desktop/Project/ReclamationV3-060324/src/main/java/tn/esprit/controls/reclamations/Helvetica-Bold-Font.ttf"));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(font, 14);
                contentStream.newLineAtOffset(20, 700);

                // Header
                String header = "ID | Email | Admin | Projet | Probleme | Objet";
                contentStream.showText(header);
                contentStream.newLine();

                // Table rows
                float margin = 20;
                float yStart = 700;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                float lineHeight = 14;
                float cellMargin = 2;

                for (Reclamation reclamation : dataList) {
                    contentStream.newLineAtOffset(margin, -lineHeight);
                    yPosition -= lineHeight;

                    String rowContent = String.format("%s | %s | %s | %s | %s | %s",
                            reclamation.getID_Reclamation(), reclamation.getEmail(), reclamation.getID_Admin(),
                            reclamation.getID_Projet(), reclamation.getID_Type_Reclamation(), reclamation.getObjet());

                    contentStream.showText(rowContent);
                    contentStream.newLineAtOffset(0, -cellMargin);
                }

                contentStream.endText();
            }

            document.save(new File(outputPath));
            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error alert)
        }
    }
}
