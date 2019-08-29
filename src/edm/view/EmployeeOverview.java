package edm.view;

import edm.Main;
import edm.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class EmployeeOverview {

    private Main main;
    @FXML
    private Button btEdit;
    @FXML
    private Label lbBirth;
    @FXML
    private Label lbTempAddress;
    @FXML
    private Label lbTax;
    @FXML
    private TableColumn<Employee, String> colPlace;
    @FXML
    private Button btDelete;
    @FXML
    private Label lbPermAddress;
    @FXML
    private Label lbAllEmployees;
    @FXML
    private Label lbSoc;
    @FXML
    private TableView<Employee> empTable;
    @FXML
    private Label lbWorkPlace;
    @FXML
    private Label lbName;
    @FXML
    private Button btNew;
    @FXML
    private TableColumn<Employee, String> empCol;
    @FXML
    private Label lbMothersName;
    @FXML
    private CheckBox chActive;
    @FXML
    private Label lbPosition;
    @FXML
    private Tooltip tooltip;
    @FXML
    private Label lbEmail;

    @FXML
    private void initialize() {
        // alkalmazottak oszlop feltöltése adatokkal
        empCol.setCellValueFactory(data -> data.getValue().nameProperty());

        // üzlet oszlop feltöltése adatokkal
        colPlace.setCellValueFactory(data -> data.getValue().workPlaceProperty());

        showEmployeeDetails(null);

        // tábla bármely sorára történő kattintással adatok megjelenítése
        empTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showEmployeeDetails(newValue);
        });
    }

    @FXML
    private void showEmployeeDetails(Employee employee) {
        if (employee != null) {
            lbName.setText(employee.getName());
            lbPosition.setText(employee.getPosition());
            lbBirth.setText(employee.getBirth());
            lbMothersName.setText(employee.getMothersName());
            lbTax.setText(employee.getTaxNumber());
            lbSoc.setText(employee.getSocialNumber());
            lbPermAddress.setText(employee.getPermAddress());
            lbTempAddress.setText(employee.getTempAddress());
            lbWorkPlace.setText(employee.getWorkPlace());
            lbEmail.setText(employee.getEmail());
            chActive.setSelected(employee.isActive());
            lbAllEmployees.setText(Integer.toString(allActiveEmployee()));
        } else {
            lbName.setText("");
            lbPosition.setText("");
            lbBirth.setText("");
            lbMothersName.setText("");
            lbTax.setText("");
            lbSoc.setText("");
            lbPermAddress.setText("");
            lbTempAddress.setText("");
            lbWorkPlace.setText("");
            lbEmail.setText("");
            chActive.setSelected(false);

        }
    }

    @FXML
    private String showTooltip() {
        int allee = 0, arena = 0, mom = 0, szerviz = 0, becsi = 0;
        for (Employee employee : main.getEmployees()) {
            if (employee.getWorkPlace().toLowerCase().equals("allee")) {
                allee += 1;
            } else if (employee.getWorkPlace().toLowerCase().equals("aréna")) {
                arena += 1;
            } else if (employee.getWorkPlace().toLowerCase().equals("mom")) {
                mom += 1;
            } else if (employee.getWorkPlace().toLowerCase().equals("service")) {
                szerviz += 1;
            } else {
                becsi += 1;
            }
        }
        return new String(
                "Allee: " + allee + "\n"
                        + "Aréna: " + arena + "\n"
                        + "Mom: " + mom + "\n"
                        + "Szervíz: " + szerviz + "\n"
                        + "Bécsi út: " + becsi + "\n"
        );
    }

    // Új gomb megnyomásának kezelése
    @FXML
    private void handleNewEmployee() throws IOException {
        Employee employee = new Employee();
        boolean isOkClicked = main.showNewEditDialog(employee);
        if (isOkClicked) {
            main.getEmployees().add(employee);
        }
    }

    // Módosít gomb megnyomásának kezelése
    @FXML
    private void handleEditEmployee() throws IOException {
        Employee selected = empTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = main.showNewEditDialog(selected);
            if (okClicked) {
                showEmployeeDetails(selected);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Nincs kiválasztva");
            alert.setHeaderText("Nincs alkalmazott kiválasztva");
            alert.setContentText("Kérlek válassz ki egy alkalmazottat!");

            alert.showAndWait();
        }
    }

    // Töröl gomb megnyomásának kezelése
    @FXML
    private void handleDeleteEmployee() {
        int selectedIndex = empTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(main.getStage());
            alert.setTitle("Alkalmazott törlése");
            alert.setHeaderText("Alkalmazott törlésének megerősítése!");
            alert.setContentText("Biztos, hogy törlöd az Alkalmazottat?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                empTable.getItems().remove(selectedIndex);
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Nincs kiválasztva");
            alert.setHeaderText("Nincs alkalmazott kiválasztva");
            alert.setContentText("Kérlek válassz ki egy alkalmazottat!");

            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main = main;

        empTable.setItems(main.getEmployees());
        main.getEmployees().sorted().comparatorProperty().bind(empTable.comparatorProperty());
        empTable.getSortOrder().add(empCol);

        lbAllEmployees.setText(Integer.toString(allActiveEmployee()));
        tooltip.setText(showTooltip());
    }

    private int allActiveEmployee() {
        int result = 0;
        for (Employee e : main.getEmployees()) {
            if (e.isActive()) result++;
        }
        return result;
    }
}
