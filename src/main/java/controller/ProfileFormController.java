package controller;

import entity.EmployeeEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.ServiceFactory;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable {

    @FXML
    private Label txtCompany;

    @FXML
    private Label txtConNo;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtId;

    @FXML
    private Label txtName;

    @FXML
    private Label txtNameImg;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        EmployeeEntity currentUser = SessionManager.getLoggedInUser();
//        if (currentUser != null) {
//            setProfileDetails(currentUser);
//        }
    }

    private void setProfileDetails(EmployeeEntity user) {
        txtEmail.setText(user.getEmail());
    }


}
