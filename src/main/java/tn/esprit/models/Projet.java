package tn.esprit.models;
import java.sql.Date;



public class Projet {
    int id ;
    String nomPr , nomPo ;
    Date dateD;
    int CA;

    public Projet(String nomPr, String nomPo, Date dateD,  int CA) {
    }

    public Projet(int id , String nomPr, String nomPo, Date dateD ,int CA) {
        this.id = id;
        this.nomPr = nomPr;
        this.nomPo = nomPo;
        this.dateD = dateD;
        this.CA = CA;
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
