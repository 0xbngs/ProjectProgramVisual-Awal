import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setSize(600, 400); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel untuk tombol utama (Master, Transaksi, Laporan)
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.add(topButtonPanel, BorderLayout.NORTH);

        // Tombol Master
        JButton masterButton = createButton("Master");
        topButtonPanel.add(masterButton);

        // Tombol Transaksi
        JButton transaksiButton = createButton("Transaksi");
        topButtonPanel.add(transaksiButton);

        // Tombol Laporan
        JButton laporanButton = createButton("Laporan");
        topButtonPanel.add(laporanButton);

        // Panel untuk submenu Master (Karyawan, Jabatan, Client, RAB, Tipe Rumah)
        JPanel masterSubPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 baris, 2 kolom, dengan jarak 10px
        masterSubPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margin
        masterSubPanel.setVisible(false); // Awalnya disembunyikan
        mainPanel.add(masterSubPanel, BorderLayout.CENTER);

        // Tombol Karyawan
        JButton karyawanButton = createSubButton("Karyawan");
        karyawanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KaryawanPage();
            }
        });
        masterSubPanel.add(karyawanButton);

        // Tombol Jabatan
        JButton jabatanButton = createSubButton("Jabatan");
        jabatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JabatanPage();
            }
        });
        masterSubPanel.add(jabatanButton);

        // Tombol Client
        JButton clientButton = createSubButton("Client");
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientPage();
            }
        });
        masterSubPanel.add(clientButton);

        // Tombol RAB
        JButton rabButton = createSubButton("RAB");
        rabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RABPage();
            }
        });
        masterSubPanel.add(rabButton);

        // Tombol Tipe Rumah
        JButton tipeRumahButton = createSubButton("Tipe Rumah");
        tipeRumahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TipeRumahPage();
            }
        });
        masterSubPanel.add(tipeRumahButton);

        // Action Listener untuk tombol Master
        masterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan atau sembunyikan submenu Master
                masterSubPanel.setVisible(!masterSubPanel.isVisible());
            }
        });

        // Action Listener untuk tombol Transaksi (opsional)
        transaksiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransaksiPage(); // Buka halaman Transaksi
            }
        });

        // Action Listener untuk tombol Laporan (opsional)
        laporanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LaporanPage(); // Buka Halaman Laporan
            }
        });

        setVisible(true);
    }

    // Method untuk membuat tombol utama
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(59, 89, 182)); // Warna background
        button.setForeground(Color.WHITE); // Warna teks
        button.setFocusPainted(false); // Hilangkan border fokus
        button.setPreferredSize(new Dimension(150, 50)); // Ukuran tombol
        return button;
    }

    // Method untuk membuat tombol submenu
    private JButton createSubButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(100, 150, 200)); // Warna background
        button.setForeground(Color.WHITE); // Warna teks
        button.setFocusPainted(false); // Hilangkan border fokus
        button.setPreferredSize(new Dimension(120, 40)); // Ukuran tombol
        return button;
    }

    public static void main(String[] args) {
        new AdminPage();
    }
}