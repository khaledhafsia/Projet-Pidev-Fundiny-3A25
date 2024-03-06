package tn.esprit.controls.reclamations;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import tn.esprit.utils.MyDataBase;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddReclamationControllers implements Initializable {

    @FXML
    private TextField objetFld;
    @FXML
    private TextArea texte;
    @FXML
    private TextField emailFld; // Replace with appropriate element type

    @FXML
    private ComboBox<String> projetComboBox;
    @FXML
    private ComboBox<String> typeReclamationComboBox;
    @FXML
    private ComboBox<String> adminComboBox;

    private boolean update;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    private MyDataBase cnx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx = MyDataBase.getInstance(); // Initialize the MyDataBase instance
        connection = cnx.getCnx();
        populateAdminComboBox();
        populateProjetComboBox();
        populateProblemComboBox();
    }

    @FXML
    private void save(MouseEvent event) throws IOException {
        String email = emailFld.getText().trim();

        if (email.isEmpty()) {
            showAlert("Champ email obligatoire!");
        } else {
            System.out.println("champs e-mail saisie : " + email);
        }

        String objet = objetFld.getText();
        if (objet.isEmpty()) {
            showAlert("Champ objet obligatoire!");
        } else {
            System.out.println("champs objet saisie : " + objet);
        }

        String Texte = texte.getText();
        if (Texte.isEmpty()) {
            showAlert("Champ Texte obligatoire!");
        } else {
            System.out.println("champs Texte saisie : " + Texte);
        }

        String nomAdmin = adminComboBox.getValue();
        if (nomAdmin.isEmpty()) {
            showAlert("Champ nomAdmin obligatoire!");
        } else {
            System.out.println("champs nomAdmin saisie : " + nomAdmin);
        }
        String nomProjet = projetComboBox.getValue();
        if (nomProjet.isEmpty()) {
            showAlert("Champ nomProjet obligatoire!");
        } else {
            System.out.println("Adresse nomProjet saisie : " + nomProjet);
        }
        String nomTypeReclamation = typeReclamationComboBox.getValue();
        if (nomTypeReclamation.isEmpty()) {
            showAlert("Champ nomTypeReclamation obligatoire!");
        } else {
            System.out.println("Adresse nomTypeReclamation saisie : " + nomTypeReclamation);
        }

        if (email.isEmpty() || objet.isEmpty() || texte.isWrapText() || nomAdmin.isEmpty() || nomProjet.isEmpty() || nomTypeReclamation.isEmpty()) {
            System.out.println("Please Fill All DATA");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            getQuery();
            try {
                if (connection != null) {  // Check if connection is not null
                    insert();
                } else {
                    System.out.println("Connection is null");
                    // Handle the case when the connection is null
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddReclamationControllers.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Error executing query. Please try again later.");
                alert.showAndWait();
            }
        }
    }

    private void getQuery() {
        if (update == false) {
            query = "INSERT INTO `reclamations`(`email`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `objet`, `texte`) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            query = "UPDATE reclamations SET email=?, ID_Projet=?, ID_Type_Reclamation=?, ID_Admin=?, objet=?, texte=? WHERE id ID_Reclamation=?";
        }
    }

    private void insert() throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, emailFld.getText());
// Assuming you have a method like getProjectIDByName in MyDataBase class
        preparedStatement.setInt(2, cnx.getProjectIDByName(projetComboBox.getValue()));
        preparedStatement.setInt(3, cnx.getreclamationIDByName(typeReclamationComboBox.getValue()));
        preparedStatement.setInt(4, cnx.getadminIDByName(adminComboBox.getValue()));
        preparedStatement.setString(5, objetFld.getText());
        preparedStatement.setString(6, texte.getText());
 // Assuming texte is the Description_Reclamation field

        preparedStatement.executeUpdate();
    }



    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de validation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void populateAdminComboBox() {
        MyDataBase myDataBase = MyDataBase.getInstance(); // Use getInstance to get the instance
        adminComboBox.getItems().addAll(myDataBase.getAllAdminNames());
    }

    private void populateProjetComboBox() {
        MyDataBase myDataBase = MyDataBase.getInstance(); // Use getInstance to get the instance
        projetComboBox.getItems().addAll(myDataBase.getAllProjectsNames());
    }

    private void populateProblemComboBox() {
        MyDataBase myDataBase = MyDataBase.getInstance(); // Use getInstance to get the instance
        typeReclamationComboBox.getItems().addAll(myDataBase.getAllProblemsNames());
    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamations/reclamamtionView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
