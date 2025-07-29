package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label userName = new Label("User name:");
        userName.setLabelFor(userTextField);
        userName.setMnemonicParsing(true);
        grid.add(userName, 0, 1);

        PasswordField passBox = new PasswordField();
        Label pass = new Label("Password:");
        pass.setLabelFor(passBox);
        pass.setMnemonicParsing(true);
        grid.addRow(2, pass, passBox);

        Hyperlink hyperlink = new Hyperlink("Forgot your password?");
        hyperlink.setMnemonicParsing(true);
        grid.setHalignment(hyperlink, HPos.RIGHT);
        grid.add(hyperlink, 1, 3);
        hyperlink.setId("hyperlink");

        Button btn = new Button("Sing in");
        btn.setDefaultButton(true);
        btn.setMnemonicParsing(true);
        btn.setId("sing_in");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setCancelButton(true);
        cancelBtn.setMnemonicParsing(true);
        cancelBtn.setId("cancel");


        HBox hBox = new HBox(5, btn, cancelBtn);
        hBox.setAlignment(Pos.TOP_RIGHT);
        grid.add(hBox, 1, 4);

        Text actionText = new Text();
        grid.add(actionText, 1, 6);


        EventHandler<ActionEvent> btnPressed = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Labeled target = (Labeled) event.getSource();
                switch (target.getId()){
                    case"sing_in":
                        actionText.setText("Sign in button pressed");
                        break;
                        case "cancel":
                            actionText.setText("Cancel button pressed");
                            break;
                            case "hyperlink":
                                actionText.setText("Hyperlink button pressed");
                                break;
                }
            }
        };

        btn.setOnAction(btnPressed);
        cancelBtn.setOnAction(btnPressed);
        hyperlink.setOnAction(btnPressed);

        Scene scene = new Scene(grid, 300, 275);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(new ColumnConstraints(), cc);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}