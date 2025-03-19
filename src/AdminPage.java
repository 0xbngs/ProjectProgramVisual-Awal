import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu masterMenu = new JMenu("Master");
        JMenu transaksiMenu = new JMenu("Transaksi");
        JMenu laporanMenu = new JMenu("Laporan");

        menuBar.add(masterMenu);
        menuBar.add(transaksiMenu);
        menuBar.add(laporanMenu);

        JMenuItem karyawanItem = new JMenuItem("Karyawan");
        JMenuItem jabatanItem = new JMenuItem("Jabatan");
        JMenuItem clientItem = new JMenuItem("Client");
        JMenuItem rabItem = new JMenuItem("RAB");
        JMenuItem tipeRumahItem = new JMenuItem("Tipe Rumah");

        masterMenu.add(karyawanItem);
        masterMenu.add(jabatanItem);
        masterMenu.add(clientItem);
        masterMenu.add(rabItem);
        masterMenu.add(tipeRumahItem);

        karyawanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KaryawanPage();
            }  
        });
        jabatanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JabatanPage(); // Membuka JabatanPage
            }
        });
        clientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientPage(); // Membuka ClientPage
            }
        });
        rabItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RABPage(); // Membuka RabPage
            }
        });
        tipeRumahItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TipeRumahPage(); // Membuka TiperumahPage
            }
        });

        setVisible(true);
    }
}