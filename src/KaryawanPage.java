import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KaryawanPage extends JFrame {
    private JTextField idField, namaField, jabatanField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTextArea displayArea;

    public KaryawanPage() {
        setTitle("Karyawan");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel idLabel = new JLabel("ID Karyawan:");
        idLabel.setBounds(30, 30, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 30, 200, 25);
        panel.add(idField);

        JLabel namaLabel = new JLabel("Nama Karyawan:");
        namaLabel.setBounds(30, 70, 100, 25);
        panel.add(namaLabel);

        namaField = new JTextField();
        namaField.setBounds(140, 70, 200, 25);
        panel.add(namaField);

        JLabel jabatanLabel = new JLabel("Jabatan:");
        jabatanLabel.setBounds(30, 110, 100, 25);
        panel.add(jabatanLabel);

        jabatanField = new JTextField();
        jabatanField.setBounds(140, 110, 200, 25);
        panel.add(jabatanField);

        addButton = new JButton("Tambah");
        addButton.setBounds(30, 150, 100, 25);
        panel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(140, 150, 100, 25);
        panel.add(updateButton);

        deleteButton = new JButton("Hapus");
        deleteButton.setBounds(250, 150, 100, 25);
        panel.add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(360, 150, 100, 25);
        panel.add(clearButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 190, 430, 150);
        panel.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahKaryawan();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateKaryawan();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusKaryawan();
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

    private void tambahKaryawan() {
        String nama = namaField.getText();
        String jabatan = jabatanField.getText();

        String query = "INSERT INTO karyawan (nama, jabatan) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, jabatan);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateKaryawan() {
        int id = Integer.parseInt(idField.getText());
        String nama = namaField.getText();
        String jabatan = jabatanField.getText();

        String query = "UPDATE karyawan SET nama = ?, jabatan = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, jabatan);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusKaryawan() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM karyawan WHERE id = ?";
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
        namaField.setText("");
        jabatanField.setText("");
    }

    private void refreshDisplay() {
        displayArea.setText("");
        String query = "SELECT * FROM karyawan";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String jabatan = resultSet.getString("jabatan");
                displayArea.append("ID: " + id + ", Nama: " + nama + ", Jabatan: " + jabatan + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new KaryawanPage();
    }
}