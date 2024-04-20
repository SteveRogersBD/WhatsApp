package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.whatsapp.Adapters.FragmentAdapters;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentAdapters(getSupportFragmentManager()));
        binding.tabLayput.setupWithViewPager(binding.viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.log_out_menu)
        {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.chat_room)
        {
            Intent intentt = new Intent(MainActivity.this,ChatRoomActivity.class);
            startActivity(intentt);
        }
        else if(id==R.id.settings_menu)
        {
            Intent intenttt = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intenttt);
        }
        return super.onOptionsItemSelected(item);
    }
}