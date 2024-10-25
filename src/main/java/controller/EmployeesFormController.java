package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesFormController implements Initializable {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colConNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private JFXComboBox<String> cooRole;

    @FXML
    private Label lblId;

    @FXML
    private TableView<Employee> tblEmployees;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    EmployeeService service = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblId.setText(service.generateId());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colConNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmployees.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (null != newValue) {
                setTextToValues(newValue);
            }
        }));
        loadTable();
    }

    @FXML
    void btnAddOnAction() {
        Employee employee = new Employee(
                lblId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                txtContact.getText()
        );
        if (service.addEmployee(employee)) {
            new Alert(Alert.AlertType.INFORMATION, "Employee added Successfully!").show();
            setTextToEmpty();
            lblId.setText(service.generateId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Add Employee!").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOnAction() {
        if (service.deleteEmployee(lblId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted Successfully").show();
            setTextToEmpty();
            lblId.setText(service.generateId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Delete Employee!").show();
        }
        loadTable();
    }

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction() {
        Employee employee = new Employee(
                lblId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                txtContact.getText()
        );
        if (service.updateEmployee(employee)) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Updated Successfully!").show();
            setTextToEmpty();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Employee!").show();
        }
        loadTable();
    }

    private void setTextToValues(Employee newValue) {
        lblId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtCompany.setText(newValue.getCompany());
        txtEmail.setText(newValue.getEmail());
        txtContact.setText(newValue.getContact());
    }

    private void setTextToEmpty() {
        txtName.setText("");
        txtCompany.setText("");
        txtEmail.setText("");
        txtContact.setText("");
    }

    private void loadTable() {
        try {
            ObservableList<Employee> employeesList = service.getAll();
            tblEmployees.setItems(employeesList);
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
