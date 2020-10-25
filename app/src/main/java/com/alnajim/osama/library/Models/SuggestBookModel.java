package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Osama Alnajm on 25-Jan-20.
 */
public class SuggestBookModel
{
    @SerializedName("bookName")
    private String bookName ;
    @SerializedName("bookAuthor")
    private String bookAuthor ;
    @SerializedName("note")
    private String note ;
    @SerializedName("date")
    private String date ;
    @SerializedName("userId")
    private String userId;

    public SuggestBookModel(String bookName, String bookAuthor, String note, String date, String userId) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.note = note;
        this.date = date;
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
