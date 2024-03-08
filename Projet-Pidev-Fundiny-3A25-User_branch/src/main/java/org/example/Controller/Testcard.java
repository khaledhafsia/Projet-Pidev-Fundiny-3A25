package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.Entities.User;
import org.example.Entities.investissements;
import org.example.Services.serviceInvestissements;
import org.example.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Testcard implements Initializable {

    serviceInvestissements sp =new serviceInvestissements();


    @FXML
    private HBox cardContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCards();
    }

    public void loadCards() {
        List<investissements> allInvestissements = sp.getInvestissementByUserId(MyDataBase.getInstance().getIdenvoi());
        cardContainer.getChildren().clear();

        for (investissements selectedInvestissement : allInvestissements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/invCard.fxml"));
                Node node = loader.load();

                // Get the controller associated with the FXML file
                InvCard controller = loader.getController();

                // Assuming setinvCard method is correctly defined in InvCard controller
                controller.setinvCard(selectedInvestissement);
                controller.setTestcardController(this);

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

    public void refreshDisplay() {

        loadCards();
    }

    @FXML
    void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addinvestissement.fxml"));
        try {
            Parent root = loader.load();

            cardContainer.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    ///////////////////////////////// triiii

    public void sortAscending() {
        ArrayList<investissements> allInvestissements = sp.getAll();
        Collections.sort(allInvestissements, investissements.montantComparatorAsc);
        loadSortedCards(allInvestissements);
    }

    public void sortDescending() {
        ArrayList<investissements> allInvestissements = sp.getAll();
        Collections.sort(allInvestissements, investissements.montantComparatorDesc);
        loadSortedCards(allInvestissements);
    }

    private void loadSortedCards(ArrayList<investissements> sortedInvestissements) {
        cardContainer.getChildren().clear();
        for (investissements selectedInvestissement : sortedInvestissements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/invCard.fxml"));
                Node node = loader.load();

                // Get the controller associated with the FXML file
                InvCard controller = loader.getController();

                // Assuming setinvCard method is correctly defined in InvCard controller
                controller.setinvCard(selectedInvestissement);
                controller.setTestcardController(this);

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

    @FXML
    void triASC(ActionEvent event) {
        sortAscending();

    }

    @FXML
    void triDESC(ActionEvent event) {
        sortDescending();

    }




    ////////////////////////////////////////

    private User currentUser;

    public void initData(User user) {
        this.currentUser=user;
    }


}

