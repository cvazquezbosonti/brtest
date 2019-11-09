package com.example.banregiotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.banregiotest.adapters.BookAdapter;
import com.example.banregiotest.models.Book;
import com.example.banregiotest.ws.ConnectionQueue;
import com.example.banregiotest.ws.GsonRequest;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Book> BookCollection;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Books");

        recyclerView = (RecyclerView) findViewById(R.id.Boocklist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Getbooks();


    }

    public void Getbooks(){
        GsonRequest request = new GsonRequest(Request.Method.GET,
                " https://jsonbox.io/box_479f5c073a80294b4c3b", Book[].class,
                null, new Response.Listener<Book[]>() {
            @Override
            public void onResponse(Book[] response) {
                BookCollection=new ArrayList<>(Arrays.asList(response));
                // specify an adapter
                adapter = new BookAdapter(getApplicationContext(),BookCollection);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No es posible comunicarse con el servidor", Toast.LENGTH_SHORT).show();
            }
        });

        ConnectionQueue.getInstance(getApplicationContext()).addToRequestQue(request);


    }
}
