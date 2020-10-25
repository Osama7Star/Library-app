package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.alnajim.osama.library.Models.SliderModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends PagerAdapter {
    private Context context;
    //private List<SliderModel> arr  = new ArrayList<>() ;
    private List<SliderModel> slideList = new ArrayList<>();
    public ImageAdapter(Context context,   List<SliderModel> slideList) {
        this.context = context;
        this.slideList = slideList;
    }

    @Override
    public int getCount() {

        try{        return slideList.size();
        }
        catch (Exception e){

        }
        return 0 ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        ImageView imageView = new ImageView(context);

        try {
            Picasso.get()
                    .load(slideList.get(position).getSliderImagePath())
                    .fit()
                    .centerCrop()
                    .into(imageView);
            container.addView(imageView);

        }
        catch (Exception e )
        {

        }


        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public static class ReviewInformationAdapter
    {
        private String reviewId ;
        private String userName ;
        private String userImageUrl ;
        private String review ;
        private String rate ;

        public ReviewInformationAdapter(String reviewId, String userName, String userImageUrl, String review, String rate) {
            this.reviewId = reviewId;
            this.userName = userName;
            this.userImageUrl = userImageUrl;
            this.review = review;
            this.rate = rate;
        }

        public String getReviewId() {
            return reviewId;
        }

        public void setReviewId(String reviewId) {
            this.reviewId = reviewId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserImageUrl() {
            return userImageUrl;
        }

        public void setUserImageUrl(String userImageUrl) {
            this.userImageUrl = userImageUrl;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}