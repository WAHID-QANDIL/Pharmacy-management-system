import SQLOperation.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class addProduct extends JFrame {
    JLabel productName=new JLabel("Product Name");
    JLabel productType=new JLabel("Product Type");
    JLabel productUnitPrice=new JLabel("Product Price");
    JLabel supplierName=new JLabel("Supplier Name");
    JLabel tableName=new JLabel("Pharmacy Products");

    JTextField productNameField=new JTextField();
    JTextField productTypeField=new JTextField();
    JTextField productUnitPriceField=new JTextField();
    JTextField supplierNameField=new JTextField();



    JTable productTable;
    JButton logout=new JButton("Logout");
    ImageIcon Icon=new ImageIcon("Media/icon.png");

    JButton ADD=new JButton("ADD");
    JButton ReturnTomain=new JButton("Return to main");

    JLabel icon = new JLabel(Icon);


    public addProduct() throws SQLException {


//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//
//            String query = "SELECT * FROM product";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            DefaultTableModel model = new DefaultTableModel();
//            int columnCount = resultSet.getMetaData().getColumnCount();
//            for (int i = 1; i <= columnCount; i++) {
//                model.addColumn(resultSet.getMetaData().getColumnName(i));
//            }
//
//            while (resultSet.next()) {
//                Object[] rowData = new Object[columnCount];
//                for (int i = 1; i <= columnCount; i++) {
//                    rowData[i - 1] = resultSet.getObject(i);
//                }
//                model.addRow(rowData);
//            }


            productTable = new JTable(Main.defaultTableModel());
//            productTable.setAutoscrolls(true);
            JScrollPane scrollPane = new JScrollPane(productTable);
            setTitle("Add Product");
//            setLocationRelativeTo(getParent());
            setSize(750, 650);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setBackground(new Color(33,150,240));
            contentPane.setLayout(null);



            productName.setBounds(100,40,100,50);
            productType.setBounds(100,90,100,50);
            productUnitPrice.setBounds(100,140,100,50);
            supplierName.setBounds(100,190,100,50);
            productNameField.setBounds(230,50,170,30);
            productTypeField.setBounds(230,100,170,30);
            productUnitPriceField.setBounds(230,150,170,30);
            supplierNameField.setBounds(230,200,170,30);
            ADD.setBounds(260,250,100,40);
            ///table
            tableName.setBounds(30,310,150,50);
            productTable.setBounds(30,350,700,200);
            ReturnTomain.setBounds(530,560,150,40);
            logout.setBounds(20,20,80,20);
            icon.setBounds(560,5,200,350);

            contentPane.add(icon);
            contentPane.add(logout);
            contentPane.add(productName);
            contentPane.add(productType);
            contentPane.add(productUnitPrice);
            contentPane.add(supplierName);
            contentPane.add(productNameField);
            contentPane.add(productTypeField);
            contentPane.add(productUnitPriceField);
            contentPane.add(supplierNameField);
            contentPane.add(ReturnTomain);
            contentPane.add(ADD);
            contentPane.add(productTable);
            contentPane.add(tableName);
            contentPane.add(scrollPane);
            setVisible(true);




            ADD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (!AreFieldsEmpty())
                    {
                        Statement statement;
                        Connection connection;
                        try {
                            connection = DatabaseConnector.connect();
                            statement = connection.createStatement();
                            String query = "INSERT INTO product(productName,productType,productUnitPrice,supplierName) VALUES ('" + productNameField.getText() + "', '" + productTypeField.getText() + "', " + productUnitPriceField.getText() + ", '" + supplierNameField.getText() + "');";
                            statement.executeUpdate(query);
                            JOptionPane.showMessageDialog(contentPane,"Product added successfully","Adding product",JOptionPane.PLAIN_MESSAGE);
                            productTable.setModel(Main.defaultTableModel());
                            connection.close();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(contentPane,"Please fill the product fields","Missing fields",JOptionPane.PLAIN_MESSAGE);

                    }
                }
            });

            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   dispose();
                   Main.loginForm = new LoginForm();
                }
            });
            ReturnTomain.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Main.pharmacyManagement = new pharmacyManagement();
                }
            });
    }

    public boolean AreFieldsEmpty()
    {
        return  productNameField.getText().isEmpty() ||
                productTypeField.getText().isEmpty() ||
                productUnitPriceField.getText().isEmpty()||
                supplierNameField.getText().isEmpty();
    }

}
