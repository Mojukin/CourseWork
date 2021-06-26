package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class FavoriteBook extends AppCompatActivity {

//    Блок переменных TextView
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;
    List<LikeBookByUser> lbbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_book);
    }

    @SuppressLint("NonConstantResourceId")
    public void ClickFooter (View view){

//        Нахождение TextView по id
        textViewProfile= (TextView) findViewById(R.id.textViewProfile);
        textViewFolder = (TextView) findViewById(R.id.textViewFolder);
        textViewSearch = (TextView) findViewById(R.id.textViewSearch);
        textViewMyBook = (TextView) findViewById(R.id.textViewMyBook);
//        switch/case для нажатия на иконку в футере
        switch (view.getId()){

//            Нажатие на иконку каталог
            case R.id.imageViewFolder:
                Intent intent = new Intent(this, MenuScreen.class );
                startActivity(intent);

                break;

//            Нажатие на иконку мои книги
            case R.id.imageViewMyBook:

                break;

//            Нажатие на иконку поиска
            case R.id.imageViewSearchFooter:

                break;

//            Нажатие на иконку профиля
            case R.id.imageViewProfile:
                Intent intent3 = new Intent(this, ProfileScreen.class );
                startActivity(intent3);

                break;

            default:
                break;
        }
    }
}