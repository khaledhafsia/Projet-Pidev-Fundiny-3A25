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



public class AfficherProjetControllerBack implements Initializable {
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
    ItemPBackController itemPController = new ItemPBackController();
    List<Collaboration> lvpc = sc.getAll();
    List<Projet> lvp = sp.getAll();
    @FXML
    void Refresh(ActionEvent event) {
        affichage();
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
    @FXML
    void sortDate(ActionEvent event) {
        // Tri de pnItems par date de projet
        ObservableList<Node> nodes1 = FXCollections.observableArrayList(pnItems1.getChildren());
        Collections.sort(nodes1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                String date1 = ((Label) node1.lookup("#dateD")).getText();
                String date2 = ((Label) node2.lookup("#dateD")).getText();
                return date1.compareTo(date2);
            }
        });
        pnItems1.getChildren().setAll(nodes1);

        // Tri de pnItems1 par date de collaboration
        ObservableList<Node> nodes2 = FXCollections.observableArrayList(pnItems.getChildren());
        Collections.sort(nodes2, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                String date1 = ((Label) node1.lookup("#date")).getText();
                String date2 = ((Label) node2.lookup("#date")).getText();
                return date1.compareTo(date2);
            }
        });
        pnItems.getChildren().setAll(nodes2);
    }

    @FXML
    void sortType(ActionEvent event) {
        // Tri de pnItems par typeColl
        ObservableList<Node> nodes2 = FXCollections.observableArrayList(pnItems.getChildren());
        Collections.sort(nodes2, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                String typeColl1 = ((Label) node1.lookup("#type")).getText();
                String typeColl2 = ((Label) node2.lookup("#type")).getText();
                return typeColl1.compareToIgnoreCase(typeColl2);
            }
        });
        pnItems.getChildren().setAll(nodes2);

    }
    @FXML
    void sortP(ActionEvent event) {
        // Tri de pnItems1 par nomPr
        ObservableList<Node> nodes1 = FXCollections.observableArrayList(pnItems1.getChildren());
        Collections.sort(nodes1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                String nomPr1 = ((Label) node1.lookup("#nomPr")).getText();
                String nomPr2 = ((Label) node2.lookup("#nomPr")).getText();
                return nomPr1.compareToIgnoreCase(nomPr2);
            }
        });
        pnItems1.getChildren().setAll(nodes1);


    }

    @FXML
    void sortCA(ActionEvent event) {
        ObservableList<Node> nodes1 = FXCollections.observableArrayList(pnItems1.getChildren());
        Collections.sort(nodes1, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                Double CA1 = Double.parseDouble(((Label) node1.lookup("#CA")).getText());
                Double CA2 = Double.parseDouble(((Label) node2.lookup("#CA")).getText());
                return CA1.compareTo(CA2);
            }
        });
        pnItems1.getChildren().setAll(nodes1);

    }


    private void handleSearch(String searchText) throws IOException {
        searchText = searchText.toLowerCase(); // Convertir le texte de recherche en minuscules

        // Effacer les éléments existants dans pnItems1
        pnItems1.getChildren().clear();

        // Parcourir tous les projets
        for (Projet projet : projets) {
            if (projet.getNomPr().toLowerCase().contains(searchText)||String.valueOf(projet.getCA()).toLowerCase().contains(searchText)) {
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemPBack.fxml"));
        Node node = loader.load();

        Label nlabel = (Label) node.lookup("#nomPr");
        Label elabel = (Label) node.lookup("#nomPo");
        Label rlabel = (Label) node.lookup("#dateD");
        Label dlabel = (Label) node.lookup("#CA");
        Button supprimer = (Button) node.lookup("#supprimer");
        Button modifier = (Button) node.lookup("#modifier");

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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjetBack.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierProjetControllerBack modifierProjetControllerBack = fxmlLoader.getController();
                modifierProjetControllerBack.initData(projet);

                modifierProjetControllerBack.setAfficherProjetControllerBack(this);

                Stage stage = new Stage();
                stage.setTitle("Modifier Projet");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                e.printStackTrace();
            }
        });
