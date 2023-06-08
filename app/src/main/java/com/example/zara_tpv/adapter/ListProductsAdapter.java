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
import com.example.zara_tpv.manager.TicketManager;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.windows.FirstWindow;
import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ViewHolder> {
    private static List<Producto> data;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Producto item);
    }

    public void addProducto(Producto producto) {
        TicketManager.setAdapter(this);
        TicketManager.addLineTicket(producto, ListProductsAdapter.this);
    }

    public static void setProductos(List<Producto> productos) {
        data = productos;
    }

    public static List<Producto> getProductos() {
        return data;
    }

    public ListProductsAdapter(List<Producto> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products, parent,false);
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

    public void removeItem(Producto producto) {
        TicketManager.eliminarLineaTicket(producto.getCb_producto());
        data.remove(producto);
        notifyItemRemoved(data.indexOf(producto));
        notifyItemRangeChanged(data.indexOf(producto), data.size());
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
            if(producto.getImagen() != null) {
                byte[] decodedBytes = Base64.decode(producto.getImagen(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                iconImage.setImageBitmap(bitmap);
            }

            String sizeModified = String.valueOf(producto.getTalla());
            if(sizeModified.equals("0")) {
                if(FirstWindow.getLanguage().equals("español")) {
                    sizeModified = "Talla única";
                } else {
                    sizeModified = "Unique size";
                }
            }
            size.setText(sizeModified);
            price.setText(String.valueOf((int) producto.getPrecio()));

            name.setText(ClotheNameManager.setName(producto, ProductsManager.getContext()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto);
                }
            });
        }
    }
}


