package tn.esprit.models;

public class Reclamation {

    private int ID_Reclamation;
    private String email;
    //private int ID_Utilisateur;
    private int ID_Projet;
    private int ID_Type_Reclamation;
    private int ID_Admin;
    private String objet;
    private String texte;

    public Reclamation() {
    }

    public Reclamation(String email, int ID_Projet, int ID_Type_Reclamation, int ID_Admin, String objet, String texte) {
        this.email = email;
        this.ID_Projet = ID_Projet;
        this.ID_Type_Reclamation = ID_Type_Reclamation;
        this.ID_Admin = ID_Admin;
        this.objet = objet;
        this.texte = texte;
    }

    public Reclamation(int ID_Reclamation, String email, int ID_Projet, int ID_Type_Reclamation, int ID_Admin, String objet, String texte) {
        this.ID_Reclamation = ID_Reclamation;
        this.email = email;
        this.ID_Projet = ID_Projet;
        this.ID_Type_Reclamation = ID_Type_Reclamation;
        this.ID_Admin = ID_Admin;
        this.objet = objet;
        this.texte = texte;
    }

    public int getID_Reclamation() {
        return ID_Reclamation;
    }

    public void setID_Reclamation(int ID_Reclamation) {
        this.ID_Reclamation = ID_Reclamation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID_Projet() {
        return ID_Projet;
    }

    public void setID_Projet(int ID_Projet) {
        this.ID_Projet = ID_Projet;
    }

    public int getID_Type_Reclamation() {
        return ID_Type_Reclamation;
    }

    public void setID_Type_Reclamation(int ID_Type_Reclamation) {
        this.ID_Type_Reclamation = ID_Type_Reclamation;
    }

    public int getID_Admin() {
        return ID_Admin;
    }

    public void setID_Admin(int ID_Admin) {
        this.ID_Admin = ID_Admin;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}