package com.example.lab4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.lab4.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.icon_home) {
                loadFragment(new HomeFragment());
            } else if (itemId == R.id.icon_add) {
                loadFragment(new AddFragment());
            } else if (itemId == R.id.icon_detail_expense) {
                loadFragment(new ViewFragment());
            }
            return true;
        });


        // Set the default selected item
        binding.btnNav.setSelectedItemId(R.id.icon_home);
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
