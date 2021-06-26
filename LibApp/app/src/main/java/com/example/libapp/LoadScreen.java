package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadScreen extends AppCompatActivity {
    ImageView imageViewLoad;

    TextView textViewLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen);

        imageViewLoad = (ImageView) findViewById(R.id.imageView_book_load);

        Runnable r = new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent(LoadScreen.this, ReadBook.class);
                startActivity(intent);
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 3000);
    }

    public void open(View view) {

        Intent intent = new Intent(this, ReadBook.class);
        startActivity(intent);
    }
}