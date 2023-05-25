package com.example.zara_tpv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.manager.DialogManager;
import com.example.zara_tpv.pojo.ListClothesNested;
import com.example.zara_tpv.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListClothesAdapterNested extends RecyclerView.Adapter<ListClothesAdapterNested.ItemViewHolder> {
    private ListClothesAdapterHorizontal adapterHorizontal;
    private List<ListClothesNested> data;
    private List<Producto> list = new ArrayList<>();
    private Context context;

    public ListClothesAdapterNested(List<ListClothesNested> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_list_clothes, parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public synchronized void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ListClothesNested model = data.get(position);
        holder.nameCategory.setText(model.getNameCategory());

        boolean isExpandable = model.isExpandable();
        holder.recyclerView.setVisibility((isExpandable) ? View.VISIBLE: View.GONE);

        holder.imageArrow.setImageResource((isExpandable) ? R.drawable.arrow_up : R.drawable.arrow_down);

        adapterHorizontal = new ListClothesAdapterHorizontal(list, new ListClothesAdapterHorizontal.OnItemClickListener() {
            @Override
            public void onItemClick(Producto clothe) {
                DialogManager.openDialogClothe(context, clothe);
            }
        });

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapterHorizontal);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setExpandable(!model.isExpandable());
                list = model.getNestedList();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ListClothesAdapterHorizontal getAdapterHorizontal() {
        return adapterHorizontal;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        RecyclerView recyclerView;
        TextView nameCategory;
        ImageView imageArrow;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout_recyclerview);
            relativeLayout = itemView.findViewById(R.id.expandable_layout_recyclerview);
            recyclerView = itemView.findViewById(R.id.recyclerview_list_clothes);
            nameCategory = itemView.findViewById(R.id.textView_title_name_category);
            imageArrow = itemView.findViewById(R.id.imageview_arrow_direction);
        }
    }

}


