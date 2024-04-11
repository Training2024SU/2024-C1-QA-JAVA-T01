package com.davidbonelo.models;

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
}
