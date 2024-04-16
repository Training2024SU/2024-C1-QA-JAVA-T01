package co.com.sofka.model;

public class Novel extends Manuscript{
    private String gender;
    private int recommendedAge;

    public Novel() {
    }

    public Novel(String gender) {
        this.gender = gender;
    }

    public Novel(String title, Author author, String gender) {
        super(title, author);
        this.gender = gender;
    }

    public Novel(String gender, int recommendedAge) {
        this.gender = gender;
        this.recommendedAge = recommendedAge;
    }

    public Novel(String title, Author author, String gender, int recommendedAge) {
        super(title, author);
        this.gender = gender;
        this.recommendedAge = recommendedAge;
    }

    public Novel(String id, int quantity, int quantityLoaned, int quantityAvailable, String title, Author author, String gender, int recommendedAge) {
        super(id, quantity, quantityLoaned, quantityAvailable, title, author);
        this.gender = gender;
        this.recommendedAge = recommendedAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "gender='" + gender + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
