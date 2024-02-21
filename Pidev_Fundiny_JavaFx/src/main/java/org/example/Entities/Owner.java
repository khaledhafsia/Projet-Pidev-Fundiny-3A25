package org.example.Entities;

public class Owner extends User {

    private float capital;


    public Owner(int id,String nom, String prenom, String email, String password,role role,float capital) {
        super(id,nom,prenom,email,password,role);
        this.capital = capital;
    }

    public Owner(String nom, String prenom, String email, String mdp, User.role role, String initialCapital) {
        super(0, nom, prenom, email, mdp, role);
        this.capital = Float.parseFloat(initialCapital);
    }
/*
    public Owner() {
        super(nom, prenom, telephone, adresse, email, password);
        this.capital = capital;
    }

 */

    public Owner(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role roles, float capital) {
        super(id, nom, prenom, telephone, adresse, email, password, roles);
        this.capital = capital;
    }


/*
    public Owner(String id, String nom, String prenom, String telephone, User.role email, String capital) {
        super(id, nom, prenom, telephone, email);
        this.capital = capital;
    }

 */



    public float getCapital() {
        return capital;
    }

    public void setCapital(String email) {
        this.capital = capital;
    }





}

