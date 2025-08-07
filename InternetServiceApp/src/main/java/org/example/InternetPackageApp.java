package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InternetPackageApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_package.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("style.css");

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Internet Package Management");
            primaryStage.setScene(scene);
            primaryStage.show();

            makeStageDraggable(primaryStage, scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeStageDraggable(Stage stage, Scene scene) {
        final double[] dragOffset = new double[2];

        scene.setOnMousePressed(event -> {
            dragOffset[0] = event.getSceneX();
            dragOffset[1] = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - dragOffset[0]);
            stage.setY(event.getScreenY() - dragOffset[1]);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}