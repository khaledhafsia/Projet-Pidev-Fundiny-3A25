package org.example.Entities;

import java.sql.Timestamp;
import org.example.Entities.Funder;
public class investissements {
    private int id,user_id,project_id;
    private double montant;
    private String description;
    private Timestamp date;

    private Funder funder;

    public investissements() {
    }
    public investissements(int id) {
        this.id = id;
    }
    public investissements(double montant, String description, Timestamp date) {

        this.montant = montant;
        this.description = description;
        this.date = date;
    }
    public investissements(int project_id,double montant, String description, Timestamp date) {
        this.project_id = project_id;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }
    public investissements(int id, int user_id, int project_id, double montant, String description, Timestamp date) {
        this.id = id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }
    public investissements(int invI, int project_id, double montant, String description, Timestamp date) {
        this.id = id;
        this.project_id = project_id;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }
    public investissements(int id, int project_id, double montant, String description, Timestamp date, Funder funder) {
        this.id = id;
        this.project_id = project_id;
        this.montant = montant;
        this.description = description;
        this.date = date;
        this.funder = funder;
    }
    public Funder getFunder() {
        return funder;
    }

    public void setFunder(Funder funder) {
        this.funder = funder;
    }

    public int getInvID() {
        return id;
    }

    public void setInvID(int id) {
        this.id = id;
    }

    public int getUserID() {
        return user_id;
    }

    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public int getProjetID() {
        return project_id;
    }

    public void setProjetID(int project_id) {
        this.project_id = project_id;
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
//                "id=" + id +
//                ", user_id=" + user_id +
//                ", project_id=" + project_id +
                " montant=" + montant +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                "}\n";
    }
}
