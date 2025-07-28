package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * JavaFX App
 */
public class App extends Application {
    private double dragOffsetX, dragOffsetY;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Hello World");
        stage.setWidth(600);
        stage.setHeight(400);
        stage.centerOnScreen();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        double x = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2;
        double y = bounds.getMinX() + (bounds.getHeight() - stage.getHeight()) / 2;

        stage.setX(x);
        stage.setY(y);

        Button close = new Button("Close");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        HBox root = new HBox(close);
        root.setBackground(null);

        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(207, 160, 231));

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragOffsetX = event.getScreenX() - stage.getX();
                dragOffsetY = event.getScreenY() - stage.getY();
            }
        });
        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - dragOffsetX);
            stage.setY(event.getScreenY() - dragOffsetY);
        });


        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}