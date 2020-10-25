package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.SingleBook;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.PostViewHolder> {
    private List<BookModel> booksList = new ArrayList<>();
    private Context context;
    private boolean bigOrSmall  ;
    private float rate ;

    private LibraryViewModel libraryViewModel;

    public BooksAdapter(Context context, Boolean bigOrSmall, LibraryViewModel libraryViewModel)
    {
        this.context = context ;
        this.bigOrSmall = bigOrSmall;
        this.rate = rate  ;
        this.libraryViewModel = libraryViewModel;

    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (!bigOrSmall)
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.books_layout, parent, false));
        else
            return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bigbook_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position)
    {
        holder.bookName.setText(booksList.get(position).getBookName());
        final String bookId  = booksList.get(position).getBookId();
        final String ISBN = booksList.get(position).getISBN();
        rate = booksList.get(position).getBookRate();

        holder.ratingBar.setRating(rate);
        String imagePath = booksList.get(position).getBookImage();
        Glide.with(context)
                .load(imagePath)
                .error(R.drawable.defaultbook)
                .into(holder.bookImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, SingleBook.class);
                intent.putExtra("bookId",bookId);
                context.startActivity(intent);
            }
        });


        libraryViewModel.GetBookReview(bookId);
        libraryViewModel.BookRateLiveData.observe((LifecycleOwner) context, new Observer<List<ReviewsModel>>() {
            @Override
            public void onChanged(List<ReviewsModel> reviewsModels)
            {
                try {

                    int sum = 0;
                    for (int i = 0; i < reviewsModels.size(); i++)
                    {
                        sum = sum+reviewsModels.get(i).getRate();
                    }
                    int rate = sum / reviewsModels.size();
                   holder.ratingBar.setRating(rate);

                    //    Toast.makeText(SingleBook.this, ""+(float) Math.ceil(rate), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e )
                {
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        try {
            return booksList.size();

        }
        catch (Exception e )
        {
            Log.i("Error","Error in MostReaderAdapter");

        }
        return 0;
    }

    public void setList(List<BookModel> moviesList) {
        this.booksList = moviesList;
        notifyDataSetChanged();
    }



    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookName;
        RatingBar ratingBar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.imgBook);
            bookName = itemView.findViewById(R.id.tvBookName);
            ratingBar = itemView.findViewById(R.id.rating);

        }
    }
}

