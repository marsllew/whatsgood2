package edu.wm.cs.cs425.helloworld;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            itemView.findViewById(R.id.favorite_heart).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "clickly");
                }
            });
            itemView.findViewById(R.id.rstar1).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(view.getContext(), Leave_Review.class);
                   intent.putExtra("food", foodname.getText());
                   intent.putExtra("location", locationname.getText());
                   intent.putExtra("rating", 1);
                   intent.putExtra("image", R.id.foodpic);
                   view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar2).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 2);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar3).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 3);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar4).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 4);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.rstar5).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Leave_Review.class);
                    intent.putExtra("food", foodname.getText());
                    intent.putExtra("location", locationname.getText());
                    intent.putExtra("rating", 5);
                    intent.putExtra("image", R.id.foodpic);
                    view.getContext().startActivity(intent);
                }
            });

            itemView.findViewById(R.id.info).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d("demo", "clickly");
                }
            });

        }
    }
}

