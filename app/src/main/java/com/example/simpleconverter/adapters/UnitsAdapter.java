package com.example.simpleconverter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.simpleconverter.R;
import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitViewHolder> {

    public interface OnUnitClickListener {
        void onUnitClick(String unit);
    }

    private final List<String> unitList;
    private final OnUnitClickListener listener;

    public UnitsAdapter(List<String> unitList, OnUnitClickListener listener) {
        this.unitList = unitList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unit, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        String unit = unitList.get(position);
        holder.textUnit.setText(unit);
        holder.itemView.setOnClickListener(v -> listener.onUnitClick(unit));
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    static class UnitViewHolder extends RecyclerView.ViewHolder {
        TextView textUnit;
        UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            textUnit = itemView.findViewById(R.id.textUnitName);
        }
    }
}
