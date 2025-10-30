package com.internetPackages.Controller;

import com.internetPackages.Exception.PackageServiceException;
import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.Service.PackageService;
import jakarta.validation.ConstraintViolation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javafx.collections.transformation.FilteredList;
import java.util.Set;
import java.util.function.Predicate;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Controller class for managing Internet Package operations.
 * Handles UI interactions for creating, updating, deleting and displaying internet packages.
 */

@Component
public class PackageController implements Initializable {

    @FXML
    private TextField firstName, lastName, address, searchField;

    private ToggleGroup durationGroup, speedGroup, bandwidthGroup;

    @FXML
    private RadioButton oneYearRadio, twoYearsRadio;
    @FXML
    private RadioButton speed2, speed5, speed10, speed20, speed50, speed100;
    @FXML
    private RadioButton bandwidth1, bandwidth5, bandwidth10, bandwidth100, bandwidthFlat;
    @FXML
    private ComboBox<String> speedFilter, durationFilter;

    @FXML
    private TableView<InternetPackageModel> dataTable;
    @FXML
    private TableColumn<InternetPackageModel, String> firstNameColumn, lastNameColumn,
            addressColumn, speedColumn,
            bandwidthColumn, durationColumn;

    @FXML
    private Label totalPackagesLabel, activeFiltersLabel, avgSpeedLabel, recordCountLabel;


    private ObservableList<InternetPackageModel> tableData;
    private FilteredList<InternetPackageModel> filteredData;

    private final PackageService packageService;
    private final InternetPackageModel model;

    private final jakarta.validation.Validator validator;

