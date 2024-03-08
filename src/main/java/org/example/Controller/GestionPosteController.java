package org.example.Controller;


import javafx.fxml.FXMLLoader;
import java.net.URL;
import org.example.Entities.Poste;
import org.example.Entities.Poste.categorie;
import org.example.Services.PosteService;
import org.example.utils.MyDataBase;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;





public class GestionPosteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;




    ObservableList <Poste> list = FXCollections.observableArrayList();
    @FXML
    private TextField titreField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField titreField2;
    @FXML
    private TableView<Poste> posteTable;
    @FXML
    private TableColumn<Poste, String> titreColumn;
    @FXML
    private TableColumn<Poste, String> descriptionColumn;
    @FXML
    private TableColumn<Poste, String> imageColumn;

    @FXML
    private TableColumn<Poste, String> categorieColumn;

    @FXML
    private ChoiceBox<Poste.categorie> categorieChoiceBox;


    ObservableList <Poste> posteList2 = FXCollections.observableArrayList();

    ObservableList <Poste> posteList = FXCollections.observableArrayList();

    private static Poste selectedPoste;

    private PosteService posteService;


    private int posteId;
    private String imagePath;
    private int id_selected;




    //  @FXML
//    private Button backButton;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posteList = FXCollections.observableArrayList();
        Connection con = MyDataBase.getInstance().getCnx();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Poste`");
            while (rs.next()) {
                Poste poste = new Poste(

                        rs.getString("titre"),
                        rs.getString("discription"),
                        rs.getString("image")
                        //rs.getString("selectedCategorie")
                );
                posteList.add(poste);
            }
            posteTable.setItems(posteList);
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("discription"));
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
            categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            categorieChoiceBox.getItems().addAll(Poste.categorie.values());


            //categorieChoiceBox.setValue(Categorie.SELF_CARE); // Set a default value if needed
        } catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        posteService = new PosteService();
        System.out.println("categorieChoiceBox initialized: " + (categorieChoiceBox != null));

    }
    @FXML
    private void handleSearch(ActionEvent event) {
        String titre = titreField2.getText();
        List<Poste> postes = posteService.getByTitre(titre);
        ObservableList<Poste> observableList = FXCollections.observableArrayList(postes);
        posteTable.setItems(observableList);

    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String titre = titreField.getText();
        String description = descriptionField.getText();
        String image = imageField.getText();

        // Convert the selected String to the corresponding enum
        Poste.categorie selectedCategorie = categorieChoiceBox.getValue();

        if (!titre.isEmpty() && !description.isEmpty() && selectedCategorie != null) {
            try {
                // Create a new Poste instance
                Poste newPoste = new Poste(titre, description, image, selectedCategorie);

                // Call the addPoste method in the service to insert the instance into the database
                posteService.addPoste(newPoste);

                // Show a success message
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Poste added successfully!");
                alert.showAndWait();

                // Clear the input fields
                titreField.clear();
                descriptionField.clear();
                imageField.clear();
                categorieChoiceBox.setValue(null);

            } catch (SQLException ex) {
                // Show an error message if there was a problem adding the Poste
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error adding Poste: " + ex.getMessage());
                alert.showAndWait();
            }
        } else {
            // Show a warning message if any fields are empty
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields and select a category");
            alert.showAndWait();
        }
    }




    @FXML
    private void tableview_clicked(MouseEvent event) {

        selectedPoste = posteTable.getSelectionModel().getSelectedItem();
        if (selectedPoste != null) {

            titreField.setText(selectedPoste.getTitre());
            descriptionField.setText(selectedPoste.getDiscription());
            imageField.setText(selectedPoste.getImage());
            categorieChoiceBox.setValue(selectedPoste.getCategorie());

            id_selected = selectedPoste.getId();
            System.out.println("Selected ID: " + id_selected); // Add this line for debugging
        }
    }
/*
    @FXML
    private void tableview_clicked(MouseEvent event) {

            selectedPoste = (Poste) posteTable.getItems();
            int index = posteTable.getSelectionModel().getSelectedItem().getId();
            titreField.setText(posteTable.getSelectionModel().getSelectedItem().getTitre());
            descriptionField.setText(posteTable.getSelectionModel().getSelectedItem().getDiscription());
            imageField.setText(posteTable.getSelectionModel().getSelectedItem().getImage()+"");
             categorieChoiceBox.setValue(selectedPoste.getCategorie());
             id_selected=posteTable.getSelectionModel().getSelectedItem().getId();
    }

*/


    @FXML
    private void handleUpdate(ActionEvent event) {
        if (id_selected != 0) {
            try {
                // Get the selected categorie from the ChoiceBox
                Poste.categorie categorie = categorieChoiceBox.getSelectionModel().getSelectedItem();

                // Update the selected Poste instance
                Poste poste = new Poste(titreField.getText(), descriptionField.getText(), imageField.getText(), categorie);

                // Update the Poste in the database
                posteService.updatePoste(poste, id_selected);

                // Show a success message
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Poste updated");
                alert.show();

                // Refresh the table view with updated data
                posteList.clear();
                posteList.addAll(posteService.getAll());

            } catch (SQLException ex) {
                Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Failed to update poste");
                alert.show();
            }
        } else {
            // Show a warning message if no item is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a poste to update");
            alert.show();
        }
    }




    @FXML
    private void handleDelete(javafx.event.ActionEvent event) throws IOException {
        PosteService posteService = new PosteService();

        try {
            Poste selectedPoste = posteTable.getSelectionModel().getSelectedItem();
            posteService.deletePoste(selectedPoste.getId());

            // Remove the deleted Poste from the table view
            posteTable.getItems().remove(selectedPoste);

            // Show a success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Poste deleted successfully!");
            alert.showAndWait();

        } catch (Exception e) {
            // Show an error message if there was a problem deleting the Poste
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error deleting Poste: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void refresh_pressed()
    {
        posteList = FXCollections.observableArrayList();
        Connection con = MyDataBase.getInstance().getCnx();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Poste`");
            while (rs.next()) {
                Poste poste = new Poste(

                        rs.getString("titre"),
                        rs.getString("discription"),
                        rs.getString("image")
                );
                posteList.add(poste);
            }
            posteTable.setItems(posteList);
            titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("discription"));
            imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        }
        catch (SQLException ex) {
            Logger.getLogger(GestionPosteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        titreField.setText("");
        descriptionField.setText("");
        imageField.setText("");
        posteTable.setItems(posteList);
    }


    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*
public void generatePDF(Poste poste) {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("poste.pdf"));
        document.open();
        document.add(new Paragraph("ID: " + poste.getId()));
        document.add(new Paragraph("titre: " + poste.getTitre()));
        document.add(new Paragraph("discription: " + poste.getDiscription()));
        document.close();
        System.out.println("PDF file created!");
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }



}

    @FXML
    public void handlepdf(ActionEvent event) {
    Poste poste = new Poste( "Titre", "Discription", "image.png");
    generatePDF(poste);
}


*/

}



