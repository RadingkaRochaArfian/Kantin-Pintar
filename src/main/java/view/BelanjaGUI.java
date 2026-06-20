package view;

import java.nio.charset.StandardCharsets;
//to do:  apply logic in daftar menu
import java.nio.file.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.*;
import model.*;

public class BelanjaGUI extends JFrame {
  private final int DEFAULT_WIDTH = 800;
  private final int DEFAULT_HEIGHT = 600;
  private final String MENU_FILE_PATH = "data/Menu.csv";
  private final String CART_FILE_PATH = "data/Cart.csv";
  private final String STRUCT_FILE_PATH = "data/Struct.txt";
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
  JLabel lQty;
  JSpinner sQty;
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
    setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    initComponents();
    setComponentsBounds();
    setComponentsLogic();
  }

  private void setComponentsLogic() {
    setMenuLogic();
    setKeranjangLogic();
  }

  private void setKeranjangLogic() {
    bHapusItem.addActionListener(e -> {
      int selRow = tKeranjang.getSelectedRow();
      if (selRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please choose an Item fom the cart table first!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
      int modelRow = tKeranjang.convertColumnIndexToModel(selRow);
      CartItem selCartItem = listKeranjang.get(modelRow);
      for (int i = 0; i < listKeranjang.size(); i++) {
        if (listKeranjang.get(i).getItem().getId().equals(selCartItem.getItem().getId())) {
          listKeranjang.remove(i);
          break;
        }
      }
      for (Item i : listMenu) {
        if (i.getId().equals(selCartItem.getItem().getId())) {
          i.setStok(i.getStok() + selCartItem.getQuantity());
          break;
        }
      }
      refreshCartFilePath(selCartItem);
      refreshTKeranjang();
      refreshMenuFilePath();
      refreshTMenu();
      JOptionPane.showMessageDialog(
          this,
          selCartItem.getQuantity() + " " + selCartItem.getItem().getNama() + " is successfully removed!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    });
    bKosongkan.addActionListener(e -> {
      for (CartItem ci : listKeranjang) {
        for (Item i : listMenu) {
          if (i.getId().equals(ci.getItem().getId())) {
            i.setStok(i.getStok() + ci.getQuantity());
            break;
          }
        }
      }
      listKeranjang.clear();
      refreshCartFilePath();
      refreshTKeranjang();
      refreshMenuFilePath();
      refreshTMenu();
      JOptionPane.showMessageDialog(
          this,
          "Successfully clear cart!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    });
  }

  private void refreshCartFilePath() {
    refreshCartFilePath(null);
  }

  private void setMenuLogic() {
    bHargaMenurun.addActionListener(e -> {
      sortMenu("harga", false);
    });
    bHargaMenaik.addActionListener(e -> {
      sortMenu("harga", true);
    });
    bNamaMenurun.addActionListener(e -> {
      sortMenu("nama", false);
    });
    bNamaMenaik.addActionListener(e -> {
      sortMenu("nama", true);
    });
    tfCari.getDocument().addDocumentListener(new DocumentListener() {
      public void insertUpdate(DocumentEvent e) {
        refreshTMenu();
      }

      public void removeUpdate(DocumentEvent e) {
        refreshTMenu();
      }

      public void changedUpdate(DocumentEvent e) {
        refreshTMenu();
      }
    });
    bTambah.addActionListener(e -> {
      int selRow = tMenu.getSelectedRow();
      if (selRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please choose an Item from the menu table first!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
      int modelRow = tMenu.convertColumnIndexToModel(selRow);
      Item selItem = listMenu.get(modelRow);
      int qty = (int) sQty.getValue();
      if (selItem.getStok() <= 0) {
        JOptionPane.showMessageDialog(
            this,
            "We are sorry! " + selItem.getNama() + " is out of stock!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (qty > selItem.getStok()) {
        JOptionPane.showMessageDialog(
            this,
            "We are sorry! " + selItem.getNama() + " doesn't have enough stock!",
            "Error",
            JOptionPane.ERROR_MESSAGE);
      }
      selItem.setStok(selItem.getStok() - qty);
      refreshTMenu();
      boolean itemFlag = false;
      for (CartItem ci : listKeranjang) {
        if (ci.getItem().getId().equals(selItem.getId())) {
          ci.setQuantity(ci.getQuantity() + qty);
          itemFlag = true;
          break;
        }
      }
      if (!itemFlag) {
        listKeranjang.add(new CartItem(selItem, qty));
      }
      refreshTKeranjang();
      refreshMenuFilePath();
      refreshCartFilePath();
      JOptionPane.showMessageDialog(
          this,
          qty + " " + selItem.getNama() + " is successfully added to cart!",
          "Success",
          JOptionPane.INFORMATION_MESSAGE);
    });
  }

  private void refreshCartFilePath(CartItem exCI) {
    try {
      List<String> lines = new ArrayList<>();
      for (CartItem ci : listKeranjang) {
        if (exCI != null && ci.getItem().getId().equals(exCI.getItem().getId()))
          continue;
        lines.add(
            ci.getItem().getId() + "," +
                String.valueOf(ci.getQuantity()));
      }
      Files.write(
          Paths.get(CART_FILE_PATH),
          lines,
          StandardCharsets.UTF_8,
          StandardOpenOption.TRUNCATE_EXISTING);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this,
          "Failed to save cart file.",
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void refreshMenuFilePath() {
    try {
      List<String> lines = new ArrayList<>();
      for (Item i : listMenu) {
        String category = i instanceof Makanan ? "Makanan" : "Minuman";
        lines.add(
            i.getNama() + "," +
                i.getHarga() + "," +
                i.getStok() + "," +
                category);
      }
      Files.write(
          Paths.get(MENU_FILE_PATH),
          lines,
          StandardCharsets.UTF_8,
          StandardOpenOption.TRUNCATE_EXISTING);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this,
          "Failed to save menu file.",
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void refreshTKeranjang() {
    tModelKeranjang.setRowCount(0);
    int total = 0;
    for (CartItem ci : listKeranjang) {
      int subTotal = ci.getItem().getHarga() * ci.getQuantity();
      total += subTotal;
      tModelKeranjang.addRow(new Object[] {
          ci.getItem().getNama(),
          ci.getItem().getKategori(),
          ci.getItem().getHarga(),
          ci.getQuantity(),
          subTotal
      });
    }
    lTotal.setText("Total: Rp" + total);
  }

  private void sortMenu(String kategori, boolean ascending) {
    listMenu.sort((item1, item2) -> {
      int comparison = 0;
      if (kategori.equalsIgnoreCase("nama")) {
        comparison = item1.getNama().compareToIgnoreCase(item2.getNama());
      } else if (kategori.equalsIgnoreCase("harga")) {
        comparison = Integer.compare(item1.getHarga(), item2.getHarga());
      }
      return ascending ? comparison : -comparison;
    });
    refreshTMenu();
  }

  private void refreshTMenu() {
    String keyword = tfCari.getText().toLowerCase().trim();
    tModelMenu.setRowCount(0);
    if (keyword.isEmpty()) {
      for (Item i : listMenu) {
        tModelMenu.addRow(new Object[] {
            i.getNama(),
            i.getKategori(),
            i.getHarga(),
            i.getStok()
        });
      }
      return;
    }
    for (Item i : listMenu) {
      String name = i.getNama().toLowerCase();
      String kategori = i.getKategori().toLowerCase();
      if (name.contains(keyword) || kategori.contains(keyword)) {
        tModelMenu.addRow(new Object[] {
            i.getNama(),
            i.getKategori(),
            i.getHarga(),
            i.getStok()
        });
      }
    }
  }

  private void setComponentsBounds() {
    add(tab, BorderLayout.CENTER);
    setMenuBounds();
    setKeranjangBounds();
    setTransaksiBounds();
    setRiwayatBounds();
  }

  private void setRiwayatBounds() {
    rp.setLayout(new BorderLayout(10, 10));
    JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    rp.add(pBottom, BorderLayout.SOUTH);
    pBottom.add(bRefresh);
    rp.add(spRiwayat);

  }

  private void setTransaksiBounds() {
    tp.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    tp.add(lBayar, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    tp.add(tfInput, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    tp.add(lKembali, gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    tp.add(lKembaliVal, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 2;
    tp.add(bBayar, gbc);

  }

  private void setKeranjangBounds() {
    kp.setLayout(new BorderLayout(10, 10));
    kp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    kp.add(pBottom, BorderLayout.SOUTH);
    pBottom.add(lTotal);
    pBottom.add(bHapusItem);
    pBottom.add(bKosongkan);
    kp.add(spKeranjang);

  }

  private void setMenuBounds() {
    mp.setLayout(new BorderLayout(10, 10));
    mp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel pUpper = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    mp.add(pUpper, BorderLayout.NORTH);
    pUpper.add(lCari);
    pUpper.add(tfCari);
    pUpper.add(bHargaMenurun);
    pUpper.add(bHargaMenaik);
    pUpper.add(bNamaMenurun);
    pUpper.add(bNamaMenaik);
    JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    mp.add(pBottom, BorderLayout.SOUTH);
    pBottom.add(lQty);
    pBottom.add(sQty);
    pBottom.add(bTambah);
    mp.add(spMenu, BorderLayout.CENTER);
  }

  private void initComponents() {
    listMenu = new ArrayList<>();
    listKeranjang = new ArrayList<>();
    setListMenu();
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
    setListKeranjang();
  }

  private void setListMenu() {
    Path fileMenu = Paths.get(MENU_FILE_PATH);
    try {
      List<String> lines = Files.readAllLines(fileMenu);
      for (String s : lines) {
        String[] line = s.split(",");
        String nama = line[0];
        int harga = Integer.parseInt(line[1]);
        int stok = Integer.parseInt(line[2]);
        String kategori = line[3];
        if (kategori.equalsIgnoreCase("Makanan"))
          listMenu.add(new Makanan(nama, harga, stok));
        else if (kategori.equalsIgnoreCase("Minuman"))
          listMenu.add(new Minuman(nama, harga, stok));

      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }
  }

  private JPanel initRiwayatPanel() {
    rp = new JPanel();
    bRefresh = new JButton("Refresh");
    tModelRiwayat = new DefaultTableModel(new String[] { "Waktu", "Total (Rp)", "Jumlah Item" }, 0);
    tRiwayat = new JTable(tModelRiwayat);
    spRiwayat = new JScrollPane(tRiwayat);
    return rp;
  }

  private JPanel initTransaksiPanel() {
    tp = new JPanel();
    lBayar = new JLabel("Jumlah Bayar (Rp):");
    lKembali = new JLabel("Kembalian (Rp):");
    lKembaliVal = new JLabel("-");
    tfInput = new JTextField(15);
    bBayar = new JButton("Proses Pembayaran");
    bBayar.setBackground(new Color(50, 200, 40));
    bBayar.setForeground(Color.WHITE);
    return tp;
  }

  private JPanel initKeranjangPanel() {
    kp = new JPanel();
    lTotal = new JLabel("Total: Rp-");
    bHapusItem = new JButton("Hapus Item");
    bKosongkan = new JButton("Kosongkan");
    tModelKeranjang = new DefaultTableModel(
        new String[] { "Nama", "Kategori", "Harga Satuan", "Qty", "Subtotal" }, 0);
    tKeranjang = new JTable(tModelKeranjang);
    spKeranjang = new JScrollPane(tKeranjang);
    addCart();
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
    lQty = new JLabel("Qty:");
    sQty = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    bTambah = new JButton("Tambah ke Keranjang");
    tModelMenu = new DefaultTableModel(new String[] { "Nama", "Kategori", "Harga", "Stok" }, 0);
    tMenu = new JTable(tModelMenu);
    spMenu = new JScrollPane(tMenu);
    addMenu();
    return mp;
  }

  private void addMenu() {
    for (Item i : listMenu) {
      tModelMenu.addRow(new Object[] { i.getNama(), i.getKategori(), i.getHarga(), i.getStok() });
    }
  }

  private void addCart() {
    for (CartItem ci : listKeranjang) {
      tModelKeranjang.addRow(new Object[] {
          ci.getItem().getNama(),
          ci.getItem().getKategori(),
          ci.getItem().getHarga(),
          ci.getQuantity(),
          ci.getItem().getHarga() * ci.getQuantity()
      });
    }
  }

  private void setListKeranjang() {
    Path fileCart = Paths.get(CART_FILE_PATH);
    try {
      List<String> lines = Files.readAllLines(fileCart);
      for (String s : lines) {
        String[] line = s.split(",");
        String id = line[0];
        int qty = Integer.parseInt(line[1]);
        for (Item i : listMenu) {
          if (i.getId().equals(id)) {
            listKeranjang.add(new CartItem(i, qty));
            break;
          }
        }
      }
      refreshTKeranjang();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this,
          "Failed to load cart file",
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
