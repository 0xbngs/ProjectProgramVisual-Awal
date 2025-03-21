import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        // Set FlatLaf look and feel
        FlatLightLaf.setup();

        setTitle("Masuk");
        setSize(350, 300); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel dengan gambar latar belakang
        BackgroundPanel panel = new BackgroundPanel(new ImageIcon("background.png").getImage());
        panel.setLayout(null); // Menggunakan null layout untuk penempatan manual
        add(panel);

        // Judul
        JLabel titleLabel = new JLabel("Masukkan detail akun Anda untuk melanjutkan!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(30, 20, 300, 25); // Sesuaikan posisi dan ukuran
        panel.add(titleLabel);

        // Input Username
        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(30, 60, 80, 25); // Sesuaikan posisi dan ukuran
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 60, 165, 25); // Sesuaikan posisi dan ukuran
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameField);

        // Input Password
        JLabel passwordLabel = new JLabel("Kata Sandi");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(30, 100, 80, 25); // Sesuaikan posisi dan ukuran
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 165, 25); // Sesuaikan posisi dan ukuran
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        // Tombol Masuk
        loginButton = new JButton("Masuk Sekarang");
        loginButton.setBounds(120, 150, 150, 30); // Sesuaikan posisi dan ukuran
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(59, 89, 182)); // Warna tombol
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false); // Hilangkan border fokus
        panel.add(loginButton);

        // Tombol Daftar (opsional)
        JButton registerButton = new JButton("Tidak memiliki Akun? Daftar");
        registerButton.setBounds(80, 200, 200, 25); // Sesuaikan posisi dan ukuran
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.setForeground(Color.WHITE);
        registerButton.setContentAreaFilled(false); // Hilangkan background tombol
        registerButton.setBorderPainted(false); // Hilangkan border tombol
        panel.add(registerButton);

        // Action Listener untuk tombol Masuk
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

// Kelas khusus untuk panel dengan gambar latar belakang
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar gambar latar belakang
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}