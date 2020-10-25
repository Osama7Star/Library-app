package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class ReviewsModel
{
    @SerializedName("reviewId")
    private String reviewId;
    @SerializedName("review")
    private String review;
    @SerializedName("rate")
    private int  rate;
    @SerializedName("date")
    private String date ;
    @SerializedName("userName")
    private String userName ;
    @SerializedName("fullName")
    private String fullName ;
    @SerializedName("imageUrl")
    private  String userImageUrl;
    @SerializedName("userId")
    private String userId;
    @SerializedName("bio")
    private String userBio;
    @SerializedName("universityName")
    private String userUniversity;
    @SerializedName("collageName")
    private String userCollage ;
    @SerializedName("bookId")
    private String bookId ;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }




    public ReviewsModel(String reviewId, String review, int rate, String date, String userName, String fullName, String userImageUrl, String userId, String bookId) {
        this.reviewId = reviewId;
        this.review = review;
        this.rate = rate;

        this.date = date;
        this.userName = userName ;
        this.fullName = fullName;
        this.userImageUrl = userImageUrl;
        this.userId = userId;
        this.bookId = bookId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    public String getUserCollage() {
        return userCollage;
    }

    public void setUserCollage(String userCollage) {
        this.userCollage = userCollage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
