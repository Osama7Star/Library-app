package com.alnajim.osama.library.Data;


import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.BooksActivitesModel;
import com.alnajim.osama.library.Models.BorrowingModel;
import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.Models.QuoteModel;
import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.Models.SliderModel;
import com.alnajim.osama.library.Models.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LibraryClient {
    private static final String BASE_URL = "http://library.ssfturkey.org/api/web/v1/recipes/";

    private LibraryInterface libraryInterface;
    private static LibraryClient INSTANCE;

    public LibraryClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        libraryInterface = retrofit.create(LibraryInterface.class);
    }

    public static LibraryClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new LibraryClient();
        }
        return INSTANCE;
    }

    public Call<List<BookModel>> GetMostReadBooks() {
        return libraryInterface.GetBooks();
    }

    public Call<List<AuthorModel>> GetAuthors(String numberOfAuthors) {
        return libraryInterface.GetAuthors(numberOfAuthors);
    }

    public Call<List<BookModel>> GetAuthorBooks(String authorId) {
        return libraryInterface.GetAuthorBooks(authorId);
    }

    public Call<List<BookModel>> GetBookById(String bookId) {
        return libraryInterface.GetBookById(bookId);
    }

    public Call<List<CategoryModel>> GetCategories(String pageSize) {
        return libraryInterface.GetCategories(pageSize);
    }

    public Call<List<CategoryModel>> GetCategoryName(String categoryId) {
        return libraryInterface.GetCategoryName(categoryId);
    }

    public Call<List<ReviewsModel>> GetBookReviews(String bookId) {
        return libraryInterface.GetBookReviews(bookId);
    }

    public Call<List<BookModel>> GetCategoryBooks(String categoryId) {
        return libraryInterface.GetCategoryBooks(categoryId);
    }

    public Call<List<UserModel>> GetuUserInformation(String userId) {
        return libraryInterface.GetuUserInformation(userId);
    }

    public Call<List<BookModel>> Getsearchbook(String text) {
        return libraryInterface.Getsearchbook(text);
    }

    public Call<String> BookSuggestion(String bookName, String bookAuthor, String note, String userId) {
        return libraryInterface.BookSuggestion(bookName, bookAuthor, note, userId);
    }

    public Call<List<QuoteModel>> GetQuote() {
        return libraryInterface.GetQuote();
    }

    public Call<List<BookModel>> GetMostRatedBooks() {
        return libraryInterface.GetMostRatedBooks();
    }

    public Call<List<BookModel>> GetCategory1Books() {
        return libraryInterface.GetCategory1Books();
    }

    public Call<List<BookModel>> GetCategory2Books() {
        return libraryInterface.GetCategory2Books();
    }

    public Call<List<BookModel>> GetCategory3Books() {
        return libraryInterface.GetCategory3Books();
    }

    public Call<List<SliderModel>> GetSliderImages() {
        return libraryInterface.GetSliderImages();
    }

    public Call<List<BooksActivitesModel>> getbooksborrowingrecords() {
        return libraryInterface.getbooksborrowingrecords();
    }

    public Call<List<QuoteModel>> Getuserquotes(String userID) {
        return libraryInterface.Getuserquotes(userID);
    }

    public Call<List<ReviewsModel>> Getuserreviews(String userID) {
        return libraryInterface.Getuserreviews(userID);
    }

    public Call<List<BookModel>> GetUserBooks(String userID) {
        return libraryInterface.GetUserBooks(userID);
    }

    public Call<List<UserModel>> Getborrowedinfo(String bookId) {
        return libraryInterface.Getborrowedinfo(bookId);
    }

    public Call<List<BorrowingModel>> Getborrowingdate(String userId, String bookId) {
        return libraryInterface.Getborrowingdate(userId, bookId);
    }

    public Call<List<BookModel>> searchbytag(String tag) {
        return libraryInterface.searchbytag(tag);
    }

    public Call<List<AuthorModel>> Getauthorname(String bookId) {
        return libraryInterface.Getauthorname(bookId);
    }

    public Call<String> AddQuote(String quote, String userId, int likeNumbers,String date) {
        return libraryInterface.AddQuote(quote, userId, likeNumbers,date);
    }

    public Call<String> AddReview(String review, String rate, String bookId, String userId) {
        return libraryInterface.AddReview(review, rate, bookId, userId);
    }

    public Call<String> SignUp(String fullName, String userName,String password, String universityName, String collageName, String bio) {
        return libraryInterface.SignUp(fullName, userName, password,universityName, collageName, bio);
    }

    public Call<ArrayList<UserModel>> LogIn(String userName, String password) {
        return libraryInterface.LogIn(userName, password);
    }

    public Call<String> addbookborrowing(String bookId, String userId, Date startDate, String endDate,int share){
        return libraryInterface.addbookborrowing(bookId, userId, startDate, endDate,share) ;
    }
    public Call<String> Updatebookstatus(String bookId,String bookStatus,String userId,String borrowingCount){
        return libraryInterface.Updatebookstatus( bookId,bookStatus, userId ,borrowingCount) ;
    }
    public Call<List<String>> CheckUserReview(String bookId,String userId){
        return libraryInterface.CheckUserReview( bookId, userId ) ;
    }

    public Call<String>  UpdateQuoteLikes  (String quoteId,int likeNumbers ){
        return libraryInterface.UpdateQuoteLikes(quoteId,likeNumbers);
    }


    public Call<List<ReviewsModel>> GetbookReview(String bookId){
        return libraryInterface.GetbookReview(bookId);
    }

    public Call<String> Checkusername(String userName){
        return libraryInterface.Checkusername(userName);
    }


    public Call<List<BookModel>> GetBookByISBN(String ISBN)
    {
        return libraryInterface.GetBookByISBN(ISBN);
    }


    public Call<List<BookModel>> GetEndedBook(String userId)
    {
        return libraryInterface.GetEndedBook(userId);
    }


}
