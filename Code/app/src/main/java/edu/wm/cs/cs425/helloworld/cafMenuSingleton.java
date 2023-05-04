package edu.wm.cs.cs425.helloworld;

import java.util.ArrayList;

public class cafMenuSingleton {
    private static cafMenuSingleton uniqInstance;
    public ArrayList<ReviewModel> grvList = new ArrayList<>();
    private cafMenuSingleton(){

    }
    public static cafMenuSingleton getInstance() {
        if(uniqInstance == null){
            uniqInstance = new cafMenuSingleton();
        }
        return uniqInstance;
    }
    public void setArrayList(ArrayList<ReviewModel> grvList){
        this.grvList = grvList;
    }
    public ArrayList<ReviewModel> getArrayList(){
        return this.grvList;
    }
}
