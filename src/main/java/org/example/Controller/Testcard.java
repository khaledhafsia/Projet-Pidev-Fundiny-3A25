package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import org.example.Entities.investissements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.example.Services.serviceInvestissements;

public class Testcard implements Initializable {

    serviceInvestissements sp =new serviceInvestissements();


    @FXML
    private HBox cardContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCards();
    }

    public void loadCards() {
        ArrayList<investissements> allInvestissements = sp.getAll();
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


}

