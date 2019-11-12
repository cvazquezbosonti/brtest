package com.example.banregiotest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.banregiotest.adapters.BookAdapter;
import com.example.banregiotest.models.Book;
import com.example.banregiotest.ws.ConnectionQueue;
import com.example.banregiotest.ws.GsonRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;

public class addbook extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText title,category,nameA,lastA,pages,isbn,desc,pubdate,publisher;
    Button btnsave,btnclose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);
        this.setTitle("Add book");
        initialize();
    }

    private void initialize() {
         title=(TextInputEditText)findViewById(R.id.inputTitle);
         category=(TextInputEditText)findViewById(R.id.inputCategory);
         nameA=(TextInputEditText)findViewById(R.id.inputFirstname);
         lastA=(TextInputEditText)findViewById(R.id.inputLastname);
         pages=(TextInputEditText)findViewById(R.id.inputPages);
         isbn=(TextInputEditText)findViewById(R.id.inputIsbn);
         desc=(TextInputEditText)findViewById(R.id.inputDesc);
         pubdate=(TextInputEditText)findViewById(R.id.inputDate);
         publisher=(TextInputEditText)findViewById(R.id.inputPublisher);
         btnsave=(Button)findViewById(R.id.btnsave);
         btnsave.setOnClickListener(this);
         btnclose=(Button)findViewById(R.id.btncancel);
         btnclose.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnsave:
                validate();
                break;
            case R.id.btncancel:
                onBackPressed();
                break;
        }

    }

    private void validate() {


       /* if(title.getText().toString().matches("")){title.setError("enter title please");}
        else{if(nameA.getText().toString().matches("")){nameA.setError("enter name of author please");}
        else{if(lastA.getText().toString().matches("")){lastA.setError("enter last name of author please");}
        else{if(lastA.getText().toString().matches("")){lastA.setError("enter las name of author please");}
        else{if(category.getText().toString().matches("")){category.setError("enter category please");}
        else{if(desc.getText().toString().matches("")){desc.setError("please enter description");}
        else{if(isbn.getText().toString().matches("")){isbn.setError("please enter isbn");}
        else{if(pubdate.getText().toString().matches("")){pubdate.setError("please enter publication date");}
        else{if(publisher.getText().toString().matches("")){publisher.setError("please enter publisher");}}}}}}}}
        }
        if(isbn.getText().toString().length()>10)
        {
            isbn.setError("isbn is must lower teen digits");
        }*/

        insertbook();


    }

    private void insertbook() {
        JSONObject JSONOB = new JSONObject();

        JsonObject objjson= new JsonObject();
        objjson.addProperty("isbn",isbn.getText().toString());
        objjson.addProperty("title",title.getText().toString());
        JsonObject authorjson= new JsonObject();
        authorjson.addProperty("first_name",nameA.getText().toString());
        authorjson.addProperty("last_name",lastA.getText().toString());
        objjson.add("author",authorjson);



        GsonRequest request = new GsonRequest(Request.Method.POST,
                "https://jsonbox.io/box_479f5c073a80294b4c3b", Book.class,
                null, new Response.Listener<Book[]>() {
            @Override
            public void onResponse(Book[] response) {

              int ola=3;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No es posible comunicarse con el servidor", Toast.LENGTH_SHORT).show();
            }
        },objjson);

        ConnectionQueue.getInstance(getApplicationContext()).addToRequestQue(request);


    }
}
