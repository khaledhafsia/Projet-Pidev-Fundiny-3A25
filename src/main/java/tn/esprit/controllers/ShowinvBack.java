package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.models.investissements;
import tn.esprit.services.serviceInvestissements;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowinvBack implements Initializable {
    @FXML
    private Label title;
    @FXML
    private ListView<investissements> listview;

    serviceInvestissements sp =new serviceInvestissements();

    @FXML
    void clear(ActionEvent event) {
        listview.getSelectionModel().clearSelection();

    }


    @FXML
    void selectfirst(ActionEvent event) {
        listview.getSelectionModel().selectFirst();

    }

    @FXML
    void selectlast(ActionEvent event) {
        listview.getSelectionModel().selectLast();

    }

    @FXML
    void selectnext(ActionEvent event) {
        listview.getSelectionModel().selectNext();

    }

    @FXML
    void selectprevious(ActionEvent event) {
        listview.getSelectionModel().selectPrevious();

    }

    @FXML
    void handleDelete(ActionEvent event) throws SQLException {
        investissements selectedInvestissement = listview.getSelectionModel().getSelectedItem();
        if (selectedInvestissement != null) {
            // Call the delete method to delete it from the database
            serviceInvestissements sp = new serviceInvestissements();
            boolean deleted = sp.delete(selectedInvestissement);
            {
                // Remove the selected item from the list view
                listview.getItems().remove(selectedInvestissement);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Suppression terminée");
                alert.showAndWait();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<investissements> allInvestissements = sp.getAll();
        for (investissements i : allInvestissements) {
            listview.getItems().add(i);
        }
    }
}

