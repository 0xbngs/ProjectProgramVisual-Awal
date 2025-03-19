import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Masuk");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("Masukkan detail akun Anda untuk melanjutkan!");
        titleLabel.setBounds(30, 10, 300, 25);
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(30, 50, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 50, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Kata Sandi");
        passwordLabel.setBounds(30, 90, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 90, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Masuk Sekarang");
        loginButton.setBounds(120, 130, 150, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("admin") && password.equals("admin123")) {
                    new AdminPage();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Username atau Kata Sandi salah");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}