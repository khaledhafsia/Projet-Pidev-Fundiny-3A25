package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.example.Entities.Projet;
import org.example.Services.ServiceProjet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AllProjectsForFunder {
    private User currentUser;
    @FXML
    private VBox postcardProjet;
    private ServiceProjet serviceProjet;
    public void initData(User user) {

        this.currentUser = user;
    }

    public AllProjectsForFunder() {
        serviceProjet = new ServiceProjet();
    }

    @FXML
    public void initialize() throws SQLException, IOException {
        try {
            List<Projet> projetList = serviceProjet.afficherListe();
            System.out.println(serviceProjet.afficherListe());

            for (Projet projet : projetList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemP.fxml"));
                VBox postCard = loader.load();
                Node node = loader.getRoot();
                if (node instanceof VBox) {
                    ItemController controller = loader.getController();
                    controller.initialize(projet, this);
                    postcardProjet.getChildren().add((VBox) node);
                } else {
                    System.err.println("Error: Loaded node is not a VBox");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
