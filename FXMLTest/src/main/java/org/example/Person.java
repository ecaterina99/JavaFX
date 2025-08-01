package org.example;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Person {

    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty email = new SimpleStringProperty(this, "email", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final StringProperty country = new SimpleStringProperty(this, "country", "");

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String address, String country) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.email.set(email);
        this.address.set(address);
        this.country.set(country);
    }

    public Person(String firstName) {
        this.firstName.set(firstName);
    }

    public Person(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }


    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {
        boolean valid = true;
        if (firstName.get() != null && firstName.get().equals("")) {
            errorList.get().add("First Name cannot be empty!");
            valid = false;
        }
        if (lastName.get() != null && lastName.get().equals("")) {
            errorList.get().add("Last Name cannot be empty!");
            valid = false;
        }
        if (email.get() != null && email.get().equals("")) {
            errorList.get().add("Email cannot be empty!");
            valid = false;
        }
        return valid;
    }

    public boolean save() {
        if (isValid()) {
            System.out.println("Person with details: \n" + this + "\nsaved!");
        }
        return true;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName.get() + "\nLast Name: " + lastName.get() +
                "\nEmail: " + email.get() + "\nAddress: " + address.get().toString() + "\nCountry: " + country.get().toString();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public StringProperty countryProperty() {
        return country;
    }

}