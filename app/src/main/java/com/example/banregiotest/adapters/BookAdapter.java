package com.example.banregiotest.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banregiotest.R;
import com.example.banregiotest.detail;
import com.example.banregiotest.models.Book;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collection;

public class BookAdapter extends RecyclerView.Adapter <BookAdapter.ViewHolder> implements Filterable {

    ArrayList<Book> ListBoocks;
    ArrayList<Book> BookListAll;
    Gson gson = new Gson();
    Context context;

    public BookAdapter(Context context, ArrayList<Book>boocks)
    {
        this.ListBoocks=boocks;
        this.BookListAll= new ArrayList<>(boocks);
        this.context=context;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Book> filterlist = new ArrayList<>();
            if(constraint.toString().isEmpty())
            {
                filterlist.addAll(BookListAll);
            }
            else{
                for(Book book: BookListAll)
                {
                    if(book.getTitle().toLowerCase().contains(constraint))
                    {filterlist.add(book);}
                    if(book.getAuthor().getFirst_name().toLowerCase().contains(constraint)){filterlist.add(book);}
                    if(book.getCategory().toLowerCase().contains(constraint)){filterlist.add(book);}
                    if(String.valueOf(book.getIsbn()).toLowerCase().contains(constraint)){filterlist.add(book);}
                    if(book.getDescription().toLowerCase().contains(constraint)){filterlist.add(book);}
                    if(String.valueOf(book.getPages()).toLowerCase().contains(constraint)){filterlist.add(book);}
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=filterlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          ListBoocks.clear();
          ListBoocks.addAll((Collection<? extends Book>) results.values);
          notifyDataSetChanged();

        }
    };


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title, author, category;
        ImageView imageBoock;
        public ViewHolder(View vista)
        {
            super(vista);
            vista.setOnClickListener(this);
            title=(TextView)vista.findViewById(R.id.titulobook);
            author=(TextView)vista.findViewById(R.id.autorbook);
            category=(TextView)vista.findViewById(R.id.categoriabook);
            imageBoock=(ImageView)vista.findViewById(R.id.imagebook);

        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, detail.class);
            String dtaExtra= gson.toJson(ListBoocks.get(getAdapterPosition()));
            intent.putExtra("dataBook",dtaExtra);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
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
