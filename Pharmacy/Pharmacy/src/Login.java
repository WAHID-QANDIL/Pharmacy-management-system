import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class Login extends JDialog{
    private JTextField textField1;
    private JTextField textField2;
    private JButton RESETButton;
    private JButton LOGINButton;
    private JPanel LoginPanel;
    private JPanel ButtonsPanel;

    public Login(JFrame frame) {
        super(frame);
        setSize(750,500);
        setTitle("LOGIN");
        setContentPane(LoginPanel);
        setBackground(new ColorUIResource(new Color(0)));
        setMaximumSize(new Dimension(750,750));
        setModal(true);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        LOGINButton.addActionListener((event)-> {
            if (textField1.getText().isEmpty() || textField2.getText().isEmpty() )
            {
                JOptionPane.showMessageDialog(ButtonsPanel,"Invalid Email or password","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(ButtonsPanel,"Invalid Email or password","ERROR",JOptionPane.ERROR_MESSAGE);
        });
        RESETButton.addActionListener((event)-> {
            textField1.setText("");
            textField2.setText("");
        });
    }
}
