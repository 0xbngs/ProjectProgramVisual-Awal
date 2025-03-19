import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RABPage extends JFrame {
    private JTextField idField, tipeField, keteranganField, hargaField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTextArea displayArea;

    public RABPage() {
        setTitle("RAB");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel idLabel = new JLabel("ID RAB:");
        idLabel.setBounds(30, 30, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 30, 200, 25);
        panel.add(idField);

        JLabel tipeLabel = new JLabel("Tipe:");
        tipeLabel.setBounds(30, 70, 100, 25);
        panel.add(tipeLabel);

        tipeField = new JTextField();
        tipeField.setBounds(140, 70, 200, 25);
        panel.add(tipeField);

        JLabel keteranganLabel = new JLabel("Keterangan:");
        keteranganLabel.setBounds(30, 110, 100, 25);
        panel.add(keteranganLabel);

        keteranganField = new JTextField();
        keteranganField.setBounds(140, 110, 200, 25);
        panel.add(keteranganField);

        JLabel hargaLabel = new JLabel("Harga:");
        hargaLabel.setBounds(30, 150, 100, 25);
        panel.add(hargaLabel);

        hargaField = new JTextField();
        hargaField.setBounds(140, 150, 200, 25);
        panel.add(hargaField);

        addButton = new JButton("Tambah");
        addButton.setBounds(30, 190, 100, 25);
        panel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(140, 190, 100, 25);
        panel.add(updateButton);

        deleteButton = new JButton("Hapus");
        deleteButton.setBounds(250, 190, 100, 25);
        panel.add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(360, 190, 100, 25);
        panel.add(clearButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 230, 530, 200);
        panel.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahRAB();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRAB();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusRAB();
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

    private void tambahRAB() {
        String tipe = tipeField.getText();
        String keterangan = keteranganField.getText();
        double harga = Double.parseDouble(hargaField.getText());

        String query = "INSERT INTO rab (tipe, keterangan, harga) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tipe);
            preparedStatement.setString(2, keterangan);
            preparedStatement.setDouble(3, harga);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRAB() {
        int id = Integer.parseInt(idField.getText());
        String tipe = tipeField.getText();
        String keterangan = keteranganField.getText();
        double harga = Double.parseDouble(hargaField.getText());

        String query = "UPDATE rab SET tipe = ?, keterangan = ?, harga = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tipe);
            preparedStatement.setString(2, keterangan);
            preparedStatement.setDouble(3, harga);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusRAB() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM rab WHERE id = ?";
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
        tipeField.setText("");
        keteranganField.setText("");
        hargaField.setText("");
    }

    private void refreshDisplay() {
        displayArea.setText("");
        String query = "SELECT * FROM rab";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tipe = resultSet.getString("tipe");
                String keterangan = resultSet.getString("keterangan");
                double harga = resultSet.getDouble("harga");
                displayArea.append("ID: " + id + ", Tipe: " + tipe + ", Keterangan: " + keterangan + ", Harga: " + harga + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RABPage();
    }
}