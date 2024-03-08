package org.example.Controller;


import org.example.Services.EventService;
import org.example.Entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class CardViewController   {
    @FXML
    private HBox produit_Hbox;

    @FXML
    private Label desc_card;

    @FXML
    private Button id_delet;

    @FXML
    private Button id_updatecard;

    @FXML
    private Label nom_card;

    @FXML
    private Label prix_card;

    @FXML
    private Label quant_card;

    private int ref_prod;

    private Event associatedObject; // Assurez-vous de définir le type approprié

    public void setAssociatedObject(Event associatedObject) {
        this.associatedObject = associatedObject;
    }
    private EventService servicesProduit = new EventService();
    private Event produit;
    private ListEventController tc = new ListEventController();


    @FXML
    void delet_card(ActionEvent event) {
        // Demander une confirmation à l'utilisateur avant de supprimer la carte
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet evenet ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la carte de l'affichage principal (cardlyout)
            servicesProduit.deleteEvent(associatedObject.getIdEvent());

            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Suppression réussie");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Le produit a été supprimé avec succès.");
            confirmationAlert.showAndWait();
        }
    }

    @FXML
    void update_card(ActionEvent event)  {

        // Utilisez l'objet associé, par exemple :
        if (associatedObject != null) {
            System.out.println("Objet sélectionné : " + associatedObject.getIdEvent());
            // Faites quelque chose avec l'objet sélectionné
        }
    }


    public void setData (Event produit){
        if (produit == null) {
            return;
        }
        ref_prod= produit.getIdEvent();
        nom_card.setText(produit.getNom());
        prix_card.setText(String.valueOf(produit.getDescription()));
        quant_card.setText(String.valueOf(produit.getCategorie()));
        desc_card.setText(String.valueOf(produit.getMontantCollecte()));
        produit_Hbox.setStyle("-fx-background-color: #FF0000; -fx-border-color: #000000; -fx-border-width: 2px;");    }
}








