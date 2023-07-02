package com.yousefh.temi_walk_and_track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void startTheGame(View view) {
        Intent intent = new Intent(this, NumberOfPlayer.class);
        startActivity(intent);
    }
}