package edu.wm.cs.cs425.helloworld;

import java.util.ArrayList;

public class menuSingleton {
    private static menuSingleton uniqInstance;
    public ArrayList<ReviewModel> grvList = new ArrayList<>();
    private menuSingleton(){

    }
    public static menuSingleton getInstance() {
        if(uniqInstance == null){
            uniqInstance = new menuSingleton();
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
