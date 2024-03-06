package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.example.Entities.investissements;
import org.example.Services.serviceInvestissements;

public class InvCardBack {

    @FXML
    private Label daDataa;

    @FXML
    private Label deDataa;

    @FXML
    private Label mDataa;

    private investissements selectedInvestissement;

    serviceInvestissements sp = new serviceInvestissements();

    public void setinvCard(investissements selectedInvestissement){
        this.selectedInvestissement = selectedInvestissement;
        mDataa.setText(String.valueOf(selectedInvestissement.getMontant()));
        deDataa.setText(String.valueOf(selectedInvestissement.getDescription()));
        daDataa.setText(String.valueOf(selectedInvestissement.getDate()));

    }
    private ShowinvBack testcardController;

    public void setTestcardController(ShowinvBack testcardController) {

        this.testcardController = testcardController;
    }



    @FXML
    void supp(ActionEvent event) {
        if (selectedInvestissement != null) {


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
}
