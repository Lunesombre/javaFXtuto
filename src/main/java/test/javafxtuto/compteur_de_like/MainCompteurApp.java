package test.javafxtuto.compteur_de_like;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class MainCompteurApp extends Application {

  private final IntegerProperty totalLikesCount = new SimpleIntegerProperty(0); // Observable puisque c'est un peu le but du tuto.
  private final Label statusLabel = new Label("You currently have " + totalLikesCount + " likes.");

  @Override
  public void start(Stage primaryStage) throws Exception {
    String fontName = "Luciole";
    Font luciole16bold = Font.font(fontName, FontWeight.BOLD, 16);
    primaryStage.setTitle("Like counter");
    primaryStage.getIcons().add(new Image("assets/icons/liked.png")); // ajoute une icône à l'appli, parce que pourquoi pas.

    VBox root = new VBox(10); //  New VBox avec une marge entre chaque nœud.
    root.setPadding(new Insets(25)); // Ajout padding
    root.setAlignment(Pos.CENTER); // Centrer les nœuds.
    root.setBackground(new Background(new BackgroundFill(Color.web("#F0F4F8"), null, null))); // couleur de fond de la VBox

    statusLabel.setFont(Font.font(fontName, 14)); // définit la police et la font size
    statusLabel.setTextFill(Color.web("#044E54")); // définit la couleur en héxadécimal
    statusLabel.textProperty().bind(Bindings.createStringBinding(() -> { // utilise des Observables et bindings pour mettre à jour le statusLabel.
      int likes = totalLikesCount.get();
      return "You currently have " + likes + (likes == 1 ? " like." : " likes.");
    }, totalLikesCount));

    Label titleLabel = new Label("Like Counter");
    titleLabel.setFont(Font.font("Pacifico", FontWeight.BOLD, 28));
    titleLabel.setTextFill(Color.web("#044E54"));
    titleLabel.setPadding(new Insets(0, 0, 40, 0));

    HBox buttonsContainer = new HBox(10); // HBox pour mettre les boutons ensemble sur une ligne

    Button addLikeButton = new Button("Love it".toUpperCase());
    stylizeMyButtons(luciole16bold, addLikeButton);

    Button removeLikeButton = new Button("Don't like it".toUpperCase());
    stylizeMyButtons(luciole16bold, removeLikeButton);

    addLikeButton.setOnAction(event -> totalLikesCount.set(totalLikesCount.get() + 1));
    removeLikeButton.setOnAction(event -> {
      if (totalLikesCount.get() > 0) {
        totalLikesCount.set(totalLikesCount.get() - 1);
      }
    });

    buttonsContainer.getChildren().addAll(addLikeButton, removeLikeButton);
    root.getChildren().addAll(titleLabel, statusLabel, buttonsContainer);

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false); // empêche de passer en plein écran.
    primaryStage.show(); // affiche la fenêtre
    primaryStage.centerOnScreen(); // centre la fenêtre, doit être appelée après show()
  }

  private void stylizeMyButtons(Font font, Button button) {
    button.setFont(font);
    button.setBackground(new Background(new BackgroundFill(Color.web("#E66A6A", 0.9), new CornerRadii(10), null)));
    button.setTextFill(Color.WHITE);
    button.setPadding(new Insets(8));
    button.setContentDisplay(ContentDisplay.BOTTOM);
    button.setPrefWidth(150); // donne une largeur aux boutons plutôt qu'ils s'adaptent à leur contenu.

  }

}
