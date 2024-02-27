package tn.esprit.controllers;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Showinvestissement {



    @FXML
    private Label title;


    serviceInvestissements sp =new serviceInvestissements();
    serviceTaches st =new serviceTaches();

    @FXML
    void retourBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addinvestissement.fxml"));
        try {
            Parent root = loader.load();

            title.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private ListView<investissements> listview;

    public void initialize() {
        ArrayList<investissements> allInvestissements = sp.getAll();
        for (investissements i : allInvestissements) {
            listview.getItems().add(i);
        }
    }
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
    private void handleUpdate() {
        investissements selectedInvestissement = listview.getSelectionModel().getSelectedItem();
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

            Stage stage = (Stage) listview.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) throws SQLException {
        investissements selectedInvestissement = listview.getSelectionModel().getSelectedItem();
        if (selectedInvestissement != null) {

            serviceInvestissements sp = new serviceInvestissements();
            boolean deleted = sp.delete(selectedInvestissement);
            {
                listview.getItems().remove(selectedInvestissement);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Suppression terminée");
                alert.showAndWait();

            }
        }else {
            System.out.println("Aucun investissement sélectionné pour la mise à jour.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Veuillez sélectionner un investissement.");
            alert.showAndWait();
        }

    }

    @FXML
    private void opentache() {
        investissements selectedInvestissement = listview.getSelectionModel().getSelectedItem();
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



            Stage stage = (Stage) listview.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showtache(ActionEvent event) {
        investissements selectedInvestissement = listview.getSelectionModel().getSelectedItem();

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
                Stage stage = (Stage) listview.getScene().getWindow();
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
