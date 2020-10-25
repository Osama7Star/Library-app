package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class QuoteModel
{
    @SerializedName("quoteId")
    private String quoteId ;
    @SerializedName("quote")
    private String quote ;
    @SerializedName("likeNumbers")
    private int likeNumbers ;
    @SerializedName("date")
    private String quoteDate ;
    @SerializedName("userId")
    private String userId;
    @SerializedName("userName")
    private String userName;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("imageUrl")
    private String imageUrl ;

    public QuoteModel(String quoteId, String quote, int likeNumbers, String quoteDate, String userId, String userName) {
        this.quoteId = quoteId;
        this.quote = quote;
        this.likeNumbers = likeNumbers;
        this.quoteDate = quoteDate;
        this.userId = userId;
        this.userName = userName;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getLikeNumbers() {
        return likeNumbers;
    }

    public void setLikeNumbers(int likeNumbers) {
        this.likeNumbers = likeNumbers;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
