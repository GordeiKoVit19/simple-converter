package com.example.simpleconverter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.simpleconverter.adapters.UnitsAdapter;
import com.example.simpleconverter.databinding.FragmentUnitsBinding;

import java.util.ArrayList;
import java.util.List;

public class UnitsFragment extends Fragment {

    private FragmentUnitsBinding binding;
    private List<String> unitsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUnitsBinding.inflate(inflater, container, false);
        unitsList = new ArrayList<>();
        unitsList.add("Метры → Сантиметры");
        unitsList.add("Сантиметры → Метры");
        unitsList.add("Минуты → Секунды");
        unitsList.add("Секунды → Минуты");

        UnitsAdapter adapter = new UnitsAdapter(unitsList, unit -> {
            Toast.makeText(getContext(), "Выбрано: " + unit, Toast.LENGTH_SHORT).show();
        });

        binding.recyclerViewUnits.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewUnits.setAdapter(adapter);

        return binding.getRoot();
    }
}
