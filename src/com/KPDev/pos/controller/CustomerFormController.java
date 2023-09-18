package com.KPDev.pos.controller;

import com.KPDev.pos.dao.DataAccessCode;
import com.KPDev.pos.dto.Customerdto;
import com.KPDev.pos.view.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtSalary;
    public Button btnSaveUpdate;
    public TableView<CustomerTm> tbl;
    public TableColumn colId;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOperate;
    public TextField txtSearch;

    private String searchText = "";

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadAllCustomers(searchText);

        tbl.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if(newValue!=null){
                        setData(newValue);
                    }
                }));

        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchText = newValue;
            try {
                loadAllCustomers(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));
    }

    private void setData(CustomerTm newValue) {
        txtEmail.setEditable(false) ;
        btnSaveUpdate.setText("Update Customer");
        txtEmail.setText(newValue.getEmail());
        txtName.setText(newValue.getName());
        txtContact.setText(newValue.getContact());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
    }

    private void loadAllCustomers(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTm> observableList= FXCollections.observableArrayList();
        int counter = 1;
        for(Customerdto customerdto: searchText.length()>0 ? DataAccessCode.searchCustomer(searchText) : DataAccessCode.findAllCustomer()){
            Button button = new Button("Delete");
            CustomerTm customerTm = new CustomerTm(counter, customerdto.getName(), customerdto.getEmail(), customerdto.getContact(), customerdto.getSalary(), button);
            System.out.println(customerdto.getName());
            observableList.add(customerTm);
            counter++;

            button.setOnAction((e)->{
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this Customer?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> selectedButtonType = alert.showAndWait();
                    if(selectedButtonType.get()==ButtonType.YES) {
                        System.out.println("HUTTO");
                        if (DataAccessCode.deleteCustomer(customerdto.getEmail())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted").show();
                            loadAllCustomers(searchText);
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again").show();
                        }
                    }else {
                        System.out.println("paka");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }
        tbl.setItems(observableList);
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {

        try {
            if (btnSaveUpdate.getText().equals("Save Customer")){
                if(DataAccessCode.createCustomer(txtEmail.getText(),txtName.getText(),txtContact.getText(),Double.parseDouble(txtSalary.getText()))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Cuteromer Saved").show();
                    clearFields();
                    loadAllCustomers(searchText);
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try again").show();

                }
            }else{
                if(DataAccessCode.updateCustomer(txtEmail.getText(),txtName.getText(),txtContact.getText(),Double.parseDouble(txtSalary.getText()))){
                    new Alert(Alert.AlertType.CONFIRMATION,"Cuteromer Updated").show();
                    clearFields();
                    loadAllCustomers(searchText);
                    txtEmail.setEditable (true) ;
                    btnSaveUpdate.setText("Save Customer");
                }else{
                    new Alert(Alert.AlertType.WARNING,"No update").show();

                }
            }

        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
    }

    public void btnBacktoHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }
    public void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }


    public void btnNewCustomer0nAction(ActionEvent actionEvent) {
        txtEmail.setEditable (true) ;
        btnSaveUpdate.setText("Save Customer");
        clearFields();
    }
}
