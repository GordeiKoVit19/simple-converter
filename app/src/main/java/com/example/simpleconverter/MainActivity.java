package com.example.simpleconverter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.simpleconverter.databinding.ActivityMainBinding;
import com.example.simpleconverter.fragments.FormulasFragment;
import com.example.simpleconverter.fragments.UnitsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new UnitsFragment())
                .commit();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_units) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new UnitsFragment())
                        .commit();
                return true;
            } else if (id == R.id.nav_formulas) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FormulasFragment())
                        .commit();
                return true;
            }
            return false;
        });


        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
