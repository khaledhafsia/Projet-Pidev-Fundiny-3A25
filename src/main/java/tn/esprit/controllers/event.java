package tn.esprit.controllers;

public class event {


/*

    @FXML
    private FlowPane myFlowPane;

    //    @FXML
//    private GridPane myGridPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        //   setEventGridPane();
        setEventFlowPane();
    }



//    void setEventGridPane() {
//        // Clear existing children from the GridPane
//        myGridPane.getChildren().clear();
//        final int NUM_COLUMNS = 4;
//        myGridPane.setHgap(10);
//        myGridPane.setVgap(10);
//        // Counter variables for row and column indices
//        int row = 0;
//        int col = 0;
//        // Loop through each item in the list
//        for (Evenement item : evenements) {
//            try {
//                // Load the FXML file for the event card
//                FXMLLoader loader = new FXMLLoader(Main.class.getResource("eventCard.fxml"));
//                Node node = loader.load();
//                EventCardController controller = loader.getController();
//
//                // Set the event data for the event card
//                controller.setEventDataUser(item);
//
//
//                // Add the event card to the GridPane at the current row and column
//                myGridPane.add(node, col++, row);
//
//
//
//
//                // If the column index exceeds the number of columns in the GridPane, move to the next row
//                if (col == NUM_COLUMNS) {
//                    col = 0;
//                    row++;
//                }
//
//                GridPane.setMargin(node,new Insets(10));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    void setEventFlowPane() {

        myFlowPane.getChildren().clear();
        myFlowPane.setHgap(10);
        myFlowPane.setVgap(10);

        for (Evenement item : evenements) {
            try {

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("eventCard.fxml"));
                Node node = loader.load();
                EventCardController controller = loader.getController();

                controller.setEventDataUser(item);

                myFlowPane.getChildren().add(node);

                FlowPane.setMargin(node, new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

private void afficherCartes() {
        ArrayList<Reservation> allReservations = getAll();
        cardLyout.getChildren().clear(); // Effacer toutes les cartes précédemment affichées
        try {
            for (Reservation reservation : allReservations) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/pidevjava/Card.fxml"));
                AnchorPane cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setReservation(reservation); // Associez la réservation à la carte
                cardController.displayReservationDetails(reservation);
                cardLyout.getChildren().add(cardBox);

                // Ajoutez un événement de clic au bouton de suppression pour chaque carte
                cardController.del_btn.setOnAction(event -> supprimerCarte(reservation)); // Utilisez la réservation associée à la carte correspondante
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Appliquer l'espace entre les cartes après avoir ajouté toutes les cartes au FlowPane
        cardLyout.setHgap(20); // Espacement horizontal
        cardLyout.setVgap(20); // Espacement vertical
        cardLyout.setAlignment(Pos.TOP_LEFT);
    }

    // Méthode pour supprimer une carte
    public void supprimerCarte(Reservation reservation) {
        if (reservation != null) {
            delete(reservation); // Supprimer la réservation de la base de données
            afficherCartes(); // Rafraîchir l'affichage des cartes après suppression
        } else {
            System.out.println("Aucune réservation associée à cette carte.");
        }
    }


*/

}