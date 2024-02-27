package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Collaboration;
import tn.esprit.services.ServiceCollaboration;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import controllers.InputValidator;

public class BackModifierCController {

    @FXML
    private TextField nomC;

    @FXML
    private TextField typeC;

    @FXML
    private DatePicker dateC;
    private Collaboration collaboration;
    ServiceCollaboration sc = new ServiceCollaboration();

    @FXML
    void validerModification(ActionEvent event) {
        if (collaboration != null) {
            boolean nomValid = InputValidator.containsOnlyLetters(nomC);
            boolean typeValid = InputValidator.containsOnlyLetters(typeC);

            if (nomValid && typeValid) {
                collaboration.setNomColl(nomC.getText());
                collaboration.setTypeColl(typeC.getText());

                LocalDate localDate = dateC.getValue();
                if (localDate != null) {
                    Date sqlDate = Date.valueOf(localDate);
                    collaboration.setDateColl(sqlDate);
                } else {
                    System.out.println("Veuillez sélectionner une date valide.");
                    return;
                }

                ServiceCollaboration sc = new ServiceCollaboration();
                sc.update(collaboration);

                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Certains champs ne contiennent pas que des lettres.");
            }
        } else {
            System.out.println("Aucune Collaboration à modifier.");
        }
    }









    public void initData(Collaboration collaboration) {
        this.collaboration = collaboration;
        nomC.setText(collaboration.getNomColl());
        typeC.setText(collaboration.getTypeColl());
    }
}

