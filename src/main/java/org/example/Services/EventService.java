package org.example.Services;

import org.example.Entities.Event;
import org.example.utils.MyDataBase;
import java.sql.PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EventService {
    private Connection cnx;
    public EventService(){
        cnx= MyDataBase.getInstance().getCnx();
    }


    public void AjouterEvenement (Event e) {
        String requete = "insert into evenement (nom, description, dateDebut, dateFin, objectifFinancement, montantCollecte, categorie)" +
                " values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps =  cnx.prepareStatement(requete);
            ps.setString(1,e.getNom());
            ps.setString(2,e.getDescription());
            ps.setString(3,e.getDateDebut());
            ps.setString(4,e.getDateFin());
            ps.setInt(5, e.getObjectifFinancement());
            ps.setInt(6,e.getMontantCollecte());
            ps.setString(7,e.getCategorie());

           /* ps.setString(1,"test tt");
            ps.setString(2,"desc");
            ps.setString(3,"11/11/2000");
            ps.setString(4,"12/11/2021");
            ps.setInt(5, 123);
            ps.setInt(6,124);
            ps.setString(7,"cat"); */

            System.out.println("evenet ajouté avec succées");

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public ArrayList<Event> getEventList() {
        ArrayList<Event> eventsList = new ArrayList<>();

        try {
            String query = "SELECT * FROM evenement ";
            PreparedStatement ps =  cnx.prepareStatement(query);

            Statement ps2 = cnx.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
               /* Event eve = new Event(
                        int idEvent,
                String nom,
                String description,
                java.util.Date dateDebut,
                        Date dateFin,
                double objectifFinancement,
                double montantCollecte,
                String statut,
                String categorie,
                String organisateur,
                String localisation,
                String image))
*/
                Event events = new Event(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("dateDebut"),
                        rs.getString("dateFin"),
                        rs.getInt("objectifFinancement"),
                        rs.getInt("montantCollecte"),
                        rs.getString("categorie")
                );
                eventsList.add(events);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsList;
    }

    public void deleteEvent(int id) {
        String requete = "delete from evenement where idEvent=?";
        try {
            PreparedStatement ps =  cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
}
