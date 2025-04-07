import SQLOperation.DatabaseConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class deleteProduct extends JFrame {
    JLabel tableName=new JLabel("Pharmacy Products");
    JLabel targetId=new JLabel("Target Product Id");


    JTextField idField=new JTextField();



    JTable productTable;
    JButton logout=new JButton("Logout");
    ImageIcon Icon=new ImageIcon("Media/icon.png");
    JButton delete=new JButton("DELETE");
    JButton main=new JButton("Return to main");

    JLabel icon = new JLabel(Icon);


    public deleteProduct() throws SQLException {


        productTable = new JTable(Main.defaultTableModel());
        JScrollPane scrollPane = new JScrollPane(productTable);
        setTitle("Delete Product");
//        setLocationRelativeTo(null);
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);



        targetId.setBounds(30,150,150,50);
        idField.setBounds(150,160,160,30);



        delete.setBounds(260,250,100,40);
        ///table
        tableName.setBounds(30,310,150,50);
        productTable.setBounds(30,350,700,200);



        main.setBounds(530,560,150,40);
        logout.setBounds(20,20,80,20);
        icon.setBounds(560,5,200,350);
        contentPane.add(icon);
        contentPane.add(logout);
        contentPane.add(main);
        contentPane.add(delete);
        contentPane.add(productTable);
        contentPane.add(tableName);
        contentPane.add(idField);
        contentPane.add(targetId);
        setVisible(true);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idField.getText().isEmpty()) {
                    try {
                        String query = "delete from  product where productId = " + Integer.parseInt(idField.getText())+";";
                        Connection connection = DatabaseConnector.connect();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(query);
                        productTable.setModel(Main.defaultTableModel());
                        JOptionPane.showMessageDialog(productTable,"product deleted successfully","Product deleted",JOptionPane.PLAIN_MESSAGE);
                        connection.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(productTable,"Please enter product id","Missing id",JOptionPane.ERROR_MESSAGE);
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


}
