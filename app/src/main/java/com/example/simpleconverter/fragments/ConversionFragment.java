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

        EditText input1 = view.findViewById(R.id.editTextValue1);
        EditText input2 = view.findViewById(R.id.editTextValue2);
        Button convertButton = view.findViewById(R.id.buttonConvert);
        Button backButton = view.findViewById(R.id.buttonBack);
        TextView result = view.findViewById(R.id.textViewResult);

        if (unit.equals("Площадь прямоугольника") || unit.equals("Периметр прямоугольника")
                || unit.equals("Rectangle Area") || unit.equals("Rectangle Perimeter")) {
            input2.setVisibility(View.VISIBLE);
        }

        String finalUnit = unit;
        convertButton.setOnClickListener(v -> {
            if (!input1.getText().toString().isEmpty()) {
                double value1 = Double.parseDouble(input1.getText().toString());
                double value2 = 0;
                if (input2.getVisibility() == View.VISIBLE && !input2.getText().toString().isEmpty()) {
                    value2 = Double.parseDouble(input2.getText().toString());
                }
                double converted = convertValue(finalUnit, value1, value2);
                result.setText(String.valueOf(converted));
            }
        });

        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

    private double convertValue(String unit, double value1, double value2) {
        switch (unit) {
            case "Метры → Сантиметры":
            case "Meters → Centimeters":
                return value1 * 100;
            case "Сантиметры → Метры":
            case "Centimeters → Meters":
                return value1 / 100;
            case "Минуты → Секунды":
            case "Minutes → Seconds":
                return value1 * 60;
            case "Секунды → Минуты":
            case "Seconds → Minutes":
                return value1 / 60;
            case "Площадь квадрата":
            case "Square Area":
                return value1 * value1;
            case "Периметр квадрата":
            case "Square Perimeter":
                return 4 * value1;
            case "Площадь прямоугольника":
            case "Rectangle Area":
                return value1 * value2;
            case "Периметр прямоугольника":
            case "Rectangle Perimeter":
                return 2 * (value1 + value2);
            default:
                return value1;
        }
    }
}
