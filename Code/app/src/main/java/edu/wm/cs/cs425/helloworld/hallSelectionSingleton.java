package edu.wm.cs.cs425.helloworld;

import java.util.ArrayList;

public class hallSelectionSingleton {

    private static hallSelectionSingleton uniqInstance1;
    public String diningHall;
    private hallSelectionSingleton(){

    }
    public static hallSelectionSingleton getInstance() {
        if(uniqInstance1 == null){
            uniqInstance1 = new hallSelectionSingleton();
        }
        return uniqInstance1;
    }
    public void setDiningHall(String location){
        this.diningHall = location;
        //this.diningHall = "Sadler";
    }
    public String getDiningHall(){
        return this.diningHall;
    }
}
