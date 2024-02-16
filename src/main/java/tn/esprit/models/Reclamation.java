package tn.esprit.models;
import java.util.Date;

public class Reclamation {
    private int ID_Reclamation;
    private int ID_Utilisateur;
    private int ID_Projet;
    private int ID_Type_Reclamation;
    private int ID_Admin;
    private String Statut_Reclamation;
    private String Description_Reclamation;
    private Date Date_Soumission;
    private Date Derniere_Mise_a_Jour;

    // Constructors (you can create more if needed)

    public Reclamation() {
    }

    public Reclamation(int ID_Reclamation, int ID_Utilisateur, int ID_Projet, int ID_Type_Reclamation, int ID_Admin, String Statut_Reclamation, String Description_Reclamation, Date Date_Soumission, Date Derniere_Mise_a_Jour) {
        this.ID_Reclamation = ID_Reclamation;
        this.ID_Utilisateur = ID_Utilisateur;
        this.ID_Projet = ID_Projet;
        this.ID_Type_Reclamation = ID_Type_Reclamation;
        this.ID_Admin = ID_Admin;
        this.Statut_Reclamation = Statut_Reclamation;
        this.Description_Reclamation = Description_Reclamation;
        this.Date_Soumission = Date_Soumission;
        this.Derniere_Mise_a_Jour = Derniere_Mise_a_Jour;
    }

    // Getters and Setters (you can generate these using your IDE)

    public int getID_Reclamation() {
        return ID_Reclamation;
    }

    public void setID_Reclamation(int ID_Reclamation) {
        this.ID_Reclamation = ID_Reclamation;
    }

    public int getID_Utilisateur() {
        return ID_Utilisateur;
    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
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

    public String getStatut_Reclamation() {
        return Statut_Reclamation;
    }

    public void setStatut_Reclamation(String Statut_Reclamation) {
        this.Statut_Reclamation = Statut_Reclamation;
    }

    public String getDescription_Reclamation() {
        return Description_Reclamation;
    }

    public void setDescription_Reclamation(String Description_Reclamation) {
        this.Description_Reclamation = Description_Reclamation;
    }

    public Date getDate_Soumission() {
        return Date_Soumission;
    }

    public void setDate_Soumission(Date Date_Soumission) {
        this.Date_Soumission = Date_Soumission;
    }

    public Date getDerniere_Mise_a_Jour() {
        return Derniere_Mise_a_Jour;
    }

    public void setDerniere_Mise_a_Jour(Date Derniere_Mise_a_Jour) {
        this.Derniere_Mise_a_Jour = Derniere_Mise_a_Jour;
    }
}
