package com.example.zara_tpv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zara_tpv.R;
import com.example.zara_tpv.pojo.Discount;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolder>{
    private List<Discount> data;
    private final OnItemClickListener listener;

    public DiscountAdapter(List<Discount> discounts, OnItemClickListener listener) {
        this.data = discounts;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Discount item);
    }

    @NonNull
    @Override
    public DiscountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent
                        .getContext())
                .inflate(R.layout.list_discounts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
        holder.description.setText(data.get(position).getDescripcion());
    }

    public void removeItem(Discount discount) {
        data.remove(discount);
        notifyItemRemoved(data.indexOf(discount));
        notifyItemRangeChanged(data.indexOf(discount), data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        Button buttonApply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.textView_text_discount);
            buttonApply = itemView.findViewById(R.id.button_apply_discount);
        }

        public void bind(Discount discount, final OnItemClickListener listener) {
            description.setText(discount.getDescripcion());
            buttonApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(discount);
                }
            });
        }
    }
}
