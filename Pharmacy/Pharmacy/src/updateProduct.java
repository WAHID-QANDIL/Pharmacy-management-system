import SQLOperation.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class updateProduct extends JFrame {

    JLabel productName=new JLabel("Product Name");
    JLabel productType=new JLabel("Product Type");
    JLabel productUnitPrice=new JLabel("Product Price");
    JLabel supplierName=new JLabel("Supplier Name");
    JLabel tableName=new JLabel("Pharmacy Products");

    JLabel targetId=new JLabel("Target Product Id");

    JTextField productNameField=new JTextField();
    JTextField productTypeField=new JTextField();
    JTextField productUnitPriceField=new JTextField();
    JTextField supplierNameField=new JTextField();
    JTextField idField=new JTextField();



    JTable productTable;
    JButton logout=new JButton("Logout");
    ImageIcon Icon=new ImageIcon("Media/icon.png");

    JButton update=new JButton("UPDATE");
    JButton main=new JButton("Return to main");

    JLabel icon = new JLabel(Icon);


    public updateProduct() throws SQLException {

//        try {
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();

            String query = "SELECT * FROM product";
            Connection connection = DatabaseConnector.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet =  preparedStatement.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(resultSet.getMetaData().getColumnName(i));
            }
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }

        productTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(productTable);
        setTitle("Update Product");
//        setLocationRelativeTo(null);
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);
        productName.setBounds(250,40,100,50);
        productType.setBounds(250,90,100,50);
        productUnitPrice.setBounds(250,140,100,50);
        supplierName.setBounds(250,190,100,50);
        targetId.setBounds(50,120,150,50);
        idField.setBounds(50,160,120,30);
        productNameField.setBounds(380,50,170,30);
        productTypeField.setBounds(380,100,170,30);
        productUnitPriceField.setBounds(380,150,170,30);
        supplierNameField.setBounds(380,200,170,30);
        update.setBounds(260,270,100,40);
        ///table
        tableName.setBounds(30,310,150,50);
        productTable.setBounds(30,350,700,200);
        main.setBounds(530,560,150,40);
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
        contentPane.add(main);
        contentPane.add(update);
        contentPane.add(productTable);
        contentPane.add(tableName);
        contentPane.add(idField);
        contentPane.add(targetId);
        setVisible(true);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!AreFieldsEmpty())
                {
                    Connection connection;
                    Statement statement;
                    String query = "UPDATE product SET productName = '" + productNameField.getText() + "', productType = '" + productTypeField.getText() + "', productUnitPrice = " + productUnitPriceField.getText() + ", supplierName = '" + supplierNameField.getText() + "' WHERE productId = '" + idField.getText() + "';";

                    try {
                        connection = DatabaseConnector.connect();
                        statement = connection.createStatement();
                        statement.executeUpdate(query);
                        JOptionPane.showMessageDialog(contentPane,"Product updated successfully","Update product",JOptionPane.PLAIN_MESSAGE);
                        productTable.setAutoCreateColumnsFromModel(true);
                        productTable.setModel(Main.defaultTableModel());
                    }
                    catch (SQLException exception)
                    {
                        throw new RuntimeException(exception.getMessage(),exception.getCause());
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(contentPane,"Please fill all product fields","Missing fields",JOptionPane.PLAIN_MESSAGE);
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



        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main.pharmacyManagement = new pharmacyManagement();

            }
        });




//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
    public boolean AreFieldsEmpty()
    {
        return idField.getText().isEmpty() ||
                productNameField.getText().isEmpty() ||
                productTypeField.getText().isEmpty() ||
                productUnitPriceField.getText().isEmpty() ||
                supplierNameField.getText().isEmpty();
    }


}
