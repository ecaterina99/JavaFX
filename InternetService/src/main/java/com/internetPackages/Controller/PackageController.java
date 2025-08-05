package com.internetPackages.Controller;

import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.Service.PackageService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PackageController implements Initializable {
    @FXML
    private TextField firstName, lastName, address;

    private ToggleGroup durationGroup, speedGroup, bandwidthGroup;

    @FXML
    private RadioButton oneYearRadio;
    @FXML
    private RadioButton twoYearsRadio;


    @FXML
    private RadioButton speed2;
    @FXML
    private RadioButton speed5;
    @FXML
    private RadioButton speed10;
    @FXML
    private RadioButton speed20;
    @FXML
    private RadioButton speed50;
    @FXML
    private RadioButton speed100;

    @FXML
    private RadioButton bandwidth1;
    @FXML
    private RadioButton bandwidth5;
    @FXML
    private RadioButton bandwidth10;
    @FXML
    private RadioButton bandwidth100;
    @FXML
    private RadioButton bandwidthFlat;


    private final PackageService packageService;
    private final InternetPackageModel model;

    public PackageController(PackageService packageService, InternetPackageModel model) {
        this.packageService = packageService;
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        durationGroup = new ToggleGroup();
        oneYearRadio.setToggleGroup(durationGroup);
        twoYearsRadio.setToggleGroup(durationGroup);

        speedGroup = new ToggleGroup();
        speed2.setToggleGroup(speedGroup);
        speed5.setToggleGroup(speedGroup);
        speed10.setToggleGroup(speedGroup);
        speed20.setToggleGroup(speedGroup);
        speed50.setToggleGroup(speedGroup);
        speed100.setToggleGroup(speedGroup);

        bandwidthGroup = new ToggleGroup();
        bandwidth1.setToggleGroup(bandwidthGroup);
        bandwidth5.setToggleGroup(bandwidthGroup);
        bandwidth10.setToggleGroup(bandwidthGroup);
        bandwidth100.setToggleGroup(bandwidthGroup);
        bandwidthFlat.setToggleGroup(bandwidthGroup);

        firstName.textProperty().bindBidirectional(model.firstNameProperty());
        lastName.textProperty().bindBidirectional(model.lastNameProperty());
        address.textProperty().bindBidirectional(model.addressProperty());

        String currentDuration = model.getDuration();
        if (currentDuration != null && !currentDuration.isEmpty()) {
            if (currentDuration.equals("1 year")) {
                durationGroup.selectToggle(oneYearRadio);
            } else if (currentDuration.equals("2 years")) {
                durationGroup.selectToggle(twoYearsRadio);
            }
        }

        String currentSpeed = model.getSpeed();
        if (currentSpeed != null && !currentSpeed.isEmpty()) {
            if (currentSpeed.equals("2")) {
                speedGroup.selectToggle(speed2);
            } else if (currentSpeed.equals("5")) {
                speedGroup.selectToggle(speed5);
            } else if (currentSpeed.equals("10")) {
                speedGroup.selectToggle(speed10);
            } else if (currentSpeed.equals("20")) {
                speedGroup.selectToggle(speed20);
            } else if (currentSpeed.equals("50")) {
                speedGroup.selectToggle(speed50);
            } else if (currentSpeed.equals("100")) {
                speedGroup.selectToggle(speed100);
            }
        }

        String currentBandwidth = model.getBandwidth();
        if (currentBandwidth != null && !currentBandwidth.isEmpty()) {
            if (currentBandwidth.equals("1")) {
                bandwidthGroup.selectToggle(bandwidth1);
            } else if (currentBandwidth.equals("5")) {
                bandwidthGroup.selectToggle(bandwidth5);
            } else if (currentBandwidth.equals("10")) {
                bandwidthGroup.selectToggle(bandwidth10);
            } else if (currentBandwidth.equals("100")) {
                bandwidthGroup.selectToggle(bandwidth100);
            } else if (currentBandwidth.equals("Flat")) {
                bandwidthGroup.selectToggle(bandwidthFlat);
            }
        }


        durationGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                model.setDuration(selected.getText());
            }
        });

        speedGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                model.setSpeed(selected.getText());
            }
        });

        bandwidthGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                RadioButton selected = (RadioButton) newToggle;
                model.setBandwidth(selected.getText());
            }
        });


    }

    @FXML
    private void save() {

        if (model.isValid()) {
            packageService.save(model);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Package saved successfully!");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields!");
        }
    }

    @FXML
    private void deleteRow() {
        if(model!=null) {
            packageService.delete(model.getId());
        }
    }

    @FXML
    private void clearForm() {
        firstName.clear();
        lastName.clear();
        address.clear();
        model.setBandwidth(null);
        if (bandwidthGroup != null) bandwidthGroup.selectToggle(null);
        model.setDuration(null);
        if (durationGroup != null) durationGroup.selectToggle(null);
        model.setSpeed(null);
        if (speedGroup != null) speedGroup.selectToggle(null);
    }




    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void closeForm() {
        Platform.exit();
    }
}