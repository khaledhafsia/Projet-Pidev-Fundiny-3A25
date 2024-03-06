package org.example.Entities;

import java.util.Date;

public class taches {
    int tacheID, invID;
    String titre, priorite, statut;
    java.sql.Date echeanceD;

    public taches() {
    }

    public taches(int tacheID, int invID, String titre, String priorite, String statut, Date echeanceD) {
        this.tacheID = tacheID;
        this.invID = invID;
        this.titre = titre;
        this.priorite = priorite;
        this.statut = statut;
        this.echeanceD = (java.sql.Date) echeanceD;
    }

    public int getTacheID() {
        return tacheID;
    }

    public void setTacheID(int tacheID) {
        this.tacheID = tacheID;
    }

    public int getInvID() {
        return invID;
    }

    public void setInvID(int invID) {
        this.invID = invID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public java.sql.Date getEcheanceD() {
        return (java.sql.Date) echeanceD;
    }

    public void setEcheanceD(Date echeanceD) {
        this.echeanceD = (java.sql.Date) echeanceD;
    }

    @Override
    public String toString() {
        return "taches{" +
//                "tacheID=" + tacheID +
//                ", invID=" + invID +
                " titre='" + titre + '\'' +
                ", priorite='" + priorite + '\'' +
                ", statut='" + statut + '\'' +
                ", echeanceD=" + echeanceD +
                "}\n";
    }
}
