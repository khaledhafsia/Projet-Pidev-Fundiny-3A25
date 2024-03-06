package org.example.Entities;
import java.sql.Date;
import java.time.LocalDate;


public class Projet {
    int id ;
    String 	nom_pr ;
    LocalDate dateD;
    int CA;
    int user_id;
    int investissements_id;

    public Projet(String 	nom_pr, String nomPo, Date dateD, int CA) {
    }

    public Projet(int id , String 	nom_pr, int CA , LocalDate dateD ) {
        this.id = id;
        this.	nom_pr = 	nom_pr;
        this.CA = CA;
        this.dateD = dateD;
    }
/*
    public Projet(int id, String 	nom_pr, Date dateD, int CA) {
        this.id = id;
        this.	nom_pr = 	nom_pr;
        this.dateD = dateD;
        this.CA = CA;
    }

 */
    public Projet(int user_id, String 	nom_pr, LocalDate dateD, int CA) {
        this.user_id = user_id;
        this.	nom_pr = 	nom_pr;
        this.dateD = dateD;
        this.CA = CA;
    }


    public Projet(String 	nom_pr, LocalDate dateD, String ca) {
    }

    public Projet(String 	nom_pr, LocalDate dateD, int ca) {
        this.	nom_pr = 	nom_pr;
        this.dateD = dateD;
        this.CA = CA;
    }

    public Projet(int user_id, String 	nom_pr, int ca, Date dateD) {
        this.user_id = user_id;
        this.	nom_pr = 	nom_pr;
        this.CA = CA;
        this.dateD = dateD.toLocalDate();

    }

    public Projet(int id, int userId, String nomPr, int ca, Date dateD) {
        this.id = id;
        this.user_id = user_id;
        this.	nom_pr = 	nom_pr;
        this.CA = CA;
        this.dateD = dateD.toLocalDate();
    }


    public Projet(int id, int userId, String nomPr, Date dateD, int ca, int investissementsId) {
        this.id = id;
        this.user_id = user_id;
        this.	nom_pr = 	nom_pr;
        this.CA = CA;
        this.dateD = dateD.toLocalDate();
        this.investissements_id=investissements_id;
    }

    public Projet(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getNomPr() {
        return 	nom_pr;
    }



    public LocalDate getDateD() {
        return dateD;
    }
    public int getCA(){
        return CA;
    }


    public void setNomPr(String 	nom_pr) {
        this.	nom_pr = 	nom_pr;
    }



    public void setDateD(LocalDate dateD) {
        this.dateD = dateD;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    @Override
    public String toString() {
        return  " 	nom_pr=" + 	nom_pr +
                ", dateD=" + dateD +
                ", CA=" + CA +
                "\n";
    }
}
