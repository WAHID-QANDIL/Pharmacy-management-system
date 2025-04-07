import SQLOperation.DatabaseConnector;
import SQLOperation.DatabaseFetcher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.*;
public class LoginForm extends JFrame{

    Container container = getContentPane();
    JButton signUp = new JButton("Sign up");
    JLabel Rbk;
    JLabel Lbk;
    ImageIcon iconUser = new ImageIcon("Media/userName.png");
    ImageIcon iconPassword = new ImageIcon("Media/password.png");
    JLabel userLabel = new JLabel(iconUser);
    JLabel Elogin = new JLabel("Email");
    JLabel Plogin = new JLabel("Password");

    JLabel passwordLabel = new JLabel(iconPassword);
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JCheckBox Employee = new JCheckBox("Employee");
    JCheckBox Client = new JCheckBox("Client");

    //constructor
    public LoginForm() {
        setTitle("Login");
        setLocationRelativeTo(null);
         setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //label fontColor
        Elogin.setForeground(Color.WHITE);
        Plogin.setForeground(Color.WHITE);

        container.add(Employee);
        container.add(Client);
        //background for each site
        Rbk=new JLabel("", new ImageIcon("Media/r.png"),JLabel.CENTER);
        Rbk.setBounds(120,0,820,390);

        Lbk=new JLabel("", new ImageIcon("Media/l.png"),JLabel.CENTER);
        Lbk.setBounds(-265,-10,800,390);
        addComponentsToContainer();


        //Functions to set layout and add in components

        setLayoutManager();
        setLocationAndSize();
        setVisible(true);

        loginButton.addActionListener((e)-> {
            ResultSet resultSet = null;
            //check on database   with try & catch
                Connection connection = null;
                if (Client.isSelected() && !Employee.isSelected() && !userField.getText().isEmpty())
                {
                    try {
                        connection = DatabaseConnector.connect();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    String query = "SELECT * FROM client;";
                    PreparedStatement statement = null;
                    try {
                        statement = connection.prepareStatement(query);
                        resultSet =  statement.executeQuery();
                        int id;
                        boolean found = false;
                        while (resultSet.next())
                        {
                            id = resultSet.getInt("clientId");
                            Main.currentClient.setEmail(resultSet.getString( "clientEmail"));
                            Main.currentClient.setPassword(resultSet.getString("ClientPassword"));
                            Main.currentClient.setAge(resultSet.getInt("ClientAge"));
                            Main.currentClient.setIdentificationNumber((resultSet.getString("ClientIdentificationNumber")));
                            Main.currentClient.setName(resultSet.getString("ClientFname" ) + resultSet.getString("ClientLname"));


                            if (userField.getText().equals(Main.currentClient.getEmail()))
                            {
                                found= true;
//                                System.out.println(Main.currentClient.getEmail());
//                                System.out.println(Main.currentClient.getPassword());
                                if (Main.currentClient.getPassword().equals(String.valueOf(passwordField.getPassword())))
                                {
                                    JOptionPane.showMessageDialog(container , "Login Successful","Logging",JOptionPane.INFORMATION_MESSAGE);
                                    //Open new frame
                                    dispose();
                                    Main.showOrders = new showOrders();
                                    ///

                                }
                                try {
                                    statement.close();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                break;
                            }
                        }
                       if(!found) {
                            JOptionPane.showMessageDialog(container, "Invalid Username or Password","ERROR",JOptionPane.ERROR_MESSAGE);
                            passwordField.setText("");
                            userField.setText("");
                            showPassword.setSelected(false);
                        }

                    } catch (SQLException exception) {
                        throw new RuntimeException(exception.getMessage(),new Throwable(exception.getCause()));
                    }
                }
                else if (!Client.isSelected() && Employee.isSelected() && !userField.getText().isEmpty()) {

                    try {
                        connection = DatabaseConnector.connect();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    String query = "SELECT * FROM employee;";
                    PreparedStatement statement = null;
                    try {
                        statement = connection.prepareStatement(query);
                        resultSet =  statement.executeQuery();
                        int id;
                        boolean found = false;
                        while (resultSet.next())
                        {
                            id = resultSet.getInt("empId");
                            Main.currentEmployee.setEmail(resultSet.getString( "empEmail"));
                            Main.currentEmployee.setPassword(resultSet.getString("empPassword"));
                            Main.currentEmployee.setLocation(resultSet.getString("empCity") + resultSet.getString("empState"));
                            Main.currentEmployee.setAge(resultSet.getInt("empAge"));
                            Main.currentEmployee.setIdentificationNumber((resultSet.getString("empIdentificationNumber")));
                            Main.currentEmployee.setName(resultSet.getString("empFname" ) + resultSet.getString("empLname"));
                            Main.currentEmployee.setGender("empGender");
                            Main.currentEmployee.setPosition("empPosition");
                            if (userField.getText().equals(Main.currentEmployee.getEmail()))
                            {
                                found= true;
                                System.out.println(Main.currentEmployee.getEmail());
                                System.out.println(Main.currentEmployee.getPassword());
                                if (Main.currentEmployee.getPassword().equals(String.valueOf(passwordField.getPassword())))
                                {
                                    JOptionPane.showMessageDialog(container , "Login Successful","Logging",JOptionPane.INFORMATION_MESSAGE);
                                    //Open new frame
                                    dispose();
                                    Main.pharmacyManagement = new pharmacyManagement();

                                }
                                try {
                                    statement.close();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                connection.close();
                                break;
                            }
                        }
                        if(!found) {
                            JOptionPane.showMessageDialog(container, "Invalid Username or Password","ERROR",JOptionPane.ERROR_MESSAGE);
                            passwordField.setText("");
                            userField.setText("");
                            showPassword.setSelected(false);
                        }

                    } catch (SQLException exception) {
                        throw new RuntimeException(exception.getMessage(),new Throwable(exception.getCause()));
                    }


                }
                else
                {
                    if (!Employee.isSelected() && ! Client.isSelected() || Employee.isSelected() && Client.isSelected())
                    {
                        JOptionPane.showMessageDialog(container,"Please confirm you're status(Client/Employee)","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if (userField.getText().isEmpty() ||String.valueOf(passwordField.getPassword()).isEmpty())
                    {
                        JOptionPane.showMessageDialog(container,"Please enter a valid email","Invalid email",JOptionPane.ERROR_MESSAGE);
                    }
                }

        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                Employee.setSelected(false);
                Client.setSelected(false);
                }
            });

        signUp.addActionListener((e -> {
            dispose();
            Main.signUpForm = new SignUpForm();
        }));
            }


    public void setLayoutManager() {
        container.setLayout(null);

    }

    public void setLocationAndSize() {
        userLabel.setBounds(300, 50, 100, 50);
        Elogin.setBounds(400, 30, 180, 50);
        Plogin.setBounds(400, 100, 180, 50);
        passwordLabel.setBounds(300, 130, 100, 60);
        userField.setBounds(400, 70, 150, 30);
        passwordField.setBounds(400, 140, 150, 30);
        showPassword.setBounds(400, 200, 150, 30);
        Employee.setBounds(330,250,100,30);
        Client.setBounds(460,250,100,30);
        loginButton.setBounds(330, 285, 100, 30);
        resetButton.setBounds(460, 285, 100, 30);
        signUp.setBounds(260,5,100,30);
    }

    public void addComponentsToContainer() {
        container.add(signUp);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(Elogin);
        container.add(Plogin);
        container.add(Rbk);
        container.add(Lbk);
    }




}

