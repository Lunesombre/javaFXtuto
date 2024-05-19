package test.javafxtuto.helloworld;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloApp extends Application {

  private static final Logger LOG = LoggerFactory.getLogger(HelloApp.class);

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Hello World"); // Change le titre de la fenêtre
    primaryStage.setWidth(1000);
    primaryStage.setHeight(600);
    primaryStage.show(); // affiche la fenêtre
    primaryStage.centerOnScreen(); // centre la fenêtre, doit être appelée après show()

    Stage otherStage = new Stage();
    otherStage.setHeight(300);
    otherStage.setWidth(450);
    otherStage.setTitle("Fenêtre non redimensionnable, auto-fermante");
    otherStage.setResizable(false); // par défaut à true, passer à false empêche de redimensionner
    otherStage.show();
    otherStage.centerOnScreen();

    Thread.ofVirtual().start(() -> {
      try {
        Thread.sleep(3000); // pause 3 sec
        Platform.runLater(otherStage::hide);         // nb close() appelle hide() ^^' // ferme la fenêtre
      } catch (InterruptedException e) {
        LOG.warn("Interrupted thread : {}", e.getMessage());
        Thread.currentThread().interrupt();
      }
    });


  }
}

