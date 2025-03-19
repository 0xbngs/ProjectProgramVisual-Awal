import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientPage extends JFrame {
    private JTextField idField, nikField, namaField, nokkField, alamatField, npwpField, asuransiField, gajiField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTextArea displayArea;

    public ClientPage() {
        setTitle("Client");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel idLabel = new JLabel("ID Client:");
        idLabel.setBounds(30, 30, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 30, 200, 25);
        panel.add(idField);

        JLabel nikLabel = new JLabel("NIK:");
        nikLabel.setBounds(30, 70, 100, 25);
        panel.add(nikLabel);

        nikField = new JTextField();
        nikField.setBounds(140, 70, 200, 25);
        panel.add(nikField);

        JLabel namaLabel = new JLabel("Nama:");
        namaLabel.setBounds(30, 110, 100, 25);
        panel.add(namaLabel);

        namaField = new JTextField();
        namaField.setBounds(140, 110, 200, 25);
        panel.add(namaField);

        JLabel nokkLabel = new JLabel("No KK:");
        nokkLabel.setBounds(30, 150, 100, 25);
        panel.add(nokkLabel);

        nokkField = new JTextField();
        nokkField.setBounds(140, 150, 200, 25);
        panel.add(nokkField);

        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setBounds(30, 190, 100, 25);
        panel.add(alamatLabel);

        alamatField = new JTextField();
        alamatField.setBounds(140, 190, 200, 25);
        panel.add(alamatField);

        JLabel npwpLabel = new JLabel("NPWP:");
        npwpLabel.setBounds(30, 230, 100, 25);
        panel.add(npwpLabel);

        npwpField = new JTextField();
        npwpField.setBounds(140, 230, 200, 25);
        panel.add(npwpField);

        JLabel asuransiLabel = new JLabel("Asuransi:");
        asuransiLabel.setBounds(30, 270, 100, 25);
        panel.add(asuransiLabel);

        asuransiField = new JTextField();
        asuransiField.setBounds(140, 270, 200, 25);
        panel.add(asuransiField);

        JLabel gajiLabel = new JLabel("Gaji:");
        gajiLabel.setBounds(30, 310, 100, 25);
        panel.add(gajiLabel);

        gajiField = new JTextField();
        gajiField.setBounds(140, 310, 200, 25);
        panel.add(gajiField);

        addButton = new JButton("Tambah");
        addButton.setBounds(30, 350, 100, 25);
        panel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(140, 350, 100, 25);
        panel.add(updateButton);

        deleteButton = new JButton("Hapus");
        deleteButton.setBounds(250, 350, 100, 25);
        panel.add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(360, 350, 100, 25);
        panel.add(clearButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 390, 630, 150);
        panel.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahClient();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClient();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusClient();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        refreshDisplay();

        setVisible(true);
    }

    private void tambahClient() {
        String nik = nikField.getText();
        String nama = namaField.getText();
        String nokk = nokkField.getText();
        String alamat = alamatField.getText();
        String npwp = npwpField.getText();
        String asuransi = asuransiField.getText();
        double gaji = Double.parseDouble(gajiField.getText());

        String query = "INSERT INTO client (nik, nama, nokk, alamat, npwp, asuransi, gaji) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, nokk);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, npwp);
            preparedStatement.setString(6, asuransi);
            preparedStatement.setDouble(7, gaji);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateClient() {
        int id = Integer.parseInt(idField.getText());
        String nik = nikField.getText();
        String nama = namaField.getText();
        String nokk = nokkField.getText();
        String alamat = alamatField.getText();
        String npwp = npwpField.getText();
        String asuransi = asuransiField.getText();
        double gaji = Double.parseDouble(gajiField.getText());

        String query = "UPDATE client SET nik = ?, nama = ?, nokk = ?, alamat = ?, npwp = ?, asuransi = ?, gaji = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nik);
            preparedStatement.setString(2, nama);
            preparedStatement.setString(3, nokk);
            preparedStatement.setString(4, alamat);
            preparedStatement.setString(5, npwp);
            preparedStatement.setString(6, asuransi);
            preparedStatement.setDouble(7, gaji);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusClient() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM client WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.setText("");
        nikField.setText("");
        namaField.setText("");
        nokkField.setText("");
        alamatField.setText("");
        npwpField.setText("");
        asuransiField.setText("");
        gajiField.setText("");
    }

    private void refreshDisplay() {
        displayArea.setText("");
        String query = "SELECT * FROM client";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nik = resultSet.getString("nik");
                String nama = resultSet.getString("nama");
                String nokk = resultSet.getString("nokk");
                String alamat = resultSet.getString("alamat");
                String npwp = resultSet.getString("npwp");
                String asuransi = resultSet.getString("asuransi");
                double gaji = resultSet.getDouble("gaji");
                displayArea.append("ID: " + id + ", NIK: " + nik + ", Nama: " + nama + ", No KK: " + nokk + ", Alamat: " + alamat + ", NPWP: " + npwp + ", Asuransi: " + asuransi + ", Gaji: " + gaji + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientPage();
    }
}