package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class PersonController {

    @FXML
    private TextField firstName, lastName, email, address, country;

    Person person;

    public PersonController() {
    }

    @FXML
    private void initialize() {

        person = new Person();

        firstName.textProperty().bindBidirectional(person.firstNameProperty());
        lastName.textProperty().bindBidirectional(person.lastNameProperty());
        email.textProperty().bindBidirectional(person.emailProperty());
        address.textProperty().bindBidirectional(person.addressProperty());
        country.textProperty().bindBidirectional(person.countryProperty());
    }

    @FXML
    private void savePerson() {
        if (person.isValid()) {
            person.save();
        } else {
            StringBuilder errMsg = new StringBuilder();
            ArrayList<String> errList = person.errorsProperty().get();
            for (String err : errList) {
                errMsg.append(err);
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Person cannot be saved");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }

    @FXML
    private void closeForm() {
        Platform.exit();
    }

    @FXML
    private void clearForm() {
        person.firstNameProperty().set("");
        person.lastNameProperty().set("");
        person.emailProperty().set("");
        person.addressProperty().set("");
        person.countryProperty().set("");
    }
}
