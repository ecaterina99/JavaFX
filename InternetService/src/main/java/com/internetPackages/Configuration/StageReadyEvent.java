package com.internetPackages.Configuration;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;
/**
 * Custom Spring ApplicationEvent that wraps a JavaFX Stage.
 * Used to notify when the JavaFX application is ready to set up the UI.
 */
public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }
}