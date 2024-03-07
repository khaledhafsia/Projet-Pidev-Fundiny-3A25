package tn.esprit.controls.reponses;

import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import tn.esprit.models.Reclamation;
import tn.esprit.models.Reponse;

import java.io.File;
import java.io.IOException;

public class ExportToPDF {

    public static void exportToPDF(ObservableList<Reponse> dataList, String outputPath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Load the custom font
            PDType0Font font = PDType0Font.load(document, new File("C:/Users/Amine/Desktop/Project/ReclamationV3-060324/src/main/java/tn/esprit/controls/reponses/Helvetica-Bold-Font.ttf"));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(font, 14);
                contentStream.newLineAtOffset(20, 700);

                // Header
                String header = "ID | utilisateur | Objet";
                contentStream.showText(header);
                contentStream.newLine();

                // Table rows
                float margin = 20;
                float yStart = 700;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                float lineHeight = 14;
                float cellMargin = 2;

                for (Reponse reponse : dataList) {
                    contentStream.newLineAtOffset(margin, -lineHeight);
                    yPosition -= lineHeight;

                    String rowContent = String.format("%s | %s | %s |",
                            reponse.getID_Reponse(), reponse.getID_Utilisateur(), reponse.getobjet());

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
