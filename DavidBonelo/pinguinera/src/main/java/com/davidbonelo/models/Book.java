package com.davidbonelo.models;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class Book extends LibraryItem {
    private String field;
    private int pages;

    public Book(String title, String author, int copies, int copiesBorrowed, String field,
                int pages) {
        super(title, author, copies, copiesBorrowed);
        this.field = field;
        this.pages = pages;
    }

    public Book(int id, String title, String author, int copies, int copiesBorrowed, String field
            , int pages) {
        super(id, title, author, copies, copiesBorrowed);
        this.field = field;
        this.pages = pages;
    }

    public static Book createBookFromInput() {
        String title = askText("Title: ");
        String author = askText("Author: ");
        int copies = askNumber("Copies: ");
        int copiesBorrowed = askNumber("Copies borrowed: ");
        String field = askText("Field: ");
        int pages = askNumber("Pages: ");
        return new Book(title, author, copies, copiesBorrowed, field, pages);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() + ", field='" + field + "', pages=" + pages + '}';
    }
}
