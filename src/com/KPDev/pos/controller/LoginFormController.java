package com.KPDev.pos.controller;

import com.KPDev.pos.dao.DataAccessCode;
import com.KPDev.pos.dto.Userdto;
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
import java.sql.*;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void loginOnAction(ActionEvent actionEvent) {
        try {
            Userdto user = DataAccessCode.findUser(txtEmail.getText());
            if (user!= null) {
                if(PasswordManager.checkPassword(txtPassword.getText(),user.getPassword())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Login Success").show();
                    setUI("DashboardForm");
                }
                else{
                    new Alert(Alert.AlertType.WARNING, "password not correct").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"User email not found").show();
            }
        }catch (ClassNotFoundException|IOException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void forgetPasswrodOnAction(ActionEvent actionEvent) {
    }

    public void createAnAcountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    public void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
