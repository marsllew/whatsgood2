package edu.wm.cs.cs425.helloworld;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Webscraper {

    public void webscrape() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        String numberdate = new Integer(dateFormat.format(date)).toString();
        System.out.println(numberdate);
        List<String> meals = Arrays.asList("breakfast", "lunch", "dinner");
        List<String> breakfast_foods = new ArrayList<>();
        List<String> lunch_foods = new ArrayList<>();
        List<String> dinner_foods = new ArrayList<>();
        List<String> late_night_foods = new ArrayList<>();
        List<List> lists = Arrays.asList(breakfast_foods, lunch_foods, dinner_foods);

        Thread x = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://menus.sodexomyway.com/BiteMenu/Menu?menuId=22802&locationId=10249001&whereami=http://dining.wm.edu/dining-near-me/sadler").get();
                    System.out.println(doc.title());

                    Elements day = doc.select("#menuid-" + numberdate + "-day");
                    for (int i =0; i < 3; i++) {
                        Elements meal = day.select(".accordion-block." + meals.get(i));
                        Elements foods = meal.select("div.col-xs-9 a");
                        for (Element food : foods) {
                            lists.get(i).add(food.text());
                        }
                    }
                    Elements lnmeal = day.select(".accordion-block.late.night");
                    Elements lnfoods = lnmeal.select("div.col-xs-9 a");
                    for (Element lnfood : lnfoods) {
                        late_night_foods.add(lnfood.text());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println(breakfast_foods);
//                System.out.println(lunch_foods);
//                System.out.println(dinner_foods);
//                System.out.println(late_night_foods);
            }
        });
        x.start();
    }
}

