package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RegisterScreen extends AppCompatActivity {

//    Блок переменных TextView
    TextView textViewProfile;
    TextView textViewFolder;
    TextView textViewSearch;
    TextView textViewMyBook;

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
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
    public void ClickReg (View view){
        EditText editTextEmail;
        EditText editTextName;
        EditText editTextSurname;
        EditText editTextPassword;
        EditText editTextRetPassword;
        EditText editTextDate;

        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextSurname = (EditText) findViewById(R.id.editText_surname);
        editTextDate = (EditText) findViewById(R.id.editText_dateofbirth);
        editTextPassword = (EditText) findViewById(R.id.editText_pass);
        editTextRetPassword = (EditText) findViewById(R.id.editText_pass2);

        String Email = editTextEmail.getText().toString();
        String Name= editTextName.getText().toString();
        String dateofbirth= editTextDate.getText().toString();
        String Surname= editTextSurname.getText().toString();
        String Password= editTextPassword.getText().toString();
        String RetPassword= editTextRetPassword.getText().toString();

        if(!Email.equals("") || !Name.equals("") || !dateofbirth.equals("") || !Surname.equals("") || !Password.equals("") || !RetPassword.equals(""))
        {
            if (Password.equals(RetPassword)){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.NAME_USER, Name);
                contentValues.put(DatabaseHelper.SURNAME_USER, Surname);
                contentValues.put(DatabaseHelper.EMAIL_USER, Email);
                contentValues.put(DatabaseHelper.DATEOFBIRTH_USER, dateofbirth);
                contentValues.put(DatabaseHelper.PASSWORD_USER, Password);

                mDBHelper = new DatabaseHelper(this);
                try {
                    mDBHelper.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }
                try {
                    mDb = mDBHelper.getWritableDatabase();
                    mDb.insert(DatabaseHelper.USER_TABLE, null, contentValues);
                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Пароли не совпадают!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Заполните все данные!", Toast.LENGTH_LONG).show();
        }
    }
}