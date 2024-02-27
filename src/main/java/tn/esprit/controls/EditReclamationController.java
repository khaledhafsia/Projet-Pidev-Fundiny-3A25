package tn.esprit.controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.models.Reclamation;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditReclamationController implements javafx.fxml.Initializable {

    @FXML
    private TextField emailFld;

    @FXML
    private TextField objetFld;

    @FXML
    private TextArea texte;

    @FXML
    private ComboBox<String> adminComboBox;

    @FXML
    private ComboBox<String> projetComboBox;

    @FXML
    private ComboBox<String> typeReclamationComboBox;

    private boolean update;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement;
    private MyDataBase cnx;
    private Reclamation selectedReclamation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx = MyDataBase.getInstance();
        connection = cnx.getCnx();
        populateAdminComboBox();
        populateProjetComboBox();
        populateProblemComboBox();
    }

    public void setSelectedReclamation(Reclamation reclamation) {
        selectedReclamation = reclamation;
        update = true;
        //populateFields();
    }

    /*private void populateFields() {
        emailFld.setText(selectedReclamation.getEmail());
        objetFld.setText(selectedReclamation.getObjet());
        texte.setText(selectedReclamation.getTexte());
        adminComboBox.setValue(selectedReclamation.getAdminName());
        projetComboBox.setValue(selectedReclamation.getProjetName());
        typeReclamationComboBox.setValue(selectedReclamation.getTypeReclamationName());
    }*/

    @FXML
    private void save(MouseEvent event) {
        String email = emailFld.getText().trim();
        String objet = objetFld.getText();
        String texteValue = texte.getText();
        String nomAdmin = adminComboBox.getValue();
        String nomProjet = projetComboBox.getValue();
        String nomTypeReclamation = typeReclamationComboBox.getValue();

        if (email.isEmpty() || objet.isEmpty() || texteValue.isEmpty() || nomAdmin.isEmpty() || nomProjet.isEmpty() || nomTypeReclamation.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }

        getQuery();
        try {
            if (connection != null) {
                insertOrUpdate();
            } else {
                showAlert("La connexion à la base de données est nulle.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert("Erreur lors de l'exécution de la requête. Veuillez réessayer plus tard.");
        }
    }

    private void getQuery() {
        if (update) {
            query = "UPDATE reclamations SET email=?, ID_Projet=?, ID_Type_Reclamation=?, ID_Admin=?, objet=?, texte=? WHERE ID_Reclamation=?";
        } else {
            query = "INSERT INTO `reclamations`(`email`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `objet`, `texte`) VALUES (?, ?, ?, ?, ?, ?)";
        }
    }

    private void insertOrUpdate() throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, emailFld.getText());
        preparedStatement.setInt(2, cnx.getProjectIDByName(projetComboBox.getValue()));
        preparedStatement.setInt(3, cnx.getreclamationIDByName(typeReclamationComboBox.getValue()));
        preparedStatement.setInt(4, cnx.getadminIDByName(adminComboBox.getValue()));
        preparedStatement.setString(5, objetFld.getText());
        preparedStatement.setString(6, texte.getText());

        if (update) {
            preparedStatement.setInt(7, selectedReclamation.getID_Reclamation());
        }

        preparedStatement.executeUpdate();
        showAlert("Réclamation enregistrée avec succès.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void populateAdminComboBox() {
        adminComboBox.getItems().addAll(cnx.getAllAdminNames());
    }

    private void populateProjetComboBox() {
        projetComboBox.getItems().addAll(cnx.getAllProjectsNames());
    }

    private void populateProblemComboBox() {
        typeReclamationComboBox.getItems().addAll(cnx.getAllProblemsNames());
    }
}
