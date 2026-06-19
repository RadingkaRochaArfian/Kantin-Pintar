package model;

public class CartItem {
  protected Item item;
  protected int qty;

  public CartItem(Item iItem, int iQty) {
    item = iItem;
    qty = iQty;
  }

  public void setQuantity(int iQty) {
    qty = iQty;
  }

  public Item getItem() {
    return item;
  }

  public int getQuantity() {
    return qty;
  }

  public int getSubtotal() {
    return item.harga * qty;
  }
}
