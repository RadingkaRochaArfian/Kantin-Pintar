package view;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.*;

public class BelanjaGUI extends JFrame {
  private JTabbedPane tab;
  private List<Item> listMenu;

  public BelanjaGUI() {
    setSize(900, 600);
    setResizable(false);
    initComponents();
    setLayout(null);
  }

  private void initComponents() {
    listMenu.add(new Makanan("Ayam Geprek", 10000, 20));
    listMenu.add(new Minuman("Teh Jeruk", 4000, 15));
    tab = new JTabbedPane();
    JPanel menuPanel = initMenuPanel();
    tab.addTab("Daftar Menu", menuPanel);

  }

  private JPanel initMenuPanel() {
    JPanel mp = new JPanel();
    mp.setLayout(null);
    JLabel lCari = new JLabel("Cari:");
    JTextField tfCari = new JTextField();
    JButton bHargaMenurun = new JButton("Harga <Desc>");
    JButton bHargaMenaik = new JButton("Harga <Asc>");
    JButton bNamaMenurun = new JButton("Nama <Desc>");
    JButton bNamaMenaik = new JButton("Nama <Asc>");
    JLabel lqty = new JLabel("Qty:");
    JSpinner sqty = new JSpinner();
    DefaultTableModel tModel = new DefaultTableModel(new String[] { "Nama", "Kategori", "Harga", "Stok" }, 0);
    JTable tMenu = new JTable(tModel);
    JComboBox<String> cbPilihan = new JComboBox<>();
    for (Item t : listMenu) {
      cbPilihan.addItem(t.getNama());
    }
    return mp;
  }
}
