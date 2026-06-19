package model;

public abstract class Item {
  protected static int foodCnt = 0;
  protected static int drinkCnt = 0;
  protected String nama;
  protected int harga;
  protected int stok;
  protected String kategori;
  protected String id;

  public Item(String iNama, int iHarga, int iStok, String iKategori) {
    nama = iNama;
    harga = iHarga;
    stok = iStok;
    kategori = iKategori;
    addId();
  }

  public String getId() {
    return id;
  }

  private void addId() {
    String idFormat = "";
    if (kategori.toLowerCase().equals("makanan")) {
      idFormat = String.format("MKN-%d", foodCnt++);
    } else if (kategori.toLowerCase().equals("minuman")) {
      idFormat = String.format("MNM-%d", drinkCnt++);
    }
    id = idFormat;
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

  public void setStok(int iStok) {
    stok = iStok;
  }
}
