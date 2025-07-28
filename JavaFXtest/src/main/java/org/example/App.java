package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button sayHelloBtn = new Button("Say Hello");

        sayHelloBtn.setOnAction(new EventHandler<ActionEvent>() {
         @Override
            public void handle(ActionEvent event) {
                String name = textField.getText();
                String msg = "Hello ";
                if(!name.trim().isEmpty()){
                    msg += name;
                }else{
                    msg += "there";
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(msg);
                alert.showAndWait();
            }
        });
        VBox root = new VBox(label, textField, sayHelloBtn);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}