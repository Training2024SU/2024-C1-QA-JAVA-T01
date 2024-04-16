package co.com.sofka.model;

public abstract class Item {
    protected String id;
    protected int quantity;
    protected int quantityLoaned;
    protected int quantityAvailable;

    public Item() {
    }

    public Item(String id, int quantity, int quantityLoaned, int quantityAvailable) {
        this.id = id;
        this.quantity = quantity;
        this.quantityLoaned = quantityLoaned;
        this.quantityAvailable = quantityAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityLoaned() {
        return quantityLoaned;
    }

    public void setQuantityLoaned(int quantityLoaned) {
        this.quantityLoaned = quantityLoaned;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
