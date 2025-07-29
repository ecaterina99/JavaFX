package org.example;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        GridPane gpane = new GridPane();

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();

        gpane.addRow(0, new Label("First name:"), firstNameField);
        gpane.addRow(1, new Label("Last name:"), lastNameField);
        gpane.addRow(2, new Label("Email:"), emailField);

        Label label = new Label("Question:");
        gpane.add(label, 0, 3, 2, 1);

        TextArea questionArea = new TextArea();
        gpane.add(questionArea, 0, 4, 2, 1);

        GridPane.setHgrow(firstNameField, Priority.ALWAYS);
        GridPane.setVgrow(questionArea, Priority.ALWAYS);

        Button sendButton = new Button("Send");
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        gpane.add(sendButton, 1, 5, 2, 1);

        Scene scene = new Scene(gpane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}