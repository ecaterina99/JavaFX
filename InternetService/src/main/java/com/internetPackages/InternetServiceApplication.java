package com.internetPackages;

import com.internetPackages.Configuration.JavaFxConfig;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main entry point for the application.
 * Launches JavaFX application which in turn starts the Spring context.
 */
@SpringBootApplication
public class InternetServiceApplication {
    public static void main(String[] args) {
        Application.launch(JavaFxConfig.class, args);
    }
}
