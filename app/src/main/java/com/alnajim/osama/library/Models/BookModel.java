package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;


public class BookModel
{
    @SerializedName("bookId")
    private String bookId;
    @SerializedName("bookName")
    private String bookName ;
    @SerializedName("bookPages")
    private int bookPages;
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("categoryName")
    private String categoryName;
    @SerializedName("authorId")
    private String authorId;
    @SerializedName("tag1")
    private String tag1;
    @SerializedName("tag2")
    private String tag2;
    @SerializedName("tag3")
    private String tag3;
    @SerializedName("summary")
    private String bookSummary ;
    @SerializedName("bookstatus")
    private String bookStatus ;
    @SerializedName("userId")
    private String userId;
    @SerializedName("rate")
    private float bookRate ;
    @SerializedName("imageUrl")
    private String bookImage ;
    @SerializedName("ISBN")
    private String ISBN ;
    @SerializedName("borrowingsCounts")
    private int borrowingsCounts;
    @SerializedName("note")
    private String note;
    public int getBorrowingsCounts() {
        return borrowingsCounts;
    }

    public void setBorrowingsCounts(int borrowingsCounts) {
        this.borrowingsCounts = borrowingsCounts;
    }

    public BookModel(String bookId, String bookName, int bookPages, String categoryId, String authorId, String tag1, String tag2, String tag3, String bookSummary, String bookStatus, String userId, float bookRate, String bookImage) {
        this.bookId     = bookId;
        this.bookName   = bookName;
        this.bookPages  = bookPages;
        this.categoryId = categoryId;
        this.authorId   = authorId;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.bookSummary = bookSummary;
        this.bookStatus  = bookStatus;
        this.userId      = userId;
        this.bookRate    = bookRate;
        this.bookImage   = bookImage;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void BookModel(){}

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public float getBookRate() {
        return bookRate;
    }

    public void setBookRate(float bookRate) {
        this.bookRate = bookRate;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
