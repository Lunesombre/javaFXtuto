package test.javafxtuto.observable_demo;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ObservableDemoApp extends Application {

  public static final String HELLO_WORLD = "Hello world";

  @Override
  public void start(Stage primaryStage) throws Exception {
    VBox root = new VBox(10);
    root.setPadding(new Insets(25));
    root.setAlignment(Pos.CENTER);
    Label label = new Label(HELLO_WORLD);
    TextField textField = new TextField(HELLO_WORLD);
    TextField textField2 = new TextField(HELLO_WORLD);
    root.getChildren().addAll(label, textField, textField2);

    Bindings.bindBidirectional(textField.textProperty(), textField2.textProperty());
    label.textProperty().bind(textField.textProperty());

    Scene scene = new Scene(root, 500, 400);

    primaryStage.setTitle("Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
    primaryStage.centerOnScreen();

  }
}
