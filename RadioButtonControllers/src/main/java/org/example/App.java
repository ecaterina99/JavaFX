package org.example;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label selection_text = new Label("Selected person: ");

        ObservableList<Person> personList = FXCollections.<Person>observableArrayList(
                new Person("John", "Jacobs", 33,"jac@mail.com"),
                new Person("John", "Deen", 21,"deen@mail.com"),
                new Person("Frederic", "Rust", 15,"rust@mail.com"),
                new Person("Michael", "Breed", 54,"breed@mail.com"));

        ChoiceBox persons = new ChoiceBox(personList);
        persons.setMinWidth(Double.MAX_VALUE);
        persons.setConverter(new PersonStringConverter());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);

        grid.addRow(0,selection_text);
        grid.add(persons, 1, 0,2,1);


        Label firstNameText = new Label("First Name");
        Label firstNameValue = new Label("");
        grid.addRow(2, firstNameText, firstNameValue);

        Label lastNameText = new Label("Last Name");
        Label lastNameValue = new Label("");
        grid.addRow(3, lastNameText, lastNameValue);

        Label emailText = new Label("Email");
        Label emailValue = new Label("");
        grid.addRow(4, emailText, emailValue);

        Label ageText = new Label("Age");
        Label ageValue = new Label("");
        grid.addRow(5, ageText, ageValue);

        firstNameValue.textProperty().bind(Bindings.selectString(persons.getSelectionModel().selectedItemProperty(), "firstName"));
        lastNameValue.textProperty().bind(Bindings.selectString(persons.getSelectionModel().selectedItemProperty(), "lastName"));
        emailValue.textProperty().bind(Bindings.selectString(persons.getSelectionModel().selectedItemProperty(), "email"));
        ageValue.textProperty().bind(Bindings.selectString(persons.getSelectionModel().selectedItemProperty(), "age"));

        ObservableList<Node> children = grid.getChildren();
     /*   for(int i = 0; i < personList.size(); i++){
            if(children.get(i).getClass()==Label.class){
                Label label = (Label) children.get(i);
                label.setFont(new Font(20));
            }
        }

      */

        Scene scene = new Scene(grid,600,400);
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }

}