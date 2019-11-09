package com.example.banregiotest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banregiotest.R;
import com.example.banregiotest.models.Book;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter <BookAdapter.ViewHolder>{

    ArrayList<Book> ListBoocks;
    Context context;

    public BookAdapter(Context context, ArrayList<Book>boocks)
    {
        this.ListBoocks=boocks;
        this.context=context;
    }



    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, author, category;
        ImageView imageBoock;
        public ViewHolder(View vista)
        {
            super(vista);

            title=(TextView)vista.findViewById(R.id.titulobook);
            author=(TextView)vista.findViewById(R.id.autorbook);
            category=(TextView)vista.findViewById(R.id.categoriabook);
            imageBoock=(ImageView)vista.findViewById(R.id.imagebook);



        }
    }

    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_of_book,parent,false);
       ViewHolder vh = new ViewHolder(v);
        return  vh;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            holder.title.setText(ListBoocks.get(position).getTitle());
            holder.author.setText(ListBoocks.get(position).getAuthor().getFirst_name()+","+ListBoocks.get(position).getAuthor().getLast_name());
            holder.category.setText(ListBoocks.get(position).getCategory());
            Picasso.with(this.context).load(ListBoocks.get(position).getImage_url()).into(holder.imageBoock);

    }


    @Override
    public int getItemCount() {
        return ListBoocks.size();
    }

}
