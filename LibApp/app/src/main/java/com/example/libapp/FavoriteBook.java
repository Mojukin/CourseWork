package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteBook extends AppCompatActivity {

//    Блок переменных TextView
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;
    List<LikeBookByUser> lbbu;

    //Переменная для работы с БД
    private DatabaseHelperFavoriteBook mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_book);

        if(DataUser._idUser==0){
            Toast.makeText(getApplicationContext(), "Для просмотра любимых книг войдите в аккаунт!", Toast.LENGTH_LONG).show();
        }
        else{
//            Обязательный кусок кода из инета
            mDBHelper = new DatabaseHelperFavoriteBook(this);
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

            String[] projection = {
                    DatabaseHelperFavoriteBook.ID_BOOK,
                    DatabaseHelperFavoriteBook.ID_USER,
                    DatabaseHelperFavoriteBook._ID_LIKE
            };
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

                String selection = DatabaseHelperFavoriteBook.ID_USER + "=?";
                String selection2 = DatabaseHelperBook.ID_BOOK + "=?";

                int id = DataUser._idUser;

                String idi = String.valueOf(id);
//                Строка для критерия выбора из базы
                String[] selectionArgs = {idi};


//                Создаем запрос к базе данных
                Cursor cursor = mDb.query(DatabaseHelperFavoriteBook.LIKEBOOKBYUSER_TABLE, projection, selection, selectionArgs, null, null, null);

//                Получаем индексы столбцов из таблицы
                int idBookColumnIndex = cursor.getColumnIndex(DatabaseHelperFavoriteBook.ID_BOOK);
                int idAuthorColumnIndex = cursor.getColumnIndex(DatabaseHelperFavoriteBook.ID_USER);
                int idLikeColumnIndex = cursor.getColumnIndex(DatabaseHelperFavoriteBook._ID_LIKE);

                ArrayList<Book> books2 = new ArrayList<>();
//                Цикл для переборы всех столбцов
                while (cursor.moveToNext()) {

//                    Получаем данные из таблицы
                    int idBook = cursor.getInt(idBookColumnIndex);
                    String idb = String.valueOf(idBook);
//                Строка для критерия выбора из базы
                    String[] selectionArgs2 = {idb};
                    Cursor cursor2 = mDb.query(DatabaseHelperBook.BOOK_TABLE, projection2, selection2, selectionArgs2, null, null, null);
                    cursor2.moveToFirst();
                    int idBookColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_BOOK);
                    int idAuthorColumnIndex2 = cursor2.getColumnIndex(DatabaseHelperBook.ID_AUTHOR);
                    int nameColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.NAME);
                    int pathimageColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.PATHIMAGE);
                    int censureColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.CENSURE);
                    int isauthorizColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.ISAUTHORIZE);
                    int pathontextColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.PATHONTEXT);
                    int newColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.NEW);
                    int popularColumnIndex = cursor2.getColumnIndex(DatabaseHelperBook.POPULAR);


//                    Получаем данные из таблицы
                    int idBook2 = cursor2.getInt(idBookColumnIndex2);
                    int idAuthor = cursor2.getInt(idAuthorColumnIndex2);
                    String currentName = cursor2.getString(nameColumnIndex);
                    String pathimage = cursor2.getString(pathimageColumnIndex);
                    int censure = cursor2.getInt(censureColumnIndex);
                    int isauthoriz = cursor2.getInt(isauthorizColumnIndex);
                    String pathontext = cursor2.getString(pathontextColumnIndex);
                    int _new = cursor2.getInt(newColumnIndex);
                    int popular = cursor2.getInt(popularColumnIndex);

                    books2.add(new Book(idBook2, idAuthor, currentName, pathimage, censure, isauthoriz, pathontext, _new, popular));

//                    books.add(new Book(idBook, idAuthor, currentName));
                    cursor2.close();
                }
                // начальная инициализация списка
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list2);
                // создаем адаптер
                StateAdapter adapter = new StateAdapter(this, books2);
                // устанавливаем для списка адаптер
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                cursor.close();


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