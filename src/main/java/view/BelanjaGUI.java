package view;

//to do:  apply logic in daftar menu
import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.Component;
import java.awt.Font;
import java.util.*;
import model.*;

public class BelanjaGUI extends JFrame {
  private final int DEFAULT_WIDTH = 800;
  private final int DEFAULT_HEIGHT = 600;
  private JTabbedPane tab;
  private List<Item> listMenu;
  private List<CartItem> listKeranjang;
  private List<HistoryItem> listRiwayat;
  private int width;
  private int height;
  JPanel mp;
  JLabel lCari;
  JTextField tfCari;
  JButton bHargaMenurun;
  JButton bHargaMenaik;
  JButton bNamaMenurun;
  JButton bNamaMenaik;
  JLabel lqty;
  JSpinner sqty;
  JButton bTambah;
  DefaultTableModel tModelMenu;
  JTable tMenu;

  JPanel kp;
  JLabel lTotal;
  JButton bHapusItem;
  JButton bKosongkan;
  DefaultTableModel tModelKeranjang;
  JTable tKeranjang;

  JPanel tp;
  JLabel lBayar;
  JLabel lKembali;
  JTextField tfInput;
  JButton bBayar;

  JPanel rp;
  JButton bRefresh;
  DefaultTableModel tModelRiwayat;
  JTable tRiwayat;

  public BelanjaGUI(int w, int h) {
    width = w;
    height = h;
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
    setLayout(null);
    setComponentsLogic();
    setComponentsBounds();
  }

  private void setComponentsLogic() {
    addComponentListener(new ComponentListener() {
      public void componentResized(ComponentEvent e) {
        width = getWidth();
        height = getHeight();
        setComponentsBounds();
      }

      public void componentShown(ComponentEvent e) {
      }

      public void componentMoved(ComponentEvent e) {
      }

      public void componentHidden(ComponentEvent e) {
      }
    });
  }

  private void setComponentsBounds() {
    tab.setBounds(0, 0, width, height);
    setMenuBounds();
  }

  private void setMenuBounds() {
    int pHeight = mp.getHeight();
    int pWidth = mp.getWidth();
    int rAll = (int) Math.round((1.0 * pWidth / DEFAULT_WIDTH) * (1.0 * pHeight / DEFAULT_HEIGHT));
    int rHeight = (int) Math.round((1.0 * pHeight / DEFAULT_HEIGHT));
    int rWidth = (int) Math.round((1.0 * pWidth / DEFAULT_WIDTH));
    int lCariY = (int) Math.round(pHeight * (-30));
    lCari.setBounds(10, lCariY, pWidth / 8, pHeight / 6);
    lCari.setFont(new Font("Arial", Font.BOLD, (int) Math.round(rAll * 13)));
    tfCari.setBounds(130, -30, 100, 100);
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
    add(tab);
  }

  private JPanel initRiwayatPanel() {
    rp = new JPanel();
    rp.setLayout(null);
    bRefresh = new JButton("Refresh");
    tModelRiwayat = new DefaultTableModel(new String[] { "Waktu", "Total (Rp)", "Jumlah Item" }, 0);
    tRiwayat = new JTable(tModelRiwayat);
    rp.add(bRefresh);
    rp.add(tRiwayat);
    return rp;
  }

  private JPanel initTransaksiPanel() {
    tp = new JPanel();
    tp.setLayout(null);
    lBayar = new JLabel("Jumlah Bayar (Rp):");
    lKembali = new JLabel("Kembalian (Rp): -");
    tfInput = new JTextField();
    bBayar = new JButton("Proses Pembayaran");
    tp.add(lBayar);
    tp.add(lKembali);
    tp.add(tfInput);
    tp.add(bBayar);
    return tp;
  }

  private JPanel initKeranjangPanel() {
    listKeranjang = new ArrayList<>();
    kp = new JPanel();
    kp.setLayout(null);
    lTotal = new JLabel("Total: Rp-");
    bHapusItem = new JButton("Hapus Item");
    bKosongkan = new JButton("Kosongkan");
    tModelKeranjang = new DefaultTableModel(
        new String[] { "Nama", "Kategori", "Harga Satuan", "Qty", "Subtotal" }, 0);
    tKeranjang = new JTable(tModelKeranjang);
    kp.add(lTotal);
    kp.add(bHapusItem);
    kp.add(bKosongkan);
    kp.add(tKeranjang);
    return kp;
  }

  private JPanel initMenuPanel() {
    mp = new JPanel();
    mp.setLayout(null);
    lCari = new JLabel("Cari:");
    tfCari = new JTextField();
    bHargaMenurun = new JButton("Harga <Desc>");
    bHargaMenaik = new JButton("Harga <Asc>");
    bNamaMenurun = new JButton("Nama <Desc>");
    bNamaMenaik = new JButton("Nama <Asc>");
    lqty = new JLabel("Qty:");
    sqty = new JSpinner();
    bTambah = new JButton("Tambah ke Keranjang");
    tModelMenu = new DefaultTableModel(new String[] { "Nama", "Kategori", "Harga", "Stok" }, 0);
    tMenu = new JTable(tModelMenu);
    mp.add(lCari);
    mp.add(tfCari);
    mp.add(bHargaMenurun);
    mp.add(bHargaMenaik);
    mp.add(bNamaMenurun);
    mp.add(bNamaMenaik);
    mp.add(lqty);
    mp.add(sqty);
    mp.add(bTambah);
    mp.add(tMenu);
    return mp;
  }
}
