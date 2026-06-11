package view;

//to do:  apply logic in daftar menu
import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
  JScrollPane spMenu;

  JPanel kp;
  JLabel lTotal;
  JButton bHapusItem;
  JButton bKosongkan;
  DefaultTableModel tModelKeranjang;
  JTable tKeranjang;
  JScrollPane spKeranjang;

  JPanel tp;
  JLabel lBayar;
  JLabel lKembali;
  JLabel lKembaliVal;
  JTextField tfInput;
  JButton bBayar;

  JPanel rp;
  JButton bRefresh;
  DefaultTableModel tModelRiwayat;
  JTable tRiwayat;
  JScrollPane spRiwayat;

  public BelanjaGUI(int w, int h) {
    width = w;
    height = h;
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
    initComponents();
    setComponentsBounds();
  }



  private void setComponentsBounds() {
    add(tab,BorderLayout.CENTER);
    setMenuBounds();
    setKerangjangBounds();
    setTransaksiBounds();
    setRiwayatBounds();
  }
  private void setRiwayatBounds(){
    rp.setLayout(new BorderLayout(10,10));
    JPanel pBottom=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
    rp.add(pBottom,BorderLayout.SOUTH);
    pBottom.add(bRefresh);
    rp.add(spRiwayat);
    
  }
  private void setTransaksiBounds(){
    tp.setLayout(new GridBagLayout());
    GridBagConstraints gbc=new GridBagConstraints();
    gbc.insets=new Insets(10,10,10,10);
    gbc.fill=GridBagConstraints.HORIZONTAL;
    gbc.gridx=0;gbc.gridy=0;
    tp.add(lBayar,gbc);
    gbc.gridx=1;gbc.gridy=0;
    tp.add(tfInput,gbc);
    gbc.gridx=0;gbc.gridy=1;
    tp.add(lKembali,gbc);
    gbc.gridx=1;gbc.gridy=1;
    tp.add(lKembaliVal,gbc);
    gbc.gridwidth=2;
    gbc.gridx=0;gbc.gridy=2;
    tp.add(bBayar,gbc);
    
  }
  private void setKerangjangBounds(){
    kp.setLayout(new BorderLayout(10,10));
    kp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    JPanel pBottom=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
    kp.add(pBottom,BorderLayout.SOUTH);
    pBottom.add(lTotal);
    pBottom.add(bHapusItem);
    pBottom.add(bKosongkan);
    kp.add(spKeranjang);
    
  }

  private void setMenuBounds() {
    mp.setLayout(new BorderLayout(10,10));
    mp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    JPanel pUpper =new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    mp.add(pUpper,BorderLayout.NORTH);
    pUpper.add(lCari);
    pUpper.add(tfCari);
    pUpper.add(bHargaMenurun);
    pUpper.add(bHargaMenaik);
    pUpper.add(bNamaMenurun);
    pUpper.add(bNamaMenaik);
    JPanel pBottom =new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
    mp.add(pBottom,BorderLayout.SOUTH);
    pBottom.add(lqty);
    pBottom.add(sqty);
    pBottom.add(bTambah);
    mp.add(spMenu,BorderLayout.CENTER);
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
    bRefresh = new JButton("Refresh");
    tModelRiwayat = new DefaultTableModel(new String[] { "Waktu", "Total (Rp)", "Jumlah Item" }, 0);
    tRiwayat = new JTable(tModelRiwayat);
    spRiwayat=new JScrollPane(tRiwayat);
    return rp;
  }

  private JPanel initTransaksiPanel() {
    tp = new JPanel();
    lBayar = new JLabel("Jumlah Bayar (Rp):");
    lKembali = new JLabel("Kembalian (Rp):");
    lKembaliVal=new JLabel("-");
    tfInput = new JTextField(15);
    bBayar = new JButton("Proses Pembayaran");
    bBayar.setBackground(new Color(50,200,40));
    bBayar.setForeground(Color.WHITE);
    return tp;
  }

  private JPanel initKeranjangPanel() {
    listKeranjang = new ArrayList<>();
    kp = new JPanel();
    lTotal = new JLabel("Total: Rp-");
    bHapusItem = new JButton("Hapus Item");
    bKosongkan = new JButton("Kosongkan");
    tModelKeranjang = new DefaultTableModel(
        new String[] { "Nama", "Kategori", "Harga Satuan", "Qty", "Subtotal" }, 0);
    tKeranjang = new JTable(tModelKeranjang);
    spKeranjang=new JScrollPane(tKeranjang);
    return kp;
  }

  private JPanel initMenuPanel() {
    mp = new JPanel();
    lCari = new JLabel("Cari:");
    tfCari = new JTextField(15);
    bHargaMenurun = new JButton("Harga <Desc>");
    bHargaMenaik = new JButton("Harga <Asc>");
    bNamaMenurun = new JButton("Nama <Desc>");
    bNamaMenaik = new JButton("Nama <Asc>");
    lqty = new JLabel("Qty:");
    sqty = new JSpinner(new SpinnerNumberModel(1,1,100,1));
    bTambah = new JButton("Tambah ke Keranjang");
    tModelMenu = new DefaultTableModel(new String[] { "Nama", "Kategori", "Harga", "Stok" }, 0);
    tMenu = new JTable(tModelMenu);
    spMenu=new JScrollPane(tMenu);
    return mp;
  }
}
