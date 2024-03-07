package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.Entities.article;
import org.example.Entities.comment;
import org.example.Services.servicearticle;
import org.example.Services.servicecomment;

import java.sql.SQLException;
import java.util.List;

public class adminControllerBlog {
    private servicearticle sa = new servicearticle();
    private servicecomment sc = new servicecomment();

    @FXML
    private VBox postContainer;

    @FXML
    public void initialize() {
        try {
            List<article> articles = sa.getAll();
            System.out.println("Articles retrieved: " + articles.size());
            for (article article : articles) {
                // Create a new card for each article
                VBox postCard = createPostCard(article);
                // Add the card to the post container
                postContainer.getChildren().add(postCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    private VBox createPostCard(article article) throws SQLException {
        // Create a VBox to hold the post content
        VBox postCard = new VBox();
        postCard.getStyleClass().add("post-card");

        // Create and set the label for the post content (description)
        Label postContentLabel = new Label(article.getDescription());
        postContentLabel.getStyleClass().add("post-content");
        postCard.getChildren().add(postContentLabel);

        // Create and set the image view for the post image
        ImageView postImageView = new ImageView(new Image(article.getImage()));
        postImageView.setFitHeight(200);
        postImageView.setFitWidth(200);

        // Create a HBox to hold the image and description
        HBox imageDescBox = new HBox();
        imageDescBox.getChildren().addAll(postImageView, postContentLabel);

        // Add the image and description HBox to the post card
        postCard.getChildren().add(imageDescBox);

        // Create a delete button for the post
        Button deletePostButton = new Button("Delete Post");
        deletePostButton.setOnAction(event -> {
            // Delete the post from the database
            sa.delete(article);
            // Remove the post card from the UI
            postContainer.getChildren().remove(postCard);
        });
        // Add the delete button to the post card
        postCard.getChildren().add(deletePostButton);

        // Create a container for comments
        VBox commentContainer = new VBox();

        // Display comments for the current article
        List<comment> comments = sc.getCommentsBypostid(article.getId());
        for (comment c : comments) {
            // Create and set the label for each comment
            Label commentLabel = new Label(c.getComment());
            commentLabel.getStyleClass().add("comment");
            // Create a delete button for each comment
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> {
                // Delete the comment from the database
                sc.delete(c);
                // Find the parent of the delete button (which is the HBox containing both label and button)
                HBox commentBox = (HBox) deleteButton.getParent();
                // Remove the entire commentBox from the UI
                commentContainer.getChildren().remove(commentBox);
            });
            // Add the comment label and delete button to the comment container
            HBox commentBox = new HBox(commentLabel, deleteButton);
            commentContainer.getChildren().add(commentBox);
        }
        // Add the comment container to the post card
        postCard.getChildren().add(commentContainer);

        return postCard;
    }
}
