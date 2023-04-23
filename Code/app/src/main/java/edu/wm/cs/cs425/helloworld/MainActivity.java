package edu.wm.cs.cs425.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RVAdapter.buttonClickListener {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VPAdapter vpAdapter;
    RVAdapter rvAdapter;
    RecyclerView recyclerView;

    ArrayList<ReviewModel> reviewModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //replacefragment(new reviews());
        recyclerView=findViewById(R.id.reviewRecycle);

        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager);
        vpAdapter = new VPAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        rvAdapter =new RVAdapter(this, reviewModelArrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);

    }

    private void setUpReviewModel(){
    String[] menunames = getResources().getStringArray(R.array.Menu_samples);
    String[] locationnames= getResources().getStringArray(R.array.Menu_samples);

    for (int i = 0; i<menunames.length; i++){
        reviewModelArrayList.add(new ReviewModel(menunames[i],locationnames[i]));
    }
    }

    @Override
    public void onButtonClick(int position) {
        int p = position +1;
        Toast.makeText(MainActivity.this, "Item clicked: "+ p, Toast.LENGTH_SHORT).show();
    }
}