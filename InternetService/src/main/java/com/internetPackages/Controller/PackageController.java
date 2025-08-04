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
    private TextField firstName, lastName, address, speed, bandwidth, duration;

    private final PackageService packageService;
    private final InternetPackageModel model;

    public PackageController(PackageService packageService, InternetPackageModel model) {
        this.packageService = packageService;
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstName.textProperty().bindBidirectional(model.firstNameProperty());
        lastName.textProperty().bindBidirectional(model.lastNameProperty());
        address.textProperty().bindBidirectional(model.addressProperty());
        speed.textProperty().bindBidirectional(model.speedProperty());
        bandwidth.textProperty().bindBidirectional(model.bandwidthProperty());
        duration.textProperty().bindBidirectional(model.durationProperty());
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
    private void clearForm() {
        firstName.clear();
        lastName.clear();
        address.clear();
        speed.clear();
        bandwidth.clear();
        duration.clear();
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


/*

//    private ObservableList<InternetPackageEntity> packagesList;

@FXML private ToggleGroup speedGroup, bandwidthGroup, durationGroup;
     @FXML private TableView<InternetPackageEntity> packagesTable;
     @FXML private TableColumn<InternetPackageEntity, String> firstNameColumn;
     @FXML private TableColumn<InternetPackageEntity, String> lastNameColumn;
     @FXML private TableColumn<InternetPackageEntity, String> addressColumn;
     @FXML private TableColumn<InternetPackageEntity, Integer> speedColumn;
     @FXML private TableColumn<InternetPackageEntity, String> bandwidthColumn;
     @FXML private TableColumn<InternetPackageEntity, Integer> durationColumn;
@Override
public void initialize(URL location, ResourceBundle resources) {
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
    bandwidthColumn.setCellValueFactory(new PropertyValueFactory<>("bandwidth"));
    durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

    loadPackages();
}
    @FXML
    private void savePackage() {
        try {
            String selectedSpeed = getSelectedToggleValue(speedGroup);
            String selectedBandwidth = getSelectedToggleValue(bandwidthGroup);
            String selectedDuration = getSelectedToggleValue(durationGroup);

            if (isValidInput(selectedSpeed, selectedBandwidth, selectedDuration)) {
                InternetPackageModel model = new InternetPackageModel(
                        firstName.getText(),
                        lastName.getText(),
                        address.getText(),
                        Integer.parseInt(selectedSpeed),
                        selectedBandwidth,
                        Integer.parseInt(selectedDuration)
                );

                packageService.save(model);
                loadPackages();
                clearForm();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Package saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Error saving package: " + e.getMessage());
        }
    }
    private void loadPackages() {
        packagesList = FXCollections.observableArrayList(packageService.findAllSales());
        // packagesTable.setItems(packagesList);
    }
    @FXML
    private void clearForm() {
        firstName.clear();
        lastName.clear();
        address.clear();
        if (speedGroup != null) speedGroup.selectToggle(null);
        if (bandwidthGroup != null) bandwidthGroup.selectToggle(null);
        if (durationGroup != null) durationGroup.selectToggle(null);
    }

    @FXML
    private void deleteSelectedRow() {
        InternetPackageEntity selected = packagesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            packagesList.remove(selected);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Package deleted successfully!");
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a package to delete!");
        }
    }

    private String getSelectedToggleValue(ToggleGroup group) {
        if (group != null && group.getSelectedToggle() != null) {
            return ((RadioButton) group.getSelectedToggle()).getText();
        }
        return null;
    }

    private boolean isValidInput(String speed, String bandwidth, String duration) {
        return firstName.getText() != null && !firstName.getText().trim().isEmpty() &&
                lastName.getText() != null && !lastName.getText().trim().isEmpty() &&
                address.getText() != null && !address.getText().trim().isEmpty() &&
                speed != null && bandwidth != null && duration != null;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

 */