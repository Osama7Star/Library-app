package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.BooksActivitesModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.SingleBook;
import com.alnajim.osama.library.UI.UserProfile;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osama Alnajm on 27-Jan-20.
 */
public class BooksActivitesAdapter   extends RecyclerView.Adapter<BooksActivitesAdapter.PostViewHolder> {
    private List<BooksActivitesModel> booksActivitesModelArrayList = new ArrayList<>();
    private Context context;
    public BooksActivitesAdapter(Context context)
    {
        this.context = context ;
    }
    @NonNull
    @Override
    public BooksActivitesAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BooksActivitesAdapter.PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.useractiviteis_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position)
    {
        holder.activites.setText(booksActivitesModelArrayList.get(position).getBookName());

        String bookName = booksActivitesModelArrayList.get(position).getBookName();
        String fullName =  booksActivitesModelArrayList.get(position).getFullName();
        String test = "بدأ "+fullName +" بقراءة كتاب \n [ "+bookName +" ]";
        holder.activites.setText(test);
        String date  = booksActivitesModelArrayList.get(position).getBorrowingDate();

        holder.date.setText(date);

        final String bookId  = booksActivitesModelArrayList.get(position).getBookdI();
        final String userId  = booksActivitesModelArrayList.get(position).getUserId();

        String userImageUrl = booksActivitesModelArrayList.get(position).getUserImageUrl();
        Glide.with(context)
                .load(userImageUrl)
                .error(R.drawable.defaultbook)
                .into(holder.profile_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleBook.class);
                intent.putExtra("bookId",bookId);
                context.startActivity(intent);
            }
        });

        holder.profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId",userId);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        try {
            return booksActivitesModelArrayList.size();

        }
        catch (Exception e )
        {
            Log.i("Error","Error in MostReaderAdapter");

        }
        return 0;
    }

    public void setList(List<BooksActivitesModel> booksActivitesModelArrayList) {
        this.booksActivitesModelArrayList = booksActivitesModelArrayList;
        notifyDataSetChanged();
    }



    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView  activites, date ;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            activites     = itemView.findViewById(R.id.tvActivites);
            date          = itemView.findViewById(R.id.tvDate);

        }
    }
}


