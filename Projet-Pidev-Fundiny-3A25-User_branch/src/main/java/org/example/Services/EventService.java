package org.example.Services;

//import com.example.demo2.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.Event;
import org.example.Entities.Event;
import org.example.utils.MyDataBase;

public class EventService {

    public void AjouterEvenement (Event e) {
        String requete = "insert into evenement (nom, description, dateDebut, dateFin, objectifFinancement, montantCollecte, categorie)" +
                " values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = MyDataBase.getInstance().getCnx().prepareStatement(requete);
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
            Statement ps = MyDataBase.getInstance().getCnx().createStatement();
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
                        rs.getInt("idEvent"),
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
            PreparedStatement ps = MyDataBase.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
}
