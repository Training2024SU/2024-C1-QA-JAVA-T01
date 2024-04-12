package com.davidbonelo.models;

public class LibraryItem {
    private int id;
    private String title;
    private String author;
    private int copies;
    private int copiesBorrowed;

    public LibraryItem(String title, String author, int copies, int copiesBorrowed) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.copiesBorrowed = copiesBorrowed;
        checkCopies();
    }

    public LibraryItem(int id, String title, String author, int copies, int copiesBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.copiesBorrowed = copiesBorrowed;
        checkCopies();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
        checkCopies();
    }

    public int getCopiesBorrowed() {
        return copiesBorrowed;
    }

    public void setCopiesBorrowed(int copiesBorrowed) {
        this.copiesBorrowed = copiesBorrowed;
        checkCopies();
    }

    public int getAvailableCopies() {
        return copies - copiesBorrowed;
    }

    private void checkCopies() {
        if (copiesBorrowed > copies) {
            // Allow but show a warning
            System.out.println("Warning: more copies borrowed than copies in existence");
        }
    }

    @Override
    public String toString() {
        return "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", copies" +
                "=" + copies + ", copiesBorrowed=" + copiesBorrowed;
    }
}
