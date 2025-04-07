import Pharmacy.Order;
import Pharmacy.Product;
import SQLOperation.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class makeOrder extends JFrame {

    JButton logout=new JButton("Logout");
    ImageIcon Icon=new ImageIcon("Media/icon.png");

    JLabel icon = new JLabel(Icon);
    JButton add=new JButton("Add");
    JButton cancel=new JButton("Cancel");
    JButton Order=new JButton("Order");

    JComboBox<Object> type=new JComboBox<>();
    JComboBox<String> cont = new JComboBox<>();
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Object> count = new DefaultComboBoxModel<Object>();


    public makeOrder() {
        Order order = new Order();
        List<Product> products = new ArrayList<Product>() ;
        try {
            Connection connection = DatabaseConnector.connect();
            Statement statement = connection.createStatement();
            String query = "SELECT productName FROM product";
            ResultSet resultSet = statement.executeQuery(query);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (resultSet.next()) {
                String item = resultSet.getString("productName");
                model.addElement(item);
            }
            connection.close();
            for (int i = 1; i < 6; i++)
            {
                count.addElement(String.valueOf(i));
            }
            cont.setModel(model);
            type.setModel(count);
            setTitle("Make Order");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);


            contentPane.add(icon);
            contentPane.add(type);
            contentPane.add(cont);
            contentPane.add(logout);
            contentPane.add(add);
            contentPane.add(cancel);
            contentPane.add(Order);
        logout.setBounds(530,580,150,40);
        add.setBounds(150,350,150,40);
        cancel.setBounds(350,350,150,40);
        type.setBounds(350,200,150,40);
        cont.setBounds(150,200,150,40);
        icon.setBounds(560,5,200,350);
        Order.setBounds(200,400,100,40);
        setVisible(true);


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               Main.loginForm = new LoginForm();

            }
        });
        Order.addActionListener((e -> {
            dispose();
            text.text = new text();

        }));
        add.addActionListener((e)->{
            Product product = new Product();
            products.add(product);

        });
        cancel.addActionListener((e -> {
            dispose();
            try {
                Main.showOrders = new showOrders();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }));

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
