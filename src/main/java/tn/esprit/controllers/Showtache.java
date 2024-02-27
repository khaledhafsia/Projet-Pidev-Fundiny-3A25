package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.models.investissements;
import tn.esprit.models.taches;
import tn.esprit.services.serviceInvestissements;
import tn.esprit.services.serviceTaches;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Showtache {
    @FXML
    private ListView<taches> listview;

    serviceTaches sp = new serviceTaches();

    @FXML
    void backBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showinvestissement.fxml"));
        try {
            Parent root = loader.load();

            listview.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setTasks(List<taches> tasks) {
        listview.getItems().clear();

        for (taches task : tasks) {
            listview.getItems().add(task);
        }
    }

    private investissements selectedInvestissement;
    public void initTask(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;

    }

    @FXML
    void addtache(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addtache.fxml"));
            Parent root = loader.load();

            Addtache controller = loader.getController();

            controller.initData(selectedInvestissement);

            Stage stage = (Stage) listview.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void clearselection(ActionEvent event) {
        listview.getSelectionModel().clearSelection();

    }

    @FXML
    void deleteBtn(ActionEvent event) {
        taches selectedTache = listview.getSelectionModel().getSelectedItem();
        if (selectedTache != null) {
            // Call the delete method to delete it from the database
            serviceTaches sp = new serviceTaches();
            boolean deleted = sp.delete(selectedTache);
            {
                // Remove the selected item from the list view
                listview.getItems().remove(selectedTache);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Suppression terminée");
                alert.showAndWait();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner une tache.");
            alert.showAndWait();
        }


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
    void updateBtn(ActionEvent event) {
        taches selectedTache = listview.getSelectionModel().getSelectedItem();
        if (selectedTache != null) {
            openUpdate(selectedTache);
        } else {
            System.out.println("Aucune tache sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner une tache.");
            alert.showAndWait();
        }

    }

    private void openUpdate(taches selectedTache) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatetache.fxml"));
        try {
            Parent root = loader.load();

            Updatetache controller = loader.getController();
            controller.initData(selectedTache);

            Updatetache controller2 = loader.getController();

            controller2.initInv(selectedInvestissement);

            Stage stage = (Stage) listview.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
