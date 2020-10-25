package com.alnajim.osama.library.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivitiesAdapter extends RecyclerView.Adapter<UserActivitiesAdapter.PostViewHolder> {
    private List<ReviewsModel> reviewsList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.useractiviteis_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
//        holder.userName.setText(reviewsList.get(position).getUserName());


    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public void setList(List<ReviewsModel> reviewsList ) {
        this.reviewsList = reviewsList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

//        TextView userName ,bookName ;
        CircleImageView circleImageView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

//            userName = itemView.findViewById(R.id.tvUserName);
//            bookName = itemView.findViewById(R.id.tvBookName);
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

