package org.example.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Funder extends User{

//    private List<investissements> investmentsList;


    public Funder(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role role) {
        super(id, nom, prenom, telephone, adresse, email, password, role);
    }


    public Funder(String nom, String prenom, String email, String hashpwd, User.role role) {
        super( nom, prenom, email, hashpwd, role);
    }


//    public Funder(int id, String nom, String prenom, String email, String password, User.role userRole, List<investissements> investmentsList) {
//        super(id, nom, prenom, email, password, userRole);
//        this.investmentsList = investmentsList;
//    }

    public Funder(int id, String nom, String prenom, String email, String password, User.role userRole) {
        super(id, nom, prenom, email, password, userRole);
    }

    public boolean isFunder() {
        return this.getRole() == User.role.Funder;
    }
//    public List<investissements> getInvestments() {
//        if (this instanceof Funder) {
//            return ((Funder) this).getInvestmentsList();
//        }
//        return Collections.emptyList(); // Return an empty list if the user is not a funder
//    }
//    public List<investissements> getInvestmentsList() {
//        return investmentsList;
//    }
//
//
//    public void setInvestmentsList(List<investissements> investmentsList) {
//        this.investmentsList = investmentsList;
//    }
//
//    public void addInvestissement(investissements inv) {
//        this.investmentsList.add(inv);
//    }
//
//    public void removeInvestissement(investissements inv) {
//        this.investmentsList.remove(inv);
//    }

}
