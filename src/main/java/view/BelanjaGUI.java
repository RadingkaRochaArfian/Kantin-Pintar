package view;

//to do:  apply logic in daftar menu
import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.util.*;
import model.*;

public class BelanjaGUI extends JFrame {
  private JTabbedPane tab;
  private List<Item> listMenu;
  private List<CartItem> listKeranjang;
  private List<HistoryItem> listRiwayat;
  private int width;
  private int height;

  public BelanjaGUI(int w, int h) {
    width = w;
    height = h;
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
    setLayout(null);
    setComponentsLogic();
  }

  private void setComponentsLogic() {
    addComponentListener(new ComponentListener() {
      public void componentResized(ComponentEvent e) {
        width = getWidth();
        height = getHeight();
        setComponentsSize();
      }

      public void componentShown(ComponentEvent e) {
      }

      public void componentMoved(ComponentEvent e) {
      }

      public void componentHidden(ComponentEvent e) {
      }
    });
  }

  private void setComponentsSize() {
    tab.setBounds(0, 0, width - 50, height - 100);
  }

  private void initComponents() {
    listMenu = new ArrayList<>();
    listMenu.add(new Makanan("Ayam Geprek", 10000, 20));
    listMenu.add(new Minuman("Teh Jeruk", 4000, 15));
    tab = new JTabbedPane();
    JPanel menuPanel = initMenuPanel();
    tab.addTab("Daftar Menu", menuPanel);
    JPanel keranjangPanel = initKeranjangPanel();
    tab.addTab("Keranjang", keranjangPanel);
    JPanel transaksiPanel = initTransaksiPanel();
    tab.addTab("Transaksi", transaksiPanel);
    JPanel riwayatPanel = initRiwayatPanel();
    tab.addTab("Riwayat", riwayatPanel);
    tab.setBounds(0, 0, width - 50, height - 100);
    add(tab);
  }

  private JPanel initRiwayatPanel() {
    JPanel rp = new JPanel();
    rp.setLayout(null);
    JButton bRefresh = new JButton("Refresh");
    DefaultTableModel tModel = new DefaultTableModel(new String[] { "Waktu", "Total (Rp)", "Jumlah Item" }, 0);
    JTable tRiwayat = new JTable(tModel);
    rp.add(bRefresh);
    rp.add(tRiwayat);
    return rp;
  }

  private JPanel initTransaksiPanel() {
    JPanel tp = new JPanel();
    tp.setLayout(null);
    JLabel lBayar = new JLabel("Jumlah Bayar (Rp):");
    JLabel lKembali = new JLabel("Kembalian (Rp): -");
    JTextField tfInput = new JTextField();
    JButton bBayar = new JButton("Proses Pembayaran");
    tp.add(lBayar);
    tp.add(lKembali);
    tp.add(tfInput);
    tp.add(bBayar);
    return tp;
  }

  private JPanel initKeranjangPanel() {
    listKeranjang = new ArrayList<>();
    JPanel kp = new JPanel();
    kp.setLayout(null);
    JLabel lTotal = new JLabel("Total: Rp-");
    JButton bHapusItem = new JButton("Hapus Item");
    JButton bKosongkan = new JButton("Kosongkan");
    DefaultTableModel tModel = new DefaultTableModel(
        new String[] { "Nama", "Kategori", "Harga Satuan", "Qty", "Subtotal" }, 0);
    JTable tKeranjang = new JTable(tModel);
    kp.add(lTotal);
    kp.add(bHapusItem);
    kp.add(bKosongkan);
    kp.add(tKeranjang);
    return kp;
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
    mp.add(lCari);
    mp.add(tfCari);
    mp.add(bHargaMenurun);
    mp.add(bHargaMenaik);
    mp.add(bNamaMenurun);
    mp.add(bNamaMenaik);
    mp.add(lqty);
    mp.add(sqty);
    mp.add(tMenu);
    return mp;
  }
}
