package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.SingleBook;
import com.alnajim.osama.library.UI.UserProfile;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserReviewsAdapter extends RecyclerView.Adapter<UserReviewsAdapter.PostViewHolder> {
    private List<ReviewsModel> reviewsList = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;
    public  UserReviewsAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.userreviews_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position)
    {
        final String fullName  = reviewsList.get(position).getFullName();
        final String userName  = reviewsList.get(position).getUserName();
        holder.fullName.setText(fullName);
        holder.review.setText(reviewsList.get(position).getReview());
        holder.ratingBar.setRating(reviewsList.get(position).getRate());
        holder.date.setText(reviewsList.get(position).getDate());
        holder.userName.setText("@"+userName);

        final  String imageUrl = reviewsList.get(position).getUserImageUrl();
        Glide.with(context)
                .load(imageUrl)
                .error(R.drawable.defultuser)
                .into(holder.circleImageView);

        final String userId = reviewsList.get(position).getUserId();
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId",userId);
                context.startActivity(intent);
            }
        });
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId",userId);
                intent.putExtra("userName",userName);
                intent.putExtra("fullName",fullName);
                intent.putExtra("imageUrl",imageUrl);
                String userUniveristy = reviewsList.get(position).getUserUniversity();
                String userCollage    = reviewsList.get(position).getUserCollage();
                String userBio        = reviewsList.get(position).getUserBio();
                intent.putExtra("userUniveristy",userUniveristy);
                intent.putExtra("userCollage",userCollage);
                intent.putExtra("userBio",userBio);




                context.startActivity(intent);            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId = reviewsList.get(position).getBookId();
                Intent intent = new Intent(context, SingleBook.class);
                intent.putExtra("bookId",bookId);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        try {
            return reviewsList.size();

        }
        catch (Exception e)
        {
            Log.i("Error", "Error in UserReviewsAdapter ");
            return 0;
        }
    }

    public void setList(List<ReviewsModel> reviewsList ) {
        this.reviewsList = reviewsList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView fullName,userName ,review,date ;
        CircleImageView circleImageView;
        RatingBar ratingBar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            userName   = itemView.findViewById(R.id.tvUserName);
            fullName   = itemView.findViewById(R.id.tvFullName);
            review     = itemView.findViewById(R.id.tvReview);
            ratingBar  = itemView.findViewById(R.id.rate);
            date       = itemView.findViewById(R.id.tvDate);
            circleImageView = itemView.findViewById(R.id.profile_image);

            circleImageView =   itemView.findViewById(R.id.profile_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(reviewsList.get(position).getReviewId());
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

