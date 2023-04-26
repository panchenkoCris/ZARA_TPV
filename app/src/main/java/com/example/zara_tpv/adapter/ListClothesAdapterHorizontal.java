package com.example.zara_tpv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.ListClothes;


import java.util.List;

public class ListClothesAdapterHorizontal extends RecyclerView.Adapter<ListClothesAdapterHorizontal.ViewHolderHorizontal> {
    private List<ListClothes> data;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListClothes item);
    }

    public ListClothesAdapterHorizontal(List<ListClothes> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListClothesAdapterHorizontal.ViewHolderHorizontal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_clothes_horizontal, parent,false);
        return new ListClothesAdapterHorizontal.ViewHolderHorizontal(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHorizontal holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void filterReference(List<ListClothes> filterList) {
        data = filterList;
        notifyDataSetChanged();
    }


    public class ViewHolderHorizontal extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolderHorizontal(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_name_clothe_menu);
        }

        public void bind(ListClothes clothe, final OnItemClickListener listener) {
            name.setText(clothe.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(clothe);
                }
            });
        }
    }

}
