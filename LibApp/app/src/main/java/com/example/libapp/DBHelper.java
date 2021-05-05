package com.example.libapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

//    Const for work db
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Library";
    public static final String TABLE_CONTACTS_AUTHOR = "author";
    public static final String ID_AUTHOR = "_idauthor";
    public static final String NAME_AUTHOR = "name";
    public static final String SURNAME_AUTHOR = "surname";
    public static final String PATHRONYMIC_AUTHOR = "pathronymic";
    public static final String DATE_OF_BIRTH_AUTHOR = "dateofbirth";



    public DBHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

//    Create db if it not found
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS_AUTHOR + "(" + ID_AUTHOR + " integer primary key," + NAME_AUTHOR + " text," + SURNAME_AUTHOR + " text," + PATHRONYMIC_AUTHOR + " text," + DATE_OF_BIRTH_AUTHOR + " text" +")");

    }

//    Upgrade version db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS_AUTHOR);

        onCreate(db);
    }
}
