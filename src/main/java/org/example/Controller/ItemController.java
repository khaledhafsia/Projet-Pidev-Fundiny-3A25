package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.example.Entities.Projet;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
public class ItemController {

    @FXML
    private HBox itemC;

    @FXML
    private Label nomPr;

    @FXML
    private Label dateD;

    @FXML
    private Label CA;

    private Projet currentproject;

    private AllProjectsForFunder allProjectsForFunder;

    public void initialize(Projet projet, AllProjectsForFunder allProjectsForFunder) {
        this.currentproject = projet;
        this.allProjectsForFunder = allProjectsForFunder;
        nomPr.setText(projet.getNomPr());
        dateD.setText(String.valueOf(projet.getDateD()));
        CA.setText(String.valueOf(projet.getCA()));
    }
}
