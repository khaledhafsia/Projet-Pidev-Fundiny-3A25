package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.Entities.User;
//import org.example.Entities.Projet;
//import org.example.Services.ServiceProjet;

import java.util.ArrayList;

public class ProjectOfOwnerController {

////    @FXML
//    private ListView<Projet> projectListView;

    private User currentUser;

    public void initData(User user) {
        this.currentUser = user;
//        fetchProjectsByUserId(currentUser.getId());
    }

//    private void fetchProjectsByUserId(int userId) {
//        ServiceProjet projetService = new ServiceProjet();
//        ArrayList<Projet> userProjects = projetService.getProjectsByUserId(userId);
//        projectListView.getItems().addAll(userProjects);
//    }
}
