package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    //bidirectional binding
    @Override
    public void start(Stage stage) {
       Slider slider1 = new Slider();
        Slider slider = new Slider();
        Circle circle = new Circle();
       slider1.valueProperty().bindBidirectional(slider.valueProperty());
        slider.valueProperty().bindBidirectional(circle.radiusProperty());


      HBox hBox = new HBox();
        hBox.getChildren().addAll(slider1, slider);

        Group group = new Group();
        group.getChildren().addAll(slider1,slider,circle);
     //   Scene scene = new Scene(hBox,200,100);
        Scene scene = new Scene(group);

        circle.centerXProperty().bind(scene.widthProperty().divide(2));
        circle.centerXProperty().bind(scene.heightProperty().divide(2));

        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}


/*public class App {

    public static void main(String[] args) {
        Person person = new Person("Jane", "Doe", 35);
        System.out.println(person.firstNameProperty());
        System.out.println(person.lastNameProperty());
        System.out.println(person.ageProperty());

/*person.ageProperty().addListener(new InvalidationListener() {
    @Override
    public void invalidated(Observable observable) {
        System.out.println("age invalidated"+ observable);
    }
});

person.ageProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Age changed. Old value:\n" + oldValue + ", New: " + newValue);
            }
        });

        person.setAge(33);
        person.setAge(38);
        person.setAge(40);


        IntegerProperty x = new SimpleIntegerProperty(10);
        IntegerProperty y = new SimpleIntegerProperty(20);

        NumberBinding sum = x.add(y);
       int value= sum.intValue();
       System.out.println(value);
       x.set(15);
       System.out.println(sum.intValue());


    }
}

 */