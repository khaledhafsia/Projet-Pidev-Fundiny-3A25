
package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;

import java.sql.Date;
import java.time.LocalDate;


public class CollaborerProjetController {

        @FXML
        private DatePicker dateColl;

        @FXML
        private TextField nomColl;

        @FXML
        private TextField TypeColl;
        private int idProjet;
        ServiceCollaboration sc ;


    @FXML
        void validerModification(ActionEvent event) {
        if(nomColl.getText().isEmpty() || TypeColl.getText().isEmpty() || dateColl.getValue()==null)
        {
           System.out.println("empty");

        }
        else
        {
          LocalDate localDate = dateColl.getValue();
          Date sqlDate = Date.valueOf(localDate);
          sc.add(new Collaboration(1 ,nomColl.getText(), TypeColl.getText(), sqlDate,idProjet));
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }


    public void initData(int idProjet, ServiceCollaboration sc) {
        this.idProjet = idProjet;
        this.sc = sc;
        System.out.println("ID du projet sélectionné : " + idProjet);
    }
}



