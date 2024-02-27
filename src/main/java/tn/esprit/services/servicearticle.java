package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.article;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
public class servicearticle implements IService<article>{

    private Connection cnx;
    public servicearticle(){
        cnx=MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(article article) throws SQLException {
        String req = "INSERT INTO article (description, image) VALUES (?, ?)";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setString(1, article.getDescription());
            statement.setString(2, article.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding article: " + e.getMessage());
        }
    }
    @Override
    public ArrayList<article> getAll() {  ArrayList<article> art = new ArrayList<>();
        String qry ="SELECT * FROM `article`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                article i = new article();

                i.setId(rs.getInt(1));
                i.setDescription(rs.getString(2));
                i.setImage(rs.getString(3));

                art.add(i);


            }

        } catch (SQLException e) {
            System.out.println("show error" +e.getMessage());

        }

        return art;
    }

    @Override
    public void update(article article) {

    }

    @Override
    public boolean delete(article article) {
        return false;
    }
}
