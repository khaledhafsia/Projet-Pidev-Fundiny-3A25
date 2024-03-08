package org.example.Entities;

import java.sql.Date;

public class Collaboration {
    int id_collaboration ;
    String nomColl ;
    String TypeColl ;
    Date dateColl ;
    int id;

    public Collaboration() {
    }

    public Collaboration(int id_collaboration, String nomColl, String TypeColl, Date dateColl, int id) {
        this.id_collaboration = id_collaboration;
        this.nomColl = nomColl;
        this.TypeColl = TypeColl;
        this.dateColl = dateColl;
        this.id = id;
    }

    public int getId_collaboration() {
        return id_collaboration;
    }
    public int getId() {
        return id;
    }

    public String getNomColl() {
        return nomColl;
    }

    public String getTypeColl() {
        return TypeColl;
    }

    public Date getDateColl() {
        return dateColl;
    }

    public void setId_collaboration(int id_collaboration) {
        this.id_collaboration = id_collaboration;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNomColl(String nomColl) {
        this.nomColl = nomColl;
    }

    public void setTypeColl(String TypeColl) {
        this.TypeColl = TypeColl;
    }

    public void setDateColl(Date dateColl) {
        this.dateColl = dateColl;
    }

    @Override
    public String toString() {
        return
                "nomColl='" + nomColl + '\'' +
                ", TypeColl=" + TypeColl +
                ", dateColl=" + dateColl +

                '}';
    }
}
