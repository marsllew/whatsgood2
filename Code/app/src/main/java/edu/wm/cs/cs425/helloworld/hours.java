package edu.wm.cs.cs425.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import androidx.fragment.app.Fragment;




public class hours extends Fragment {
    //These are the wait times that I used by using popular times on Google
//and also asking people who worked at the dining hall and have expertise in knowing the wait times
    String[] SadlerWaitTime = {
            "2 minutes",
            "3 minutes",
            "5 minutes",
            "2 minutes",
            "3 minutes",
            "5 minutes",
            "3 minutes",
            "2 minutes"
    };

    String[] CommonsWaitTime = {
            "2 minutes",
            "5 minutes",
            "3 minutes",
            "2 minutes",
            "3 minutes",
            "5 minutes",
            "10 minutes",
            "2 minutes",
    };
    //Marketplace does not stay open that long that is why there is not much information

    String[] MarketplaceWaitTime = {
            "3 minutes",
            "5 minutes",
            "8 minutes",
            "5 minutes",
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hours, container, false);
        TextView Sadlertv;
        TextView Commonstv;
        TextView Marketplacetv;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        boolean isWeekend = (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
        Sadlertv = view.findViewById(R.id.Sadlertv);
        Commonstv = view.findViewById(R.id.Commonstv);
        Marketplacetv = view.findViewById(R.id.Marketplacetv);
        //Use if/else to set the texts with wait times
        //corresponding to which dining hall it is
        if (hour >= 8 && hour <= 10) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[0]);
        } else if (hour == 11) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[1]);
        } else if (hour == 12) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[2]);
        } else if (hour >= 13 && hour <= 16) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[3]);
        } else if (hour == 17) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[4]);
        } else if (hour == 18) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[5]);
        } else if (hour == 19) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[6]);
        } else if (hour >= 20 && hour <= 24) {
            Sadlertv.setText("Sadler Wait Time: " + SadlerWaitTime[7]);
        } else {
            Sadlertv.setText("Sadler Wait Time: " + "Closed");
        }


        if (hour >= 7 && hour < 9) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[0]);
        } else if (hour >= 10 && hour < 12) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[1]);
        } else if (hour >= 13 && hour < 14) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[2]);
        } else if (hour >= 16 && hour < 17) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[3]);
        } else if (hour >= 17 && hour < 18) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[4]);
        } else if (hour >= 18 && hour < 19) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[5]);
        } else if (hour >= 19 && hour < 20) {
            Commonstv.setText("Commons Wait Time: " + CommonsWaitTime[6]);
        } else {
            Commonstv.setText("Commons Wait Time: " + "Closed");
        }


        if (isWeekend) {
            Marketplacetv.setText("Marketplace is closed on Weekends");
        } else if (hour >= 9 && hour < 10) {
            Marketplacetv.setText("Marketplace Wait Time: " + MarketplaceWaitTime[0]);
        } else if (hour >= 10 && hour < 12) {
            Marketplacetv.setText("Marketplace Wait Time: " + MarketplaceWaitTime[1]);
        } else if (hour >= 12 && hour < 13) {
            Marketplacetv.setText("Marketplace Wait Time: " + MarketplaceWaitTime[2]);
        } else if (hour >= 13 && hour < 15) {
            Marketplacetv.setText("Marketplace Wait Time: " + MarketplaceWaitTime[3]);
        } else {
            Marketplacetv.setText("Marketplace Wait Time: " + "Closed");
        }

        return view;
    }
}
