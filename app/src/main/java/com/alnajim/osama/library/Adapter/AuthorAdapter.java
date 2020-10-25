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

import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.AuthorBooks;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.PostViewHolder> {
    private List<AuthorModel> authorList = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public AuthorAdapter(Context context){this.context=context;}

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.authors_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position)
    {
        final String authorName = authorList.get(position).getAuthorName();
        final String bio        = authorList.get(position).getAuthorBio();
        final String imagePath  = authorList.get(position).getAuthorImageUrl();
        final String authorId   = authorList.get(position).getAuthorId();

        holder.authorName.setText(authorName);
       // holder.authorBookNumber.setText(6+" ");
        Log.i("authorIdtest", authorId+"test");

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.defaultbook)
                .error(R.drawable.defaultbook)
                .into(holder.authorImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, AuthorBooks.class);
                intent.putExtra("authorId",authorId);
                intent.putExtra("authorName",authorName);
                intent.putExtra("authorBio",bio);
                intent.putExtra("authorImage",imagePath);




                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        try {
            return authorList.size();

        }
        catch (Exception e )
        {
            return  0 ;
        }
    }

    public void setList(List<AuthorModel> auhtorList) {
        this.authorList = auhtorList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView authorName ;//authorBookNumber;
        ImageView authorImage;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            authorName = itemView.findViewById(R.id.tvAuthorName);
            authorImage  = itemView.findViewById(R.id.imgAuthor);
         //   authorBookNumber = itemView.findViewById(R.id.tvAuthorBookNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(authorList.get(position).getAuthorId());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(String imageId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

