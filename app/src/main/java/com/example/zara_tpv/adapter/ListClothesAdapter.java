package com.example.zara_tpv.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.ListClothes;

import java.util.List;

public class ListClothesAdapter extends RecyclerView.Adapter<ListClothesAdapter.ViewHolder> {
    private List<ListClothes> data;
    private LayoutInflater inflater;
    private Context context;
    private Resources res;

    public ListClothesAdapter(List<ListClothes> data, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ListClothesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListClothesAdapter.ViewHolder(inflater.inflate(R.layout.list_clothes, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListClothesAdapter.ViewHolder holder, int position) {
        ListClothes clothe = data.get(position);
        holder.name.setText(clothe.getName());
        holder.size.setText(clothe.getSize());
        holder.price.setText(clothe.getPrice());
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
    }

}


