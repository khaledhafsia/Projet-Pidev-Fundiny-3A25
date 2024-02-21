package org.example.Entities;

public class Funder extends User{

    private float participation;


    public float getParticipation() {
        return participation;
    }

    public void setParticipation(float participation) {
        this.participation = participation;
    }

    public Funder(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role role) {
        super(id, nom, prenom, telephone, adresse, email, password, role);
    }

    public Funder(String nom, String prenom, String email, String mdp, User.role role, String initialParticipation) {
        super(0, nom, prenom, email, mdp, role);
        this.participation = Float.parseFloat(initialParticipation);
    }

    public Funder(int id, String nom, String prenom, String email, String password, User.role role, float participation) {
        super(id, nom, prenom, email, password, role);
        this.participation = participation;
    }

    public Funder(int id, String nom, String prenom, String telephone, String adresse, String email, String password, float participation) {
        super(id, nom, prenom, telephone, adresse, email, password);
        this.participation = participation;
    }

    public Funder(String nom, String prenom, String telephone, String adresse, String email, String password, float participation) {
        super(nom, prenom, telephone, adresse, email, password);
        this.participation = participation;
    }

    public Funder(int id, String nom, String prenom, String telephone, String adresse, String email, String password, User.role role, float participation) {
        super(id, nom, prenom, telephone, adresse, email, password, role);
        this.participation = participation;
    }
/*
    public Funder(String nom, String prenom, String telephone, String email) {
        super(id, nom, prenom, telephone, email);
        this.participation = participation;
    }

    public Funder(int id, String nom, String prenom, String email, String password, User.role role) {
        super(id, nom, prenom, email, password, role);
    }

    public Funder(String nom, String prenom, String email, String password) {
        super(id, nom, prenom, email, password);
    }


    public Funder(String nom, String prenom, String telephone, String adresse, User.role email, String password) {
        super(nom, prenom, telephone, adresse, email, password);
    }
 */







}
