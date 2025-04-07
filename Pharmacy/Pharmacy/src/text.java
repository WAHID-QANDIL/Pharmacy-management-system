import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class text extends JFrame {

    static text text;
    JButton logout=new JButton("Logout");
    JButton reTurn =new JButton("return");
    ImageIcon Icon=new ImageIcon("Media/icon.png");
    ImageIcon ICON=new ImageIcon("Media/thanks.jpg");
    JLabel txt=new JLabel(ICON);
    JLabel icon = new JLabel(Icon);



    public text() {




        setTitle("Make Order");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);


        contentPane.add(icon);
        contentPane.add(reTurn);
        contentPane.add(txt);

        contentPane.add(logout);



        logout.setBounds(400,400,150,40);
        reTurn.setBounds(100,400,150,40);
        txt.setBounds(170,100,300,300);

        icon.setBounds(560,5,200,350);








        setVisible(true);





        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               Main.loginForm = new LoginForm();


            }
        });
        reTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    Main.showOrders = new showOrders();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

//                new showOrders();


            }
        });









    }


}
