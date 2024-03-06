package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.example.Entities.investissements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.example.Services.serviceInvestissements;

public class ShowinvBack implements Initializable {

    @FXML
    private HBox cardContainer;

    @FXML
    private Label title;

    serviceInvestissements sp =new serviceInvestissements();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCards();
    }

    public void loadCards() {
        ArrayList<investissements> allInvestissements = sp.getAll();
        cardContainer.getChildren().clear();

        for (investissements selectedInvestissement : allInvestissements) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/invCardBack.fxml"));
                Node node = loader.load();

                // Get the controller associated with the FXML file
                InvCardBack controller = loader.getController();

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

}

