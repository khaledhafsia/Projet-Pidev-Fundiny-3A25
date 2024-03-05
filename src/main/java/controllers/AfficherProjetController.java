package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;
import tn.esprit.services.StatistiqueCollaboration;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import controllers.ExcelExporter;



public class AfficherProjetController implements Initializable {
    private final ServiceProjet sp = new ServiceProjet();
    private final ServiceCollaboration sc = new ServiceCollaboration();
    private AjouterProjetController ajouterProjetController;

    @FXML
    private ListView<Projet> lv;
    @FXML
    private ListView<Collaboration> lvc;
    @FXML
    private ScrollPane lvcc;

    @FXML
    private VBox pnItems;
    @FXML
    private VBox pnItems1;
    @FXML
    private TextField filterField;

    ArrayList<Projet> projets = sp.getAll();
    ArrayList<Collaboration> collaborations = sc.getAll();

    private AfficherProjetController afficherProjetController;
    ItemPController itemPController = new ItemPController();
    List<Collaboration> lvpc = sc.getAll();
    List<Projet> lvp = sp.getAll();

    public void setAjouterProjetController(AjouterProjetController ajouterProjetController) {
        this.ajouterProjetController = ajouterProjetController;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        affichage();
        projets = sp.getAll(); // Récupérer tous les projets
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                handleSearch(newValue); // Appeler handleSearch avec le nouveau texte de recherche
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void handleSearch(String searchText) throws IOException {
        searchText = searchText.toLowerCase(); // Convertir le texte de recherche en minuscules

        // Effacer les éléments existants dans pnItems1
        pnItems1.getChildren().clear();

        // Parcourir tous les projets
        for (Projet projet : projets) {
            if (projet.getNomPr().toLowerCase().contains(searchText)) {
                // Créer un VBox représentant le projet et l'ajouter à pnItems1
                VBox projetNode = createProjetNode(projet);
                pnItems1.getChildren().add(projetNode);
                projetNode.setUserData(projet);
            }
        }
    }

    // Méthode pour créer un nœud représentant un projet
    private VBox createProjetNode(Projet projet) throws IOException {
        // Créer un VBox pour afficher les détails du projet
        VBox vbox = new VBox();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemP.fxml"));
        Node node = loader.load();

        Label nlabel = (Label) node.lookup("#nomPr");
        Label elabel = (Label) node.lookup("#nomPo");
        Label rlabel = (Label) node.lookup("#dateD");
        Label dlabel = (Label) node.lookup("#CA");
        Button supprimer = (Button) node.lookup("#supprimer");
        Button modifier = (Button) node.lookup("#modifier");
        Button collaborer = (Button) node.lookup("#collaborer");

        nlabel.setText(projet.getNomPr());
        elabel.setText(projet.getNomPo());
        rlabel.setText(String.valueOf(projet.getDateD()));
        dlabel.setText(String.valueOf(projet.getCA()));

        // Ajouter les actions aux boutons
        supprimer.setOnAction(event -> {
            boolean suppressionReussie = sp.delete(projet);
            if (suppressionReussie) {
                affichage();
                System.out.println("Projet supprimé avec succès.");
            } else {
                System.out.println("Erreur lors de la suppression du projet.");
            }
        });

        modifier.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierProjetController modifierProjetController = fxmlLoader.getController();
                modifierProjetController.initData(projet);

                modifierProjetController.setAfficherProjetController(this);

                Stage stage = new Stage();
                stage.setTitle("Modifier Projet");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                e.printStackTrace();
            }
        });

        collaborer.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                CollaborerProjetController collaborerProjetController = fxmlLoader.getController();
                collaborerProjetController.initData(projet.getId(), sc);

                collaborerProjetController.setAfficherProjetController(this);

                Stage stage = new Stage();
                stage.setTitle("Collaborer Projet");
                stage.setScene(new Scene(root));
                stage.show();
                Collaboration nouvelleCollaboration = collaborerProjetController.getNouvelleCollaboration();
                if (nouvelleCollaboration != null) {
                    lvc.getItems().add(nouvelleCollaboration);
                }
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                e.printStackTrace();
            }
        });
        // Ajouter les labels et boutons au VBox
        vbox.getChildren().add(node);
        node.setUserData(projet);

        return vbox;
    }


    @FXML
    void Stat(MouseEvent event) {
        try {
            StatistiqueCollaboration statistiqueCollaboration = new StatistiqueCollaboration();

            // Récupérer les données pour le graphique
            HashMap<Integer, Double> data = statistiqueCollaboration.generateData();

            // Convertir les données en une liste observable de PieChart.Data
            ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
            for (Integer idProjet : data.keySet()) {
                dataList.add(new PieChart.Data("Projet ID " + idProjet, data.get(idProjet)));
            }

            // Charger le fichier FXML de la vue de statistiques
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatistiqueCollaboration.fxml"));
            Parent root = loader.load();

            StatistiqueCollaborationController controller = loader.getController();

            controller.setData(dataList);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Statistiques des collaborations par projet");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actualiser(ActionEvent event) {
        projets = sp.getAll();
        collaborations = sc.getAll();
        lv.setItems(FXCollections.observableArrayList(projets));
        lvc.setItems(FXCollections.observableArrayList(collaborations));
    }

    @FXML
    void modifier(MouseEvent event) throws IOException {
        Projet projetAModifier = lv.getSelectionModel().getSelectedItem();
        Collaboration collaborationAModifier = lvc.getSelectionModel().getSelectedItem();

        if (projetAModifier != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierProjetController modifierProjetController = fxmlLoader.getController();
                modifierProjetController.initData(projetAModifier);
                Stage stage = new Stage();
                stage.setTitle("Modifier Projet");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                System.out.println("FXML file path: " + getClass().getResource("/ModifierProjet.fxml"));
                e.printStackTrace();
            }
        }
        if (collaborationAModifier != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaboration.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierCollaborationController modifierCollaborationController = fxmlLoader.getController();
                modifierCollaborationController.initData(collaborationAModifier);
                Stage stage = new Stage();
                stage.setTitle("Modifier Collaboration");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                System.out.println("FXML file path: " + getClass().getResource("/ModifierCollaboration.fxml"));
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun projet sélectionné pour la modification.");
        }
    }


    @FXML
    void supprimer(MouseEvent event) {
        if (sp != null || sc != null) {
            Projet projetASupprimer = lv.getSelectionModel().getSelectedItem();
            Collaboration collaborationASupprimer = lvc.getSelectionModel().getSelectedItem();

            if (projetASupprimer != null) {
                boolean suppressionReussieProjet = sp.delete(projetASupprimer);
                if (suppressionReussieProjet) {
                    lv.getItems().remove(projetASupprimer);
                    System.out.println("Projet supprimé avec succès de la base de données et de la ListView.");
                } else {
                    System.out.println("Erreur lors de la suppression du projet de la base de données.");
                }
            } else if (collaborationASupprimer != null) {
                boolean suppressionReussieCollaboration = sc.delete(collaborationASupprimer);
                if (suppressionReussieCollaboration) {
                    lvc.getItems().remove(collaborationASupprimer);
                    System.out.println("Collaboration supprimée avec succès de la base de données et de la ListView.");
                } else {
                    System.out.println("Erreur lors de la suppression de la collaboration de la base de données.");
                }
            } else {
                System.out.println("Aucun projet ou collaboration sélectionné pour la suppression.");
            }
        } else {
            System.out.println("Erreur : ServiceProjet ou ServiceCollaboration est null.");
        }
    }


    @FXML
    void collaborer(MouseEvent event) throws IOException {
        Projet projetACollaborer = lv.getSelectionModel().getSelectedItem();

        if (projetACollaborer != null) {
            int idProjet = projetACollaborer.getId();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Collaborer avec un Projet");
            stage.setScene(new Scene(fxmlLoader.load()));

            CollaborerProjetController collaborerProjetController = fxmlLoader.getController();
            collaborerProjetController.initData(idProjet, sc);

            collaborerProjetController.setAfficherProjetController(this);

            stage.showAndWait();

            affichage();
        } else {
            System.out.println("Aucun projet sélectionné pour la collaboration.");
        }
    }

    @FXML
    void ExporterExcel(ActionEvent event) {
        // Récupérer les listes de projets et de collaborations depuis la base de données
        List<Projet> projetsList = sp.getAll();
        List<Collaboration> collaborationsList = sc.getAll();

        // Vérifier si les listes ont été récupérées avec succès
        if (projetsList != null && collaborationsList != null) {
            // Créer des ListView pour les projets et les collaborations
            ListView<Projet> projetListView = new ListView<>();
            ListView<Collaboration> collaborationListView = new ListView<>();

            // Ajouter les éléments des listes aux ListView correspondantes
            projetListView.getItems().addAll(projetsList);
            collaborationListView.getItems().addAll(collaborationsList);

            // Appeler la méthode exportToExcel avec les ListView créées
            try {
                ExcelExporter.exportToExcel(projetListView, collaborationListView, "data.xlsx");
                System.out.println("Exportation réussie vers data.xlsx");
            } catch (IOException e) {
                System.out.println("Échec de l'exportation : " + e.getMessage());
            }
        } else {
            System.out.println("Échec de récupération des données depuis la base de données.");
        }
    }



    public void affichage() {
        pnItems.getChildren().clear();
        pnItems1.getChildren().clear();

        try {
            ServiceCollaboration Us = new ServiceCollaboration();
            List<Collaboration> users = Us.afficherListe();
            ServiceProjet sp = new ServiceProjet();
            List<Projet> lv = sp.afficherListe();

            for (Collaboration user : users) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Item.fxml"));
                    Node node = loader.load();


                    Label nlabel = (Label) node.lookup("#nom");
                    Label elabel = (Label) node.lookup("#type");
                    Label rlabel = (Label) node.lookup("#date");
                    Button supprimer = (Button) node.lookup("#supprimer");
                    Button modifier = (Button) node.lookup("#modifier");


                    nlabel.setText(user.getNomColl());
                    elabel.setText(user.getTypeColl());
                    rlabel.setText(String.valueOf(user.getDateColl()));

                    supprimer.setOnAction(event -> {
                        boolean suppressionReussie = Us.delete(user);
                        if (suppressionReussie) {
                            affichage();
                            System.out.println("Collaboration supprimée avec succès.");
                        } else {
                            System.out.println("Erreur lors de la suppression de la collaboration.");
                        }
                    });
                    modifier.setOnAction(event -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaboration.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            ModifierCollaborationController modifierCollaborationController = fxmlLoader.getController();
                            modifierCollaborationController.initData(user);

                            modifierCollaborationController.setAfficherProjetController(this);

                            Stage stage = new Stage();
                            stage.setTitle("Modifier Collaboration");
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            System.out.println("Erreur lors du chargement du fichier FXML de la modification de la collaboration.");
                            e.printStackTrace();
                        }
                    });

                    pnItems.getChildren().add(node);
                    node.setUserData(user);

                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            // Affichage des projets
            // Remplacer la boucle des projets par celle-ci
            for (Projet projet : lv) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemP.fxml"));
                    Node node = loader.load();

                    Label nlabel = (Label) node.lookup("#nomPr");
                    Label elabel = (Label) node.lookup("#nomPo");
                    Label rlabel = (Label) node.lookup("#dateD");
                    Label dlabel = (Label) node.lookup("#CA");
                    Button supprimer = (Button) node.lookup("#supprimer");
                    Button modifier = (Button) node.lookup("#modifier");
                    Button collaborer = (Button) node.lookup("#collaborer");

                    nlabel.setText(projet.getNomPr());
                    elabel.setText(projet.getNomPo());
                    rlabel.setText(String.valueOf(projet.getDateD()));
                    dlabel.setText(String.valueOf(projet.getCA()));

                    supprimer.setOnAction(event -> {
                        boolean suppressionReussie = sp.delete(projet);
                        if (suppressionReussie) {
                            affichage();
                            System.out.println("Projet supprimé avec succès.");
                        } else {
                            System.out.println("Erreur lors de la suppression du projet.");
                        }
                    });

                    modifier.setOnAction(event -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            ModifierProjetController modifierProjetController = fxmlLoader.getController();
                            modifierProjetController.initData(projet);

                            modifierProjetController.setAfficherProjetController(this);

                            Stage stage = new Stage();
                            stage.setTitle("Modifier Projet");
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                            e.printStackTrace();
                        }
                    });

                    collaborer.setOnAction(event -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            CollaborerProjetController collaborerProjetController = fxmlLoader.getController();
                            collaborerProjetController.initData(projet.getId(), sc);

                            collaborerProjetController.setAfficherProjetController(this);

                            Stage stage = new Stage();
                            stage.setTitle("Collaborer Projet");
                            stage.setScene(new Scene(root));
                            stage.show();
                            Collaboration nouvelleCollaboration = collaborerProjetController.getNouvelleCollaboration();
                            if (nouvelleCollaboration != null) {
                                lvc.getItems().add(nouvelleCollaboration);
                            }
                        } catch (IOException e) {
                            System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                            e.printStackTrace();
                        }
                    });

                    pnItems1.getChildren().add(node);
                    node.setUserData(projet);

                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(AfficherProjetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
