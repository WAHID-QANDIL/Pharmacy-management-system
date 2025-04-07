import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class pharmacyManagement extends JFrame {
       JButton add=new JButton("Add Products");
       JButton delete=new JButton("Delete Products");
       JButton update=new JButton("Update Products");
       JButton logout=new JButton("Logout");
       ImageIcon Icon=new ImageIcon("Media/icon.png");
      JLabel icon = new JLabel(Icon);


    public pharmacyManagement() {
        setTitle("Pharmacy Management System");
//        setLocationRelativeTo(null);
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(33,150,240));
        contentPane.setLayout(null);

     add.setBounds(80,250,150,100);
     update.setBounds(250,250,150,100);
     delete.setBounds(420,250,150,100);
     logout.setBounds(50,20,80,20);
        icon.setBounds(560,5,200,350);

        contentPane.add(icon);
        contentPane.add(logout);
        contentPane.add(add);
        contentPane.add(delete);
        contentPane.add(update);
        setVisible(true);

  update.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          dispose();
          try {
              Main.updateProduct = new updateProduct();
          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }

      }
    });

    delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                Main.deleteProduct = new deleteProduct();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });



        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    Main.addProduct = new addProduct();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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


    }


}
