package com.KPDev.pos.controller;

import com.KPDev.pos.dao.DataAccessCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainPageController {
    public AnchorPane context;
    public TextField txtName;
    public TextField txtProductCode;
    public TableView tbl;
    public Button btnSaveUpdate;
    public TextArea txtDescription;
    private String searchText ="";

    public void initialize(){
        loadProductId();
    }

    private void loadProductId() {
        try {
            String productId = String.valueOf(DataAccessCode.getLastProductId());
            txtProductCode.setText(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnBacktoHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }
    public void btnNewProduct0nAction(ActionEvent actionEvent) {
    try {


        if(btnSaveUpdate.getText().equals("Save Product")){
            if(DataAccessCode.saveProduct(Integer.parseInt(txtProductCode.getText()),txtDescription.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Cuteromer Saved").show();
                clearFields();
                loadAllProducts(searchText);
            }else{
                new Alert(Alert.AlertType.WARNING,"Try again").show();
            }
        }else{
            if(DataAccessCode.saveProduct(Integer.parseInt(txtProductCode.getText()),txtDescription.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Cuteromer Saved").show();
                clearFields();
                loadAllProducts(searchText);
            }else{
                new Alert(Alert.AlertType.WARNING,"Try again").show();
            }
        }
    }
    catch (SQLException|ClassNotFoundException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
    }

    private void loadAllProducts(String searchText) {
    }

    public void btnNew0nAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
    private void clearFields() {
        txtDescription.clear();
        txtProductCode.clear();
        loadProductId();
    }


}
