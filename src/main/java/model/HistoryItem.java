package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryItem {
  protected String waktu;
  protected int total;
  protected int jumlahItem;

  public HistoryItem(int iTotal, int iJumlahItem) {
    total = iTotal;
    jumlahItem = iJumlahItem;
    setWaktu();
  }

  private void setWaktu() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm::ss");
    waktu = now.format(formatter);
  }
}
