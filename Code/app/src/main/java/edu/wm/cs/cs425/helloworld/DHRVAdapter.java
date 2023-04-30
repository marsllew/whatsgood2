package edu.wm.cs.cs425.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DHRVAdapter extends RecyclerView.Adapter<DHRVAdapter.MyViewHolder> {
    Context context;
    ArrayList<DHModel> dhModelArrayList;
    public DHRVAdapter( Context context,ArrayList<DHModel> dhModelArrayList){
        this.context = context;
        this.dhModelArrayList = dhModelArrayList;
        }
    @NonNull
    @Override
    public DHRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dininghall_rv_row,parent, false);

        return new DHRVAdapter.MyViewHolder(view);

        }

    @Override
    public void onBindViewHolder(@NonNull DHRVAdapter.MyViewHolder holder, int position) {
        holder.DHname.setText(dhModelArrayList.get(position).getDHname());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if ( position == 1){
                    AppCompatActivity activity = (AppCompatActivity)view.getContext();
                    menus menus = new menus();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.DHlayout,menus).addToBackStack(null).commit();
                }
                else{
                    AppCompatActivity activity = (AppCompatActivity)view.getContext();
                    cafmenu cafmenu = new cafmenu();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.DHlayout,cafmenu).addToBackStack(null).commit();
                }
            }
        });
        }

    @Override
    public int getItemCount() {
        return dhModelArrayList.size();
        }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView DHname;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        DHname = itemView.findViewById(R.id.DHname);


    }
}}
