package edu.wm.cs.cs425.helloworld;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;


public class cafmenu extends Fragment {

    Dictionary<String, List<String>> final_dict = new Hashtable<>();
    ArrayList<ReviewModel> rvList = new ArrayList<>();
    ImageButton dhback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus, container, false);

        dhback=view.findViewById(R.id.dhback);
        dhback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dhmenu dhmenu = new dhmenu();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.menulayout, dhmenu).addToBackStack(null).commit();
            }
        });

        Webscraper2 menu_retriever = new Webscraper2();
        try {
            final_dict = menu_retriever.webscrape();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> k = final_dict.keys();
        String name = "";
        String cals = "";
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            for (String item : final_dict.get(key)) {
                for (int i=0; i < item.length(); i++){
                    //System.out.println(item);
                    //System.out.println(item.charAt(i));
                    if (Character.isDigit(item.charAt(i))) {
                        name = item.substring(0, i);
                        cals = item.substring(i);
                        break;
                    }
                    else { name = item; cals = "0cal"; }
                }
                rvList.add(new ReviewModel(name, key, cals));
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.menuRecycle);
        RVAdapter menuadapt = new RVAdapter(getContext(), rvList);
        LinearLayoutManager llmMenu = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llmMenu);
        recyclerView.setAdapter(menuadapt);
        // Inflate the layout for this fragment
        return view;
    }


}