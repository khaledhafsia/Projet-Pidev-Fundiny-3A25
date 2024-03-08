package org.example.Services;

import org.example.interfaces.IServiceblog;
import org.example.Entities.comment;
import org.example.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class servicecomment implements IServiceblog<comment> {

        private Connection cnx;

        public servicecomment() {
            cnx = MyDataBase.getInstance().getCnx();
        }





    public void add(comment comment) throws SQLException {
        String req = "INSERT INTO comment (id, comment) VALUES (?, ?)";

        try (PreparedStatement statement = cnx.prepareStatement(req)) {
            statement.setInt(1, comment.getPostid());
            statement.setString(2, comment.getComment());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding comment: " + e.getMessage());
            throw e; // Rethrow the exception to handle it in the calling code
        }
    }
    @Override
    public ArrayList<comment> getAll() throws SQLException {
        ArrayList<comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comment";
        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                comment comment = new comment();
              comment.setCommentid(resultSet.getInt("Commentid"));
                comment.setComment(resultSet.getString("comment"));

                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving articles: " + e.getMessage());
        }
        return comments;
    }
    public List<comment> getCommentsBypostid(int postid) throws SQLException {
        List<comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE postid = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, postid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment comment = new comment();
                comment.setCommentid(resultSet.getInt("Commentid"));
                comment.setPostid(resultSet.getInt("postid"));
                comment.setComment(resultSet.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving comments: " + e.getMessage());
            throw e; // Rethrow the exception to handle it in the calling code
        }
        return comments;
    }
    @Override
    public void update(comment comment) {

    }

    @Override
    public boolean delete(comment comment) {
            String qry = "DELETE FROM comment WHERE `commentid`=?";
            try {
                PreparedStatement stm = cnx.prepareStatement(qry);
                stm.setInt(1, comment.getCommentid());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Aucune ligne supprimée pour le commentaire' commentid: " + comment.getCommentid());
                    return false; // Aucune ligne supprimée
                } else {
                    System.out.println("commentaire supprimé avec succès, commentid: " + comment.getCommentid());
                    return true; // Suppression réussie
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression deu commentaire : " + e.getMessage());
                return false; // Erreur lors de la suppression
            }



    }


}