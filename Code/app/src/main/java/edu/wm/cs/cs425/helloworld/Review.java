package edu.wm.cs.cs425.helloworld;

public class Review {
    int rating;
    String text;
    String food;
    String location;

    public Review(int rating, String text, String food, String location) {
        this.rating = rating;
        this.text = text;
        this.food = food;
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
