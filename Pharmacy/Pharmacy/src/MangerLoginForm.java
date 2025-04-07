import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;
public class MangerLoginForm extends JFrame{

    JButton signUp = new JButton("Sign up");
    Container container = getContentPane();
    JLabel Rbk;
    JLabel Lbk;
    ImageIcon iconUser=new ImageIcon("Media/userName.png");
    ImageIcon iconPassword=new ImageIcon("Media/password.png");
    JLabel userLabel = new JLabel(iconUser);
    JLabel Elogin = new JLabel("Manager Email ");
    JLabel Plogin = new JLabel("Manager Password");

    JLabel passwordLabel = new JLabel(iconPassword);
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    //constructor
    public MangerLoginForm() {

        setTitle("Login Manager" );
        setLocationRelativeTo(null);
        setSize(600,360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //label fontColor
        Elogin.setForeground(Color.WHITE);
        Plogin.setForeground(Color.WHITE);

        //background for each site
        Rbk=new JLabel("", new ImageIcon("Media/r.png"),JLabel.CENTER);
        Rbk.setBounds(120,0,820,390);

        Lbk=new JLabel("", new ImageIcon("Media/l.png"),JLabel.CENTER);
        Lbk.setBounds(-265,-10,800,390);


        //Functios to set layout and add in compunnint
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        setVisible(true);





        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!userField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty())
                {
                    if (userField.getText().equals(Main.mangerEmail) && String.valueOf(passwordField.getPassword()).equals(Main.mangerPassword))
                    {
                        JOptionPane.showMessageDialog(container,"Logging in","Manger Logging",JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        Main.newEmployeeForm = new newEmployeeForm();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(container,"Invalid email or password","Error",JOptionPane.ERROR_MESSAGE);

                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(container,"Please enter email and password","Missing password or email",JOptionPane.ERROR_MESSAGE);
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
        loginButton.setBounds(330, 285, 100, 30);
        resetButton.setBounds(460, 285, 100, 30);
        signUp.setBounds(260,5,100,30);

    }


    public void addComponentsToContainer() {

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
        container.add(signUp);


    }



    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

