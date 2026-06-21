package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CartItem;
import model.Transaksi;

public class StrukExporter implements Exportable {
  protected Transaksi transaksi;
  protected JFrame window;

  public StrukExporter(Transaksi iTransaksi, JFrame iWindow) {
    transaksi = iTransaksi;
    window = iWindow;
  }

  public void exportToFile(String pathFileStruk) {
    SimpleDateFormat formatterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss");
    String date = formatterTime.format(new Date());
    DecimalFormatSymbols simbol = new DecimalFormatSymbols();
    simbol.setGroupingSeparator('.');
    DecimalFormat formatter = new DecimalFormat("#,###", simbol);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathFileStruk))) {
      writer.write("===== STRUK PEMESANAN KANTIN =====\n");
      writer.write("Waktu: " + date + "\n");
      writer.write("----------------------------------\n");
      for (CartItem ci : transaksi.getListBelanja()) {
        String name = ci.getItem().getNama();
        int qty = ci.getQuantity();
        int harga = ci.getItem().getHarga();
        String formattedHarga = formatter.format(harga);
        writer.write(String.format(
            "%-18s x%-3d Rp%s",
            name,
            qty,
            formattedHarga));
      }
      writer.write("----------------------------------\n");
      writer.write(String.format(
          "%-10s: Rp%s\n%-10s: Rp%s\n%-10s: Rp%s\n",
          "TOTAL",
          formatter.format(transaksi.getTotalHarga()),
          "BAYAR",
          formatter.format(transaksi.getNominalBayar()),
          "KEMBALIAN",
          formatter.format(transaksi.getKembalian())));
      writer.write("==================================\n");
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          window,
          e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

}
