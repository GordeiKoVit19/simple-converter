package com.example.simpleconverter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simpleconverter.R;

public class ConversionFragment extends Fragment {

    private static final String ARG_UNIT = "unit";

    public static ConversionFragment newInstance(String unit) {
        ConversionFragment fragment = new ConversionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_UNIT, unit);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_conversion, container, false);

        String unit = "";
        if (getArguments() != null) {
            unit = getArguments().getString(ARG_UNIT);
        }

        EditText input = view.findViewById(R.id.editTextValue);
        Button convertButton = view.findViewById(R.id.buttonConvert);
        Button backButton = view.findViewById(R.id.buttonBack);
        TextView result = view.findViewById(R.id.textViewResult);

        String finalUnit = unit;
        convertButton.setOnClickListener(v -> {
            if (!input.getText().toString().isEmpty()) {
                double value = Double.parseDouble(input.getText().toString());
                double converted = convertValue(finalUnit, value);
                result.setText(String.valueOf(converted));
            }
        });

        backButton.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });

        return view;
    }

    private double convertValue(String unit, double value) {
        switch (unit) {
            case "Метры → Сантиметры":
            case "Meters → Centimeters":
                return value * 100;

            case "Сантиметры → Метры":
            case "Centimeters → Meters":
                return value / 100;

            case "Минуты → Секунды":
            case "Minutes → Seconds":
                return value * 60;

            case "Секунды → Минуты":
            case "Seconds → Minutes":
                return value / 60;

            default:
                return value;
        }
    }
}
