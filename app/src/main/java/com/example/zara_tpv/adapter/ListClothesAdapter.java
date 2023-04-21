package com.example.zara_tpv.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.ListClothes;

import java.util.List;

public class ListClothesAdapter extends RecyclerView.Adapter<ListClothesAdapter.ViewHolder> {
    private List<ListClothes> data;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListClothes item);
    }

    public ListClothesAdapter(List<ListClothes> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListClothesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_clothes, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClothesAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, size, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imageView_clothe);
            name = itemView.findViewById(R.id.textView_name_clothe);
            size = itemView.findViewById(R.id.textView_size_clothe);
            price = itemView.findViewById(R.id.textView_price_clothe);
        }

        public void bind(ListClothes clothe, final OnItemClickListener listener) {
            name.setText(clothe.getName());
            size.setText(clothe.getSize());
            price.setText(clothe.getPrice());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(clothe);
                }
            });
        }
    }

}


