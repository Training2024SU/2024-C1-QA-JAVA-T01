package com.davidbonelo.models;

import java.util.List;

public class LibraryItem {
    private int id;
    private String title;
    private String author;
    private int copies;
    private int copiesBorrowed;

    public LibraryItem(int id, String title, String author, int copies, int copiesBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.copiesBorrowed = copiesBorrowed;
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
    }

    public int getCopiesBorrowed() {
        return copiesBorrowed;
    }

    public void setCopiesBorrowed(int copiesBorrowed) {
        this.copiesBorrowed = copiesBorrowed;
    }

}
