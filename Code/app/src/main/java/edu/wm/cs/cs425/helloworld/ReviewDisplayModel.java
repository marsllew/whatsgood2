package edu.wm.cs.cs425.helloworld;

public class ReviewDisplayModel {
    String username;
    String Foodname;
    String locationname;

    String userText;
    int image;

    double rating;


    public ReviewDisplayModel(String foodName, String foodLocation, String username, String userText, double rating) {
        this.Foodname = foodName;
        this.locationname = foodLocation;
        this.username =username;
        this.userText = userText;
        this.rating = rating;
    }

    public String getFoodname() {
        return Foodname;
    }

    public String getLocationname() {
        return locationname;
    }

    public String getusername() {
        return username;
    }

    public String getUserText(){
        return userText;
    }

    public double getUserRating(){return rating;}

}
