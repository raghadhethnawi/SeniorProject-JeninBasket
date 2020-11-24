package com.example.seniorproject;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class Categories_Adapter extends ListAdapter<Category, CategoryViewHolder> {


    public Categories_Adapter() {
        super(new DiffUtil.ItemCallback<Category>() {
            @Override
            public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(getItem(position));
// fill date
    }
}


 class CategoryViewHolder extends RecyclerView.ViewHolder{

     public CategoryViewHolder(@NonNull View itemView) {
         super(itemView);
     }

     void bind(Category category){
         TextView tv = itemView.findViewById(R.id.nameTextView);
         tv.setText(category.getName());

         ImageView img = itemView.findViewById(R.id.imageView);
         Glide.with(itemView.getContext())
                 .load(category.getImage())
                 .into(img);

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent();
                 
             }
         });
     }

 }
