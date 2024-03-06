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
import org.example.Services.serviceInvestissements;
import java.io.IOException;
import java.util.List;

public class InvCard {
    @FXML
    private Label daData;

    @FXML
    private Label deData;

    @FXML
    private Label mData;

    private investissements selectedInvestissement;
    serviceInvestissements sp = new serviceInvestissements();
    serviceTaches st =new serviceTaches();

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
        if (selectedInvestissement != null) {
            int selectedInvestissementID = selectedInvestissement.getInvID();

            List<taches> tasks = st.getTasksByInvestissementID(selectedInvestissementID);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showtache.fxml"));

            try {
                Parent root = loader.load();
                Showtache controller = loader.getController();
                controller.setTasks(tasks);

                Showtache controller2 = loader.getController();
                controller2.initTask(selectedInvestissement);


                Stage stage = (Stage) mData.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Please select an investment first.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }

    }

}
