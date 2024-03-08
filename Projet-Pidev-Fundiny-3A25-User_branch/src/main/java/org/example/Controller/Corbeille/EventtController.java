package org.example.Controller.Corbeille;
//import com.example.demo2.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Entities.Event;

import java.net.URL;
import java.sql.*;

import java.util.Date;
import java.util.ResourceBundle;


public class EventtController implements Initializable {
    @FXML
    private TextField idEventField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField dateDebutField;

    @FXML
    private TextField dateFinField;

    @FXML
    private TextField objectifFinancementField;

    @FXML
    private TextField montantCollecteField;

    @FXML
    private TextField categorieField;



    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Event> TableView;

    @FXML
    private TableColumn<Event, Integer> idEvent;

    @FXML
    private TableColumn<Event, String> nomColumn;

    @FXML
    private TableColumn<Event, String> descriptionColumn;

    @FXML
    private TableColumn<Event, Date> dateDebutColumn;

    @FXML
    private TableColumn<Event, Date> dateFinColumn;

    @FXML
    private TableColumn<Event, Integer> objectifFinancementColumn;

    @FXML
    private TableColumn<Event, Integer> montantCollecteColumn;

    @FXML
    private TableColumn<Event, String> categorieColumn;


    private Connection connection;

    private void getConnection() {
        try {
            // Établir la connexion avec la base de données (assurez-vous de remplacer les paramètres par les vôtres)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/event", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeQuery(String query) {
        getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEvent();
        // Supposons que vous ayez une TableView nommée eventTableView
        TableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Récupérer les valeurs de la ligne sélectionnée
                Event rowData = TableView.getSelectionModel().getSelectedItem();

                // Récupérer l'ID de la ligne sélectionnée
                Integer idEvent = rowData.getIdEvent(); // Supposons que getIdEvent() récupère l'ID

                // Afficher l'ID dans le champ de texte correspondant
                //idEventField.setText(idEvent);
                System.out.println(idEvent);
            }
        });

    }
@FXML
    private void updateButton() {
        String query = "UPDATE evenement SET nom=?, description=?, dateDebut=?, dateFin=?, objectifFinancement=?, montantCollecte=?, categorie=? WHERE idEvent=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idEventField.getText());
            statement.setString(2, nomField.getText());
            statement.setString(3, descriptionField.getText());
            statement.setString(4, dateDebutField.getText());
            statement.setString(5, dateFinField.getText());
            statement.setString(6, objectifFinancementField.getText());
            statement.setString(7, montantCollecteField.getText());
            statement.setString(8, categorieField.getText());
            statement.executeUpdate();
            showEvent();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    private void deleteButton() {
        String query = "DELETE FROM evenement WHERE idEvent="+idEventField.getText()+"";
        executeQuery(query);
        showEvent();
    }

    @FXML
    private void insertButton() {
        if (!nomField.getText().isEmpty()&&(!descriptionField.getText().isEmpty())) {


            String query = "INSERT INTO evenement (idEvent, nom, description, dateDebut, dateFin, objectifFinancement, montantCollecte, categorie) VALUES ('"
                    + idEventField.getText() + "','"
                    + nomField.getText() + "','"
                    + descriptionField.getText() + "','"
                    + dateDebutField.getText() + "','"
                    + dateFinField.getText() + "','"
                    + objectifFinancementField.getText() + "','"
                    + montantCollecteField.getText() + "','"
                    + categorieField.getText() + "')";


            executeQuery(query);
            showEvent();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Results:");
            alert.setContentText("event added succussfully");

            alert.showAndWait();
        }else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Results:");
            alert.setContentText("il faut remplir tous les champs obligatoire");

            alert.showAndWait();}
        //vider les champs
        /*nomField.clear();
        descriptionField.setText("");
        dateDebutField.setText("");
        dateFinField.setText("");
        objectifFinancementField.setText("");
        montantCollecteField.setText("");
        categorieField.setText("");
        idEventField.setText("");*/
 }
    public ObservableList<Event> getEventList(){
        ObservableList<Event> eventsList = FXCollections.observableArrayList();
        getConnection();
        String query = "SELECT * FROM evenement ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Event events;
            while(rs.next()) {
              /*  events = new Event(rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin"),
                        rs.getInt("objectifFinancement"),
                        rs.getInt("montantCollecte"),
                        rs.getString("categorie"));
                eventsList.add(events); */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsList;
    }
    public void showEvent() {
        ObservableList<Event> list = getEventList();

       nomColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("description"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<Event,Date>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<Event,Date>("dateFin"));
        objectifFinancementColumn.setCellValueFactory(new PropertyValueFactory<Event,Integer>("objectifFinancement"));
        montantCollecteColumn.setCellValueFactory(new PropertyValueFactory<Event,Integer>("montantCollecte"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<Event,String>("categorie"));


        TableView.setItems(list);
    }













}
