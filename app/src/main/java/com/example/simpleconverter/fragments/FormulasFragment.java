package com.example.simpleconverter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.simpleconverter.R;
import com.example.simpleconverter.adapters.UnitsAdapter;
import com.example.simpleconverter.databinding.FragmentUnitsBinding;

import java.util.Arrays;
import java.util.List;

public class FormulasFragment extends Fragment {

    private FragmentUnitsBinding binding;
    private List<String> formulasList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUnitsBinding.inflate(inflater, container, false);

        formulasList = Arrays.asList(getResources().getStringArray(R.array.formulas_list));

        UnitsAdapter adapter = new UnitsAdapter(formulasList, formula -> {
            ConversionFragment fragment = ConversionFragment.newInstance(formula);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        binding.recyclerViewUnits.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewUnits.setAdapter(adapter);

        return binding.getRoot();
    }
}
