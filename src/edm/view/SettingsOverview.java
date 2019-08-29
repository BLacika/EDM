package edm.view;

import edm.Main;
import edm.utils.EDMSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Map;

public class SettingsOverview {

    private Main main;
    private Map<String, String> settings;
    @FXML
    private TextField txEmail;
    @FXML
    private PasswordField txPass;
    @FXML
    private Button btSave;

    public SettingsOverview() {
        settings = EDMSettings.loadSettings();
    }

    @FXML
    private void initialize() {
        loadDatas();
    }

    @FXML
    private void onSaveButton() {
        settings.put("email", txEmail.getText());
        settings.put("password", txPass.getText());

        EDMSettings.saveSettings(settings);
    }

    private void loadDatas() {
        txEmail.setText(settings.get("email"));
        txPass.setText(settings.get("password"));
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
