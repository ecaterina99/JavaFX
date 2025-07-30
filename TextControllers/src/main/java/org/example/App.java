package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        TextField nameFld = new TextField();
        Label anchorLbl = new Label("");
        Label caretLbl = new Label("");
        Label lengthLbl = new Label("");
        Label selectedTextLbl = new Label("");
        Label selectionLbl = new Label("");
        Label textLbl = new Label("");

        anchorLbl.textProperty().bind(nameFld.anchorProperty().asString());
        caretLbl.textProperty().bind(nameFld.caretPositionProperty().asString());
        lengthLbl.textProperty().bind(nameFld.lengthProperty().asString());
        selectedTextLbl.textProperty().bind(nameFld.selectedTextProperty());
        selectionLbl.textProperty().bind(nameFld.selectionProperty().asString());
        textLbl.textProperty().bind(nameFld.textProperty());

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(5);

        root.addRow(0, new Label("Name:"), nameFld);
        root.addRow(1, new Label("Anchor Position:"), anchorLbl);
        root.addRow(2, new Label("Caret Postion:"), caretLbl);
        root.addRow(3, new Label("Length:"), lengthLbl);
        root.addRow(4, new Label("Selected Text:"), selectedTextLbl);
        root.addRow(5, new Label("Selection:"), selectionLbl);
        root.addRow(6, new Label("Text:"), textLbl);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Text Input Control Properties");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
