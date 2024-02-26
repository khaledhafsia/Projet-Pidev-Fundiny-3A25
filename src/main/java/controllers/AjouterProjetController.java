package controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceProjet;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.lang.Float;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.stage.Stage;
import tn.esprit.test.MainFX;

public class AjouterProjetController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final ServiceProjet sp = new ServiceProjet();

    @FXML
    private TextField nomPrT;

    @FXML
    private TextField nomPoT;

    @FXML
    private DatePicker dateDT;

    @FXML
    private TextField CAT;




    @FXML
    void Ajouter(ActionEvent event) throws SQLException {

        if(nomPrT.getText().isEmpty() || nomPoT.getText().isEmpty() || dateDT.getValue()==null || CAT.getText().isEmpty()){
            System.out.println("empty");
        }
        else {
            LocalDate localDate = dateDT.getValue();
            Date sqlDate = Date.valueOf(localDate);
            sp.add(new Projet(1,nomPrT.getText(), nomPoT.getText(), sqlDate, Integer.parseInt(CAT.getText())));

        }



    }
    @FXML
    void Afficher(MouseEvent event)throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherProjet.fxml"));
            Parent my_root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Liste des Projets");
            stage.setScene(new Scene(my_root));
            stage.show();
        }
        catch (Exception e){
            System.out.println("FXML file path: " + getClass().getResource("/AfficherProjet.fxml"));
            e.printStackTrace();
        }


    }

}
