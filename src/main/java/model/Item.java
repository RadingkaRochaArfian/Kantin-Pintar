package model;

public abstract class Item {
  protected String nama;
  protected int harga;
  protected int stok;
  protected String kategori;

  public Item(String iNama, int iHarga, int iStok, String iKategori) {
    nama = iNama;
    harga = iHarga;
    stok = iStok;
    kategori = iKategori;
  }

  public abstract String getInfo();

  public String getNama() {
    return nama;
  }

  public int getHarga() {
    return harga;
  }

  public int getStok() {
    return stok;
  }

  public String getKategori() {
    return kategori;
  }
}
