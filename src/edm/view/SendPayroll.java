package edm.view;

import edm.Main;
import edm.model.Employee;
import edm.utils.EDMSettings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


public class SendPayroll {

    private Main main;
    @FXML
    private TableView<Employee> payrollTable;
    @FXML
    private TableColumn<Employee, String> empCol;
    @FXML
    private TableColumn<Employee, String> payrollPath;
    @FXML
    private TextField txSubject;
    @FXML
    private TextArea txMessage;
    @FXML
    private Button btSend;

    public SendPayroll() {

    }

    @FXML
    private void initialize() {
        empCol.prefWidthProperty().bind(payrollTable.widthProperty().multiply(0.3));
        empCol.setResizable(false);
        payrollPath.prefWidthProperty().bind(payrollTable.widthProperty().multiply(0.7));
        payrollPath.setResizable(false);

        empCol.setCellValueFactory(data -> data.getValue().nameProperty());
        payrollPath.setCellValueFactory(data -> data.getValue().payrollPathProperty());

        payrollTable.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                Employee employee = payrollTable.getSelectionModel().getSelectedItem();
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);

                File file = fileChooser.showOpenDialog(main.getStage());
                if (file != null) {
                    employee.setPayrollPath(file.getPath());
                } else {
                    employee.setPayrollPath("");
                }
            }
        });
    }

    @FXML
    private void onSendButton() {
        String user = EDMSettings.loadSettings().get("email");
        String pass = EDMSettings.loadSettings().get("password");

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.auth", "true");


        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        for (Employee employee : payrollTable.getItems()) {
            try {
                String fileName = employee.getPayrollPath();
                if (!fileName.equals("")) {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(user));
                    message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(employee.getEmail()));
                    message.setSubject(txSubject.getText());

                    BodyPart messageBodyPart = new MimeBodyPart();
                    ((MimeBodyPart) messageBodyPart).setText(txMessage.getText());

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);

                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(fileName);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(new File(fileName).getName());
                    multipart.addBodyPart(messageBodyPart);
                    message.setContent(multipart);

                    Transport.send(message);
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Üzenet");
        alert.setHeaderText(null);
        alert.setContentText("Üzenetek sikeresen elküldve!");
        alert.showAndWait();
    }

    public void setMain(Main main) {
        this.main = main;

        payrollTable.setItems(main.getEmployees());
        main.getEmployees().sorted().comparatorProperty().bind(payrollTable.comparatorProperty());
        payrollTable.getSortOrder().add(empCol);
    }
}
