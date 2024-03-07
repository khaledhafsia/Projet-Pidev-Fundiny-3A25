package tn.esprit.test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/reponses/reponseView.fxml"));
           Parent root = loader.load();
           Scene sc = new Scene(root);
           primaryStage.setScene(sc);
           primaryStage.show();

       }catch (IOException e){
           System.out.println(e.getMessage());
       }
    }


}