/*
        collaborer.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjetBack.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                CollaborerProjetControllerBack collaborerProjetControllerBack = fxmlLoader.getController();
                collaborerProjetControllerBack.initData(projet.getId(), sc);

                collaborerProjetControllerBack.setAfficherProjetControllerBack(this);

                Stage stage = new Stage();
                stage.setTitle("Collaborer Projet");
                stage.setScene(new Scene(root));
                stage.show();
                Collaboration nouvelleCollaboration = collaborerProjetControllerBack.getNouvelleCollaboration();
                if (nouvelleCollaboration != null) {
                    lvc.getItems().add(nouvelleCollaboration);
                }
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                e.printStackTrace();
            }
        });*/
        // Ajouter les labels et boutons au VBox
        vbox.getChildren().add(node);
        node.setUserData(projet);

        return vbox;
    }
    private String getNomProjetFromId(int idProjet) {
        for (Projet projet : projets) {
            if (projet.getId() == idProjet) {
                return projet.getNomPr();
            }
        }
        return ""; // Retourne une chaîne vide si aucun projet correspondant n'est trouvé
    }



    @FXML
    void Stat(MouseEvent event) {
        try {
            StatistiqueCollaboration statistiqueCollaboration = new StatistiqueCollaboration();

            // Récupérer les données pour le graphique
            HashMap<Integer, Double> data = statistiqueCollaboration.generateData();

            // Calculer la somme totale des valeurs pour obtenir les pourcentages
            double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

            // Convertir les données en une liste observable de PieChart.Data
            ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
            for (Integer idProjet : data.keySet()) {
                String nomProjet = getNomProjetFromId(idProjet);
                double valeur = data.get(idProjet);
                double pourcentage = (valeur / total) * 100;
                dataList.add(new PieChart.Data(nomProjet + " (" + String.format("%.2f", pourcentage) + "%)", valeur));
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
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaborationBack.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            ModifierCollaborationControllerBack modifierCollaborationControllerBack = fxmlLoader.getController();
                            modifierCollaborationControllerBack.initData(user);

                            modifierCollaborationControllerBack.setAfficherProjetControllerBack(this);

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemPBack.fxml"));
                    Node node = loader.load();

                    Label nlabel = (Label) node.lookup("#nomPr");
                    Label elabel = (Label) node.lookup("#nomPo");
                    Label rlabel = (Label) node.lookup("#dateD");
                    Label dlabel = (Label) node.lookup("#CA");
                    Button supprimer = (Button) node.lookup("#supprimer");
                    Button modifier = (Button) node.lookup("#modifier");
                    //Button collaborer = (Button) node.lookup("#collaborer");

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
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjetBack.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            ModifierProjetControllerBack modifierProjetControllerBack = fxmlLoader.getController();
                            modifierProjetControllerBack.initData(projet);

                            modifierProjetControllerBack.setAfficherProjetControllerBack(this);

                            Stage stage = new Stage();
                            stage.setTitle("Modifier Projet");
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                            e.printStackTrace();
                        }
                    });

                   /* collaborer.setOnAction(event -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            CollaborerProjetControllerBack collaborerProjetControllerBack = fxmlLoader.getController();
                            collaborerProjetControllerBack.initData(projet.getId(), sc);

                            collaborerProjetControllerBack.setAfficherProjetControllerBack(this);

                            Stage stage = new Stage();
                            stage.setTitle("Collaborer Projet");
                            stage.setScene(new Scene(root));
                            stage.show();
                            Collaboration nouvelleCollaboration = collaborerProjetControllerBack.getNouvelleCollaboration();
                            if (nouvelleCollaboration != null) {
                                lvc.getItems().add(nouvelleCollaboration);
                            }
                        } catch (IOException e) {
                            System.out.println("Erreur lors du chargement du fichier FXML de la modification du projet.");
                            e.printStackTrace();
                        }
                    });*/

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
