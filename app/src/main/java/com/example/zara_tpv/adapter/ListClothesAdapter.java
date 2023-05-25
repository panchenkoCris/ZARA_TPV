package com.example.zara_tpv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListClothesAdapter extends RecyclerView.Adapter<ListClothesAdapter.ViewHolder> {
    private List<Producto> data;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Producto item);
    }

    public void addProducto(Producto producto) {
        this.data.add(producto);
    }

    public void setProductos(List<Producto> productos) {
        this.data.addAll(productos);
    }

    public ListClothesAdapter(List<Producto> data, OnItemClickListener listener) {
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

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
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

        public void bind(Producto producto, final OnItemClickListener listener) {
            name.setText(producto.getDescripcion());
            size.setText(String.valueOf(producto.getTalla()));
            price.setText(String.valueOf((int) producto.getPrecio()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto);
                }
            });
        }
    }
}


