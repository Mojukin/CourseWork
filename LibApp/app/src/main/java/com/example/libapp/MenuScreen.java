package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MenuScreen extends AppCompatActivity {

    //Переменная для работы с БД
    private DatabaseHelperBook mDBHelper;
    private SQLiteDatabase mDb;

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

//        Обязательный кусок кода из инета
        mDBHelper = new DatabaseHelperBook(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

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

                String[] projection = {
                        DatabaseHelperBook.ID_BOOK,
                        DatabaseHelperBook.ID_AUTHOR,
                        DatabaseHelperBook.NAME,
                        DatabaseHelperBook.PATHIMAGE,
                        DatabaseHelperBook.CENSURE,
                        DatabaseHelperBook.ISAUTHORIZE,
                        DatabaseHelperBook.PATHONTEXT,
                        DatabaseHelperBook.NEW,
                        DatabaseHelperBook.POPULAR
                };

                if(DataUser._idUser!=0){
                    String selection = DatabaseHelperBook.NEW + "=?";


                    String s = "1";
//                Строка для критерия выбора из базы
                    String[] selectionArgs = {s};

//                Создаем запрос к базе данных
                    Cursor cursor = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection, selection, selectionArgs, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor.getInt(idBookColumnIndex);
                        int idAuthor = cursor.getInt(idAuthorColumnIndex);
                        String currentName = cursor.getString(nameColumnIndex);
                        String pathimage = cursor.getString(pathimageColumnIndex);
                        int censure = cursor.getInt(censureColumnIndex);
                        int isauthoriz = cursor.getInt(isauthorizColumnIndex);
                        String pathontext = cursor.getString(pathontextColumnIndex);
                        int _new = cursor.getInt(newColumnIndex);
                        int popular = cursor.getInt(popularColumnIndex);

                        books.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }
                    // начальная инициализация списка
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter = new StateAdapter(this, books);
                    // устанавливаем для списка адаптер
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    cursor.close();
                }
                else{
                    String selection = DatabaseHelperBook.NEW + "=? AND " + DatabaseHelperBook.ISAUTHORIZE + "=?";

//                Строка для критерия выбора из базы
                    String[] selectionArgs = {"1", "0"};

//                Создаем запрос к базе данных
                    Cursor cursor = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection, selection, selectionArgs, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex = cursor.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor.getInt(idBookColumnIndex);
                        int idAuthor = cursor.getInt(idAuthorColumnIndex);
                        String currentName = cursor.getString(nameColumnIndex);
                        String pathimage = cursor.getString(pathimageColumnIndex);
                        int censure = cursor.getInt(censureColumnIndex);
                        int isauthoriz = cursor.getInt(isauthorizColumnIndex);
                        String pathontext = cursor.getString(pathontextColumnIndex);
                        int _new = cursor.getInt(newColumnIndex);
                        int popular = cursor.getInt(popularColumnIndex);

                        books.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }
                    // начальная инициализация списка
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter = new StateAdapter(this, books);
                    // устанавливаем для списка адаптер
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    cursor.close();
                }


                break;

