package model;

import controller.Payable;
import java.util.List;

public class Transaksi implements Payable {
  protected List<CartItem> listBelanja;
  protected int totalHarga;
  protected int kembalian;
  protected int totalQuantity;
  protected int nominalBayar;
  protected boolean statusTransaksi;

  public Transaksi(List<CartItem> iListBelanja) {
    listBelanja = iListBelanja;
    totalQuantity = 0;
    totalHarga = hitungTotalHarga();
    statusTransaksi = false;
    kembalian = 0;
  }

  private int hitungTotalHarga() {
    int total = 0;
    for (CartItem ci : listBelanja) {
      total += ci.getItem().getHarga() * ci.getQuantity();
      totalQuantity++;
    }
    return total;
  }

  public int getTotalQuantity() {
    return totalQuantity;
  }

  public void prosesPembayaran(double jumlah) {
    nominalBayar = (int) jumlah;
    if (nominalBayar >= totalHarga) {
      kembalian = nominalBayar - totalHarga;
      statusTransaksi = true;
    } else {
      statusTransaksi = false;
    }
  }

  public List<CartItem> getListBelanja() {
    return listBelanja;
  }

  public int getTotalHarga() {
    return totalHarga;
  }

  public int getKembalian() {
    return kembalian;
  }

  public int getNominalBayar() {
    return nominalBayar;
  }

  public boolean isBerhasil() {
    return statusTransaksi;
  }
}
