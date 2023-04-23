package edu.wm.cs.cs425.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
Context context;
ArrayList<ReviewModel> reviewModelArrayList;
public RVAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList){
 this.context = context;
 this.reviewModelArrayList = reviewModelArrayList;
}
    @NonNull
    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_row,parent, false);

        return new RVAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.MyViewHolder holder, int position) {
        holder.foodname.setText(reviewModelArrayList.get(position).getFoodName());
        holder.locationname.setText(reviewModelArrayList.get(position).getFoodLocation());
    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView foodname, locationname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.foodpic);
            foodname = itemView.findViewById(R.id.item_name);
            locationname = itemView.findViewById(R.id.item_location);
            itemView.findViewById(R.id.imageButton14).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "clickly");
                }
            });

        }
    }
}
