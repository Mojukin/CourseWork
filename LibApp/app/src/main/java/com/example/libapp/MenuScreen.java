package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MenuScreen extends AppCompatActivity {

//    Блок переменных TextView
    TextView textViewNew;
    TextView textViewGenres;
    TextView textViewPopular;
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;

//    Блок переменных EditText
    EditText editTextSearch;

//    Блок переменных String
    String textNew;
    String textGenres;
    String textPopular;

//    Блок переменных Boolean
    Boolean is_active_new = false;
    Boolean is_active_genres = false;
    Boolean is_active_popular = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

//        Нахождение TextView по id
        textViewNew = (TextView)findViewById(R.id.textViewNew);
        textViewGenres = (TextView)findViewById(R.id.textViewGenres);
        textViewPopular = (TextView)findViewById(R.id.textViewPopular);

//        Получаем изначальный текст из TextView
        textNew = (String)textViewNew.getText();
        textGenres = (String)textViewGenres.getText();
        textPopular = (String)textViewPopular.getText();

    }

    @SuppressLint("NonConstantResourceId")
    public void ClickHeader(View view){

//        Нахождение TextView по id
        textViewNew = (TextView)findViewById(R.id.textViewNew);
        textViewGenres = (TextView)findViewById(R.id.textViewGenres);
        textViewPopular = (TextView)findViewById(R.id.textViewPopular);
        
//        Получаем цвета из ресурсов
        Resources resources = getResources();
        int orangeColor = resources.getColor(R.color.orange_txt);
        int grayColor = resources.getColor(R.color.gray_txt);

//        Установка возможности нажатия на слово
        textViewNew.setClickable(true);
        textViewGenres.setClickable(true);
        textViewPopular.setClickable(true);

//        switch/case для нажатия на слово в header
        switch (view.getId()){

//            Нажатие на слово новинки
            case R.id.textViewNew:

//                Устанавливаем остальные элементы неактивными
                is_active_genres = false;
                is_active_popular = false;

//                Проверка активен ли элемент
                if (is_active_new){
                    textViewNew.setClickable(false);
                    break;
                }
//                Перекраска элементов в нужные цвета
                String str = (String) textViewNew.getText();
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new UnderlineSpan(), 0 , str.length() , 0);
                textViewNew.setTextColor(orangeColor);
                textViewPopular.setTextColor(grayColor);
                textViewGenres.setTextColor(grayColor);
                textViewPopular.setText(textPopular);
                textViewGenres.setText(textGenres);
                textViewNew.setText(spannableString);
                is_active_new = true;

                break;

//            Нажатие на слово Жанры
            case R.id.textViewGenres:

//                Устанавливаем остальные элементы неактивными
                is_active_new = false;
                is_active_popular = false;

//                Проверка активен ли элемент
                if (is_active_genres){
                    textViewGenres.setClickable(false);
                    break;
                }

//                Перекраска элементов в нужные цвета
                String str2 = (String) textViewGenres.getText();
                SpannableString spannableString2 = new SpannableString(str2);
                spannableString2.setSpan(new UnderlineSpan(), 0 , str2.length() , 0);
                textViewGenres.setTextColor(orangeColor);
                textViewPopular.setTextColor(grayColor);
                textViewNew.setTextColor(grayColor);
                textViewPopular.setText(textPopular);
                textViewNew.setText(textNew);
                textViewGenres.setText(spannableString2);
                is_active_genres = true;

                break;

//            Нажатие на слово популярное
            case R.id.textViewPopular:

//                Устанавливаем остальные элементы неактивными
                is_active_new = false;
                is_active_genres = false;

//                Проверка активен ли элемент
                if (is_active_popular){
                    textViewPopular.setClickable(false);
                    break;
                }

//                Перекраска элементов в нужные цвета
                String str3 = (String) textViewPopular.getText();
                SpannableString spannableString3 = new SpannableString(str3);
                spannableString3.setSpan(new UnderlineSpan(), 0 , str3.length() , 0);
                textViewPopular.setTextColor(orangeColor);
                textViewNew.setTextColor(grayColor);
                textViewGenres.setTextColor(grayColor);
                textViewNew.setText(textNew);
                textViewGenres.setText(textGenres);
                textViewPopular.setText(spannableString3);
                is_active_popular = true;

                break;

            default:
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void ClickFooter (View view){

//        Нахождение TextView по id
        textViewProfile= (TextView) findViewById(R.id.textViewProfile);
        textViewFolder = (TextView) findViewById(R.id.textViewFolder);
        textViewSearch = (TextView) findViewById(R.id.textViewSearch);
        textViewMyBook = (TextView) findViewById(R.id.textViewMyBook);

//        Нахождение EditText по id
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);

//        switch/case для нажатия на иконку в футере
        switch (view.getId()){

//            Нажатие на иконку каталог
            case R.id.imageViewFolder:

                break;

//            Нажатие на иконку мои книги
            case R.id.imageViewMyBook:
                Intent intent2 = new Intent(this, FavoriteBook.class );
                startActivity(intent2);

                break;

//            Нажатие на иконку поиска
            case R.id.imageViewSearchFooter:
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInputFromWindow(editTextSearch.getApplicationWindowToken(),InputMethodManager.SHOW_IMPLICIT, 0);
                view.setFocusableInTouchMode(true);
                editTextSearch.requestFocus();
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