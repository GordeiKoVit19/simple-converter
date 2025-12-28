package com.example.simpleconverter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new UnitsFragment())
                    .commit();
        }

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
    }
}
