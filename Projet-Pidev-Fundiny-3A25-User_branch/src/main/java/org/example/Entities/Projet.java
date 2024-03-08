package org.example.Entities;
import java.sql.Date;



public class Projet {
    int id,user_id ;
    String nomPr , nomPo ;
    Date dateD;
    int CA;

    public Projet(int user_id, String nomPr, String nomPo, Date dateD,  int CA) {
    }

    public Projet(int id ,int user_id, String nomPr, String nomPo, Date dateD ,int CA) {
        this.id = id;
        this.user_id = user_id;
        this.nomPr = nomPr;
        this.nomPo = nomPo;
        this.dateD = dateD;
        this.CA = CA;
    }

    public Projet() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getNomPr() {
        return nomPr;
    }

    public String getNomPo() {
        return nomPo;
    }

    public Date getDateD() {
        return dateD;
    }
    public int getCA(){
        return CA;
    }


    public void setNomPr(String nomPr) {
        this.nomPr = nomPr;
    }

    public void setNomPo(String nomPo) {
        this.nomPo = nomPo;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public void setCA(int CA) {
        this.CA = CA;
    }

    @Override
    public String toString() {
        return  " nomPr=" + nomPr +
                ", nomPo=" + nomPo +
                ", dateD=" + dateD +
                ", CA=" + CA +
                "\n";
    }
}
