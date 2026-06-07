package model;

public class Minuman extends Item {
  public Minuman(String nama, int harga, int stok) {
    super(nama, harga, stok, "Minuman");
  }

  public String getInfo() {
    String info = String.format("Nama: %s\nHarga: %d\nStok: %d\nKategori: %s\n", super.nama, super.harga, super.stok,
        super.kategori);
    return info;
  }
}
