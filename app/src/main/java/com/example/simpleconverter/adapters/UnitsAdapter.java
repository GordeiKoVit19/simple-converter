package com.example.simpleconverter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitViewHolder> {
    private List<String> unitsList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String unit);
    }

    public UnitsAdapter(List<String> unitsList, OnItemClickListener listener) {
        this.unitsList = unitsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        String unit = unitsList.get(position);
        holder.textView.setText(unit);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(unit));
    }

    @Override
    public int getItemCount() {
        return unitsList.size();
    }
    static class UnitViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
