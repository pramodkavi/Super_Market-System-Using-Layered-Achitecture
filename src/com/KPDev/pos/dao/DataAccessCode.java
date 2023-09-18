package com.KPDev.pos.dao;

import com.KPDev.pos.db.DbConnection;
import com.KPDev.pos.dto.Customerdto;
import com.KPDev.pos.dto.Userdto;
import com.KPDev.pos.util.PasswordManager;
import com.KPDev.pos.view.tm.CustomerTm;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCode {
    public static boolean createUser(String email, String password, String firstName, String lastName) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO user VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2, PasswordManager.encryptPassword(password));
        preparedStatement.setString(3, firstName);
        preparedStatement.setString(4, lastName);
        return preparedStatement.executeUpdate() > 0;
    }

    public static Userdto findUser(String email) throws ClassNotFoundException, SQLException {
        String sql = "select * from user where email = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return new Userdto(
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName")
            );
        }
        return null;

    }

    // Customer management

    public static boolean createCustomer(
            String name , String email,  String contact,Double salary
    ) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, email);
        preparedStatement.setDouble(4, salary);
        preparedStatement.setString(3, contact);
        return preparedStatement.executeUpdate() > 0;
    }

    public static boolean updateCustomer(
            String email , String name,  String contact,Double salary
    ) throws ClassNotFoundException, SQLException {
        String sql="UPDATE customer SET name=?, contact=? ,salary=? WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, contact);
        preparedStatement.setDouble(3, salary);
        preparedStatement.setString(4, email);
        return preparedStatement.executeUpdate() > 0;

    }

    public static Customerdto findCustomer(
            String email
    ) throws ClassNotFoundException, SQLException {
        String sql="select * from customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return new Customerdto(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary")
            );
        }
        return null;
    }

    public static boolean deleteCustomer(
            String email
    ) throws ClassNotFoundException, SQLException {
        String sql="DELETE FROM customer WHERE email=?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, email);
        return preparedStatement.executeUpdate() > 0;
    }

    public static List<Customerdto> findAllCustomer(
    ) throws ClassNotFoundException, SQLException {
        String sql="select * from customer";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customerdto> dtos = new ArrayList<>();
        while(resultSet.next()){
            dtos.add(new Customerdto(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary")
            ));
        }
        return dtos;
    }
    public static List<Customerdto> searchCustomer(
            String searchText
    ) throws SQLException, ClassNotFoundException {
        searchText= "%"+searchText+"%";

        String sql="select * from customer WHERE email like ? || name like ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customerdto> dtos = new ArrayList<>();
        while(resultSet.next()){
            dtos.add(new Customerdto(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary")
            ));
        }
        return dtos;
    }


    //product management

    public static int getLastProductId() throws SQLException, ClassNotFoundException {
        String sql="select * from product order by code desc limit 1";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return (resultSet.getInt("code")+1);
        }
        return 1;
    }

    public static boolean saveProduct(int code, String description) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product VALUES(?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,code);
        preparedStatement.setString(2, description);
        return preparedStatement.executeUpdate() > 0;
    }
}
