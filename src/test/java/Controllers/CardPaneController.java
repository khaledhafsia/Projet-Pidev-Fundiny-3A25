package Controllers;

import com.gluonhq.charm.glisten.control.CardPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceProjet;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CardPaneController implements Initializable {

    @FXML
    private ScrollPane ScrollPane;

    @FXML
    private CardPane<Projet> CardPane;

    private ServiceProjet sp = new ServiceProjet();
    ArrayList<Projet> projets = sp.getAll();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Projet> projets = sp.getAll();
        for (Projet projet : projets) {
            CustomCard<Projet> card = createCard(projet);
            CardPane
        }
    }

    private CustomCard<Projet> createCard(Projet projet) {
        // Créez une carte avec les détails du projet
        CustomCard<Projet> card = new CustomCard<>(projet);
        // Ajoutez d'autres éléments à la carte si nécessaire
        return card;
    }

}
