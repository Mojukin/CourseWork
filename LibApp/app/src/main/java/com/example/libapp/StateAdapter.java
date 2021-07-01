package com.example.libapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.state.State;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.RecyclerViewViewHolder>{

    private ArrayList<Book> arrayList;
    private Context context;
    private int sumOrder = 0;

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public TextView titleBook, authorBook;
        public ImageView imageView;
        public CardView cardView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            // присваиваешь пееременным элементы из view
            titleBook = itemView.findViewById(R.id.titleBookTextView);
            authorBook = itemView.findViewById(R.id.authorBookTextView);
            imageView = itemView.findViewById(R.id.bookImageView);
            cardView = itemView.findViewById(R.id.cardViewBook);
        }
    }

    public StateAdapter(Context context, ArrayList<Book> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item,
                viewGroup, false);

        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder recyclerViewViewHolder, int i) {
    // красная исчещнет когда присвоишь cardView вьюхи книги
        recyclerViewViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Book book = arrayList.get(i);

                DataBook._idbook = book.get_idbook();
                DataBook._idauthor = book.get_idauthor();
                DataBook.name = book.getName();
                DataBook.oathimagebook = book.getOathimagebook();
                DataBook.pathontext = book.getPathontext();
                context.startActivity(new Intent(context, ReadBook.class));
            }
        });
        // тут присваиваешь текст вьюхи из массива
        //recyclerViewViewHolder.orderDateTime.setText(order.getDate());
        Book book = arrayList.get(i);
        String name = book.getName();
        String id = String.valueOf(book.get_idauthor());
        if (id.equals("1")){
            recyclerViewViewHolder.titleBook.setText(name);
            recyclerViewViewHolder.authorBook.setText("Лавкрафт Филлипс Говард ");
        }
        else if(id.equals("2")){
            recyclerViewViewHolder.titleBook.setText(name);
            recyclerViewViewHolder.authorBook.setText("Лев Николаевич Толстой");
        }
        else if(id.equals("3")){
            recyclerViewViewHolder.titleBook.setText(name);
            recyclerViewViewHolder.authorBook.setText("Федор Михайлович Достоевский");
        }
        else if(id.equals("4")){
            recyclerViewViewHolder.titleBook.setText(name);
            recyclerViewViewHolder.authorBook.setText("Дэн Браун");
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}