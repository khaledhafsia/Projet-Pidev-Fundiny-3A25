package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.Entities.investissements;
import org.example.Entities.taches;
import org.example.Services.serviceTaches;
//


import java.io.IOException;
import java.util.List;

public class TchCard {
    @FXML
    private Label dech;

    @FXML
    private Label prior;

    @FXML
    private Label statut;

    @FXML
    private Label titre;
    private investissements selectedInvestissement;
    private taches selectedTasks;


    serviceTaches st =new serviceTaches();

    private Showtache showtacheController;

    public void setShowtacheController(Showtache showtacheController) {
        this.showtacheController = showtacheController;
    }

    public void initTask(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;

    }

    public void settchCard(taches selectedTasks){
        this.selectedTasks = selectedTasks;
        titre.setText(String.valueOf(selectedTasks.getTitre()));
        prior.setText(String.valueOf(selectedTasks.getPriorite()));
        statut.setText(String.valueOf(selectedTasks.getStatut()));
        dech.setText(String.valueOf(selectedTasks.getEcheanceD()));

    }

    @FXML
    void modif(ActionEvent event) {
        if (selectedTasks != null) {
            openUpdate(selectedTasks);
        } else {
            System.out.println("Aucune tache sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner une tache.");
            alert.showAndWait();
        }
    }



    private void openUpdate(taches selectedTasks) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatetache.fxml"));
        try {
            Parent root = loader.load();

            Updatetache controller = loader.getController();
            controller.initData(selectedTasks);

            Updatetache controller2 = loader.getController();

            controller2.initInv(selectedInvestissement);

            Stage stage = (Stage) titre.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void supp(ActionEvent event) {
        if (selectedTasks != null) {


            boolean deleted = st.delete(selectedTasks);
            if (deleted) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Suppression terminée");
                alert.showAndWait();
                showtacheController.refreshTasksDisplay();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("La suppression a échoué.");
                alert.setContentText("Une erreur est survenue lors de la suppression de l'investissement.");
                alert.showAndWait();
            }}else {
            System.out.println("Aucune tache sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }

    }
}
