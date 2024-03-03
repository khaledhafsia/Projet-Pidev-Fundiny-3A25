package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import tn.esprit.models.investissements;
import tn.esprit.services.serviceInvestissements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Testcard implements Initializable {

    serviceInvestissements sp =new serviceInvestissements();

    @FXML
    private FlowPane myFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<investissements> allInvestissements = sp.getAll();
        myFlowPane.getChildren().clear();
        myFlowPane.setHgap(10);
        myFlowPane.setVgap(10);

        for (investissements item : allInvestissements) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("invCard.fxml"));
                Node node = loader.load();
                InvCard controller = loader.getController();

                controller.setinvCard(item);

                myFlowPane.getChildren().add(node);

                FlowPane.setMargin(node, new Insets(10));
                System.out.println("congrats");
            } catch (IOException e) {
                System.out.println("no");
                e.printStackTrace();
            }
        }

    }


}

