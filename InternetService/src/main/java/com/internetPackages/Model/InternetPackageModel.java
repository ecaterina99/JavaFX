package com.internetPackages.Model;

import javafx.beans.property.*;
import org.springframework.stereotype.Component;
/**
 * UI Model for Internet Package data binding and validation.
 */
@Component
public class InternetPackageModel {
    private final IntegerProperty id = new SimpleIntegerProperty();
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

    public boolean isValid() {
        return !isNullOrEmpty(getFirstName()) &&
                !isNullOrEmpty(getLastName()) &&
                !isNullOrEmpty(getAddress()) &&
                !isNullOrEmpty(getSpeed()) &&
                !isNullOrEmpty(getBandwidth()) &&
                !isNullOrEmpty(getDuration());
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
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

    public IntegerProperty idProperty() {
        return id;
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getDuration() {
        return this.duration.get();
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

    public int getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
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