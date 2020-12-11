package com.example.anecdotru;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnecdotAdaper extends RecyclerView.Adapter<AnecdotAdaper.AnecdotViewHolder> {
    private ArrayList<ExampleList> mExampleList;

    public AnecdotAdaper(ArrayList<ExampleList> exampleLists){
        mExampleList = exampleLists;
    }

    @NonNull
    @Override
    public AnecdotAdaper.AnecdotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_list,parent,false);
        AnecdotViewHolder avh = new AnecdotViewHolder(v);
        return avh;
    }


    @Override
    public void onBindViewHolder(@NonNull AnecdotViewHolder holder, int position) {
        ExampleList currentItem = mExampleList.get(position);

        holder.imageView.setImageResource(currentItem.getImageResourse());
        holder.text1.setText(currentItem.getText1());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class AnecdotViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView text1;
        public AnecdotViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            text1 = itemView.findViewById(R.id.tv_view_holder_number);
        }
    }
}
