package org.example.Entities;

import java.util.Collections;
import java.util.List;

public class Owner extends User {

//    private List<Projet> projetsList;


    public Owner(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role role) {
        super(id, nom, prenom, telephone, adresse, email, password, role);
    }


    public Owner(String nom, String prenom, String email, String hashpwd, User.role role) {
        super(nom, prenom, email, hashpwd, role);
    }


//    public Owner(int id, String nom, String prenom, String email, String password, User.role userRole, List<Projet> projetsList) {
//        super(id, nom, prenom, email, password, userRole);
//        this.projetsList = projetsList;
//    }

    public Owner(int id, String nom, String prenom, String email, String password, User.role userRole) {
        super(id, nom, prenom, email, password, userRole);
    }

    public boolean isOwner() {
        return this.getRole() == User.role.Owner;
    }

//    public List<Projet> getProjects() {
//        if (this instanceof Owner) {
//            return ((Owner) this).getProjetsList();
//        }
//        return Collections.emptyList();
//    }
//
//    public List<Projet> getProjetsList() {
//        return projetsList;
//    }
//
//
//    public void setProjectList(List<Projet> projetsList) {
//        this.projetsList = projetsList;
//    }
//
//    public void addProject(Projet inv) {
//        this.projetsList.add(inv);
//    }
//
//    public void removeProject(Projet inv) {
//        this.projetsList.remove(inv);
//    }

}