package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Osama Alnajm on 27-Jan-20.
 */
public class BooksActivitesModel
{

    @SerializedName("bookId")
    private String bookdI ;
    @SerializedName("userId")
    private String userId;
    @SerializedName("bookName")
    private String bookName ;
    @SerializedName("userName")
    private String userName ;
    @SerializedName("fullName")
    private String fullName ;
    @SerializedName("startDate")
    private String borrowingDate ;
    @SerializedName("imageUrl")
    private String userImageUrl ;

    public BooksActivitesModel(String bookName, String userName, String borrowingDate,String userImageUrl) {
        this.bookName = bookName;
        this.userName = userName;
        this.borrowingDate = borrowingDate;
        this.userImageUrl = userImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookdI() {
        return bookdI;
    }

    public void setBookdI(String bookdI) {
        this.bookdI = bookdI;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
