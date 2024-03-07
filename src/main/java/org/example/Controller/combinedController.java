package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.example.Entities.article;
import org.example.Entities.comment;
import org.example.Services.servicearticle;
import org.example.Services.servicecomment;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
public class combinedController {private servicearticle sa = new servicearticle();
    private servicecomment sc = new servicecomment();
    private String image;

    @FXML
    private VBox postContainer;

    @FXML
    private TextField tfdescription2;



    @FXML
    void btnCREATEClicked(ActionEvent event) throws SQLException {

        String description = tfdescription2.getText();
        if (!description.isEmpty()) {
            sa.add(new article(1, description, image));
            System.out.println("Article added successfully.");
            postContainer.getChildren().clear();
            initialize();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please correct invalid fields.");
            alert.setContentText("All fields are required.");
            alert.showAndWait();
        }
    }

    @FXML
    void selectImageClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Set initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Filter for image files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        // Load and display the selected image
        if (selectedFile != null) {
            image = selectedFile.toURI().toString();
            System.out.println("Image path: " + image);
        }
    }



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

        // Create a container for comments
        VBox commentContainer = new VBox();

        // Display comments for the current article
        List<comment> comments = sc.getCommentsBypostid(article.getId());
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

        // Add the text area and button for adding comments to the post card
        postCard.getChildren().addAll(commentTextArea, addCommentButton);

        // Add the comment container to the post card
        postCard.getChildren().add(commentContainer);

        return postCard;
    }


}
