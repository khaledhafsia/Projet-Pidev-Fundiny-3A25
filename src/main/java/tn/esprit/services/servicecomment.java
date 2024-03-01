package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.article;
import tn.esprit.models.comment;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class servicecomment implements IService<comment> {

        private Connection cnx;

        public servicecomment() {
            cnx = MyDataBase.getInstance().getCnx();
        }



    @Override
    public ArrayList<article> getAll() throws SQLException {
        return null;
    }

    @Override
        public void add(comment comment) throws SQLException {
            String req = "INSERT INTO comment (comment) VALUES ( ?)";

            try (PreparedStatement statement = cnx.prepareStatement(req)) {
                statement.setString(1, comment.getComment());

                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error adding comment: " + e.getMessage());
            }
        }





    @Override
    public void update(comment comment) {

    }

    @Override
    public boolean delete(comment comment) {
        return false;
    }


}
