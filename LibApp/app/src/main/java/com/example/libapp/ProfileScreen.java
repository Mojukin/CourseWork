package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.TextView;

public class ProfileScreen extends AppCompatActivity {

    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
    }
    @SuppressLint("NonConstantResourceId")
    public void ClickFooter (View view){

        textViewProfile= (TextView) findViewById(R.id.textViewProfile);
        textViewFolder = (TextView) findViewById(R.id.textViewFolder);
        textViewSearch = (TextView) findViewById(R.id.textViewSearch);
        textViewMyBook = (TextView) findViewById(R.id.textViewMyBook);

        switch (view.getId()){

            case R.id.imageViewFolder:
                Intent intent = new Intent(this, MenuScreen.class );
                startActivity(intent);

                break;

            case R.id.imageViewMyBook:
                Intent intent2 = new Intent(this, FavoriteBook.class );
                startActivity(intent2);

                break;

            case R.id.imageViewSearchFooter:

                break;

            case R.id.imageViewProfile:

                break;

            default:
                break;
        }
    }

    public void  ClickBut (View view){
        switch (view.getId()){

            case R.id.button_aythoriz_reg:
                Intent intent = new Intent(this, AuthorizScreen.class );
                startActivity(intent);
                break;
            case R.id.button_refactor_data:
                Intent intent2 = new Intent(this, RefactorDataUser.class );
                startActivity(intent2);
                break;
        }
    }
}