package com.example.banregiotest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.banregiotest.models.Book;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class detail extends AppCompatActivity {
Book book;
TextView title,author,category,desc,publication,isbn,pages;
ImageView imageBook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        Gson gson = new Gson();
        book=gson.fromJson(getIntent().getStringExtra("dataBook"),Book.class);
        this.setTitle("Detail Book");
        initialize();
    }

    private void initialize()  {
        imageBook=(ImageView)findViewById(R.id.detailImageBook);
        title= (TextView)findViewById(R.id.detailtitle);
        author= (TextView)findViewById(R.id.detailauthor);
        category=(TextView)findViewById(R.id.detailcategory);
        desc=(TextView)findViewById(R.id.detailDesc);
        publication=(TextView)findViewById(R.id.DetailPublication);
        isbn=(TextView)findViewById(R.id.DetailIsbn);
        pages=(TextView)findViewById(R.id.DetailPages);
        Picasso.with(this).load(book.getImage_url()).into(imageBook);
        title.setText(book.getTitle());
        author.setText(book.getAuthor().getFirst_name()+","+book.getAuthor().getLast_name());
        category.setText(book.getCategory());
        desc.setText(book.getDescription());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(book.get_createdOn());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        publication.setText(outputFormat.format(date));
        isbn.setText(String.valueOf(book.getIsbn()));
        pages.setText(String.valueOf(book.getPages()));





    }
}
