package tn.esprit.models;
import java.util.Date;

public class Reponse {
    private int ID_Reponse;
    private int ID_Reclamation;
    private int ID_Administrateur;
    private String Contenu_Reponse;
    private Date Date_Reponse;

    // Constructors (you can create more if needed)

    public Reponse() {
    }

    public Reponse(int ID_Reponse, int ID_Reclamation, int ID_Administrateur, String Contenu_Reponse, Date Date_Reponse) {
        this.ID_Reponse = ID_Reponse;
        this.ID_Reclamation = ID_Reclamation;
        this.ID_Administrateur = ID_Administrateur;
        this.Contenu_Reponse = Contenu_Reponse;
        this.Date_Reponse = Date_Reponse;
    }

    // Getters and Setters (you can generate these using your IDE)

    public int getID_Reponse() {
        return ID_Reponse;
    }

    public void setID_Reponse(int ID_Reponse) {
        this.ID_Reponse = ID_Reponse;
    }

    public int getID_Reclamation() {
        return ID_Reclamation;
    }

    public void setID_Reclamation(int ID_Reclamation) {
        this.ID_Reclamation = ID_Reclamation;
    }

    public int getID_Administrateur() {
        return ID_Administrateur;
    }

    public void setID_Administrateur(int ID_Administrateur) {
        this.ID_Administrateur = ID_Administrateur;
    }

    public String getContenu_Reponse() {
        return Contenu_Reponse;
    }

    public void setContenu_Reponse(String Contenu_Reponse) {
        this.Contenu_Reponse = Contenu_Reponse;
    }

    public Date getDate_Reponse() {
        return Date_Reponse;
    }

    public void setDate_Reponse(Date Date_Reponse) {
        this.Date_Reponse = Date_Reponse;
    }
}
