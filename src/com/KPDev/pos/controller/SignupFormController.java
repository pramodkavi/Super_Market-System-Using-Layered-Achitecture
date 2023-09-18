package com.KPDev.pos.controller;

import com.KPDev.pos.dao.DataAccessCode;
import com.KPDev.pos.util.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javafx.scene.input.KeyCode.O;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public TextField txtFirstName;
    public TextField txtLastName;

    public void SignupOnAction(ActionEvent actionEvent) {
        try {

            if (DataAccessCode.createUser(txtEmail.getText(),txtPassword.getText(),txtFirstName.getText(),txtLastName.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved");
                setUI("LoginForm");
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again");
            }
        }catch (ClassNotFoundException |IOException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
