package org.example.Entities;

public class Reponse {
    private int ID_Reponse;
    private int ID_Utilisateur;
    private String email;
    private String objet;
    private String texte;

    // Constructors (you can create more if needed)

    public Reponse() {
    }

    public Reponse(int ID_Reponse, int ID_Utilisateur, String email, String objet, String texte) {
        this.ID_Reponse = ID_Reponse;
        this.ID_Utilisateur = ID_Utilisateur;
        this.email = email;
        this.objet = objet;
        this.texte = texte;
    }

    // Getters and Setters (you can generate these using your IDE)

    public int getID_Reponse() {
        return ID_Reponse;
    }

    public void setID_Reponse(int ID_Reponse) {
        this.ID_Reponse = ID_Reponse;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getobjet() {
        return objet;
    }

    public void setobjet(String objet) {
        this.objet = objet;
    }

    public String gettexte() {
        return texte;
    }

    public void settexte(String texte) {
        this.texte = texte;
    }
}
