package tn.esprit.models;

import java.sql.Timestamp;

public class investissements {
    private int invID,userID,projetID;
    private double montant;
    private String description;
    private Timestamp date;

    public investissements() {
    }

    public investissements(int invID, int userID, int projetID, double montant, String description, Timestamp date) {
        this.invID = invID;
        this.userID = userID;
        this.projetID = projetID;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }

    public int getInvID() {
        return invID;
    }

    public void setInvID(int invID) {
        this.invID = invID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjetID() {
        return projetID;
    }

    public void setProjetID(int projetID) {
        this.projetID = projetID;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "investissements{" +
//                "invID=" + invID +
//                ", userID=" + userID +
//                ", projetID=" + projetID +
                " montant=" + montant +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                "}\n";
    }
}