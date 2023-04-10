package edu.wm.cs.cs425.helloworld;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {
    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new hours();
            case 1: return new menus();
            case 2: return new reviews();
            case 3: return new favorites();
            default: return new hours();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
