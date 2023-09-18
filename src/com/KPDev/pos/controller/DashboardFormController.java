package com.KPDev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane context;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ProductMainForm");

    }

    public void btnOrderDetailOnAction(ActionEvent actionEvent) {
    }

    public void btnIncomeReportOnAction(ActionEvent actionEvent) {
    }

    public void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
