package com.internetPackages.Configuration;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * Initializes the JavaFX primary stage when StageReadyEvent is published.
 */
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final ApplicationContext applicationContext;
    private double dragOffsetX;
    private double dragOffsetY;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/package_manager.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.setTitle("Internet Package Management");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}