package com.davidbonelo.models;

public class Book extends LibraryItem{
    private String field;
    private int pages;

    public Book(int id, String title, String author, int copies, int copiesBorrowed, String field, int pages) {
        super(id, title, author, copies, copiesBorrowed);
        this.field = field;
        this.pages = pages;
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
}
