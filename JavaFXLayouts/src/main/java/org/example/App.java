package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Button button1 = new Button("First Button");
        Button button2 = new Button("Second Button");
        button2.layoutYProperty().bind(button1.layoutYProperty());
        button2.layoutXProperty().bind(button1.layoutXProperty().add(button1.widthProperty().add(10)));

        Parent group = new Group(button1, button2);
        group.setEffect(new DropShadow());

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}