package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InternetPackageController implements Initializable {

    @FXML
    private TextField firstName, lastName, address;

    @FXML
    private RadioButton oneYearRadio, twoYearsRadio;
    @FXML
    private RadioButton speed2, speed5, speed10, speed20, speed50, speed100;
    @FXML
    private RadioButton bandwidth1, bandwidth5, bandwidth10, bandwidth100, bandwidthFlat;

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

    private ToggleGroup durationGroup, speedGroup, bandwidthGroup;
    private ObservableList<InternetPackageModel> tableData;

    private final InternetPackageService internetPackageService = new InternetPackageService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeToggleGroups();
        initializeTable();
        loadTableData();
        setupTableSelection();
    }

    private void initializeToggleGroups() {
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
    }

    private void setupTableSelection() {
        dataTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        populateForm(newValue);
                    }
                }
        );
    }

    private void populateForm(InternetPackageModel model) {
        firstName.setText(model.getFirstName());
        lastName.setText(model.getLastName());
        address.setText(model.getAddress());

        setRadioButtonByText(durationGroup, model.getDuration());
        setRadioButtonByText(speedGroup, model.getSpeed());
        setRadioButtonByText(bandwidthGroup, model.getBandwidth());
    }

    private void setRadioButtonByText(ToggleGroup group, String text) {
        if (text == null) return;

        group.getToggles().stream()
                .filter(toggle -> ((RadioButton) toggle).getText().equals(text))
                .findFirst()
                .ifPresent(group::selectToggle);
    }

    private void loadTableData() {
        try {
            List<InternetPackageModel> packages = internetPackageService.show();
            tableData.clear();
            tableData.addAll(packages);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
    }

    @FXML
    private void save() {
        InternetPackageModel model = createModelFromForm();

        if (model.isValid()) {
            internetPackageService.save(model);
            tableData.add(model);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Package saved successfully!");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields!");
        }
    }

    private InternetPackageModel createModelFromForm() {
        InternetPackageModel model = new InternetPackageModel();
        model.setFirstName(firstName.getText());
        model.setLastName(lastName.getText());
        model.setAddress(address.getText());

        model.setDuration(getSelectedRadioText(durationGroup));
        model.setSpeed(getSelectedRadioText(speedGroup));
        model.setBandwidth(getSelectedRadioText(bandwidthGroup));

        return model;
    }

    private String getSelectedRadioText(ToggleGroup group) {
        Toggle selected = group.getSelectedToggle();
        return selected != null ? ((RadioButton) selected).getText() : null;
    }

    @FXML
    private void deleteRow() {
        InternetPackageModel selectedItem = dataTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                internetPackageService.deleteById(selectedItem.getId());

                tableData.remove(selectedItem);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Package deleted successfully!");
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

        if (durationGroup != null) durationGroup.selectToggle(null);
        if (speedGroup != null) speedGroup.selectToggle(null);
        if (bandwidthGroup != null) bandwidthGroup.selectToggle(null);

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