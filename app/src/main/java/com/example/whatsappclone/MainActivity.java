package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Setting the color of actionBar and it's title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Replace R.color.colorPrimary with your color resource
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().
                    getColor(R.color.colorPrimary)));
            SpannableString spannableString = new SpannableString("WhatsApp");
            spannableString.setSpan(new ForegroundColorSpan(Color.WHITE),0,spannableString.length(), 0);
            actionBar.setTitle(spannableString);
        }


        auth = FirebaseAuth.getInstance();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logOut_menu)
        {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this,SIgn_in_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.settings_menu)
        {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}