//            Нажатие на слово Устаревшее
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


                String[] projection2 = {
                        DatabaseHelperBook.ID_BOOK,
                        DatabaseHelperBook.ID_AUTHOR,
                        DatabaseHelperBook.NAME,
                        DatabaseHelperBook.PATHIMAGE,
                        DatabaseHelperBook.CENSURE,
                        DatabaseHelperBook.ISAUTHORIZE,
                        DatabaseHelperBook.PATHONTEXT,
                        DatabaseHelperBook.NEW,
                        DatabaseHelperBook.POPULAR
                };

                if(DataUser._idUser!=0){
                    String selection2 = DatabaseHelperBook.NEW + "=?";

//                Строка для критерия выбора из базы
                    String[] selectionArgs2 = {"0"};

//                Создаем запрос к базе данных
                    Cursor cursor2 = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection2, selection2, selectionArgs2, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books2 = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor2.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor2.getInt(idBookColumnIndex2);
                        int idAuthor = cursor2.getInt(idAuthorColumnIndex2);
                        String currentName = cursor2.getString(nameColumnIndex2);
                        String pathimage = cursor2.getString(pathimageColumnIndex2);
                        int censure = cursor2.getInt(censureColumnIndex2);
                        int isauthoriz = cursor2.getInt(isauthorizColumnIndex2);
                        String pathontext = cursor2.getString(pathontextColumnIndex2);
                        int _new = cursor2.getInt(newColumnIndex2);
                        int popular = cursor2.getInt(popularColumnIndex2);

                        books2.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }

                    // начальная инициализация списка
                    RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter2 = new StateAdapter(this, books2);
                    // устанавливаем для списка адаптер
                    recyclerView2.setAdapter(adapter2);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    cursor2.close();

                }
                else {
                    String selection2 = DatabaseHelperBook.NEW + "=? AND " + DatabaseHelperBook.ISAUTHORIZE + "=?";

//                Строка для критерия выбора из базы
                    String[] selectionArgs2 = {"0", "0"};

//                Создаем запрос к базе данных
                    Cursor cursor2 = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection2, selection2, selectionArgs2, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books2 = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor2.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor2.getInt(idBookColumnIndex2);
                        int idAuthor = cursor2.getInt(idAuthorColumnIndex2);
                        String currentName = cursor2.getString(nameColumnIndex2);
                        String pathimage = cursor2.getString(pathimageColumnIndex2);
                        int censure = cursor2.getInt(censureColumnIndex2);
                        int isauthoriz = cursor2.getInt(isauthorizColumnIndex2);
                        String pathontext = cursor2.getString(pathontextColumnIndex2);
                        int _new = cursor2.getInt(newColumnIndex2);
                        int popular = cursor2.getInt(popularColumnIndex2);

                        books2.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }

                    // начальная инициализация списка
                    RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter2 = new StateAdapter(this, books2);
                    // устанавливаем для списка адаптер
                    recyclerView2.setAdapter(adapter2);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    cursor2.close();
                }

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

                String[] projection3 = {
                        DatabaseHelperBook.ID_BOOK,
                        DatabaseHelperBook.ID_AUTHOR,
                        DatabaseHelperBook.NAME,
                        DatabaseHelperBook.PATHIMAGE,
                        DatabaseHelperBook.CENSURE,
                        DatabaseHelperBook.ISAUTHORIZE,
                        DatabaseHelperBook.PATHONTEXT,
                        DatabaseHelperBook.NEW,
                        DatabaseHelperBook.POPULAR
                };

                if(DataUser._idUser!=0){

                    String selection3 = DatabaseHelperBook.POPULAR + "=?";

//                Строка для критерия выбора из базы
                    String[] selectionArgs3 = {"1"};

//                Создаем запрос к базе данных
                    Cursor cursor3 = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection3, selection3, selectionArgs3, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books3 = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor3.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor3.getInt(idBookColumnIndex3);
                        int idAuthor = cursor3.getInt(idAuthorColumnIndex3);
                        String currentName = cursor3.getString(nameColumnIndex3);
                        String pathimage = cursor3.getString(pathimageColumnIndex3);
                        int censure = cursor3.getInt(censureColumnIndex3);
                        int isauthoriz = cursor3.getInt(isauthorizColumnIndex3);
                        String pathontext = cursor3.getString(pathontextColumnIndex3);
                        int _new = cursor3.getInt(newColumnIndex3);
                        int popular = cursor3.getInt(popularColumnIndex3);

                        books3.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }

                    // начальная инициализация списка
                    RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter3 = new StateAdapter(this, books3);
                    // устанавливаем для списка адаптер
                    recyclerView3.setAdapter(adapter3);
                    recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    cursor3.close();

                }
                else {
                    String selection3 = DatabaseHelperBook.POPULAR + "=? AND " + DatabaseHelperBook.ISAUTHORIZE + "=?";

//                Строка для критерия выбора из базы
                    String[] selectionArgs3 = {"1", "0"};

//                Создаем запрос к базе данных
                    Cursor cursor3 = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection3, selection3, selectionArgs3, null, null, null);

//                Получаем индексы столбцов из таблицы
                    int idBookColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex3 = cursor3.getColumnIndex(DatabaseHelperBook.POPULAR);

                    ArrayList<Book> books3 = new ArrayList<>();
//                Цикл для переборы всех столбцов
                    while (cursor3.moveToNext()) {

//                    Получаем данные из таблицы
                        int idBook = cursor3.getInt(idBookColumnIndex3);
                        int idAuthor = cursor3.getInt(idAuthorColumnIndex3);
                        String currentName = cursor3.getString(nameColumnIndex3);
                        String pathimage = cursor3.getString(pathimageColumnIndex3);
                        int censure = cursor3.getInt(censureColumnIndex3);
                        int isauthoriz = cursor3.getInt(isauthorizColumnIndex3);
                        String pathontext = cursor3.getString(pathontextColumnIndex3);
                        int _new = cursor3.getInt(newColumnIndex3);
                        int popular = cursor3.getInt(popularColumnIndex3);

                        books3.add(new Book(idBook, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

                    }

                    // начальная инициализация списка
                    RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.list);
                    // создаем адаптер
                    StateAdapter adapter3 = new StateAdapter(this, books3);
                    // устанавливаем для списка адаптер
                    recyclerView3.setAdapter(adapter3);
                    recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    cursor3.close();

                }



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