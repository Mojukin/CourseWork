package com.example.libapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class ReadBook extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final String DIR_SD = "MyFiles";
    String[] font = { "sans-serif", "monospace"};

    Boolean sanserif = true;
    Boolean monospace = false;

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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                TextView textView = (TextView)findViewById(R.id.textView2);
                String item = (String)parent.getItemAtPosition(position);
                if(item == "sans-serif" && sanserif == false){
                    Typeface face=Typeface.createFromAsset(getAssets(), "font/sansserif.ttf");
                    textView.setTypeface(face);
                    monospace = false;
                    sanserif = true;
                }
                else if(item == "monospace" && monospace == false) {
                    Typeface face=Typeface.createFromAsset(getAssets(),"font/monospace.otf");
                    textView.setTypeface(face);
                    monospace = true;
                    sanserif = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner.setOnItemSelectedListener(itemSelectedListener);
        try {
            TextView textView = (TextView)findViewById(R.id.textView2);
            String book = DataBook.name;

            if(DataBook.name.equals("Алхимик")){
                InputStream is = this.getResources().openRawResource(R.raw.alchimick);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Ангелы и демоны")){
                InputStream is = this.getResources().openRawResource(R.raw.angel_and_demons);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Анна Каренина")){
                InputStream is = this.getResources().openRawResource(R.raw.anna_karenina);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Бесы")){
                InputStream is = this.getResources().openRawResource(R.raw.besy);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Зов Ктулху")){
                InputStream is = this.getResources().openRawResource(R.raw.call_of_ctulchu);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Код да Винчи")){
                InputStream is = this.getResources().openRawResource(R.raw.da_vinci_code);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Дагон")){
                InputStream is = this.getResources().openRawResource(R.raw.dagon);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Идиот")){
                InputStream is = this.getResources().openRawResource(R.raw.idiot);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Точка обмана")){
                InputStream is = this.getResources().openRawResource(R.raw.point_of_illision);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Преступление и наказание")){
                InputStream is = this.getResources().openRawResource(R.raw.prestuplenie_i_nakazanie);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }
            else if(DataBook.name.equals("Война и мир")){
                InputStream is = this.getResources().openRawResource(R.raw.voina_i_mir);
                byte[] buffer = new byte[is.available()];
                while (is.read(buffer) != -1);
                String jsontext = new String(buffer);
                textView.setText(jsontext);
            }

        } catch (Exception e) {

        }
            ((EditText)findViewById(R.id.editText_search)).setOnFocusChangeListener(new View.OnFocusChangeListener(){
                TextView tv = (TextView) findViewById(R.id.textView2);
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    editTextSearch = (EditText) findViewById(R.id.editText_search);
                    String criteria = editTextSearch.getText().toString();
                    String fullText = tv.getText().toString();
                    if(fullText.indexOf(criteria) != -1){
                        int startIndex = fullText.indexOf(criteria);
                        int stopIndex = startIndex + criteria.length();

                        Spannable WordtoSpan = new SpannableString(fullText);
                        WordtoSpan.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, stopIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tv.setText(WordtoSpan);

                    }

                    if(!hasFocus){
                    }

                }

            });

        }
    @SuppressLint("NonConstantResourceId")
    public void click (View view) {

        imageButtonSettings = (ImageButton) findViewById(R.id.imageButton_settings);
        imageButtonBack = (ImageButton) findViewById(R.id.imageButton_back);
        editTextSearch = (EditText) findViewById(R.id.editText_search);
        textViewSizeText = (TextView) findViewById(R.id.textView_size_txt);
        TextView textView = (TextView) findViewById(R.id.textView2);

//        switch/case для нажатия на иконку в header
        switch (view.getId()) {

//            Нажатие на иконку назад
            case R.id.imageButton_back:
                Intent intent = new Intent(this, MenuScreen.class);
                startActivity(intent);
                break;

//            Нажатие на иконку настройки
            case R.id.imageButton_settings:
                constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout_settings);
                if (!settings_active) {
                    constraintLayout.animate().setDuration(600).translationY(-560).start();
                    imageButtonSettings.animate().setDuration(600).rotationBy(120).start();
                    settings_active = true;
                } else {
                    constraintLayout.animate().setDuration(600).translationY(560).start();
                    imageButtonSettings.animate().setDuration(600).rotationBy(-120).start();
                    settings_active = false;
                }
                break;

//            Нажатие на кнопку +А
            case R.id.button_minus:
                int size = Integer.parseInt(textViewSizeText.getText().toString());
                size--;
                textView.setTextSize(size);
                textViewSizeText.setText(String.valueOf(size));


                break;

//            Нажатие на кнопку -А
            case R.id.button_plus:
                int size2 = Integer.parseInt(textViewSizeText.getText().toString());
                size2++;
                textView.setTextSize(size2);
                textViewSizeText.setText(String.valueOf(size2));

                break;

            default:
                break;
        }
    }
    public void CLic(View v){
        editTextSearch.clearFocus();
    }
}


