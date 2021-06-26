package com.example.libapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadBook extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final String DIR_SD = "MyFiles";
    String[] font = { "sans-serif", "monospace"};

//    Блок переменных ImageView
    ImageButton imageButtonSettings;
    ImageButton imageButtonBack;

//    Блок переменных EditText
    EditText editTextSearch;

//    Блок переменных ConstraintLayout
    ConstraintLayout constraintLayout;

//    Блок переменных Boolean
    Boolean settings_active = false;

//    Блок переменных textView
    TextView textViewSizeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_adapter_for_droplist, font);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);



//        // проверяем доступность SD
//        if (!Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
//            return;
//        }
//        StringBuilder text = new StringBuilder();
//        try {
//            // открываем поток для чтения
//            BufferedReader br = new BufferedReader(new FileReader(DataBook.nameBook));
//            String line;
//            while ((line = br.readLine()) != null) {
//                text.append(line);
//                text.append('\n');
//            }
//            br.close() ;
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        TextView tv = (TextView)findViewById(R.id.textView2);
//        tv.setText(text.toString()); ////Set the text to text view.

    }

    @SuppressLint("NonConstantResourceId")
    public void click (View view){

        imageButtonSettings = (ImageButton) findViewById(R.id.imageButton_settings);
        imageButtonBack = (ImageButton) findViewById(R.id.imageButton_back);
        editTextSearch = (EditText) findViewById(R.id.editText_search);
        textViewSizeText = (TextView) findViewById(R.id.textView_size_txt);

//        switch/case для нажатия на иконку в header
        switch (view.getId()){

//            Нажатие на иконку назад
            case R.id.imageButton_back:
                Intent intent = new Intent(this, MenuScreen.class );
                startActivity(intent);
                break;

//            Нажатие на иконку настройки
            case R.id.imageButton_settings:
                constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout_settings);
                if(!settings_active){
                    constraintLayout.animate().setDuration(600).translationY(-560).start();
                    imageButtonSettings.animate().setDuration(600).rotationBy(120).start();
                    settings_active = true;
                }
                else {
                    constraintLayout.animate().setDuration(600).translationY(560).start();
                    imageButtonSettings.animate().setDuration(600).rotationBy(-120).start();
                    settings_active = false;
                }
                break;

//            Нажатие на кнопку +А
            case R.id.button_minus:
                int size = Integer.parseInt(textViewSizeText.getText().toString());
                size--;
                textViewSizeText.setText(String.valueOf(size));

                break;

//            Нажатие на кнопку -А
            case R.id.button_plus:
                int size2 = Integer.parseInt(textViewSizeText.getText().toString());
                size2++;
                textViewSizeText.setText(String.valueOf(size2));

                break;

            default:
                break;
        }
    }
}