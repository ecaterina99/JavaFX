package com.internetPackages;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


import java.net.URL;

public class JavaFxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(InternetServiceApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}

/*
    private double dragOffsetX;
    private double dragOffsetY;

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlURL = getClass().getClassLoader().getResource("add_package.fxml");
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

 */