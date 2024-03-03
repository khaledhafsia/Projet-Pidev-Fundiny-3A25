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

*/

}