package org.example.Services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Entities.*;
import org.example.interfaces.Iservice;
import org.example.utils.MyDataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class ServiceProjet   {
//public class ServiceProjet implements Iservice<Projet> {
    private static Connection cnx ;
    public ServiceProjet(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    /*
    @Override
    public void add(Projet projet) {
        String qry = "INSERT INTO `projet`( `nom_pr`, `dateD`, `CA`) VALUES (?,?,?)" ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,projet.getNomPr());
            stm.setString(2, projet.getDateD().toString());
            stm.setInt(3,projet.getCA());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


     */
        public static void add(Projet projet, int user_id) {
        String qry = "INSERT INTO `projet`(`user_id`, `nom_pr`, `date_d`, `ca`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, user_id);
            stm.setString(2, projet.getNomPr());
            stm.setString(3, projet.getDateD().toString());
            stm.setInt(4, projet.getCA());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //@Override
    public ArrayList<Projet> getAll() {
        ArrayList<Projet> projets = new ArrayList<>();
        String qry = "SELECT * FROM projet";

        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(qry)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String 	nom_pr = rs.getString("nom_pr");
                Date dateD = rs.getDate("date_d");
                int CA = rs.getInt("ca");
                //String nomPo = rs.getString(3);
                ///int CA = rs.getInt(5);


                Projet pr = new Projet(id,user_id,nom_pr, CA,dateD);
                //Projet pr = new Projet(id, nom_pr, nomPo,dateD, CA);
                projets.add(pr);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return projets;
    }
    /*
    public List<Projet> afficherListe() throws SQLException {
        String req = "SELECT * FROM projet";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Projet> lv = new ArrayList<Projet>();
        while (rs.next()) {
            Projet p = new Projet(
                                rs.getInt("id"),
                                rs.getString("nom_pr"),
                    rs.getInt("CA"),
                    rs.getDate("dateD")
                        );
            lv.add(p);
        }

        return lv;
    }

     */
    public List<Projet> afficherListe() {
        List<Projet> projectList = new ArrayList<>();
        String query = "SELECT * FROM `projet`";

        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            projectList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String nom_pr = resultSet.getString("nom_pr");
                Date dateD = Date.valueOf(resultSet.getDate("date_d").toLocalDate());
                int CA = resultSet.getInt("ca");
                int investissements_id = resultSet.getInt("investissements_id");

                Projet projet;
                projet = new Projet(id, user_id, nom_pr, dateD, CA, investissements_id);
                projectList.add(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }
    public ObservableList<Projet> getAllProjects() {
        ObservableList<Projet> projects = FXCollections.observableArrayList();
        List<Projet> projectList = afficherListe();
        projects.addAll(projectList);
        return projects;
    }

    //@Override
    public void update(Projet projet) {
        String qry = "UPDATE `projet` SET `nom_pr`=?, `nomPo`=?, `dateD`=?, `CA`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, projet.getNomPr());
            //stm.setString(2, projet.getNomPo());
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


    //@Override
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





