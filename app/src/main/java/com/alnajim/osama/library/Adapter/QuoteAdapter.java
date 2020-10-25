package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.QuoteModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.UserProfile;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.PostViewHolder> {
    private List<QuoteModel> quoteModelList = new ArrayList<>();
    public OnItemClickListener listener;
    private boolean isLiked = false ;
    private int like  ;
    private Context context ;
    public QuoteAdapter(Context context)
    {
        this.context = context ;

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.userqoutes_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {
        holder.fullName.setText(quoteModelList.get(position).getFullName());
        holder.userName.setText("@"+quoteModelList.get(position).getUserName());
        holder.quote.setText(quoteModelList.get(position).getQuote());
        like =  quoteModelList.get(position).getLikeNumbers();
        holder.quoteDate.setText(quoteModelList.get(position).getQuoteDate());

        holder.LikeNumbers.setText( like+"");
        String imagePath = quoteModelList.get(position).getImageUrl();
        Glide.with(context)
                .load(imagePath)
                .error(R.drawable.defultuser)
                .into(holder.profileImage);


        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(!isLiked) {
                    like =  quoteModelList.get(position).getLikeNumbers();
                    like++;

                    holder.LikeNumbers.setText( like + "");
                    holder.LikeNumbers.setTextColor(context.getResources().getColor(R.color.green));
                    holder.likeButton.setImageResource(R.drawable.ic_thumb_up_green_24dp);
                    isLiked = true ;
                }
                else
                {
                    like =  quoteModelList.get(position).getLikeNumbers();
                    holder.LikeNumbers.setText(like  + "");
                    holder.LikeNumbers.setTextColor(context.getResources().getColor(R.color.black));
                    holder.likeButton.setImageResource(R.drawable.ic_thumb_up_gray_24dp);
                    isLiked = false ;

                }
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(quoteModelList.get(position).getQuoteId(),like);
                }
            }
        });

        holder.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = quoteModelList.get(position).getUserId();
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId",userId);

                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = quoteModelList.get(position).getUserId();
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId",userId);

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount()
    {

        try {
            return quoteModelList.size();

        }
        catch (Exception e )
        {
            return  0 ;
        }
    }

    public void setList(List<QuoteModel> reviewsList ) {
        this.quoteModelList = reviewsList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView fullName ,quote ,LikeNumbers,quoteDate,userName;
        ImageView  likeButton ;
        CircleImageView profileImage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.tvFullName);
            userName = itemView.findViewById(R.id.tvUserName);
            quote = itemView.findViewById(R.id.tvquote);
            LikeNumbers = itemView.findViewById(R.id.tvLikeNumber);
            likeButton = itemView.findViewById(R.id.imgLikeButton);
            quoteDate  = itemView.findViewById(R.id.tvDate);
            profileImage =   itemView.findViewById(R.id.profile_image);




        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(String quoteId, int likesNumbers);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}