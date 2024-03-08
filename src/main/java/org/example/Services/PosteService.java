package org.example.Services;


//import org.example.Entities.commentaires;
import org.example.Entities.Poste;
import java.sql.SQLException;
import java.util.List;
import org.example.utils.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PosteService {

    private static Poste selectedPoste;

    public static void setSelectedPoste(Poste poste) {
        selectedPoste = poste;
    }

    public static Poste getSelectedPoste() {
        return selectedPoste;
    }



    Connection con;
    Statement stm;
    PreparedStatement ste;
    public PosteService() {
        con = MyDataBase.getInstance().getCnx();
    }



    public void addPoste(Poste p) throws SQLException {
        String requete = "INSERT INTO `Poste` (`titre`, `discription`,`image`, `categorie`) VALUES (?, ?, ?, ?)";
        PreparedStatement ste = con.prepareStatement(requete);
        ste.setString(1, p.getTitre());
        ste.setString(2, p.getDiscription());
        ste.setString(3, p.getImage());
        ste.setString(4, p.getCategorie().name()); // Convert enum to string
        ste.executeUpdate();
    }



    public List<Poste> getAll() throws SQLException {
        String  requete = "SELECT * FROM poste";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(requete);
        System.out.println(rst.toString());
        List<Poste> Poste = new ArrayList<Poste>();
        while(rst.next()){

            Poste c;
            c = new Poste(rst.getString(2),rst.getString(3),rst.getString(4));
            Poste.add(c);

        }
        return Poste;

    }


    public void updatePoste(Poste p, int id) throws SQLException {
        String req = "UPDATE Poste SET titre = ?, discription = ?, image = ?, categorie = ? WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, p.getTitre());
        pre.setString(2, p.getDiscription());
        pre.setString(3, p.getImage());
        pre.setString(4, p.getCategorie().name()); // Convert enum to string
        pre.setInt(5, id);
        pre.executeUpdate();
    }


    public void deletePoste(int id )throws SQLException  {

        String req = "DELETE FROM Poste WHERE `id` = "+ id;
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    public List<Poste> getByTitre(String titre) {
        List<Poste> postes = new ArrayList<>();
        try {
            String query = "SELECT * FROM `Poste` WHERE `titre` LIKE '%" + titre + "%'";
            Connection con = MyDataBase.getInstance().getCnx();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Poste poste = new Poste(

                        rs.getString("titre"),
                        rs.getString("discription"),
                        rs.getString("image")
                );
                postes.add(poste);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return postes;
    }







    public List<Poste> getByCategory(String category) {
        List<Poste> postes = new ArrayList<>();
        try {
            String query = "SELECT * FROM `Poste` WHERE `categorie` = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, category);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Poste poste = new Poste(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("discription"),
                        rs.getString("image"),
                        Poste.categorie.valueOf(rs.getString("categorie")) // Convert string to enum
                );
                postes.add(poste);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return postes;
    }

}


