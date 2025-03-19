import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipeRumahPage extends JFrame {
    private JTextField idField, tipeField, hargaPokokField, luasBangunanField, luasTanahField, kamarTidurField, deskripsiField, kamarMandiField, lantaiField, listrikField, sumberAirField, hargaRumahField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTextArea displayArea;

    public TipeRumahPage() {
        setTitle("Tipe Rumah");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel idLabel = new JLabel("ID Tipe Rumah:");
        idLabel.setBounds(30, 30, 120, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(160, 30, 200, 25);
        panel.add(idField);

        JLabel tipeLabel = new JLabel("Tipe:");
        tipeLabel.setBounds(30, 70, 120, 25);
        panel.add(tipeLabel);

        tipeField = new JTextField();
        tipeField.setBounds(160, 70, 200, 25);
        panel.add(tipeField);

        JLabel hargaPokokLabel = new JLabel("Harga Pokok:");
        hargaPokokLabel.setBounds(30, 110, 120, 25);
        panel.add(hargaPokokLabel);

        hargaPokokField = new JTextField();
        hargaPokokField.setBounds(160, 110, 200, 25);
        panel.add(hargaPokokField);

        JLabel luasBangunanLabel = new JLabel("Luas Bangunan:");
        luasBangunanLabel.setBounds(30, 150, 120, 25);
        panel.add(luasBangunanLabel);

        luasBangunanField = new JTextField();
        luasBangunanField.setBounds(160, 150, 200, 25);
        panel.add(luasBangunanField);

        JLabel luasTanahLabel = new JLabel("Luas Tanah:");
        luasTanahLabel.setBounds(30, 190, 120, 25);
        panel.add(luasTanahLabel);

        luasTanahField = new JTextField();
        luasTanahField.setBounds(160, 190, 200, 25);
        panel.add(luasTanahField);

        JLabel kamarTidurLabel = new JLabel("Kamar Tidur:");
        kamarTidurLabel.setBounds(30, 230, 120, 25);
        panel.add(kamarTidurLabel);

        kamarTidurField = new JTextField();
        kamarTidurField.setBounds(160, 230, 200, 25);
        panel.add(kamarTidurField);

        JLabel deskripsiLabel = new JLabel("Deskripsi:");
        deskripsiLabel.setBounds(30, 270, 120, 25);
        panel.add(deskripsiLabel);

        deskripsiField = new JTextField();
        deskripsiField.setBounds(160, 270, 200, 25);
        panel.add(deskripsiField);

        JLabel kamarMandiLabel = new JLabel("Kamar Mandi:");
        kamarMandiLabel.setBounds(30, 310, 120, 25);
        panel.add(kamarMandiLabel);

        kamarMandiField = new JTextField();
        kamarMandiField.setBounds(160, 310, 200, 25);
        panel.add(kamarMandiField);

        JLabel lantaiLabel = new JLabel("Lantai:");
        lantaiLabel.setBounds(30, 350, 120, 25);
        panel.add(lantaiLabel);

        lantaiField = new JTextField();
        lantaiField.setBounds(160, 350, 200, 25);
        panel.add(lantaiField);

        JLabel listrikLabel = new JLabel("Listrik:");
        listrikLabel.setBounds(30, 390, 120, 25);
        panel.add(listrikLabel);

        listrikField = new JTextField();
        listrikField.setBounds(160, 390, 200, 25);
        panel.add(listrikField);

        JLabel sumberAirLabel = new JLabel("Sumber Air:");
        sumberAirLabel.setBounds(30, 430, 120, 25);
        panel.add(sumberAirLabel);

        sumberAirField = new JTextField();
        sumberAirField.setBounds(160, 430, 200, 25);
        panel.add(sumberAirField);

        JLabel hargaRumahLabel = new JLabel("Harga Rumah:");
        hargaRumahLabel.setBounds(30, 470, 120, 25);
        panel.add(hargaRumahLabel);

        hargaRumahField = new JTextField();
        hargaRumahField.setBounds(160, 470, 200, 25);
        panel.add(hargaRumahField);

        addButton = new JButton("Tambah");
        addButton.setBounds(30, 510, 100, 25);
        panel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(140, 510, 100, 25);
        panel.add(updateButton);

        deleteButton = new JButton("Hapus");
        deleteButton.setBounds(250, 510, 100, 25);
        panel.add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(360, 510, 100, 25);
        panel.add(clearButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 550, 730, 100);
        panel.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTipeRumah();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTipeRumah();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusTipeRumah();
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

    private void tambahTipeRumah() {
        String tipe = tipeField.getText();
        double hargaPokok = Double.parseDouble(hargaPokokField.getText());
        double luasBangunan = Double.parseDouble(luasBangunanField.getText());
        double luasTanah = Double.parseDouble(luasTanahField.getText());
        int kamarTidur = Integer.parseInt(kamarTidurField.getText());
        String deskripsi = deskripsiField.getText();
        int kamarMandi = Integer.parseInt(kamarMandiField.getText());
        int lantai = Integer.parseInt(lantaiField.getText());
        String listrik = listrikField.getText();
        String sumberAir = sumberAirField.getText();
        double hargaRumah = Double.parseDouble(hargaRumahField.getText());

        String query = "INSERT INTO tipe_rumah (tipe, harga_pokok, luas_bangunan, luas_tanah, kamar_tidur, deskripsi, kamar_mandi, lantai, listrik, sumber_air, harga_rumah) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tipe);
            preparedStatement.setDouble(2, hargaPokok);
            preparedStatement.setDouble(3, luasBangunan);
            preparedStatement.setDouble(4, luasTanah);
            preparedStatement.setInt(5, kamarTidur);
            preparedStatement.setString(6, deskripsi);
            preparedStatement.setInt(7, kamarMandi);
            preparedStatement.setInt(8, lantai);
            preparedStatement.setString(9, listrik);
            preparedStatement.setString(10, sumberAir);
            preparedStatement.setDouble(11, hargaRumah);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTipeRumah() {
        int id = Integer.parseInt(idField.getText());
        String tipe = tipeField.getText();
        double hargaPokok = Double.parseDouble(hargaPokokField.getText());
        double luasBangunan = Double.parseDouble(luasBangunanField.getText());
        double luasTanah = Double.parseDouble(luasTanahField.getText());
        int kamarTidur = Integer.parseInt(kamarTidurField.getText());
        String deskripsi = deskripsiField.getText();
        int kamarMandi = Integer.parseInt(kamarMandiField.getText());
        int lantai = Integer.parseInt(lantaiField.getText());
        String listrik = listrikField.getText();
        String sumberAir = sumberAirField.getText();
        double hargaRumah = Double.parseDouble(hargaRumahField.getText());

        String query = "UPDATE tipe_rumah SET tipe = ?, harga_pokok = ?, luas_bangunan = ?, luas_tanah = ?, kamar_tidur = ?, deskripsi = ?, kamar_mandi = ?, lantai = ?, listrik = ?, sumber_air = ?, harga_rumah = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tipe);
            preparedStatement.setDouble(2, hargaPokok);
            preparedStatement.setDouble(3, luasBangunan);
            preparedStatement.setDouble(4, luasTanah);
            preparedStatement.setInt(5, kamarTidur);
            preparedStatement.setString(6, deskripsi);
            preparedStatement.setInt(7, kamarMandi);
            preparedStatement.setInt(8, lantai);
            preparedStatement.setString(9, listrik);
            preparedStatement.setString(10, sumberAir);
            preparedStatement.setDouble(11, hargaRumah);
            preparedStatement.setInt(12, id);
            preparedStatement.executeUpdate();
            refreshDisplay();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusTipeRumah() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM tipe_rumah WHERE id = ?";
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
        hargaPokokField.setText("");
        luasBangunanField.setText("");
        luasTanahField.setText("");
        kamarTidurField.setText("");
        deskripsiField.setText("");
        kamarMandiField.setText("");
        lantaiField.setText("");
        listrikField.setText("");
        sumberAirField.setText("");
        hargaRumahField.setText("");
    }

    private void refreshDisplay() {
        displayArea.setText("");
        String query = "SELECT * FROM tipe_rumah";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tipe = resultSet.getString("tipe");
                double hargaPokok = resultSet.getDouble("harga_pokok");
                double luasBangunan = resultSet.getDouble("luas_bangunan");
                double luasTanah = resultSet.getDouble("luas_tanah");
                int kamarTidur = resultSet.getInt("kamar_tidur");
                String deskripsi = resultSet.getString("deskripsi");
                int kamarMandi = resultSet.getInt("kamar_mandi");
                int lantai = resultSet.getInt("lantai");
                String listrik = resultSet.getString("listrik");
                String sumberAir = resultSet.getString("sumber_air");
                double hargaRumah = resultSet.getDouble("harga_rumah");
                displayArea.append("ID: " + id + ", Tipe: " + tipe + ", Harga Pokok: " + hargaPokok + ", Luas Bangunan: " + luasBangunan + ", Luas Tanah: " + luasTanah + ", Kamar Tidur: " + kamarTidur + ", Deskripsi: " + deskripsi + ", Kamar Mandi: " + kamarMandi + ", Lantai: " + lantai + ", Listrik: " + listrik + ", Sumber Air: " + sumberAir + ", Harga Rumah: " + hargaRumah + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TipeRumahPage();
    }
}