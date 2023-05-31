package com.example.zara_tpv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.manager.TypesManager;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Type;

import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ViewHolder> {
    private List<Producto> data;
    private final OnItemClickListener listener;
    private boolean windowIsShop;

    public void filterReference(List<Producto> list) {
    }

    public interface OnItemClickListener {
        void onItemClick(Producto item);
    }

    public void addProducto(Producto producto) {
        this.data.add(producto);
    }

    public void setProductos(List<Producto> productos) {
        this.data.addAll(productos);
    }

    public ListProductsAdapter(List<Producto> data, boolean windowIsShop,OnItemClickListener listener) {
        this.data = data;
        this.windowIsShop = windowIsShop;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (windowIsShop) ?
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products_shop, parent,false) :
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductsAdapter.ViewHolder holder, int position) {
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
            if(!windowIsShop) {
                iconImage = itemView.findViewById(R.id.imageView_clothe);
                name = itemView.findViewById(R.id.textView_name_clothe);
                size = itemView.findViewById(R.id.textView_size_clothe);
                price = itemView.findViewById(R.id.textView_price_clothe);
            } else {
                name = itemView.findViewById(R.id.textView_name_clothe_menu);
            }
        }

        public void bind(Producto producto, final OnItemClickListener listener) {
            if(!windowIsShop) {
                size.setText(String.valueOf(producto.getTalla()));
                price.setText(String.valueOf((int) producto.getPrecio()));
            }

            Type type = TypesManager.getOneType(producto.getId_tipo());
            name.setText(type.getNombre_tipo()+" "+((type.getLongitud_tipo()!=null) ? type.getLongitud_tipo() : producto.getColor()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto);
                }
            });
        }
    }
}


