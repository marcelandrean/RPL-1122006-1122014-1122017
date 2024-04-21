package models;

public class MerchandiseOrder extends Order {

    private Merchandise merch;

    public MerchandiseOrder(int quantity, Merchandise merch) {
        super(quantity);
        this.merch = merch;
    }

    public Merchandise getMerch() {
        return merch;
    }

    public void setMerch(Merchandise merch) {
        this.merch = merch;
    }
}
