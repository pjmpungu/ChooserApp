package com.jonathannalikka.chooserapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private ArrayList<Person> people;
    ItemClicked activity;

    public PersonAdapter(Context context, ArrayList<Person> list){
        people=list;
        activity = (ItemClicked) context;
    }

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvFName, tvLName;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvFName = itemView.findViewById(R.id.tvFName);
            tvLName = itemView.findViewById(R.id.tvLName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(people.indexOf((Person) itemView.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(people.get(position));

        holder.tvFName.setText(people.get(position).getFname());
        holder.tvLName.setText(people.get(position).getLname());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
