package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    /*
    //group
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
     */

    /* HBox & VBox
    @Override
    public void start(Stage stage) {
        Button button1 = new Button("First Button");
        Button button2 = new Button("Second Button");
        Button button3 = new Button("Third Button");

        button1.setMaxHeight(60);
        button1.setMaxWidth(200);

        HBox.setHgrow(button1, Priority.ALWAYS);
        Insets insets = new Insets(10, 20, 30, 40);
        HBox.setMargin(button1, insets);

        HBox root = new HBox(10,button1, button2, button3);
        root.setAlignment(Pos.BOTTOM_RIGHT);
        root.setFillHeight(true);

        Scene scene = new Scene(root,300,300);
        stage.setScene(scene);
        stage.show();
    }
     */

    /*Tile pane
    @Override
    public void start(Stage stage) {
        List<Node> controls = new ArrayList<>();
        Node node;
        for (int i = 0; i < 50; i++) {
            node = new Button("Button" + i);
            controls.add(node);
        }
        TilePane tilePane = new TilePane(10,10);
        tilePane.getChildren().addAll(controls);

        Scene scene = new Scene(tilePane);
        stage.setScene(scene);
        stage.show();
    }

     */
    /*
    Border Pane Layout
    @Override
    public void start(Stage stage) {
        Button center = new Button("CENTER");
        Button top = new Button("TOP");
        Button right = new Button("RIGHT");
        Button bottom = new Button("BOTTOM");
        Button left = new Button("LEFT");
        top.setMaxWidth(Double.MAX_VALUE);
        bottom.setMaxWidth(Double.MAX_VALUE);

        BorderPane borderPane = new BorderPane(center, top, right, bottom, left);
        BorderPane.setAlignment(top, Pos.TOP_RIGHT);
        BorderPane.setAlignment(bottom, Pos.TOP_RIGHT);

        Insets margins = new Insets(10, 20, 10, 20);
        BorderPane.setMargin(top, margins);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {

        Button one = new Button("lobby");
        Button two = new Button("start match");
        Button three = new Button("help");
        Button four = new Button("about");
        Button five = new Button("copyright - the smart man");

        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = new FlowPane(one, two, three, four);

        flowPane.setAlignment(Pos.TOP_LEFT);
        BorderPane.setAlignment(five, Pos.BOTTOM_RIGHT);

        borderPane.setTop(flowPane);
        borderPane.setBottom(five);
        borderPane.setCenter(new Text("the game"));

        Scene scene = new Scene(borderPane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
     */

    public static void main(String[] args) {
        launch();
    }
}