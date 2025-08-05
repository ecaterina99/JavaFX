package com.internetPackages.Controller;

import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.Service.PackageService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
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


    @FXML
    private TableView<InternetPackageModel> dataTable;
    @FXML
    private TableColumn<InternetPackageModel, String> firstNameColumn;
    @FXML
    private TableColumn<InternetPackageModel, String> lastNameColumn;
    @FXML
    private TableColumn<InternetPackageModel, String> addressColumn;
    @FXML
    private TableColumn<InternetPackageModel, String> speedColumn;
    @FXML
    private TableColumn<InternetPackageModel, String> bandwidthColumn;
    @FXML
    private TableColumn<InternetPackageModel, String> durationColumn;


    private ObservableList<InternetPackageModel> tableData;


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
        initializeTable();
        loadTableData();
    }



    private void initializeTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        bandwidthColumn.setCellValueFactory(new PropertyValueFactory<>("bandwidth"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tableData = FXCollections.observableArrayList();
        dataTable.setItems(tableData);

        dataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadSelectedItemToForm(newValue);
            }
        });
    }

    private void loadTableData() {
        try {
            List<InternetPackageModel> packages = packageService.findAll();
            tableData.clear();
            tableData.addAll(packages);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
    }

    private void loadSelectedItemToForm(InternetPackageModel selectedItem) {
        model.setId(selectedItem.getId());
        model.setFirstName(selectedItem.getFirstName());
        model.setLastName(selectedItem.getLastName());
        model.setAddress(selectedItem.getAddress());
        model.setSpeed(selectedItem.getSpeed());
        model.setBandwidth(selectedItem.getBandwidth());
        model.setDuration(selectedItem.getDuration());
    }


    @FXML
    private void save() {

        if (model.isValid()) {
            packageService.save(model);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Package saved successfully!");
            clearForm();
            loadTableData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields!");
        }
    }

    @FXML
    private void deleteRow() {
        InternetPackageModel selectedItem = dataTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                packageService.delete(selectedItem.getId());
                showAlert(Alert.AlertType.INFORMATION, "Success", "Package deleted successfully!");
                loadTableData();
                clearForm();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete package: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a package to delete!");
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
        dataTable.getSelectionModel().clearSelection();
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