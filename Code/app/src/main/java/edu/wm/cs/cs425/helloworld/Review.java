package edu.wm.cs.cs425.helloworld;

public class Review {
    double rating;
    String text;
    String food;
    String location;

    String calories;

    public Review(double rating, String text, String food, String location, String calories) {
        this.rating = rating;
        this.text = text;
        this.food = food;
        this.location = location;
        this.calories = calories;
    }

    public double getRating() {
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

    public String getCalories(){return calories;}

    public void setCalories(String calories){this.calories = calories;}
}
