package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tn.esprit.models.article;
import tn.esprit.models.comment;
import tn.esprit.services.servicearticle;
import tn.esprit.services.servicecomment;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class showarticleController implements Initializable {

    private servicearticle sa = new servicearticle();
    private servicecomment sc = new servicecomment();

    @FXML
    private VBox postContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        // Create and set the image view for the post image
        ImageView postImageView = new ImageView(new Image(article.getImage()));
        postImageView.setFitHeight(200);
        postImageView.setFitWidth(200);
        postCard.getChildren().add(postImageView);

        // Create and set the label for the post content
        Label postContentLabel = new Label(article.getDescription());
        postContentLabel.getStyleClass().add("post-content");
        postCard.getChildren().add(postContentLabel);

        // Display comments for the current article
        List<comment> comments = sc.getCommentsBypostid(article.getId());
        VBox commentContainer = new VBox();
        for (comment c : comments) {
            // Create and set the label for each comment
            Label commentLabel = new Label(c.getComment());
            commentLabel.getStyleClass().add("comment");
            commentContainer.getChildren().add(commentLabel);
        }

        // Create text area and button for adding comments
        TextArea commentTextArea = new TextArea();
        commentTextArea.setPromptText("Add a comment...");

        Button addCommentButton = new Button("Add Comment");
        addCommentButton.setOnAction(event -> {
            String newComment = commentTextArea.getText();
            try {
                // Add the comment to the database with the correct articleId
                sc.add(new comment(article.getId(), newComment));
                // Clear the comment text area after adding the comment
                commentTextArea.clear();
                // Refresh the UI to display the new comment
                commentContainer.getChildren().clear(); // Clear existing comments
                List<comment> updatedComments = sc.getCommentsBypostid(article.getId());
                for (comment c : updatedComments) {
                    // Add updated comments to the comment container
                    Label updatedCommentLabel = new Label(c.getComment());
                    updatedCommentLabel.getStyleClass().add("comment");
                    commentContainer.getChildren().add(updatedCommentLabel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception
            }
        });

        // Add the text area, button, and comments to the post card
        postCard.getChildren().addAll(commentContainer, commentTextArea, addCommentButton);

        return postCard;
    }
}
