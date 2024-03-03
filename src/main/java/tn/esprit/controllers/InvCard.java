package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.models.investissements;
import tn.esprit.services.serviceInvestissements;

import java.io.IOException;
import java.sql.SQLException;

public class InvCard {
    @FXML
    private Label daData;

    @FXML
    private Label deData;

    @FXML
    private Label mData;

    private investissements selectedInvestissement;

    public void setinvCard(investissements selectedInvestissement){
        this.selectedInvestissement = selectedInvestissement;
        mData.setText(String.valueOf(selectedInvestissement.getMontant()));
        deData.setText(String.valueOf(selectedInvestissement.getDescription()));
        daData.setText(String.valueOf(selectedInvestissement.getDate()));

    }
    private Testcard testcardController;

    public void setTestcardController(Testcard testcardController) {
        this.testcardController = testcardController;
    }
    @FXML
    void delete(ActionEvent event) {
        if (selectedInvestissement != null) {

            serviceInvestissements sp = new serviceInvestissements();
            boolean deleted = sp.delete(selectedInvestissement);
            if (deleted) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Suppression terminée");
                alert.showAndWait();
                 testcardController.refreshDisplay();
            }else {
                // Handle deletion failure
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("La suppression a échoué.");
                alert.setContentText("Une erreur est survenue lors de la suppression de l'investissement.");
                alert.showAndWait();
        }}else {
            System.out.println("Aucun investissement sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }

    }

    @FXML
    void update(ActionEvent event) {
        if (selectedInvestissement != null) {
            openUpdateForm(selectedInvestissement);
        } else {
            System.out.println("Aucun investissement sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }
    }

    private void openUpdateForm(investissements selectedInvestissement) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateinvestissement.fxml"));
        try {
            Parent root = loader.load();

            Updateinvestissement controller = loader.getController();
            controller.initData(selectedInvestissement);

            Stage stage = (Stage) mData.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addTache(ActionEvent event) {
        if (selectedInvestissement != null) {
            openTacheForm(selectedInvestissement);
        } else {
            System.out.println("Aucun investissement sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }
    }

    private void openTacheForm(investissements selectedInvestissement) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addtache.fxml"));
        try {
            Parent root = loader.load();

            Addtache controller = loader.getController();
            controller.initData(selectedInvestissement);

            Stage stage = (Stage) mData.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void showTache(ActionEvent event) {

    }

}
