package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.article;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public ArrayList<article> getAll() throws SQLException {
        ArrayList<article> articles = new ArrayList<>();
        String query = "SELECT * FROM article";
        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                article article = new article();
                article.setId(resultSet.getInt("id"));
                article.setDescription(resultSet.getString("description"));
                article.setImage(resultSet.getString("image"));
                articles.add(article);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving articles: " + e.getMessage());
        }
        return articles;
    }




    //    @Override
//    public article getById(int id) throws SQLException{
//        String query = "SELECT * FROM article WHERE id = ?";
//        try (PreparedStatement statement = cnx.prepareStatement(query)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    article article = new article();
//                    article.setId(resultSet.getInt("id"));
//                    article.setDescription(resultSet.getString("description"));
//                    article.setImage(resultSet.getString("image"));
//                    return article;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving article: " + e.getMessage());
//        }
//        return null;
//    }
    @Override
    public void update(article article) {

    }

    @Override
    public boolean delete(article article) {
        return false;
    }


}
