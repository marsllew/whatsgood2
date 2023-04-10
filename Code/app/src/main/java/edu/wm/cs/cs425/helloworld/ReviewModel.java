package edu.wm.cs.cs425.helloworld;

public class ReviewModel {
    String FoodName;
    String FoodLocation;
    int image;


    public ReviewModel(String foodName, String foodLocation) {
        this.FoodName = foodName;
        this.FoodLocation = foodLocation;
        this.image = image;
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
}
