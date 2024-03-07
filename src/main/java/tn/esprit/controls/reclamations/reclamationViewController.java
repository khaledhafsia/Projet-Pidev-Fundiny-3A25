package tn.esprit.controls.reclamations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Reclamation;
import tn.esprit.utils.MyDataBase;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrinterJob;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.swing.text.Document;

public class reclamationViewController implements javafx.fxml.Initializable {

    @FXML
    private ListView<Reclamation> ReclamationListView;

    private final ObservableList<Reclamation> RecList = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Button printBtn;

    @FXML
    private Button pdfBtn;

    @FXML
    private ComboBox<String> sortingComboBox;

    private final ObservableList<String> sortingOptions = FXCollections.observableArrayList("ID", "Email");
    private ExportToPDF ExportToPDF;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Your existing code...
        loadData();

    }


    private void loadData() {
        ReclamationListView.setItems(RecList);
        try {
            RecList.addAll(MyDataBase.getInstance().getAllReclamations());

            ReclamationListView.setCellFactory(param -> new ListCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setStyle("-fx-cursor: hand; -fx-base: #00E676;");

                    HBox buttons = new HBox(editButton);
                    buttons.setStyle("-fx-alignment:center");
                    setGraphic(buttons);

                    // Handle click on list item
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1) {
                            showCardView(getItem());
                        }
                    });
                }

                @Override
                protected void updateItem(Reclamation item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText("ID: " + item.getID_Reclamation() + ", Projet: " + item.getID_Projet() +
                                ", Probleme: " + item.getID_Type_Reclamation() + ", Objet: " + item.getObjet());
                        setGraphic(getGraphic());
                    }
                }

                private void handleEditReclamation() {
                    // Implement the logic for editing a reclamation here
                    // You can open another dialog or navigate to another view for editing
                    // Make sure to update the RecList and refresh the view if needed
                }
            });

        } catch (Exception e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void showCardView(Reclamation selectedReclamation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamations/CardViewRec.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec le contenu chargé depuis le FXML
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre (stage)
            Stage stage = new Stage();
            stage.setScene(scene);

            // Obtenez le contrôleur du chargeur
            CardViewRec cardViewController = loader.getController();

            // Utilisez la méthode setData pour définir les données
            cardViewController.setData(selectedReclamation);

            // Affichez la nouvelle fenêtre (stage)
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void getAddReclamation(MouseEvent event) {
        System.out.println("Add button clicked!");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/reclamations/addReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            // Refresh the data after adding a new reclamation
            RecList.clear();
            RecList.addAll(MyDataBase.getInstance().getAllReclamations());

        } catch (IOException e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void handleRefresh(MouseEvent event) {
        // Refresh the data
        refreshData();

    }

    private void refreshData() {
        RecList.clear();
        RecList.addAll(MyDataBase.getInstance().getAllReclamations());
    }

    @FXML
    private void handleSearch(MouseEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();

        // You can add more criteria as needed
        RecList.setAll(MyDataBase.getInstance().searchReclamations(searchText));
    }

    @FXML
    private void handlePDF(MouseEvent event) {
        // Specify the path where you want to save the PDF
        String outputPath = "C:/Users/Amine/Desktop/Reclamation.pdf";

        // Call the exportToPDF method from ExportToPDF class
        ExportToPDF.exportToPDF(RecList, outputPath);

        // Optionally, show an alert indicating that the PDF has been generated
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF Generation");
        alert.setHeaderText(null);
        alert.setContentText("PDF generated successfully.");
        alert.showAndWait();
    }

    @FXML
    private void handlePrint(MouseEvent event) {
        Node nodeToPrint = ReclamationListView.getScene().getRoot();

        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(nodeToPrint.getScene().getWindow())) {
            boolean success = printerJob.printPage(nodeToPrint);

            if (success) {
                printerJob.endJob();
            }
        }
    }
}
