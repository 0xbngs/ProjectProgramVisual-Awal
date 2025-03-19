import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI extends JFrame {
    public DashboardUI() {
        setTitle("Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JButton masterButton = createMenuButton("Master");
        JButton transaksiButton = createMenuButton("Transaksi");
        JButton laporanButton = createMenuButton("Laporan");

        masterButton.addActionListener(e -> showMasterMenu());

        gbc.gridx = 0;
        mainPanel.add(masterButton, gbc);
        gbc.gridx = 1;
        mainPanel.add(transaksiButton, gbc);
        gbc.gridx = 2;
        mainPanel.add(laporanButton, gbc);

        add(mainPanel);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 150));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        button.setFocusPainted(false);
        return button;
    }

    private void showMasterMenu() {
        JPopupMenu masterMenu = new JPopupMenu();
        JMenuItem karyawanItem = new JMenuItem("Karyawan");
        JMenuItem jabatanItem = new JMenuItem("Jabatan");
        JMenuItem clientItem = new JMenuItem("Client");
        JMenuItem rabItem = new JMenuItem("RAB");
        JMenuItem tipeRumahItem = new JMenuItem("Tipe Rumah");

        karyawanItem.addActionListener(e -> new KaryawanFrame());
        jabatanItem.addActionListener(e -> new JabatanFrame());
        clientItem.addActionListener(e -> new ClientFrame());
        rabItem.addActionListener(e -> new RABFrame());
        tipeRumahItem.addActionListener(e -> new TipeRumahFrame());

        masterMenu.add(karyawanItem);
        masterMenu.add(jabatanItem);
        masterMenu.add(clientItem);
        masterMenu.add(rabItem);
        masterMenu.add(tipeRumahItem);

        masterMenu.show(this, 300, 200);
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(0, 0, Color.PINK, getWidth(), getHeight(), Color.ORANGE);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardUI().setVisible(true));
    }
}

// Contoh kelas CRUD untuk Karyawan
class KaryawanFrame extends JFrame {
    public KaryawanFrame() {
        setTitle("Karyawan");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class JabatanFrame extends JFrame {
    public JabatanFrame() {
        setTitle("Jabatan");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class ClientFrame extends JFrame {
    public ClientFrame() {
        setTitle("Client");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class RABFrame extends JFrame {
    public RABFrame() {
        setTitle("RAB");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class TipeRumahFrame extends JFrame {
    public TipeRumahFrame() {
        setTitle("Tipe Rumah");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}