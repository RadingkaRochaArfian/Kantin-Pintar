package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryItem {
  protected String waktu;
  protected int total;
  protected int jumlahItem;

  public HistoryItem(String iWaktu, String iTotal, String iJumlahItem) {
    waktu = iWaktu;
    total = Integer.parseInt(iTotal);
    jumlahItem = Integer.parseInt(iJumlahItem);
  }

  public HistoryItem(int iTotal, int iJumlahItem) {
    total = iTotal;
    jumlahItem = iJumlahItem;
    setWaktu();
  }

  public String getWaktu() {
    return waktu;
  }

  public int getTotal() {
    return total;
  }

  public int getJumlahItem() {
    return jumlahItem;
  }

  private void setWaktu() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    waktu = now.format(formatter);
  }
}
