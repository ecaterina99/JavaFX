package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private double dragOffsetX;
    private double dragOffsetY;


    @Override
    public void start(Stage stage) throws IOException {

        URL fxmlURL = getClass().getClassLoader().getResource("add_person.fxml");
        GridPane root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);

        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.show();

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragOffsetX = event.getSceneX();
                dragOffsetY = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - dragOffsetX);
                stage.setY(event.getScreenY() - dragOffsetY);
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}