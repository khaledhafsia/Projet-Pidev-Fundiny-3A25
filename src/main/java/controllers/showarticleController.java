package controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import tn.esprit.models.article;
import tn.esprit.services.servicearticle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class showarticleController implements Initializable {

    servicearticle sa = new servicearticle();
    @FXML
    private ListView<article> listview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<article> article = sa.getAll();
        listview.getItems().clear();
        for (article i : article) {
            listview.getItems().add(i);
        }

    }
}
