import SQLOperation.DatabaseConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class newEmployeeForm extends JFrame   {

    Container container = new Container();
    JLabel background;
    JButton logout = new JButton("Logout");
    JLabel Fname = new JLabel("First Name");
    JLabel Lname = new JLabel("Last Name");
    JLabel age = new JLabel("Age");
    JLabel IdentificationNamber = new JLabel("Identification Number");
    JLabel city = new JLabel("City");
    JLabel state = new JLabel("State");
    JLabel gender = new JLabel("Gender");
    JLabel email = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Password");
    JLabel RE_passwordlabel = new JLabel("Confirm Password");

    JTextField FnameField = new JTextField();
    JTextField LnameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField IdentificationNamberField = new JTextField();
    JTextField cityField = new JTextField();
    JTextField stateField = new JTextField();
    JTextField genderField = new JTextField();
    JTextField emailField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField RE_passwordField = new JPasswordField();
    ImageIcon Icon=new ImageIcon("Media/icon.png");
    JLabel icon = new JLabel(Icon);

    JButton AddEmployee = new JButton("Add Employee");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    private boolean fieldsAreEmpty() {
        return emailField.getText().isEmpty() || FnameField.getText().isEmpty() || LnameField.getText().isEmpty() ||
                ageField.getText().isEmpty() || cityField.getText().isEmpty() || stateField.getText().isEmpty() ||
                genderField.getText().isEmpty()  || IdentificationNamberField.getText().isEmpty();
    }
    //constructor

    public newEmployeeForm() throws RuntimeException {
        setTitle("Add Employee");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);


        background=new JLabel("", new ImageIcon("Media/Background.jpg"),JLabel.CENTER);
        background.setBounds(0,0,650,650);




        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
       setVisible(true);

        AddEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if there eny empty fields


                if (fieldsAreEmpty()) {
                    JOptionPane.showMessageDialog(container, "Invalid Field", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ResultSet resultSet = null;
                Connection connection = null;
                boolean found = false;
                String query = "select empEmail from Employee;";
                PreparedStatement pStatement;
                Main.currentClient.setEmail(emailField.getText());
                try {
                    connection = DatabaseConnector.connect();
                    pStatement = connection.prepareStatement(query);
                    resultSet = pStatement.executeQuery();
                    while (resultSet.next()) {
                        if (resultSet.getString("empEmail").equals(emailField.getText())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {

                        if (isValidEmail(emailField.getText())) {
                            final String q = "INSERT INTO Employee(empFname, empLname, empAge, empIdentificationNumber, empCity, empState, empGender, empEmail, empPassword) " +
                                    "VALUES(\"" + FnameField.getText() + "\",\"" + LnameField.getText() + "\"," + Integer.parseInt(ageField.getText()) + "," +
                                    IdentificationNamberField.getText() + ",\"" + cityField.getText() + "\",\"" + stateField.getText() + "\",\"" +
                                    genderField.getText() + "\",\"" + emailField.getText() + "\",\"" + String.valueOf(passwordField.getPassword()) + "\")";
                            try (
                                    Connection cconnection = DatabaseConnector.connect();
                                    Statement statement = cconnection.createStatement();
                            ) {

                                if (Integer.parseInt(String.valueOf(passwordField.getPassword())) != Integer.parseInt( String.valueOf(RE_passwordField.getPassword()))) {
                                    JOptionPane.showMessageDialog(container, "Failed to add new Employee, Please confirm you password", "Error", JOptionPane.ERROR_MESSAGE);
                                    connection.close();
                                    return;
                                }


                                int rowsAffected = rowsAffected = statement.executeUpdate(q);
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(container, "New Employee added successfully", "Add new Employee", JOptionPane.PLAIN_MESSAGE);
                                    connection.close();
                                    return;
                                }
                            } catch (SQLException | NumberFormatException ex) {
                                throw new RuntimeException(ex);
                            }
                            connection.close();
                        }
                        else {
                            JOptionPane.showMessageDialog(container, "Failed to add new Employee, Please enter a valid user email", "Error", JOptionPane.ERROR_MESSAGE);
                            emailField.setText("");
                            connection.close();

                        }
                    }
                    else {

                        JOptionPane.showMessageDialog(container,"User email already exists");
                        connection.close();
                    }
                } catch (SQLException x)
                {
                    throw new RuntimeException();
                }
            }

        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                emailField.setText("");
                passwordField.setText("");
                FnameField.setText("");
                LnameField.setText("");
                ageField.setText("");
                stateField.setText("");
                cityField.setText("");
                IdentificationNamberField.setText("");
                RE_passwordField.setText("");
                genderField.setText("");
                showPassword.setSelected(false);

            }
        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    RE_passwordField.setEchoChar((char) 0);

                } else {
                    passwordField.setEchoChar('*');
                    RE_passwordField.setEchoChar('*');
                }

            }
        });



        logout.addActionListener((e)->{
            dispose();
            Main.loginForm = new LoginForm();

        });

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        logout.setBounds(20,20,80,20);
        Fname.setBounds(150, 60, 100, 60);
        FnameField.setBounds(300, 80, 150, 30);
        Lname.setBounds(150, 100, 100, 60);
        LnameField.setBounds(300, 120, 150, 30);
        age.setBounds(150, 140, 100, 60);
        ageField.setBounds(300, 160, 150, 30);
        gender.setBounds(150, 180, 100, 60);
        genderField.setBounds(300, 200, 150, 30);
        state.setBounds(150, 220, 100, 60);
        stateField.setBounds(300, 240, 150, 30);
        city.setBounds(150, 260, 100, 60);
        cityField.setBounds(300, 280, 150, 30);
        IdentificationNamber.setBounds(150, 300, 140, 60);
        IdentificationNamberField.setBounds(300, 320, 150, 30);
        email.setBounds(150, 340, 100, 60);
        emailField.setBounds(300, 360, 150, 30);
        passwordLabel.setBounds(150, 380, 100, 60);
        passwordField.setBounds(300, 400, 150, 30);
        RE_passwordlabel.setBounds(150, 420, 120, 60);
        RE_passwordField.setBounds(300, 440, 150, 30);
        icon.setBounds(450,10,200,400);
        showPassword.setBounds(460, 440, 150, 30);
        AddEmployee.setBounds(220, 500, 120, 30);
        resetButton.setBounds(350, 500, 100, 30);


    }



    public void addComponentsToContainer() {
        container.add(logout);
        container.add(Fname);
        container.add(Lname);
        container.add(FnameField);
        container.add(LnameField);
        container.add(age);
        container.add(ageField);
        container.add(gender);
        container.add(genderField);
        container.add(state);
        container.add(stateField);
        container.add(city);
        container.add(cityField);
        container.add(IdentificationNamber);
        container.add(IdentificationNamberField);
        container.add(RE_passwordlabel);
        container.add(RE_passwordField);
        container.add(email);
        container.add(passwordLabel);
        container.add(emailField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(AddEmployee);
        container.add(resetButton);
        container.add(icon);
        container.add(background);

        add(container);
    }
    public static boolean isValidEmail(String email) {
//        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("\\\\b[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\\\.)+(com|net)\\\\b");
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        String pattern = "\\b[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+(com|net)\\b";
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();

    }


}

