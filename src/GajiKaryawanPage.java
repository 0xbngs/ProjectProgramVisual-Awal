import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GajiKaryawanPage extends JFrame {
    private JTextField karyawanIdField;
    private JTextField jabatanIdField;
    private JLabel karyawanNamaLabel;
    private JLabel jabatanLabel;
    private JLabel gajiPokokLabel;
    private JLabel bonusLabel;
    private JLabel rumahTerjualLabel;
    private JLabel totalGajiLabel;

    public GajiKaryawanPage() {
        setTitle("Gaji Karyawan");
        setSize(800, 600); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margin
        add(mainPanel);

        // Judul "Gaji Karyawan"
        JLabel titleLabel = new JLabel("Gaji Karyawan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Font tebal dan besar
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel untuk Karyawan, Jabatan, dan Gaji
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 20, 20)); // 3 baris, 1 kolom
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margin
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Panel Karyawan
        JPanel karyawanPanel = createKaryawanPanel();
        contentPanel.add(karyawanPanel);

        // Panel Jabatan
        JPanel jabatanPanel = createJabatanPanel();
        contentPanel.add(jabatanPanel);

        // Panel Gaji
        JPanel gajiPanel = createGajiPanel();
        contentPanel.add(gajiPanel);

        setVisible(true);
    }

    // Method untuk membuat panel Karyawan
    private JPanel createKaryawanPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Karyawan")); // Judul panel
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.anchor = GridBagConstraints.WEST; // Posisi komponen ke kiri

        // Label dan Field untuk ID Karyawan
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("ID Karyawan:");
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        karyawanIdField = new JTextField(20);
        panel.add(karyawanIdField, gbc);

        // Tombol Cari Karyawan
        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton searchKaryawanButton = new JButton("Cari");
        searchKaryawanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchKaryawanData();
            }
        });
        panel.add(searchKaryawanButton, gbc);

        // Label untuk Nama Karyawan
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel namaLabel = new JLabel("Nama:");
        panel.add(namaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        karyawanNamaLabel = new JLabel("");
        panel.add(karyawanNamaLabel, gbc);

        return panel;
    }

    // Method untuk membuat panel Jabatan
    private JPanel createJabatanPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Jabatan")); // Judul panel
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.anchor = GridBagConstraints.WEST; // Posisi komponen ke kiri

        // Label dan Field untuk ID Jabatan
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("ID Jabatan:");
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        jabatanIdField = new JTextField(20);
        panel.add(jabatanIdField, gbc);

        // Tombol Cari Jabatan
        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton searchJabatanButton = new JButton("Cari");
        searchJabatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchJabatanData();
            }
        });
        panel.add(searchJabatanButton, gbc);

        // Label untuk Jabatan, Gaji Pokok, dan Bonus
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel jabatanLabelTitle = new JLabel("Jabatan:");
        panel.add(jabatanLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        jabatanLabel = new JLabel("");
        panel.add(jabatanLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel gajiPokokLabelTitle = new JLabel("Gaji Pokok:");
        panel.add(gajiPokokLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gajiPokokLabel = new JLabel("");
        panel.add(gajiPokokLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel bonusLabelTitle = new JLabel("Bonus:");
        panel.add(bonusLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        bonusLabel = new JLabel("");
        panel.add(bonusLabel, gbc);

        return panel;
    }

    // Method untuk membuat panel Gaji
    private JPanel createGajiPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gaji")); // Judul panel
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.anchor = GridBagConstraints.WEST; // Posisi komponen ke kiri

        // Label untuk Gaji Pokok, Bonus, Rumah Terjual, dan Total Gaji
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel gajiPokokLabelTitle = new JLabel("Gaji Pokok:");
        panel.add(gajiPokokLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gajiPokokLabel = new JLabel("");
        panel.add(gajiPokokLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel bonusLabelTitle = new JLabel("Bonus:");
        panel.add(bonusLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        bonusLabel = new JLabel("");
        panel.add(bonusLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel rumahTerjualLabelTitle = new JLabel("Rumah Terjual:");
        panel.add(rumahTerjualLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        rumahTerjualLabel = new JLabel("");
        panel.add(rumahTerjualLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel totalGajiLabelTitle = new JLabel("Total Gaji:");
        panel.add(totalGajiLabelTitle, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        totalGajiLabel = new JLabel("");
        panel.add(totalGajiLabel, gbc);

        return panel;
    }

    // Method untuk mencari data Karyawan
    private void searchKaryawanData() {
        String idKaryawan = karyawanIdField.getText();
        if (idKaryawan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Karyawan terlebih dahulu.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM karyawan WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idKaryawan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Tampilkan nama karyawan
                karyawanNamaLabel.setText(resultSet.getString("nama"));
            } else {
                JOptionPane.showMessageDialog(this, "Data karyawan tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mencari data karyawan: " + e.getMessage());
        }
    }

    // Method untuk mencari data Jabatan
    private void searchJabatanData() {
        String idJabatan = jabatanIdField.getText();
        if (idJabatan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Jabatan terlebih dahulu.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM jabatan WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idJabatan);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Tampilkan jabatan, gaji pokok, dan bonus
                jabatanLabel.setText(resultSet.getString("jabatan"));
                gajiPokokLabel.setText(String.valueOf(resultSet.getDouble("gaji_pokok")));
                bonusLabel.setText(String.valueOf(resultSet.getDouble("bonus")));

                // Hitung total gaji
                double gajiPokok = resultSet.getDouble("gaji_pokok");
                double bonus = resultSet.getDouble("bonus");
                double totalGaji = gajiPokok + bonus;
                totalGajiLabel.setText(String.valueOf(totalGaji));
            } else {
                JOptionPane.showMessageDialog(this, "Data jabatan tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mencari data jabatan: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GajiKaryawanPage();
    }
}