    public PackageController(PackageService packageService, InternetPackageModel model) {
        this.packageService = packageService;
        this.model = model;
        jakarta.validation.ValidatorFactory factory =
                jakarta.validation.Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeToggleGroups();
        bindFieldsToModel();
        setupInitialRadioButtonValues();
        addRadioButtonListeners();
        initializeTable();
        initializeFilters();
        loadTableData();
        setupTableRowClickHandler();

        addFieldValidationListeners();

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

    private void bindFieldsToModel() {
        firstName.textProperty().bindBidirectional(model.firstNameProperty());
        lastName.textProperty().bindBidirectional(model.lastNameProperty());
        address.textProperty().bindBidirectional(model.addressProperty());
    }


    private void addFieldValidationListeners() {
        firstName.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                firstName.setStyle("");
            }
        });

        lastName.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                lastName.setStyle("");
            }
        });

        address.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.isEmpty()) {
                address.setStyle("");
            }
        });
    }

    private void setupInitialRadioButtonValues() {
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
            switch (currentSpeed) {
                case "2" -> speedGroup.selectToggle(speed2);
                case "5" -> speedGroup.selectToggle(speed5);
                case "10" -> speedGroup.selectToggle(speed10);
                case "20" -> speedGroup.selectToggle(speed20);
                case "50" -> speedGroup.selectToggle(speed50);
                case "100" -> speedGroup.selectToggle(speed100);
            }
        }

        String currentBandwidth = model.getBandwidth();
        if (currentBandwidth != null && !currentBandwidth.isEmpty()) {
            switch (currentBandwidth) {
                case "1" -> bandwidthGroup.selectToggle(bandwidth1);
                case "5" -> bandwidthGroup.selectToggle(bandwidth5);
                case "10" -> bandwidthGroup.selectToggle(bandwidth10);
                case "100" -> bandwidthGroup.selectToggle(bandwidth100);
                case "Flat" -> bandwidthGroup.selectToggle(bandwidthFlat);
            }
        }
    }

    private void addRadioButtonListeners() {
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

    private void initializeTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        bandwidthColumn.setCellValueFactory(new PropertyValueFactory<>("bandwidth"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tableData = FXCollections.observableArrayList();
        filteredData = new FilteredList<>(tableData, p -> true);
        dataTable.setItems(filteredData);
    }

    private void initializeFilters() {
        // Initialize speed filter ComboBox
        speedFilter.setItems(FXCollections.observableArrayList(
                "All Speeds", "2", "5", "10", "20", "50", "100"
        ));
        speedFilter.setValue("All Speeds");

        // Initialize duration filter ComboBox
        durationFilter.setItems(FXCollections.observableArrayList(
                "All Durations", "1 year", "2 years"
        ));
        durationFilter.setValue("All Durations");

        // Add listeners for filtering
        searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        speedFilter.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());
        durationFilter.valueProperty().addListener((obs, oldVal, newVal) -> applyFilters());
    }

    private void applyFilters() {
        filteredData.setPredicate(createPredicate());
        updateStatistics();
    }

    private Predicate<InternetPackageModel> createPredicate() {
        return pkg -> {
            // Search filter (by name)
            String search = searchField.getText();
            boolean matchesSearch = search == null || search.isEmpty() ||
                    pkg.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
                    pkg.getLastName().toLowerCase().contains(search.toLowerCase()) ||
                    pkg.getAddress().toLowerCase().contains(search.toLowerCase());

            // Speed filter
            String selectedSpeed = speedFilter.getValue();
            boolean matchesSpeed = selectedSpeed == null ||
                    selectedSpeed.equals("All Speeds") ||
                    pkg.getSpeed().equals(selectedSpeed);

            // Duration filter
            String selectedDuration = durationFilter.getValue();
            boolean matchesDuration = selectedDuration == null ||
                    selectedDuration.equals("All Durations") ||
                    pkg.getDuration().equals(selectedDuration);

            return matchesSearch && matchesSpeed && matchesDuration;
        };
    }

    @FXML
    private void resetFilters() {
        searchField.clear();
        speedFilter.setValue("All Speeds");
        durationFilter.setValue("All Durations");
        applyFilters();
    }

    private void updateStatistics() {
        // Total packages
        totalPackagesLabel.setText(String.valueOf(tableData.size()));

        int activeFilters = 0;
        if (searchField.getText() != null && !searchField.getText().isEmpty()) activeFilters++;
        if (!speedFilter.getValue().equals("All Speeds")) activeFilters++;
        if (!durationFilter.getValue().equals("All Durations")) activeFilters++;
        activeFiltersLabel.setText(String.valueOf(activeFilters));

        double avgSpeed = filteredData.stream()
                .mapToInt(pkg -> {
                    try {
                        return Integer.parseInt(pkg.getSpeed());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .average()
                .orElse(0.0);
        avgSpeedLabel.setText(String.format("%.0f", avgSpeed));

        recordCountLabel.setText(String.format("Showing %d of %d records",
                filteredData.size(), tableData.size()));
    }

    private void setupTableRowClickHandler() {
        dataTable.setRowFactory(tv -> {
            TableRow<InternetPackageModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    loadSelectedRowIntoForm();
                }
            });
            return row;
        });
    }

    private void loadSelectedRowIntoForm() {
        InternetPackageModel selected = dataTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            model.setId(selected.getId());
            model.setFirstName(selected.getFirstName());
            model.setLastName(selected.getLastName());
            model.setAddress(selected.getAddress());
            model.setSpeed(selected.getSpeed());
            model.setBandwidth(selected.getBandwidth());
            model.setDuration(selected.getDuration());
            setupInitialRadioButtonValues();
        }
    }

    private void loadTableData() {
        try {
            List<InternetPackageModel> packages = packageService.findAll();
            tableData.clear();
            tableData.addAll(packages);
            updateStatistics();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
    }

    @FXML
    private void save() {
        clearFieldHighlights();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder();

        if (model.getFirstName() == null || model.getFirstName().trim().isEmpty()) {
            firstName.setStyle("-fx-border-color: #f5576c; -fx-border-width: 2; -fx-background-color: #fff5f5;");
            errorMessage.append("• First Name: This field cannot be empty\n");
            hasErrors = true;
        }

        if (model.getLastName() == null || model.getLastName().trim().isEmpty()) {
            lastName.setStyle("-fx-border-color: #f5576c; -fx-border-width: 2; -fx-background-color: #fff5f5;");
            errorMessage.append("• Last Name: This field cannot be empty\n");
            hasErrors = true;
        }

        if (model.getAddress() == null || model.getAddress().trim().isEmpty()) {
            address.setStyle("-fx-border-color: #f5576c; -fx-border-width: 2; -fx-background-color: #fff5f5;");
            errorMessage.append("• Address: This field cannot be empty\n");
            hasErrors = true;
        } else {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            if (!model.getAddress().matches(emailRegex)) {
                address.setStyle("-fx-border-color: #f5576c; -fx-border-width: 2; -fx-background-color: #fff5f5;");
                errorMessage.append("• Address: Invalid email format (e.g., user@example.com)\n");
                hasErrors = true;
            }
        }

        if (model.getSpeed() == null || model.getSpeed().isEmpty()) {
            errorMessage.append("• Speed: Please select internet speed\n");
            hasErrors = true;
        }

        if (model.getBandwidth() == null || model.getBandwidth().isEmpty()) {
            errorMessage.append("• Bandwidth: Please select bandwidth limit\n");
            hasErrors = true;
        }

        if (model.getDuration() == null || model.getDuration().isEmpty()) {
            errorMessage.append("• Duration: Please select contract duration\n");
            hasErrors = true;
        }

        if (hasErrors) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", errorMessage.toString());
            return;
        }

        try {
            InternetPackageModel saved = packageService.save(model);
            showSuccessNotification("Package saved successfully!");
            clearForm();
            loadTableData();
        } catch (PackageServiceException e) {
            String message = e.getMessage();
            if (message.contains("Validation failed")) {
                showAlert(Alert.AlertType.ERROR, "Validation Error",
                        "Failed to save package:\n\n" + message);
            } else if (message.contains("duplicate entry") || message.contains("constraint violation")) {
                showAlert(Alert.AlertType.ERROR, "Database Error",
                        "Failed to save package: Duplicate entry or constraint violation.\n" +
                                "Please check if a similar record already exists.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error",
                        "Failed to save package:\n\n" + message);
            }
        } catch (DataIntegrityViolationException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error",
                    "Failed to save package: Data integrity violation.\n" +
                            "Please check if similar record already exists.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error",
                    "Failed to save package:\n\n" + e.getMessage());
        }
    }

    @FXML
    private void deleteRow() {
        InternetPackageModel selectedItem = dataTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a package to delete!");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Package");
        confirmation.setContentText("Are you sure you want to delete the package for " +
                selectedItem.getFirstName() + " " + selectedItem.getLastName() + "?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    packageService.delete(selectedItem.getId());
                    showSuccessNotification("Package deleted successfully!");
                    loadTableData();
                    clearForm();
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete package: " + e.getMessage());
                }
            }
        });
    }

    @FXML
    private void clearForm() {
        firstName.clear();
        lastName.clear();
        address.clear();
        model.setId(0);
        model.setBandwidth(null);
        if (bandwidthGroup != null) bandwidthGroup.selectToggle(null);
        model.setDuration(null);
        if (durationGroup != null) durationGroup.selectToggle(null);
        model.setSpeed(null);
        if (speedGroup != null) speedGroup.selectToggle(null);
        dataTable.getSelectionModel().clearSelection();
        clearFieldHighlights();
    }

    private void clearFieldHighlights() {
        firstName.setStyle("");
        lastName.setStyle("");
        address.setStyle("");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Platform.runLater(alert::close);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void closeForm() {
        Platform.exit();
    }
}
