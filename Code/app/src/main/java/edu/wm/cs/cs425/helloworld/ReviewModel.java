package edu.wm.cs.cs425.helloworld;

public class ReviewModel {
    String FoodName;
    String FoodLocation;
    int image;

    String calories;


    public ReviewModel(String foodName, String foodLocation, String cal) {
        this.FoodName = foodName;
        this.FoodLocation = foodLocation;
        this.image = image;
        this.calories = cal;
    }

    public String getFoodName() {
        return FoodName;
    }

    public String getFoodLocation() {
        return FoodLocation;
    }

    public int getImage() {
        return image;
    }

    public String getCalories() { return calories; }
}
