package com.example.zara_tpv.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.manager.ClotheNameManager;
import com.example.zara_tpv.manager.ProductsManager;
import com.example.zara_tpv.pojo.Producto;
import java.util.List;

public class ListProductsShopAdapter extends RecyclerView.Adapter<ListProductsShopAdapter.ViewHolder> {
    private static List<Producto> data;
    private static List<Producto> originalData;
    private final OnItemClickListener listener;

    public void setFilteredList(List<Producto> listFiltered) {
        this.data = listFiltered;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Producto item);
    }

    public void setProductos(List<Producto> productos) {
        this.data.addAll(productos);
    }

    public ListProductsShopAdapter(List<Producto> data, OnItemClickListener listener) {
        this.data = data;
        this.originalData = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListProductsShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products_shop, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductsShopAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItemAdmin(Producto producto) {
        data.remove(producto);
        notifyItemRemoved(data.indexOf(producto));
        notifyItemRangeChanged(data.indexOf(producto), data.size());
    }

    public void updateItemAdmin(Producto producto) {
        data.remove(producto);
        notifyItemRemoved(data.indexOf(producto));
        data.add(producto);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imageView_clothe_menu);
            name = itemView.findViewById(R.id.textView_name_clothe_menu);
        }

        public void bind(Producto producto, final OnItemClickListener listener) {
            if(producto.getImagen() != null) {
                byte[] decodedBytes = Base64.decode(producto.getImagen(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                iconImage.setImageBitmap(bitmap);
            }

            name.setText(ClotheNameManager.setName(producto, ProductsManager.getContext()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto);
                }
            });
        }
    }

    public static List<Producto> getOriginalData() {
        return originalData;
    }
}


