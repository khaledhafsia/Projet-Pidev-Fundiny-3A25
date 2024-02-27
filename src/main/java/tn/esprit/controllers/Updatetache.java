package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.investissements;
import tn.esprit.models.taches;
import tn.esprit.services.serviceInvestissements;
import tn.esprit.services.serviceTaches;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class Updatetache implements Initializable {
    @FXML
    private DatePicker datetf;

    @FXML
    private TextField invtf;

    @FXML
    private TextField titretf;

    @FXML
    private ChoiceBox<String> prioritetf;
    private String[] options = {"élevé", "moyen", "faible"};

    @FXML
    private ChoiceBox<String> statuttf;
    private String[] options2 = {"en attente", "en cours", "terminé"};

    private taches selectedTache;

    private investissements selectedInvestissement;

    serviceTaches sp = new serviceTaches();

    @FXML
    void modifBt(ActionEvent event) {
        int invID = Integer.parseInt(invtf.getText());
        String titre = titretf.getText();
        String priorite = prioritetf.getValue();
        String statut = statuttf.getValue();
        Date echeanceD = Date.valueOf(datetf.getValue());

        selectedTache.setInvID(invID);
        selectedTache.setTitre(titre);
        selectedTache.setPriorite(priorite);
        selectedTache.setStatut(statut);
        selectedTache.setEcheanceD(echeanceD);


        if (isInputValid())
        {
            sp.update(selectedTache);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("mise à jour terminée");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis");
            alert.showAndWait();
        }

    }

    @FXML
    void retourBt(ActionEvent event) {
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

    public void initInv(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;
    }

    public void initData(taches selectedTache) {
        this.selectedTache = selectedTache;
        prioritetf.getItems().addAll(options);
        statuttf.getItems().addAll(options2);
        invtf.setText(String.valueOf(selectedTache.getInvID()));
        titretf.setText(String.valueOf(selectedTache.getTitre()));
        prioritetf.setValue(String.valueOf(selectedTache.getPriorite()));
        statuttf.setValue(String.valueOf(selectedTache.getStatut()));
        Date date = selectedTache.getEcheanceD();
        if (date != null) {
            LocalDate localDate = date.toLocalDate();
            datetf.setValue(localDate);
        } else {
            datetf.setValue(null);
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
