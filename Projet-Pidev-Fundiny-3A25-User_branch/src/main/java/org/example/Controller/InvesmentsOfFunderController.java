package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import org.example.Entities.User;
//import org.example.Entities.investissements;
//import org.example.Services.serviceInvestissements;

import java.util.ArrayList;

public class InvesmentsOfFunderController {

    @FXML
//    private ListView<investissements> investmentsListView;

    private User currentUser;

//    public void initData(User user) {
//        this.currentUser = user;
//        fetchInvestmentsByUserId(currentUser.getId());
//    }

//    private void fetchInvestmentsByUserId(int userId) {
//        serviceInvestissements investissementsService = new serviceInvestissements();
//        ArrayList<investissements> userInvestments = investissementsService.getInvestmentsByUserId(userId);
//        investmentsListView.getItems().addAll(userInvestments);
//
//        // Customize cell rendering to display different attributes of the investment
//        investmentsListView.setCellFactory(param -> new ListCell<investissements>() {
//            @Override
//            protected void updateItem(investissements item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    // Display different attributes of the investment
//                    setText("ID: " + item.getInvID() + ", Montant: " + item.getMontant() + ", Description: " + item.getDescription() + ", Date: " + item.getDate());
//                }
//            }
//        });
//    }
}
