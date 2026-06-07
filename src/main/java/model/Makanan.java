
package model;

public class Makanan extends Item {
  public Makanan(String nama, int harga, int stok) {
    super(nama, harga, stok, "Makanan");
  }

  public String getInfo() {
    String info = String.format("Nama: %s\nHarga: %d\nStok: %d\nKategori: %s\n", super.nama, super.harga, super.stok,
        super.kategori);
    return info;
  }
}
