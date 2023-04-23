package edu.wm.cs.cs425.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{
Context context;
ArrayList<ReviewModel> reviewModelArrayList;
buttonClickListener buttonClickListener;

public RVAdapter(Context context, ArrayList<ReviewModel> reviewModelArrayList, buttonClickListener buttonClickListener){
 this.context = context;
 this.reviewModelArrayList = reviewModelArrayList;
 this.buttonClickListener= buttonClickListener;
}
    @NonNull
    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_row,parent, false);

        return new MyViewHolder(view,buttonClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.MyViewHolder holder, int position) {
        holder.foodname.setText(reviewModelArrayList.get(position).getFoodName());
        holder.locationname.setText(reviewModelArrayList.get(position).getFoodLocation());
        holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return reviewModelArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView foodname, locationname;
        ImageButton rstar1;
        buttonClickListener buttonClickListener;

        public MyViewHolder(@NonNull View itemView, buttonClickListener buttonClickListener) {
            super(itemView);

            imageView= itemView.findViewById(R.id.foodpic);
            foodname = itemView.findViewById(R.id.item_name);
            locationname = itemView.findViewById(R.id.item_location);
            rstar1= itemView.findViewById(R.id.rstar1);

            this.buttonClickListener =buttonClickListener;
            rstar1.setOnClickListener(this);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {buttonClickListener.onButtonClick(getAdapterPosition());}
    }
    public interface buttonClickListener{
        void onButtonClick(int position);
    }
}
