package tn.esprit.controls;

import com.mysql.cj.jdbc.JdbcConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tn.esprit.models.Reclamation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
public class addReclamationController {
    @FXML
    private TextField emailUserFld;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Reclamation reclamation = null;
    private boolean update;
    int roleId;
    private int ID_Reclamation;

    public addReclamationController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void save(MouseEvent event) throws IOException {
        this.connection = JdbcConnection.getConnect();
        String E_U_Fld = this.emailUserFld.getText();
        if (E_U_Fld.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText((String)null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        } else {
            this.getQuery();
            this.insert();
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/addReclamation.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        this.emailUserFld.setText((String)null);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/Reclamation.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getQuery() {
        if (!this.update) {
            this.query = "INSERT INTO `Reclamation`(`ID_Utilisateur`, `ID_Projet`, `ID_Type_Reclamation`, `ID_Admin`, `Statut_Reclamation`, `Description_Reclamation`, `Date_Soumission`, `Derniere_Mise_a_Jour`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            this.query = "UPDATE `Reclamation` SET \" +\n" +
                    "                \"`ID_Utilisateur`=?, \" +\n" +
                    "                \"`ID_Projet`=?, \" +\n" +
                    "                \"`ID_Type_Reclamation`=?, \" +\n" +
                    "                \"`ID_Admin`=?, \" +\n" +
                    "                \"`Statut_Reclamation`=?, \" +\n" +
                    "                \"`Description_Reclamation`=?, \" +\n" +
                    "                \"`Date_Soumission`=?, \" +\n" +
                    "                \"`Derniere_Mise_a_Jour`=? \" +\n" +
                    "                \"WHERE `ID_Reclamation`='\" + this.ID_Reclamation + \"'";
        }

    }

    private void insert() {
        try {
            this.preparedStatement = this.connection.prepareStatement(this.query);
            this.preparedStatement.setString(1, this.emailUserFld.getText());
            this.preparedStatement.execute();
        } catch (SQLException var2) {
            Logger.getLogger(addReclamationController.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }

    void setTextField(int id_Reclamation, String E_U_Fld) {
        this.ID_Reclamation = id_Reclamation;
        this.emailUserFld.setText(E_U_Fld);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }
}
