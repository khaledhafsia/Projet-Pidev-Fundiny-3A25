package tn.esprit.test;

import controllers.AfficherProjetController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.services.ServiceProjet;

public class MainFX extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Back.fxml"));
        Parent root = loader.load();
        // Configurer la scène et afficher la fenêtre
        Scene scene = new Scene(root);
        primaryStage.setTitle("Projet");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {

        launch(args);
    }

}
