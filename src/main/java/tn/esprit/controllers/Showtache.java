package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
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

    @FXML
    private HBox cardContainer;


    serviceTaches sp = new serviceTaches();

    @FXML
    void backBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/testC.fxml"));
        try {
            Parent root = loader.load();

            cardContainer.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void setTasks(List<taches> tasks) {
        cardContainer.getChildren().clear();
        for (taches selectedTasks : tasks) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tchCard.fxml"));
                Node node = loader.load();

                // Get the controller associated with the FXML file
                TchCard controller = loader.getController();

                // Assuming setinvCard method is correctly defined in InvCard controller
                controller.settchCard(selectedTasks);

                controller.initTask(selectedInvestissement);

                controller.setShowtacheController(this);


                // Assuming setinvCard method is correctly defined in InvCard controller
                controller.settchCard(selectedTasks);


                // Add the card node to the VBox inside the ScrollPane
                cardContainer.getChildren().add(node);

                System.out.println("Card loaded successfully");
            } catch (IOException e) {
                // Handle FXML loading errors
                System.err.println("Error loading card FXML: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                // Handle other exceptions
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private investissements selectedInvestissement;
    public void initTask(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;

    }

    public void refreshTasksDisplay() {
        if (selectedInvestissement != null) {
            // Fetch updated list of tasks associated with the selected investment
            //List<taches> tasks = sp.getTasksByInvestissement(selectedInvestissement);


            int selectedInvestissementID = selectedInvestissement.getInvID();

            List<taches> tasks = sp.getTasksByInvestissementID(selectedInvestissementID);

            // Update the display
            setTasks(tasks);
        }
    }







}
