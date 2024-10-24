package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Login;
import entity.LoginEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.LoginService;
import service.custom.impl.LoginServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXComboBox<String> cmbPosition;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    @FXML
    void btnLogInOnAction(ActionEvent event) {

        String username = txtEmail.getText();
        String password = txtPassword.getText();
        String role="";

        if (!hasEmptyFields()) {
            LoginEntity isValidLogin = service.verifyLogin(
                    new Login(
                            txtEmail.getText(),
                            txtPassword.getText(),
                            role
                    ));

            if (isValidLogin != null) {
                Stage stage = (Stage) scenePane.getScene().getWindow();
                if (isValidLogin.getRole().equals("Admin")) {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_user_form.fxml"))));
                        stage.show();
                        stage.setResizable(false);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (isValidLogin.getRole().equals("Employee")) {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_form.fxml"))));
                        stage.show();
                        stage.setResizable(false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                showAlert("Login Failed", "Invalid username or password!");
            }
        } else {
            showAlert("EmptyField", "Please fill the empty input fields!!");
        }


    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean hasEmptyFields() {
        return (txtEmail.getText()).isEmpty() || (txtPassword.getText()).isEmpty();
    }


    @FXML
    void lnkForgotPasswordOnAction(ActionEvent event) {
        try {
            scenePane = FXMLLoader.load(getClass().getResource("../view/recovery_form.fxml"));
            borderPane.setCenter(scenePane);
            borderPane.toFront();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
