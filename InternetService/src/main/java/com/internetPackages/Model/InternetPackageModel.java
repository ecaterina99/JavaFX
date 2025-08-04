package com.internetPackages.Model;

import javafx.beans.property.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InternetPackageModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty speed = new SimpleStringProperty();
    private final StringProperty bandwidth = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();

    public InternetPackageModel(String firstName, String lastName, String address, String speed, String bandwidth, String duration) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.bandwidth.set(bandwidth);
        this.duration.set(duration);
    }

    public InternetPackageModel() {

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
        if (address.get() != null && address.get().equals("")) {
            errorList.get().add("Email cannot be empty!");
            valid = false;
        }
        return valid;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public Property<String> speedProperty() {
        return speed;
    }

    public StringProperty bandwidthProperty() {
        return bandwidth;
    }

    public Property<String> durationProperty() {
        return duration;
    }

    public String getLastName() {
        return lastName.get();
    }

    public ArrayList<String> getErrorList() {
        return errorList.get();
    }

    public ObjectProperty<ArrayList<String>> errorListProperty() {
        return errorList;
    }

    public String getDuration() {
        return duration.get();
    }

    public String getBandwidth() {
        return bandwidth.get();
    }

    public String getSpeed() {
        return speed.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(Long.parseLong(id));
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth.set(bandwidth);
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

}