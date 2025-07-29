package org.example;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class ToggleTest extends Application {

    //  ToggleButton onOff = null;

    ToggleButton s_toggle;
    ToggleButton m_toggle;
    ToggleButton l_toggle;
    ToggleButton xl_toggle;

    Label info;
    Label header;

    @Override
    public void start(Stage stage) {

      /*  onOff = new ToggleButton("Power OFF");
        onOff.setOnAction(this::toggle);

        GridPane gridPane = new GridPane();
        gridPane.add(onOff, 0, 0, 1, 1);

        ToggleButton btn = new ToggleButton("Shirt");
        ToggleButton btn1 = new ToggleButton("Shoes");
        ToggleButton btn2 = new ToggleButton("Jacket");

        ToggleGroup group = new ToggleGroup();

        btn.setToggleGroup(group);
        btn1.setToggleGroup(group);
        btn2.setToggleGroup(group);

        gridPane.addRow(1, btn, btn1, btn2);

 */

        header = new Label("Choose your size: ");
        info = new Label();

        s_toggle = new ToggleButton("S");
        m_toggle = new ToggleButton("M");
        l_toggle = new ToggleButton("L");
        xl_toggle = new ToggleButton("XL");

        s_toggle.setPrefWidth(50);
        m_toggle.setPrefWidth(50);
        l_toggle.setPrefWidth(50);
        xl_toggle.setPrefWidth(50);

        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(s_toggle, m_toggle, l_toggle, xl_toggle);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> toggle(observable, oldValue, newValue));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10.0);
        gridPane.setHgap(10.0);
        gridPane.add(header, 0, 0, 4, 1);
        gridPane.addRow(1, s_toggle, m_toggle, l_toggle, xl_toggle);
        gridPane.add(info, 0, 2, 4, 1);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();

    }

  /*  private void toggle(ActionEvent e) {
        if (onOff.isSelected()) {
            onOff.setText("Power ON");
        } else {
            onOff.setText("Power OFF");
        }
    }
   */

    private void toggle(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

        if (oldValue != null && newValue == null) {
            info.setText("You didn't select anything!");
        }

        if (newValue != null) {
            ToggleButton toggleButton = (ToggleButton) newValue;
            info.setText("You selected " + toggleButton.getText() + " size!");
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
