import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JabatanPage extends JFrame {
    private JTextField idField, jabatanField, gajiPokokField, bonusField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTextArea displayArea;

    public JabatanPage() {
        setTitle("Jabatan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel idLabel = new JLabel("ID Jabatan:");
        idLabel.setBounds(30, 30, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 30, 200, 25);
        panel.add(idField);

        JLabel jabatanLabel = new JLabel("Jabatan:");
        jabatanLabel.setBounds(30, 70, 100, 25);
        panel.add(jabatanLabel);

        jabatanField = new JTextField();
        jabatanField.setBounds(140, 70, 200, 25);
        panel.add(jabatanField);

        JLabel gajiPokokLabel = new JLabel("Gaji Pokok:");
        gajiPokokLabel.setBounds(30, 110, 100, 25);
        panel.add(gajiPokokLabel);

        gajiPokokField = new JTextField();
        gajiPokokField.setBounds(140, 110, 200, 25);
        panel.add(gajiPokokField);

        JLabel bonusLabel = new JLabel("Bonus:");
        bonusLabel.setBounds(30, 150, 100, 25);
        panel.add(bonusLabel);

        bonusField = new JTextField();
        bonusField.setBounds(140, 150, 200, 25);
        panel.add(bonusField);

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
                tambahJabatan();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJabatan();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusJabatan();
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

    private void tambahJabatan() {
        String jabatan = jabatanField.getText();
        double gajiPokok = Double.parseDouble(gajiPokokField.getText());
        double bonus = Double.parseDouble(bonusField.getText());

        String query = "INSERT INTO jabatan (jabatan, gaji_pokok, bonus) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, jabatan);
            preparedStatement.setDouble(2, gajiPokok);
            preparedStatement.setDouble(3, bonus);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateJabatan() {
        int id = Integer.parseInt(idField.getText());
        String jabatan = jabatanField.getText();
        double gajiPokok = Double.parseDouble(gajiPokokField.getText());
        double bonus = Double.parseDouble(bonusField.getText());

        String query = "UPDATE jabatan SET jabatan = ?, gaji_pokok = ?, bonus = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, jabatan);
            preparedStatement.setDouble(2, gajiPokok);
            preparedStatement.setDouble(3, bonus);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusJabatan() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM jabatan WHERE id = ?";
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
        jabatanField.setText("");
        gajiPokokField.setText("");
        bonusField.setText("");
    }

    private void refreshDisplay() {
        displayArea.setText("");
        String query = "SELECT * FROM jabatan";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String jabatan = resultSet.getString("jabatan");
                double gajiPokok = resultSet.getDouble("gaji_pokok");
                double bonus = resultSet.getDouble("bonus");
                displayArea.append("ID: " + id + ", Jabatan: " + jabatan + ", Gaji Pokok: " + gajiPokok + ", Bonus: " + bonus + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JabatanPage();
    }
}