import Pharmacy.Client;
import Pharmacy.Employee;
import SQLOperation.DatabaseConnector;
import SQLOperation.DatabaseFetcher;
import SQLOperation.DatabaseModifier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.lang.*;
import java.sql.*;

public class  Main {
    static ResultSet resultSet ;
    static JTable jTable = new JTable();
    static void resultSet() throws SQLException {
        String query = "select * from product;";
        Connection connection = DatabaseConnector.connect();
        PreparedStatement statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();
    }
    static void resultSet(String query) throws SQLException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement statement = connection.prepareStatement(query);
        resultSet = statement.executeQuery();
    }
    static DefaultTableModel defaultTableModel  () throws SQLException {

        resultSet();
        DefaultTableModel model = new DefaultTableModel();
        int columnCount = Main.resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(Main.resultSet.getMetaData().getColumnName(i));
        }
        while (Main.resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = Main.resultSet.getObject(i);
            }
            model.addRow(rowData);
        }
        return model;
    }


    static DefaultTableModel defaultTableModel  (String query) throws SQLException {

        resultSet(query);
        DefaultTableModel model = new DefaultTableModel();
        int columnCount = Main.resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(Main.resultSet.getMetaData().getColumnName(i));
        }
        while (Main.resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = Main.resultSet.getObject(i);
            }
            model.addRow(rowData);
        }
        return model;
    }


    final static public String mangerEmail = "root@pharmacy.com";
    final static public String mangerPassword = "Y123!";
    static Employee currentEmployee = new Employee();
    static Client currentClient = new Client();
    static LoginForm loginForm;
    static SignUpForm signUpForm;
    static MangerLoginForm mangerLoginForm;
    static addProduct addProduct;
    static newEmployeeForm newEmployeeForm;
    static pharmacyManagement pharmacyManagement;
    static updateProduct updateProduct;
    static deleteProduct deleteProduct;
    static showOrders showOrders;
    static makeOrder makeOrder;


    public static void main(String[] args) throws RuntimeException, SQLException, IOException {

        new SignUpForm();

    }
}