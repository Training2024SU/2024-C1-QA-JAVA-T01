package co.com.sofka.model;

public class Manuscript extends Item{
    protected String title;
    protected Author author;

    public Manuscript() {
    }

    public Manuscript(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Manuscript(String id, int quantity, int quantityLoaned, int quantityAvailable, String title, Author author) {
        super(id, quantity, quantityLoaned, quantityAvailable);
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Manuscript{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
