package com.davidbonelo.models;

import static com.davidbonelo.Utils.askNumber;
import static com.davidbonelo.Utils.askText;

public class Novel extends LibraryItem {
    private String genre;
    private int recommendedAge;

    public Novel(String title, String author, int copies, int copiesBorrowed, String genre,
                 int recommendedAge) {
        super(title, author, copies, copiesBorrowed);
        this.genre = genre;
        this.recommendedAge = recommendedAge;
    }

    public Novel(int id, String title, String author, int copies, int copiesBorrowed,
                 String genre, int recommendedAge) {
        super(id, title, author, copies, copiesBorrowed);
        this.genre = genre;
        this.recommendedAge = recommendedAge;
    }

    public static Novel createNovelFromInput() {
        String title = askText("Title: ");
        String author = askText("Author: ");
        int copies = askNumber("Copies: ");
        int copiesBorrowed = askNumber("Copies borrowed: ");
        String genre = askText("Genre: ");
        int recommendedAge = askNumber("Recommended age: ");
        return new Novel(title, author, copies, copiesBorrowed, genre, recommendedAge);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    @Override
    public String toString() {
        return "Novel{" + super.toString() + ", genre='" + genre + "', recommendedAge=" + recommendedAge + '}';
    }
}
