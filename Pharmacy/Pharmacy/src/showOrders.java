import SQLOperation.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class showOrders extends JFrame {
    JTable productTable;
    JButton logout=new JButton("Logout");
    ImageIcon Icon=new ImageIcon("Media/icon.png");




    JLabel icon = new JLabel(Icon);
    JLabel text = new JLabel("search");
    JButton search = new JButton("search");
    JTextField searchField=new JTextField();
    JButton reset = new JButton("reset");
    JButton mkOrder = new JButton("Make Order");



    public showOrders() throws SQLException {

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
        JScrollPane scrollPane = new JScrollPane(productTable);


        setTitle("search Product");
//        setLocationRelativeTo(null);
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);




        ///table

        productTable.setBounds(30,150,600,300);
        text.setBounds(30,130,150,20);
        mkOrder.setBounds(30,500,130,40);


        logout.setBounds(530,580,150,40);
        search.setBounds(380,80,80,20);
        reset.setBounds(280,80,80,20);
        searchField.setBounds(290,30,150,30);
        icon.setBounds(560,5,200,350);
        contentPane.add(icon);
        contentPane.add(logout);



        contentPane.add(productTable);
        contentPane.add(search);
        contentPane.add(reset);
        contentPane.add(searchField);
        contentPane.add(text);
        contentPane.add(mkOrder);



        setVisible(true);





        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               Main.loginForm = new LoginForm();
            }
        });

        search.addActionListener((e -> {
            String query = "SELECT * FROM product WHERE productName LIKE '" + search.getText() + "%';";
            try {
                productTable.setModel(Main.defaultTableModel());
            }catch (SQLException exception)
            {
                throw new RuntimeException();
            }


        }));
        reset.addActionListener((e -> {
            try {
                productTable.setModel(Main.defaultTableModel());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }));
        mkOrder.addActionListener((e -> {
            dispose();
            Main.makeOrder = new makeOrder();
        }));






//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }


}
