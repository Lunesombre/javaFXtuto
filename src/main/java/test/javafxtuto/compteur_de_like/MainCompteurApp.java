package test.javafxtuto.compteur_de_like;

import java.util.Objects;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainCompteurApp extends Application {

  private int totalLikesCount = 0;
  private final Label label = new Label(NB_LIKE + totalLikesCount);
  public static final String NB_LIKE = "Nombre de like actuel : ";

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Compteur de Likes");

    VBox root = new VBox(10); //  New VBox avec une marge entre chaque nœud.
    root.setPadding(new Insets(25)); // Ajout padding
    root.setAlignment(Pos.CENTER); // Centrer les nœuds.

    ImageView likeImageView = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/icons/like.png")).toString());
    likeImageView.setFitHeight(30);
    likeImageView.setFitWidth(30);
    ImageView likehoverImageView = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/icons/like-hover.png")).toString());
    likehoverImageView.setFitHeight(30);
    likehoverImageView.setFitWidth(30);
    ImageView likedImageView = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/icons/liked.png")).toString());
    likedImageView.setFitWidth(30);
    likedImageView.setFitHeight(30);
    ImageView dislikeImageView = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/icons/dislike.png")).toString());
    dislikeImageView.setFitHeight(30);
    dislikeImageView.setFitWidth(30);
    Image dislikeHover = new Image("/assets/icons/dislike-hover.png", 30, 30, false, false);
    ImageView dislikeHoverImageView = new ImageView();
    dislikeHoverImageView.setImage(dislikeHover);
    Image disliked = new Image("/assets/icons/disliked.png", 30, 30, false, false);
    ImageView dislikedImageView = new ImageView();
    dislikedImageView.setImage(disliked);

    Button addLikeButton = new Button("J'adore");

    addLikeButton.setContentDisplay(ContentDisplay.BOTTOM);
    addLikeButton.graphicProperty().bind(Bindings.when(addLikeButton.hoverProperty()).then(likehoverImageView).otherwise(likeImageView));

    Button removeLikeButton = new Button("Je n'aime plus");
    removeLikeButton.graphicProperty().bind(Bindings.when(removeLikeButton.hoverProperty()).then(dislikeHoverImageView).otherwise(dislikeImageView));

    removeLikeButton.setContentDisplay(ContentDisplay.BOTTOM);

    addLikeButton.setOnAction(event -> {
      updateStatusLabel(++totalLikesCount);
      changeIconOnClick(likeImageView, likedImageView, likehoverImageView, addLikeButton);
    });

    removeLikeButton.setOnAction(event -> {
      if (totalLikesCount > 0) {
        updateStatusLabel(--totalLikesCount);
        changeIconOnClick(dislikeImageView, dislikedImageView, dislikeHoverImageView, removeLikeButton);
      }
    });

    root.getChildren().addAll(label, addLikeButton, removeLikeButton);

    Scene scene = new Scene(root, 300, 200);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show(); // affiche la fenêtre
    primaryStage.centerOnScreen(); // centre la fenêtre, doit être appelée après show()
  }

  /**
   * Change l'icône d'un bouton lorsqu'il est cliqué et la garde 250ms. Après la pause, l'icône du bouton est rétablie à son état normal ou à son état
   * de hover, en fonction de si la souris survole le bouton ou non.
   *
   * @param normalImageView  L'icône normale du bouton.
   * @param clickedImageView L'icône du bouton lorsqu'il est cliqué.
   * @param hoverImageView   L'icône du bouton lorsqu'il est survolé par la souris.
   * @param button           Le bouton dont l'icône doit être changée.
   */
  private void changeIconOnClick(ImageView normalImageView, ImageView clickedImageView, ImageView hoverImageView, Button button) {
    BooleanProperty isAnimationFinished = new SimpleBooleanProperty(false);
    PauseTransition pause = new PauseTransition(Duration.millis(250));
    pause.setOnFinished(e -> isAnimationFinished.set(true));
    pause.play();
    button.graphicProperty().bind(
        Bindings.when(isAnimationFinished).then(
                Bindings.when(button.hoverProperty())
                    .then(hoverImageView)
                    .otherwise(normalImageView))
            .otherwise(clickedImageView));
  }

  private void updateStatusLabel(int numberOfLikes) {
    label.setText(NB_LIKE + numberOfLikes);
  }
}
