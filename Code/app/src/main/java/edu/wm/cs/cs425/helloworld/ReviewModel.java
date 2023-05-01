package edu.wm.cs.cs425.helloworld;

import android.media.Image;

import java.util.Dictionary;
import java.util.Hashtable;

public class ReviewModel {
    String FoodName;
    String FoodLocation;

    int image;

    String calories;

    Dictionary<String, Integer> image_dict = new Hashtable<>();



    public ReviewModel(String foodName, String foodLocation, String cal) {
        image_dict.put("DELI", R.drawable.turkeysliced);
        image_dict.put("DESSERT", R.drawable.cupcakes);
        image_dict.put("GRILL", R.drawable.cheesburger);
        image_dict.put("SALAD BAR", R.drawable.salad);
        image_dict.put("SALAD", R.drawable.salad);
        image_dict.put("MACTOWN", R.drawable.macandcheese);
        image_dict.put("KALAMATA", R.drawable.kalamata);
        image_dict.put("SIMPLE SERVINGS", R.drawable.cornbread);
        image_dict.put("STARCH", R.drawable.rice);
        image_dict.put("CONDIMENT/GARNISH", R.drawable.condiment);
        image_dict.put("HEART & STONE", R.drawable.cheesepizza);
        image_dict.put("ENTREE", R.drawable.chickenburger);
        image_dict.put("VEGETABLE", R.drawable.romaine);
        image_dict.put("WOK", R.drawable.wok);
        image_dict.put("BAKERY", R.drawable.cookies);
        image_dict.put("BREAKFAST", R.drawable.oamlet);
        image_dict.put("COMFORT", R.drawable.comfort);
        image_dict.put("SOUP", R.drawable.soup);
        image_dict.put("CUSTO-MEX", R.drawable.tacos);
        image_dict.put("GLOBAL KITCHEN", R.drawable.pasta);
        image_dict.put("SADLER'S DELI", R.drawable.turkeysliced);
        image_dict.put("SWEET SPOT", R.drawable.cupcakes);
        image_dict.put("ACTION - MADE TO ORDER", R.drawable.comfort);
        image_dict.put("-", R.drawable.comfort);
        image_dict.put("APPETIZER", R.drawable.appetizer);
        this.FoodName = foodName;
        this.FoodLocation = foodLocation;
        //this.image = image_dict.get(this.FoodLocation);
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
