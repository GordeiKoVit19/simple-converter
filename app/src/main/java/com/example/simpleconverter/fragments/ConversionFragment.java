package com.example.simpleconverter.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleconverter.R;
import com.example.simpleconverter.databinding.FragmentConversionBinding;
import com.example.simpleconverter.viewmodel.ConversionViewModel;

public class ConversionFragment extends Fragment {

    private static final String ARG_UNIT = "unit";
    private FragmentConversionBinding binding;
    private ConversionViewModel viewModel;
    private String unit;

    public static ConversionFragment newInstance(String unit) {
        ConversionFragment fragment = new ConversionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_UNIT, unit);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentConversionBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            unit = getArguments().getString(ARG_UNIT);
        }

        binding.textUnitTitle.setText(unit);

        viewModel = new ViewModelProvider(requireActivity()).get(ConversionViewModel.class);

        viewModel.getResult().observe(getViewLifecycleOwner(), res -> {
            if (res != null) {
                if (res == Math.floor(res)) {
                    binding.textViewResult.setText(String.valueOf(res.intValue()));
                } else {
                    binding.textViewResult.setText(String.valueOf(res));
                }
            } else {
                binding.textViewResult.setText(getString(R.string.text_result));
            }
        });

        String u = unit.trim().toLowerCase();
        if ((u.contains("прямоугольник") || u.contains("rectangle")) &&
                (u.contains("площадь") || u.contains("area") || u.contains("периметр") || u.contains("perimeter"))) {
            binding.layoutValue2.setVisibility(View.VISIBLE);
        } else {
            binding.layoutValue2.setVisibility(View.GONE);
        }

        binding.buttonConvert.setOnClickListener(v -> {
            String val1Text = binding.editTextValue1.getText().toString().trim();
            String val2Text = binding.editTextValue2.getText().toString().trim();

            if (val1Text.isEmpty()) {
                Toast.makeText(getContext(),
                        getString(R.string.hint_enter_value),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidNumber(val1Text)) {
                Toast.makeText(getContext(),
                        getString(R.string.invalid_input_toast),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double value1 = Double.parseDouble(val1Text);
            double value2 = 0;

            if (binding.layoutValue2.getVisibility() == View.VISIBLE) {
                if (val2Text.isEmpty()) {
                    Toast.makeText(getContext(),
                            getString(R.string.hint_enter_second_value_toast),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidNumber(val2Text)) {
                    Toast.makeText(getContext(),
                            getString(R.string.invalid_input_toast),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                value2 = Double.parseDouble(val2Text);
            }

            viewModel.convert(unit, value1, value2);
        });

        binding.textViewResult.setOnClickListener(v -> {
            String resultText = binding.textViewResult.getText().toString();
            if (!resultText.equals(getString(R.string.text_result))) {
                ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("result", resultText);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(),
                        getString(R.string.result_copied),
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return binding.getRoot();
    }

    private boolean isValidNumber(String input) {
        if (TextUtils.isEmpty(input)) return false;
        if (input.startsWith(".") || (input.startsWith("0") && input.length() > 1)) return false;
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
