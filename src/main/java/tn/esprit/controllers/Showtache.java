package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.investissements;
import tn.esprit.models.taches;
import tn.esprit.services.serviceInvestissements;
import tn.esprit.services.serviceTaches;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Showtache {

    @FXML
    private HBox cardContainer;

    @FXML
    private TextField searchField;


    serviceTaches sp = new serviceTaches();
    private List<taches> tasks;

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
        this.tasks = tasks;
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

    @FXML
    void sortBtn(ActionEvent event) {
        sortAndDisplayTasks();

    }

    @FXML
    void sortSBtn(ActionEvent event) {
        sortSAndDisplayTasks();

    }
    ///////////////////////// tri :

    private void displayTasks() {
        cardContainer.getChildren().clear();
        for (taches selectedTask : tasks) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tchCard.fxml"));
                Node node = loader.load();

                // Get the controller associated with the FXML file
                TchCard controller = loader.getController();
                controller.settchCard(selectedTask);
                controller.initTask(selectedInvestissement);
                controller.setShowtacheController(this);

                // Add the card node to the VBox inside the ScrollPane
                cardContainer.getChildren().add(node);
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

    private void sortAndDisplayTasks() {
        if (tasks != null) {
            // Sort tasks based on priority
            Collections.sort(tasks, taches.priorityComparator);

            // Update the display
            displayTasks();
        }
    }

    private void sortSAndDisplayTasks() {
        if (tasks != null) {
            // Sort tasks based on priority
            Collections.sort(tasks, taches.statutComparator);

            // Update the display
            displayTasks();
        }
    }







    //////////////////////////////////
    /////////////// recherche

    public void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTasks(newValue);
        });
    }


    private void filterTasks(String searchText) {
        cardContainer.getChildren().clear();
        for (taches task : tasks) {
            if (task.getTitre().toLowerCase().contains(searchText.toLowerCase())) {
                addTaskCard(task);
            }
        }
    }

    private void addTaskCard(taches task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tchCard.fxml"));
            Node node = loader.load();
            TchCard controller = loader.getController();
            controller.settchCard(task);
            controller.initTask(selectedInvestissement);
            controller.setShowtacheController(this);
            cardContainer.getChildren().add(node);
        } catch (IOException e) {
            // Handle FXML loading errors
            e.printStackTrace();
        }
    }


    ///////////////////////////

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
