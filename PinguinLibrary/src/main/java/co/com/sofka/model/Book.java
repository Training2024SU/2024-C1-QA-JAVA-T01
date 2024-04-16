package co.com.sofka.model;

public class Book extends Manuscript{
    private String category;

    public Book() {
    }

    public Book(String category) {
        this.category = category;
    }

    public Book(String title, Author author, String category) {
        super(title, author);
        this.category = category;
    }

    public Book(String id, int quantity, int quantityLoaned, int quantityAvailable, String title, Author author, String category) {
        super(id, quantity, quantityLoaned, quantityAvailable, title, author);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
