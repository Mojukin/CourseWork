package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AuthorizScreen extends AppCompatActivity {

//    Блок переменных TextView
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;
    TextView textViewUser;
    EditText editTextEmail;
    EditText editTextPassword;

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authoriz_screen);

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
                Intent intent3 = new Intent(this, ProfileScreen.class );
                startActivity(intent3);
                break;

            default:
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void ClickBut (View view){

//        Нахождение EditText по id
        editTextEmail= (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_pass2);
        textViewUser = (TextView) findViewById(R.id.textView_user);

//        Обязательный кусок кода из инета
        mDBHelper = new DatabaseHelper(this);
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

//        switch/case для нажатия на кнопку
        switch (view.getId()) {

//            Нажите на кнопку войти
            case R.id.button_enter:

//                Строки в которы хранится введенная нами информация в editText
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

//                Имена столбцов которые мы выбираем из базы
                String[] projection = {
                        DatabaseHelper._ID_USER,
                        DatabaseHelper.NAME_USER,
                        DatabaseHelper.SURNAME_USER,
                        DatabaseHelper.EMAIL_USER,
                        DatabaseHelper.DATEOFBIRTH_USER,
                        DatabaseHelper.PASSWORD_USER
                };

//                Строка для типа выбора из базы
                String selection = DatabaseHelper.EMAIL_USER + "=?";

//                Строка для критерия выбора из базы
                String[] selectionArgs = {email};

//                Создаем запрос к базе данных
                Cursor cursor = mDb.query(DatabaseHelper.USER_TABLE, projection, selection, selectionArgs, null, null, null);

//                Получаем индексы столбцов из таблицы
                int idColumnIndex = cursor.getColumnIndex(DatabaseHelper._ID_USER);
                int nameColumnIndex = cursor.getColumnIndex(DatabaseHelper.NAME_USER);
                int surnameColumnIndex = cursor.getColumnIndex(DatabaseHelper.SURNAME_USER);
                int emailColumnIndex = cursor.getColumnIndex(DatabaseHelper.EMAIL_USER);
                int dateOfBirthColumnIndex = cursor.getColumnIndex(DatabaseHelper.DATEOFBIRTH_USER);
                int passwordColumnIndex = cursor.getColumnIndex(DatabaseHelper.PASSWORD_USER);

//                Цикл для переборы всех столбцов
                while (cursor.moveToNext()) {

//                    Получаем данные из таблицы
                    int id = cursor.getInt(idColumnIndex);
                    String currentName = cursor.getString(nameColumnIndex);
                    String currentSurname = cursor.getString(surnameColumnIndex);
                    String currentEmail = cursor.getString(emailColumnIndex);
                    String currentDateOfBirth = cursor.getString(dateOfBirthColumnIndex);
                    String currentPassword = cursor.getString(passwordColumnIndex);

//                    Проверяем на сходство введенных данных и данных из базы
                    if(email.equals(currentEmail) && password.equals(currentPassword)){

//                    Помещаем данные в экземпляр класса
                        DataUser._idUser = id;
                        DataUser.nameUser = currentName;
                        DataUser.surnameUser = currentSurname;
                        DataUser.emailUser = currentEmail;
                        DataUser.date_of_birthUser = currentDateOfBirth;
                        DataUser.passwordUser = currentPassword;

                        Toast.makeText(getApplicationContext(), "Авторизация успешна!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(this, ProfileScreen.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Неверно введен логин или пароль!", Toast.LENGTH_LONG).show();
                    }

                }

                cursor.close();
                return;

//            Нажатие на кнопку зарегистрироваться
            case R.id.button_reg:
                Intent intent2 = new Intent(this, RegisterScreen.class);
                startActivity(intent2);
                return;

            default:
                return;
        }
    }
}
