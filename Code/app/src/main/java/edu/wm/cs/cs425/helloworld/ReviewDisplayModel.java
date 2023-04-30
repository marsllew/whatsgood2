package edu.wm.cs.cs425.helloworld;

public class ReviewDisplayModel {
    String username;
    String Foodname;
    String locationname;


    public ReviewDisplayModel(String foodName, String foodLocation, String username) {
        this.Foodname = foodName;
        this.locationname = foodLocation;
        this.username =username;
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
}
