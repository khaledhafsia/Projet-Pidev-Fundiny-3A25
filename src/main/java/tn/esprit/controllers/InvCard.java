package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.investissements;

public class InvCard {
    @FXML
    private Label daData;

    @FXML
    private Label deData;

    @FXML
    private Label mData;
    public void setinvCard(investissements item){
       mData.setText(String.valueOf(item.getMontant()));
        deData.setText(String.valueOf(item.getDescription()));
        daData.setText(String.valueOf(item.getDate()));



    }
}
