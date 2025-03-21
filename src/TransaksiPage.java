import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransaksiPage extends JFrame {
    public TransaksiPage() {
        setTitle("Transaksi");
        setSize(600, 400); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel untuk tombol utama (Penjualan Rumah, Gaji Karyawan)
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.add(topButtonPanel, BorderLayout.CENTER);

        // Tombol Penjualan Rumah
        JButton penjualanButton = createButton("Penjualan Rumah");
        penjualanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PenjualanRumahPage(); // Buka halaman Penjualan Rumah
            }
        });
        topButtonPanel.add(penjualanButton);

        // Tombol Gaji Karyawan
        JButton gajiButton = createButton("Gaji Karyawan");
        gajiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GajiKaryawanPage(); // Buka halaman Gaji Karyawan
            }
        });
        topButtonPanel.add(gajiButton);

        setVisible(true);
    }

    // Method untuk membuat tombol
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(59, 89, 182)); // Warna background
        button.setForeground(Color.WHITE); // Warna teks
        button.setFocusPainted(false); // Hilangkan border fokus
        button.setPreferredSize(new Dimension(200, 50)); // Ukuran tombol
        return button;
    }

    public static void main(String[] args) {
        new TransaksiPage();
    }
}