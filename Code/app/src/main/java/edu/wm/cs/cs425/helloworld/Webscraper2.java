package edu.wm.cs.cs425.helloworld;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Webscraper2 {

    public Dictionary<String, List<String>> webscrape() throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        String numberdate = new Integer(dateFormat.format(date)).toString();
        Dictionary<String, List<String>> final_dict = new Hashtable<>();

        Thread x = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                String now = "";
                try {
                    doc = Jsoup.connect("https://menus.sodexomyway.com/BiteMenu/Menu?menuId=14731&locationId=10249001&whereami=https://dining.wm.edu/dining-near-me/resident-dining").get();

                    DateFormat dateFormat = new SimpleDateFormat("kkmm");
                    int time = new Integer(dateFormat.format(date));

                    if (time < 1000) { now = "breakfast";}
                    else if (time < 1500) {now = "lunch";}
                    else if (time < 2000) {now = "dinner";}
                    else now = "late.night";
                    System.out.println(now + time);

                    Dictionary<String ,String> ids = new Hashtable<>();

                    //System.out.println(numberdate);
                    Elements day = doc.select("div.bite-day-menu");
                    //System.out.println("day " + day);
                    Elements meal = day.select("div.accordion-block." + now);
                    //System.out.println("meal " + meal);
                    Elements station = meal.select("div.bite-menu-course h5[id]");
                    //System.out.println("stattion " + station);
                    for (Element stat : station) { ids.put(stat.id(), stat.text()); }



                    Enumeration<String> k = ids.keys();
                    while (k.hasMoreElements()) {
                        String key = k.nextElement();

                        List<String> wompa = new ArrayList<>();
                        Elements per_station = meal.select("ul.bite-menu-item[aria-describedby=" + key + "]");
                        //System.out.println("per station " + per_station);
                        Elements foods = per_station.select("div.col-xs-9 a");
                        for (Element food : foods) {
                            String food_id = food.attr("data-number");
                            //System.out.println(food_id);
                            Elements calories = per_station.select("[data-number=" + food_id + "]");
                            //System.out.println(calories.text());
                            wompa.add(calories.text());
                        }

                        final_dict.put(ids.get(key), wompa);
                    }
                    System.out.println(final_dict);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        x.start();
        try {
            x.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return final_dict;
    }
}