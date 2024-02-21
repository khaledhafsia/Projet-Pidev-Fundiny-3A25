package tn.esprit.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reclamationViewController implements javafx.fxml.Initializable{
    public reclamationViewController() { /* compiled code */ }

    public void initialize(java.net.URL url, java.util.ResourceBundle rb) { /* compiled code */ }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) { /* compiled code */ }

    @FXML
    private void getAddReclamation(MouseEvent event) {
        try {
            Parent parent = (Parent) FXMLLoader.load(this.getClass().getResource("/recources/addReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException var6) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, (String)null, var6);
        }
    }


}