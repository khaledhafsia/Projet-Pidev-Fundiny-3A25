package org.example.Entities;

import java.util.Comparator;
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

    public static Comparator<taches> priorityComparator = new Comparator<taches>() {
        @Override
        public int compare(taches task1, taches task2) {
            // Define your logic for comparing tasks based on priority attribute
            // Assuming priority is a String field with values "élevée", "moyenne", and "faible"
            String priority1 = task1.getPriorite();
            String priority2 = task2.getPriorite();

            // Define the order of priorities
            String[] priorityOrder = {"élevée", "moyenne", "faible"};

            // Get the index of each priority in the order array
            int index1 = getIndex(priority1, priorityOrder);
            int index2 = getIndex(priority2, priorityOrder);

            // Compare based on the index
            return Integer.compare(index1, index2);
        }

        private int getIndex(String priority, String[] priorityOrder) {
            for (int i = 0; i < priorityOrder.length; i++) {
                if (priorityOrder[i].equals(priority)) {
                    return i;
                }
            }
            return -1; // Default value if priority not found (shouldn't happen in this case)
        }
    };

    public static Comparator<taches> statutComparator = new Comparator<taches>() {
        @Override
        public int compare(taches task1, taches task2) {
            // Define your logic for comparing tasks based on statut attribute
            // Assuming statut is a String field with values "en attente", "en cours", and "terminée"
            String statut1 = task1.getStatut();
            String statut2 = task2.getStatut();

            // Define the order of statuts
            String[] statutOrder = {"en attente", "en cours", "terminée"};

            // Get the index of each statut in the order array
            int index1 = getIndex(statut1, statutOrder);
            int index2 = getIndex(statut2, statutOrder);

            // Compare based on the index
            return Integer.compare(index1, index2);
        }

        private int getIndex(String statut, String[] statutOrder) {
            for (int i = 0; i < statutOrder.length; i++) {
                if (statutOrder[i].equals(statut)) {
                    return i;
                }
            }
            return -1; // Default value if statut not found (shouldn't happen in this case)
        }
    };

}
