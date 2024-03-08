package org.example.Controller;

import org.example.Services.EventService;
import org.example.Entities.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListEventController implements Initializable {


    EventService sp = new EventService();

    private Event event;
    @FXML
    private AnchorPane afficherprod;

    @FXML
    private FlowPane cardlyout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        test();
    }

    @FXML
    void reload_page(ActionEvent event) throws IOException {
        cardlyout.getChildren().clear();
        test();
    }
    @FXML
    void EmptyView() {
        cardlyout.getChildren().clear();
    }
    @FXML
    void add_page(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/form.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    void test() {
        ArrayList<Event> listeprod = sp.getEventList();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listeprod.isEmpty()) {
            System.out.println("La liste des produits est vide.");
        } else {
            System.out.println("Nombre de produits récupérés depuis la base de données : " + listeprod.size());

            // Créer et afficher une CardView pour chaque produit
            for (Event produit : listeprod) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo2/corbeille/CardViewEvent.fxml"));
                    Pane cardView = loader.load();
                    CardViewController controller = loader.getController();
                    controller.setData(produit); // Appel de la méthode setData
                    controller.setAssociatedObject(produit);
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }




}
