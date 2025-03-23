import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LaporanPage extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable tipeRumahTable;

    public LaporanPage() {
        setTitle("Laporan");
        setSize(1200, 800); // Sesuaikan ukuran frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margin
        add(mainPanel);

        // Judul "Laporan"
        JLabel titleLabel = new JLabel("Laporan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Font tebal dan besar
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Tengah
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // TabbedPane untuk fitur-fitur laporan
        tabbedPane = new JTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Tambahkan tab untuk setiap fitur
        tabbedPane.addTab("Transaksi Client", createTransaksiClientPanel());
        tabbedPane.addTab("Gaji Akhir Karyawan", createGajiAkhirPanel());
        tabbedPane.addTab("Tipe Rumah yang Terjual", createTipeRumahTerjualPanel());
        tabbedPane.addTab("Keuntungan Kotor", createKeuntunganKotorPanel());

        // Tombol Back
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(200, 200, 200)); // Warna background
        backButton.setFocusPainted(false); // Hilangkan border fokus
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup halaman laporan
                new AdminPage(); // Kembali ke halaman admin
            }
        });

        // Panel untuk tombol Back
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Margin atas
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method untuk membuat panel Transaksi Client
    private JPanel createTransaksiClientPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.setBackground(new Color(240, 240, 240));

    // Panel untuk input tanggal
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    topPanel.setBackground(new Color(240, 240, 240));

    // Input Tanggal Awal
    JLabel tanggalAwalLabel = new JLabel("Tanggal Awal:");
    JTextField tanggalAwalField = new JTextField(10);
    topPanel.add(tanggalAwalLabel);
    topPanel.add(tanggalAwalField);

    // Input Tanggal Akhir
    JLabel tanggalAkhirLabel = new JLabel("Tanggal Akhir:");
    JTextField tanggalAkhirField = new JTextField(10);
    topPanel.add(tanggalAkhirLabel);
    topPanel.add(tanggalAkhirField);

    // Tombol Cari (Hanya dideklarasikan sekali!)
    JButton searchButton = new JButton("Cari");
    searchButton.setFont(new Font("Arial", Font.BOLD, 14));
    searchButton.setBackground(new Color(59, 89, 182));
    searchButton.setForeground(Color.WHITE);
    searchButton.setFocusPainted(false);

    // Tabel Data Transaksi
    String[] columnNames = {"Timestamp", "Nama Client", "Tipe Rumah", "Nama Karyawan", "Harga"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    JTable transaksiTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(transaksiTable);

    // ActionListener untuk tombol Cari
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadTransaksiData(
                tanggalAwalField.getText(), 
                tanggalAkhirField.getText(), 
                tableModel // Kirim tableModel ke method loadTransaksiData
            );
        }
    });

    topPanel.add(searchButton);
    panel.add(topPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}

    // Method untuk membuat panel Gaji Akhir Karyawan
    private JPanel createGajiAkhirPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        // Tabel Data Gaji Akhir Karyawan
        String[] columnNames = {"ID Karyawan", "Nama Karyawan", "Jabatan", "Gaji Pokok", "Bonus", "Total Gaji"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable gajiTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(gajiTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Load Data
        JButton loadButton = new JButton("Load Data");
        loadButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadButton.setBackground(new Color(59, 89, 182)); // Warna background
        loadButton.setForeground(Color.WHITE); // Warna teks
        loadButton.setFocusPainted(false); // Hilangkan border fokus
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGajiAkhirData(tableModel);
            }
        });

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(loadButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Method untuk membuat panel Tipe Rumah yang Terjual
   private JPanel createTipeRumahTerjualPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin
    panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

    // Tabel Data Tipe Rumah yang Terjual
    String[] columnNames = {"Tipe Rumah", "Jumlah Terjual", "Total Pendapatan"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    tipeRumahTable = new JTable(tableModel); // Gunakan variabel instance yang sudah dideklarasikan
    JScrollPane scrollPane = new JScrollPane(tipeRumahTable);
    panel.add(scrollPane, BorderLayout.CENTER);

    // Tombol Load Data
    JButton loadButton = new JButton("Load Data");
    loadButton.setFont(new Font("Arial", Font.BOLD, 14));
    loadButton.setBackground(new Color(59, 89, 182)); // Warna background
    loadButton.setForeground(Color.WHITE); // Warna teks
    loadButton.setFocusPainted(false); // Hilangkan border fokus
    loadButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTipeRumahTerjualData(); // Panggil method refresh
        }
    });

    // Panel untuk tombol
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(new Color(240, 240, 240));
    buttonPanel.add(loadButton);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    // Load data saat pertama kali tab dibuka
    refreshTipeRumahTerjualData();

    return panel;
}

    // Method untuk membuat panel Keuntungan Kotor
    private JPanel createKeuntunganKotorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin
        panel.setBackground(new Color(240, 240, 240)); // Warna background lembut

        // Label untuk menampilkan total keuntungan kotor
        JLabel keuntunganLabel = new JLabel("Total Keuntungan Kotor: Rp 0");
        keuntunganLabel.setFont(new Font("Arial", Font.BOLD, 18));
        keuntunganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(keuntunganLabel, BorderLayout.CENTER);

        // Tombol Hitung Keuntungan
        JButton hitungButton = new JButton("Hitung Keuntungan");
        hitungButton.setFont(new Font("Arial", Font.BOLD, 14));
        hitungButton.setBackground(new Color(59, 89, 182)); // Warna background
        hitungButton.setForeground(Color.WHITE); // Warna teks
        hitungButton.setFocusPainted(false); // Hilangkan border fokus
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungKeuntunganKotor(keuntunganLabel);
            }
        });

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(hitungButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Method untuk memuat data transaksi
    private void loadTransaksiData(String tanggalAwal, String tanggalAkhir, DefaultTableModel tableModel) {
    // Clear existing data
    tableModel.setRowCount(0);

    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query untuk mengambil data transaksi dengan JOIN ke tabel terkait
        String query = "SELECT t.timestamp, c.nama AS nama_client, tr.tipe AS tipe_rumah, k.nama AS nama_karyawan, t.harga_rumah " +
                       "FROM transaksi t " +
                       "JOIN client c ON t.client_id = c.id " +
                       "JOIN tipe_rumah tr ON t.tipe_rumah_id = tr.id " +
                       "JOIN karyawan k ON t.karyawan_id = k.id " +
                       "WHERE t.timestamp BETWEEN ? AND ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Handle tanggal (format: yyyy-MM-dd)
        if (!tanggalAwal.isEmpty() && !tanggalAkhir.isEmpty()) {
            // Tambahkan waktu untuk mencakup seluruh hari
            preparedStatement.setString(1, tanggalAwal + " 00:00:00");
            preparedStatement.setString(2, tanggalAkhir + " 23:59:59");
        } else {
            // Jika tanggal kosong, tampilkan semua data
            preparedStatement.setString(1, "1970-01-01");
            preparedStatement.setString(2, "2100-12-31");
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        // Tambahkan data ke tabel
        while (resultSet.next()) {
            String timestamp = resultSet.getString("timestamp");
            String namaClient = resultSet.getString("nama_client");
            String tipeRumah = resultSet.getString("tipe_rumah");
            String namaKaryawan = resultSet.getString("nama_karyawan");
            double hargaRumah = resultSet.getDouble("harga_rumah");

            tableModel.addRow(new Object[]{timestamp, namaClient, tipeRumah, namaKaryawan, hargaRumah});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data: " + e.getMessage());
    }
}

    // Method untuk memuat data gaji akhir karyawan
    private void loadGajiAkhirData(DefaultTableModel tableModel) {
    tableModel.setRowCount(0);

    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query yang sesuai dengan struktur database Anda
        String query = 
            "SELECT k.id, k.nama, k.jabatan, " + // Ambil jabatan langsung dari karyawan
            "j.gaji_pokok, " +
            "COUNT(t.id) AS jumlah_penjualan, " +
            "COUNT(t.id) * 500000 AS bonus, " + // Hitung bonus dari jumlah penjualan
            "(j.gaji_pokok + (COUNT(t.id) * 500000)) AS total_gaji " +
            "FROM karyawan k " +
            "JOIN jabatan j ON k.jabatan = j.jabatan " + // JOIN berdasarkan nama jabatan
            "LEFT JOIN transaksi t ON k.id = t.karyawan_id " +
            "GROUP BY k.id";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            tableModel.addRow(new Object[]{
                resultSet.getInt("id"),
                resultSet.getString("nama"),
                resultSet.getString("jabatan"),
                resultSet.getDouble("gaji_pokok"),
                resultSet.getDouble("bonus"),
                resultSet.getDouble("total_gaji")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Error: " + e.getMessage(), 
            "Database Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    private void simpanDataTransaksi() {
    // Ambil data dari form
    String clientId = clientFields[0].getText(); // Pastikan ini adalah ID client (bukan NIK)
    String tipeRumahId = tipeRumahFields[0].getText(); // ID Tipe Rumah
    String karyawanId = karyawanFields[0].getText(); // ID Karyawan
    double hargaRumah = Double.parseDouble(tipeRumahFields[11].getText()); // Harga Rumah

    // Timestamp otomatis
    Timestamp timestamp = new Timestamp(new Date().getTime());

    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query untuk menyimpan data transaksi
        String query = "INSERT INTO transaksi (timestamp, client_id, tipe_rumah_id, karyawan_id, harga_rumah) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTimestamp(1, timestamp);
        preparedStatement.setInt(2, Integer.parseInt(clientId)); // client_id adalah INT
        preparedStatement.setInt(3, Integer.parseInt(tipeRumahId)); // tipe_rumah_id adalah INT
        preparedStatement.setInt(4, Integer.parseInt(karyawanId)); // karyawan_id adalah INT
        preparedStatement.setDouble(5, hargaRumah);

        // Eksekusi query
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Data transaksi berhasil disimpan!");

            // Panggil method untuk memperbarui data di LaporanPage
            LaporanPage laporanPage = new LaporanPage(); // Buat instance LaporanPage
            laporanPage.refreshTipeRumahTerjualData(); // Panggil method refresh
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data transaksi.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + e.getMessage());
    }
}
    private void refreshTipeRumahTerjualData() {
    DefaultTableModel tableModel = (DefaultTableModel) tipeRumahTable.getModel();
    tableModel.setRowCount(0); // Clear existing data

    try (Connection connection = DatabaseConnection.getConnection()) {
        // Query untuk mengambil data tipe rumah yang terjual
        String query = "SELECT tr.tipe, COUNT(t.id) AS jumlah_terjual, SUM(t.harga_rumah) AS total_pendapatan " +
                       "FROM transaksi t " +
                       "JOIN tipe_rumah tr ON t.tipe_rumah_id = tr.id " +
                       "GROUP BY tr.tipe";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Tambahkan data ke tabel
        while (resultSet.next()) {
            String tipeRumah = resultSet.getString("tipe");
            int jumlahTerjual = resultSet.getInt("jumlah_terjual");
            double totalPendapatan = resultSet.getDouble("total_pendapatan");

            tableModel.addRow(new Object[]{tipeRumah, jumlahTerjual, totalPendapatan});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data: " + e.getMessage());
    }
}
    

    // Method untuk memuat data tipe rumah yang terjual
    private void loadTipeRumahTerjualData(DefaultTableModel tableModel) {
        // Implementasi query untuk memuat data tipe rumah yang terjual
        // Contoh: SELECT tipe_rumah, COUNT(*) AS jumlah_terjual, SUM(harga_rumah) AS total_pendapatan FROM transaksi GROUP BY tipe_rumah
    }

    // Method untuk menghitung keuntungan kotor
    private void hitungKeuntunganKotor(JLabel keuntunganLabel) {
        // Implementasi query untuk menghitung total keuntungan kotor
        // Contoh: SELECT SUM(harga_rumah) AS total_keuntungan FROM transaksi
    }

    public static void main(String[] args) {
        new LaporanPage();
    }
}