package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class ProfileScreen extends AppCompatActivity {
//    Блок переменных TextView
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;
    TextView textViewUser;
    TextView textViewInitial;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        textViewUser = (TextView) (findViewById(R.id.textView_user));
        textViewInitial = (TextView) findViewById(R.id.textView_initial);

        if(DataUser._idUser != 0) {
            char[] chArray = DataUser.nameUser.toCharArray();

            String s = String.valueOf(chArray[0]);

                textViewInitial.setText(s);
                textViewUser.setText(DataUser.nameUser + " " + DataUser.surnameUser);
            }
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
                Intent intent2 = new Intent(this, FavoriteBook.class );
                startActivity(intent2);

                break;

//            Нажатие на иконку поиска
            case R.id.imageViewSearchFooter:

                break;

//            Нажатие на иконку профиля
            case R.id.imageViewProfile:

                break;

            default:
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void  ClickBut (View view){

//        switch/case для нажатия на кнопку
        switch (view.getId()){

//            Нажатие на кнопку авторизация/регистрация
            case R.id.button_aythoriz_reg:
                Intent intent = new Intent(this, AuthorizScreen.class );
                startActivity(intent);

                break;

//            Нажатие на кнопку редактирование данных
            case R.id.button_refactor_data:
                Intent intent2 = new Intent(this, RefactorDataUser.class );
                startActivity(intent2);

                break;
        }
    }
}