import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PenjualanRumahPage extends JFrame {
    private JTextField[] clientFields; // Array untuk menyimpan field client
    private JTextField[] tipeRumahFields; // Array untuk menyimpan field tipe rumah
    private JTextField[] karyawanFields; // Array untuk menyimpan field karyawan

    public PenjualanRumahPage() {
        setTitle("Penjualan Rumah");
        setSize(900, 700); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margin
        add(mainPanel);

        // Judul "Penjualan Rumah"
        JLabel titleLabel = new JLabel("Penjualan Rumah");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Font tebal dan besar
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // JTabbedPane untuk Client, Tipe Rumah, dan Karyawan
        JTabbedPane tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Form Client
        String[] clientLabels = {"NIK", "Nama", "No. KK", "NPWP", "Asuransi", "Gaji", "Alamat"};
        clientFields = new JTextField[clientLabels.length];
        JPanel clientPanel = createFormPanel("Client", clientLabels, clientFields);
        tabbedPane.addTab("Client", new JScrollPane(clientPanel));

        // Form Tipe Rumah
        String[] tipeRumahLabels = {"ID Tipe Rumah", "Tipe", "Harga Pokok", "Luas Bangunan", "Luas Tanah", "Kamar Tidur", "Deskripsi", "Kamar Mandi", "Lantai", "Listrik", "Sumber Air", "Harga Rumah"};
        tipeRumahFields = new JTextField[tipeRumahLabels.length];
        JPanel tipeRumahPanel = createFormPanel("Tipe Rumah", tipeRumahLabels, tipeRumahFields);
        tabbedPane.addTab("Tipe Rumah", new JScrollPane(tipeRumahPanel));

        // Form Karyawan
        String[] karyawanLabels = {"ID", "Nama"};
        karyawanFields = new JTextField[karyawanLabels.length];
        JPanel karyawanPanel = createFormPanel("Karyawan", karyawanLabels, karyawanFields);
        tabbedPane.addTab("Karyawan", new JScrollPane(karyawanPanel));

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        simpanButton.setFont(new Font("Arial", Font.BOLD, 16));
        simpanButton.setBackground(new Color(59, 89, 182)); // Warna background
        simpanButton.setForeground(Color.WHITE); // Warna teks
        simpanButton.setFocusPainted(false); // Hilangkan border fokus
        simpanButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PenjualanRumahPage.this, "Data penjualan rumah berhasil disimpan!");
            }
        });

        // Panel untuk tombol Simpan
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margin atas
        buttonPanel.add(simpanButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method untuk membuat form panel dengan field dan tombol Search
    private JPanel createFormPanel(String title, String[] labels, JTextField[] fields) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title)); // Judul panel
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antar komponen
        gbc.anchor = GridBagConstraints.WEST; // Posisi komponen ke kiri

        for (int i = 0; i < labels.length; i++) {
            // Label untuk field
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel label = new JLabel(labels[i] + " :");
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(label, gbc);

            // TextField untuk input
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            fields[i] = new JTextField(20);
            panel.add(fields[i], gbc);

            // Tombol Cari dengan ikon kaca pembesar
            gbc.gridx = 2;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0.0;
            JButton searchButton = new JButton(new ImageIcon("search_icon.png")); // Ganti dengan path ikon kaca pembesar
            searchButton.setPreferredSize(new Dimension(30, 30)); // Ukuran tombol
            searchButton.setBackground(new Color(200, 220, 240)); // Warna background
            searchButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding

            // Buat salinan final dari variabel yang digunakan di dalam inner class
            final String fieldName = labels[i]; // Variabel final untuk field name
            final JTextField finalTextField = fields[i]; // Variabel final untuk textField

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Panggil method untuk mencari data dari database
                    if (title.equals("Client")) {
                        searchClientData(fieldName, finalTextField);
                    } else if (title.equals("Tipe Rumah")) {
                        searchTipeRumahData(fieldName, finalTextField);
                    } else if (title.equals("Karyawan")) {
                        searchKaryawanData(fieldName, finalTextField);
                    }
                }
            });
            panel.add(searchButton, gbc);
        }

        return panel;
    }

    // Method untuk mencari data Client dari database
    private void searchClientData(String fieldName, JTextField textField) {
        String searchKey = JOptionPane.showInputDialog(this, "Masukkan " + fieldName + " untuk dicari:");
        if (searchKey != null && !searchKey.isEmpty()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM client WHERE " + fieldName + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, searchKey);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Isi semua field client dengan data dari database
                    clientFields[0].setText(resultSet.getString("nik"));
                    clientFields[1].setText(resultSet.getString("nama"));
                    clientFields[2].setText(resultSet.getString("nokk"));
                    clientFields[3].setText(resultSet.getString("npwp"));
                    clientFields[4].setText(resultSet.getString("asuransi"));
                    clientFields[5].setText(String.valueOf(resultSet.getDouble("gaji")));
                    clientFields[6].setText(resultSet.getString("alamat"));
                } else {
                    JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method untuk mencari data Tipe Rumah dari database
   private void searchTipeRumahData(String fieldName, JTextField textField) {
    // Mapping antara label di UI dan nama kolom di database
    String columnName;
    switch (fieldName) {
        case "ID Tipe Rumah":
            columnName = "id";
            break;
        case "Tipe":
            columnName = "tipe";
            break;
        case "Harga Pokok":
            columnName = "harga_pokok";
            break;
        case "Luas Bangunan":
            columnName = "luas_bangunan";
            break;
        case "Luas Tanah":
            columnName = "luas_tanah";
            break;
        case "Kamar Tidur":
            columnName = "kamar_tidur";
            break;
        case "Deskripsi":
            columnName = "deskripsi";
            break;
        case "Kamar Mandi":
            columnName = "kamar_mandi";
            break;
        case "Lantai":
            columnName = "lantai";
            break;
        case "Listrik":
            columnName = "listrik";
            break;
        case "Sumber Air":
            columnName = "sumber_air";
            break;
        case "Harga Rumah":
            columnName = "harga_rumah";
            break;
        default:
            JOptionPane.showMessageDialog(this, "Kolom tidak valid: " + fieldName);
            return;
    }

    String searchKey = JOptionPane.showInputDialog(this, "Masukkan " + fieldName + " untuk dicari:");
    if (searchKey != null && !searchKey.isEmpty()) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Gunakan nama kolom yang sesuai dengan database
            String query = "SELECT * FROM tipe_rumah WHERE `" + columnName + "` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, searchKey);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Isi semua field di form Tipe Rumah dengan data dari database
                tipeRumahFields[0].setText(String.valueOf(resultSet.getInt("id"))); // ID Tipe Rumah (kolom "id" di database)
                tipeRumahFields[1].setText(resultSet.getString("tipe"));
                tipeRumahFields[2].setText(String.valueOf(resultSet.getDouble("harga_pokok")));
                tipeRumahFields[3].setText(String.valueOf(resultSet.getDouble("luas_bangunan")));
                tipeRumahFields[4].setText(String.valueOf(resultSet.getDouble("luas_tanah")));
                tipeRumahFields[5].setText(String.valueOf(resultSet.getInt("kamar_tidur")));
                tipeRumahFields[6].setText(resultSet.getString("deskripsi"));
                tipeRumahFields[7].setText(String.valueOf(resultSet.getInt("kamar_mandi")));
                tipeRumahFields[8].setText(String.valueOf(resultSet.getInt("lantai")));
                tipeRumahFields[9].setText(resultSet.getString("listrik"));
                tipeRumahFields[10].setText(resultSet.getString("sumber_air"));
                tipeRumahFields[11].setText(String.valueOf(resultSet.getDouble("harga_rumah")));
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mencari data: " + e.getMessage());
        }
    }
}

    // Method untuk mencari data Karyawan dari database
    private void searchKaryawanData(String fieldName, JTextField textField) {
        String searchKey = JOptionPane.showInputDialog(this, "Masukkan " + fieldName + " untuk dicari:");
        if (searchKey != null && !searchKey.isEmpty()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM karyawan WHERE " + fieldName + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, searchKey);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Isi semua field karyawan dengan data dari database
                    karyawanFields[0].setText(String.valueOf(resultSet.getInt("id")));
                    karyawanFields[1].setText(resultSet.getString("nama"));
                } else {
                    JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new PenjualanRumahPage();
    }
}