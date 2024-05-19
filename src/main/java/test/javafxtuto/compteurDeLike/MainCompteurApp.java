package test.javafxtuto.compteurDeLike;

import java.util.Objects;
import javafx.animation.PauseTransition;
import javafx.application.Application;
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

/*    StackPane root = new StackPane();
    Label label = new Label("Hello World");
    Rectangle rectangle = new Rectangle(360, 200, Color.YELLOW);

    root.getChildren().add(label); // on ajoute un nœud enfant au StackPane
    root.getChildren().add(
        rectangle); // on ajoute un autre nœud enfant au StackPane, on le remonte au-dessus du label, sinon, comme il s'empile au-dessus, il le cache.
    rectangle.toBack(); // envoie le rectangle en arrière

    Scene scene = new Scene(root, 600, 400); // on crée une scène avec comme paramètre le nœud parent, on peut lui ajouter directement une taille
    primaryStage.setScene(scene); // on ajoute la scene au stage.
*/

    VBox root = new VBox(10); //  New VBox avec une marge entre chaque nœud.
    root.setPadding(new Insets(25)); // Ajout padding
    root.setAlignment(Pos.CENTER); // Centrer les nœuds.

    ImageView likeImageView = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/icons/like.png")).toString());
    likeImageView.setFitHeight(30);
    likeImageView.setFitWidth(30);
//    likeImageView.setPreserveRatio(true);
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
    addLikeButton.setGraphic(likeImageView);
    addLikeButton.setContentDisplay(ContentDisplay.BOTTOM);
    addLikeButton.setOnMouseEntered(event -> addLikeButton.setGraphic(likehoverImageView));
    addLikeButton.setOnMouseExited(event -> addLikeButton.setGraphic(likeImageView));

    Button addDislikeButton = new Button("Je n'aime plus");
    addDislikeButton.setGraphic(dislikeImageView);
    addDislikeButton.setContentDisplay(ContentDisplay.BOTTOM);
    addDislikeButton.setOnMouseEntered(event -> addDislikeButton.setGraphic(dislikeHoverImageView));
    addDislikeButton.setOnMouseExited(event -> addDislikeButton.setGraphic(dislikeImageView));

    addLikeButton.setOnAction(event -> {
      updateStatusLabel(++totalLikesCount);
      addLikeButton.setGraphic(likedImageView);
      PauseTransition pause = new PauseTransition(Duration.millis(250));
      pause.setOnFinished(e -> addLikeButton.setGraphic(likeImageView));
      pause.play();
    });

    addDislikeButton.setOnAction(event -> {
      if (totalLikesCount > 0) {
        updateStatusLabel(--totalLikesCount);
        addDislikeButton.setGraphic(dislikedImageView);
        PauseTransition pause = new PauseTransition(Duration.millis(250));
        pause.setOnFinished(e -> addDislikeButton.setGraphic(dislikeImageView));
        pause.play();
      }
    });

    root.getChildren().addAll(label, addLikeButton, addDislikeButton);

    Scene scene = new Scene(root, 300, 200);

    primaryStage.setScene(scene);
    primaryStage.show(); // affiche la fenêtre
    primaryStage.centerOnScreen(); // centre la fenêtre, doit être appelée après show()
  }

  private void updateStatusLabel(int numberOfLikes) {
    label.setText(NB_LIKE + numberOfLikes);
  }
}
