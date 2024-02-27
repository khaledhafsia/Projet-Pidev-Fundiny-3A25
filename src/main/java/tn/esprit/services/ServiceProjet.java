package tn.esprit.services;

import tn.esprit.models.Projet;
import tn.esprit.interfaces.IService;
import tn.esprit.utils.MyDataBase;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;




import java.util.ArrayList;

public class ServiceProjet implements IService<Projet> {
    private Connection cnx ;
    public ServiceProjet(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(Projet projet) {
        String qry = "INSERT INTO `projet`( `nomPr`, `nomPo`, `dateD`, `CA`) VALUES (?,?,?,?)" ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,projet.getNomPr());
            stm.setString(2, projet.getNomPo());
            stm.setString(3, projet.getDateD().toString());
            stm.setInt(4,projet.getCA());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Projet> getAll() {
        ArrayList<Projet> projets = new ArrayList<>();
        String qry = "SELECT * FROM projet";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(qry)) {

            while (rs.next()) {
                int id = rs.getInt(1);
                String nomPr = rs.getString(2);
                String nomPo = rs.getString(3);
                Date dateD = rs.getDate("dateD");
                int CA = rs.getInt(5);

                Projet pr = new Projet(id, nomPr, nomPo,dateD, CA);
                projets.add(pr);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return projets;
    }
    @Override
    public void update(Projet projet) {
        String qry = "UPDATE `projet` SET `nomPr`=?, `nomPo`=?, `dateD`=?, `CA`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, projet.getNomPr());
            stm.setString(2, projet.getNomPo());
            stm.setString(3, String.valueOf(projet.getDateD()));
            stm.setInt(4, projet.getCA());
            stm.setInt(5, projet.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour pour le projet avec l'ID : " + projet.getId());
            } else {
                System.out.println("Mise à jour réussie pour le projet avec l'ID : " + projet.getId());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }


    @Override
    public boolean delete(Projet projet) {
        String qry = "DELETE FROM `projet` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, projet.getId());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne supprimée pour le projet avec l'ID : " + projet.getId());
                return false;
            } else {
                System.out.println("Projet supprimé avec succès, ID : " + projet.getId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet : " + e.getMessage());
            return false;
        }}

    }

