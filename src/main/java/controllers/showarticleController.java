package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tn.esprit.models.article;
import tn.esprit.services.servicearticle;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class showarticleController implements Initializable {

    private servicearticle sa = new servicearticle();
//    @FXML
//    private Button addCommentButton;

    @FXML
    private TextArea commentTextArea;
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

    private VBox createPostCard(article article) {
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

        return postCard;
    }

    public void addCommentButtonClicked(ActionEvent Event) {
    }
}
