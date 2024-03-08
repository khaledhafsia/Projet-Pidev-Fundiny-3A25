package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.Entities.investissements;
import org.example.Entities.taches;
import org.example.Services.serviceInvestissements;
import org.example.Services.serviceTaches;


//import tn.esprit.models.investissements;
//import tn.esprit.models.taches;
//import tn.esprit.services.serviceInvestissements;
//import tn.esprit.services.serviceTaches;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class Addtache implements Initializable {
    @FXML
    private DatePicker datetf;

    @FXML
    private TextField invidtf;


    @FXML
    private TextField titretf;

    @FXML
    private ChoiceBox<String> prioritetf;
    private String[] options = {"élevée", "moyenne", "faible"};

    @FXML
    private ChoiceBox<String> statuttf;
    private String[] options2 = {"en attente", "en cours", "terminée"};
    private investissements selectedInvestissement;
    serviceTaches sp = new serviceTaches();

    public void initData(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;
        prioritetf.getItems().addAll(options);
        statuttf.getItems().addAll(options2);
        invidtf.setText(String.valueOf(selectedInvestissement.getInvID()));
    }


    @FXML
    void addBtn(ActionEvent event) throws SQLException {
        if (isInputValid())
        {
            LocalDate localDate = datetf.getValue();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

            sp.add(new taches(1,Integer.parseInt(invidtf.getText()), titretf.getText(), prioritetf.getValue(), statuttf.getValue(),sqlDate));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("tache ajoutée");
            alert.showAndWait();
            titretf.clear();
            prioritetf.getSelectionModel().clearSelection();
            statuttf.getSelectionModel().clearSelection();
            datetf.setValue(null);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis");
            alert.showAndWait();
        }
    }

    @FXML
    void backBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showtache.fxml"));

        int selectedInvestissementID = selectedInvestissement.getInvID();
        List<taches> tasks = sp.getTasksByInvestissementID(selectedInvestissementID);
        try {
            Parent root = loader.load();

            Showtache controller = loader.getController();


            controller.setTasks(tasks);

            Showtache controller2 = loader.getController();

            controller.initTask(selectedInvestissement);



            datetf.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isInputValid() {

        boolean isValid = !titretf.getText().isEmpty()
                && prioritetf.getValue() != null
                && statuttf.getValue() != null
                && datetf.getValue() != null;

        return isValid;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datetf.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                LocalDate.parse(newValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {

                datetf.getEditor().setText("");
            }
        });
    }
}
