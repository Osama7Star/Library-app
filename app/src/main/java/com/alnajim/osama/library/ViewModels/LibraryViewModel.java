package com.alnajim.osama.library.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alnajim.osama.library.Data.LibraryClient;
import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.BooksActivitesModel;
import com.alnajim.osama.library.Models.BorrowingModel;
import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.Models.ConfigrationModel;
import com.alnajim.osama.library.Models.QuoteModel;
import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.Models.SliderModel;
import com.alnajim.osama.library.Models.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryViewModel extends ViewModel
{
    public MutableLiveData<List<BookModel>> BookInformationLiveData = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> MostReadBooksLiveData   = new MutableLiveData<>();
    public MutableLiveData<List<CategoryModel>> CategoriesLiveData      = new MutableLiveData<>();
    public MutableLiveData<List<CategoryModel>> OneCategoryLiveData     = new MutableLiveData<>();
    public MutableLiveData<List<AuthorModel>> AuthorsLiveData         = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> AuthorBooksLiveData     = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> CategoryBooks           = new MutableLiveData<>();
    public MutableLiveData<List<UserModel>> UserInformationLiveData           = new MutableLiveData<>();

    public MutableLiveData<List<BookModel>> SearchBookLiveData                = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> MostRatedBooksLiveData            = new MutableLiveData<>();


    ///
    public MutableLiveData<List<BookModel>> Category1BooksLiveData            = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> Category2BooksLiveData            = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> Category3BooksLiveData            = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> EndedBookLiveData                 = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> LastBooksLiveData                      = new MutableLiveData<>();



    ///

    public MutableLiveData<List<ReviewsModel>> ReviewsLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ReviewsModel>> BookRateLiveData = new MutableLiveData<>();

    public MutableLiveData<List<QuoteModel>> QuoteLiveData = new MutableLiveData<>();
    public MutableLiveData<List<SliderModel>> SliderImagesLiveData = new MutableLiveData<>();
    public MutableLiveData<List<BooksActivitesModel>> BooksActivitesLiveData = new MutableLiveData<>();
    public MutableLiveData<List<QuoteModel>> UserQuotedLiveData  = new MutableLiveData<>();
    public MutableLiveData<List<ReviewsModel>> UserReviewLiveData  = new MutableLiveData<>();
    public MutableLiveData<List<BookModel>> UserBooksLiveData  = new MutableLiveData<>();
    public MutableLiveData<List<String>> BookReviewLiveDate  = new MutableLiveData<>();
    public MutableLiveData<List<BorrowingModel>> BorrowingDateLiveData  = new MutableLiveData<>();

    public MutableLiveData<String> AddQuoteLiveData    = new MutableLiveData<>();
    public MutableLiveData<List<String> > AddQuoteLiveData1    = new MutableLiveData<>();

    public MutableLiveData<String> AddReviewLiveData   = new MutableLiveData<>();
    public MutableLiveData<String> SignUpLiveData      = new MutableLiveData<>();
    public MutableLiveData<List<String>> ReviewLiveDate      = new MutableLiveData<>();
    public MutableLiveData<String> UserNameLiveData      = new MutableLiveData<>();
    public MutableLiveData<List<String>> BorrowBookLiveData      = new MutableLiveData<>();

    public MutableLiveData<List<ConfigrationModel>> ConditionsLiveData      = new MutableLiveData<>();









    public void GetMostReadBook()
    {
        //  MostReadBooksLiveData.setValue(LibraryClient.getINSTANCE().GetMostReadBooks());
    }





    public  void GetMostReadBooks()
    {

        LibraryClient.getINSTANCE().GetMostReadBooks().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                MostReadBooksLiveData.setValue(response.body());
                Log.i("trueConnection","Working");

            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {
                //  Log.i("errorConnectionsss",t.getMessage());

            }
        });
    }

    /////
    public void GetAuthors(String numberOfAuthors)
    {
        LibraryClient.getINSTANCE().GetAuthors(numberOfAuthors).enqueue(new Callback<List<AuthorModel>>() {
            @Override
            public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {

                AuthorsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AuthorModel>> call, Throwable t) {

            }
        });

    }


    ////GET INFORMATION FOR ONE AUTHRO BY ID
    public void getAuthorBook(String authorId)
    {

        LibraryClient.getINSTANCE().GetAuthorBooks(authorId).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                AuthorBooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }


    ///// GET ALL CATEGORIES
    public void GetCategories(String pageSize)
    {

        LibraryClient.getINSTANCE().GetCategories(pageSize).enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                CategoriesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t)
            {

                //   Log.i("GetbookInformation", t.getMessage());

            }
        });

    }

    //    ////// GET ALL BOOKS'S INFORMATION BY BOOKId
    public void GetBookInformation (String bookId)
    {
        LibraryClient.getINSTANCE().GetBookById(bookId).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response)
            {
                try {
                    BookInformationLiveData.setValue(response.body());

                }
                catch (Exception e )
                {}
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {
                // Log.i("insideonFailuretest",t.getMessage());

            }
        });
    }

    public void GetBookByISBN(String ISBN)
    {
        LibraryClient.getINSTANCE().GetBookByISBN(ISBN).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                BookInformationLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });

    }

    public void GetCategoryName(String categoryId)
    {

        LibraryClient.getINSTANCE().GetCategoryName(categoryId).enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                OneCategoryLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
            }
        });
    }


    /////GET ALL BOOK'S REVIEWS
    public void GetBookReviews(String bookId){

        LibraryClient.getINSTANCE().GetBookReviews(bookId).enqueue((new Callback<List<ReviewsModel>>() {
            @Override
            public void onResponse(Call<List<ReviewsModel>> call, Response<List<ReviewsModel>> response) {
                ReviewsLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<ReviewsModel>> call, Throwable t) {
                Log.i("Why11", t.getMessage());
            }
        }));

    }

    ///// GET ALL BOOKS IN CATEGORY
    public void GetCategoryBooks(String categoryId){
        LibraryClient.getINSTANCE().GetCategoryBooks(categoryId).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                CategoryBooks.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });

    }

    /// GET User Data BY id
    public void GetUserInformation(String userId){
        LibraryClient.getINSTANCE().GetuUserInformation(userId).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                UserInformationLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }

    public void Getsearchbook(String text)
    {
        LibraryClient.getINSTANCE().Getsearchbook(text).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                SearchBookLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }

    //      SUGGEST BOOK
    public void BookSuggest(String bookName,String bookAuthor,String note,String userId)
    {
        LibraryClient.getINSTANCE().BookSuggestion(bookName, bookAuthor, note,userId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AddReviewLiveData.setValue(response.body());
                Log.i("responsebook", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    //    GET THE QUOTE
    public void GetQuote()
    {
        LibraryClient.getINSTANCE().GetQuote().enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                QuoteLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {

            }
        });
    }
    //GET THE MOST RATED BOOKS
    public void GetMostRatedBooks(){
        LibraryClient.getINSTANCE().GetMostRatedBooks().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                MostRatedBooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }


    //    GET FROM SPECIFIC CATEOGRY
    public void GetCategory1Books(){
        LibraryClient.getINSTANCE().GetCategory1Books().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                Category1BooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }

    public void GetCategory2Books(){
        LibraryClient.getINSTANCE().GetCategory2Books().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                Category2BooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }
    public void GetCategory3Books(){
        LibraryClient.getINSTANCE().GetCategory3Books().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                Category3BooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }


    public void GetSliderImages()
    {
        LibraryClient.getINSTANCE().GetSliderImages().enqueue(new Callback<List<SliderModel>>() {
            @Override
            public void onResponse(Call<List<SliderModel>> call, Response<List<SliderModel>> response)
            {
                //  Log.i("ImageCorrect", response.body().size()+" ");

                SliderImagesLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<SliderModel>> call, Throwable t) {
                //  Log.i("ImageError", t.getMessage());
            }
        });
    }

    public void getbooksborrowingrecords(){

        LibraryClient.getINSTANCE().getbooksborrowingrecords().enqueue(new Callback<List<BooksActivitesModel>>() {
            @Override
            public void onResponse(Call<List<BooksActivitesModel>> call, Response<List<BooksActivitesModel>> response) {
                BooksActivitesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BooksActivitesModel>> call, Throwable t) {

            }
        });


    }



    public void Getuserquotes(String userId)
    {
        LibraryClient.getINSTANCE().Getuserquotes(userId).enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                UserQuotedLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {

            }
        });
    }

    public void Getuserreviews(String userId) {
        LibraryClient.getINSTANCE().Getuserreviews(userId).enqueue(new Callback<List<ReviewsModel>>() {
            @Override
            public void onResponse(Call<List<ReviewsModel>> call, Response<List<ReviewsModel>> response)
            {
                UserReviewLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<ReviewsModel>> call, Throwable t)
            {
            }
        });
    }

    public void Getuserrebooks(String userId) {
        LibraryClient.getINSTANCE().GetUserBooks(userId).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                UserBooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }



    /// GET THE LAST BOOKS
    public void GetLastBooks() {
        LibraryClient.getINSTANCE().GetLastBooks().enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                LastBooksLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }





    public void Getborrowedinfo (String bookId)
    {
        LibraryClient.getINSTANCE().Getborrowedinfo(bookId).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                UserInformationLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

                //    Log.i("borrowingInfo", t.getMessage());

            }
        });
    }


    public void Getborrowingdate(String userId,String bookId){
        LibraryClient.getINSTANCE().Getborrowingdate(userId,bookId).enqueue(new Callback<List<BorrowingModel>>() {
            @Override
            public void onResponse(Call<List<BorrowingModel>> call, Response<List<BorrowingModel>> response) {
                BorrowingDateLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<BorrowingModel>> call, Throwable t) {

            }
        });
    }


    public void searchbytag(String tag){
        LibraryClient.getINSTANCE().searchbytag(tag).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                SearchBookLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }

    // GET THE BOOK'S AUTHOR NAME
    public void Getauthorname(String bookId)
    {

        LibraryClient.getINSTANCE().Getauthorname(bookId).enqueue(new Callback<List<AuthorModel>>() {
            @Override
            public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {
                AuthorsLiveData.setValue(response.body());
                //  Log.i("AuthoName1", "onResponse: "+response.body().get(0).getAuthorName()+);
            }

            @Override
            public void onFailure(Call<List<AuthorModel>> call, Throwable t) {
                Log.i("AuthoName2", "onResponse: "+t.getMessage());

            }
        });
    }


    public void  AddQuote(String quote,String userId,int likeNumbers,String date){
        LibraryClient.getINSTANCE().AddQuote(quote,userId,likeNumbers,date).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AddQuoteLiveData.setValue(response.body());
                Log.i("QuouteResponse", response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("QuouteResponse", t.getMessage());

            }
        });
    }

    public void  AddReview(String review ,String rate,String bookId,String userId)
    {
        LibraryClient.getINSTANCE().AddReview(review,rate,bookId,userId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AddReviewLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("---", t.getMessage());
            }
        });

    }

    public void  SignUp(String fullName ,String userName,String password,String universityName,String collageName,String bio){
        LibraryClient.getINSTANCE().SignUp(fullName , userName,password, universityName, collageName, bio).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SignUpLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void LogIn(String userName,String password)
    {
        LibraryClient.getINSTANCE().LogIn(userName,password).enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                UserInformationLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                Log.i("fuck", t.getMessage());

            }
        });
    }

    public void addbookborrowing(String bookId, String userId, Date startDate, String endDate,int share)
    {
        LibraryClient.getINSTANCE().addbookborrowing(bookId,userId,startDate,endDate,share).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AddQuoteLiveData.setValue(response.body());
                Log.i("Response", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }


    /// UPDATE THE BOOK INFO

    public void Updatebookstatus(String bookId,String bookStatus ,String userId,String borrowingCount)
    {
        LibraryClient.getINSTANCE().Updatebookstatus(bookId,bookStatus, userId,borrowingCount).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SignUpLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    public void CheckUserReview(String bookId,String userId)
    {
        LibraryClient.getINSTANCE().CheckUserReview(bookId,userId).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                ReviewLiveDate.setValue(response.body());
                Log.i("testReview", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("testReview", "onResponse: "+t.getMessage());

            }
        });
    }



    /// UPDATE LIKE NUMBERS FOR QUOTE
    public void UpdateQuoteLikes  (String quoteId,int likeNumbers )
    {
        LibraryClient.getINSTANCE().UpdateQuoteLikes(quoteId,likeNumbers).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SignUpLiveData.setValue(response.body());
                //     Log.i("testfukc", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("testfukc", t.getMessage());

            }
        });
    }


    /// GET THE RATE FOR THE BOOK
    public void GetBookReview(String bookId){
        LibraryClient.getINSTANCE().GetbookReview(bookId).enqueue(new Callback<List<ReviewsModel>>() {
            @Override
            public void onResponse(Call<List<ReviewsModel>> call, Response<List<ReviewsModel>> response) {
                BookRateLiveData.setValue(response.body());
                Log.i("bookRate", "onResponse: "+response.body());

            }

            @Override
            public void onFailure(Call<List<ReviewsModel>> call, Throwable t) {
                Log.i("bookRate", "onResponse: "+t.getMessage());

            }
        });
    }

    public void Checkusername(String userName)
    {
        LibraryClient.getINSTANCE().Checkusername(userName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                UserNameLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void GetEndedBook(String userId){
        LibraryClient.getINSTANCE().GetEndedBook(userId).enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                EndedBookLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {

            }
        });
    }

    public void SearchAuthors(String authorName)
    {
        LibraryClient.getINSTANCE().SearchAuthors(authorName).enqueue(new Callback<List<AuthorModel>>() {
            @Override
            public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {

                AuthorsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AuthorModel>> call, Throwable t) {

            }
        });

    }


    public void GetConditions()
    {

        Log.i("test11", "Before the response");

        LibraryClient.getINSTANCE().GetConditions().enqueue(new Callback<List<ConfigrationModel>>() {
            @Override
            public void onResponse(Call<List<ConfigrationModel>> call, Response<List<ConfigrationModel>> response) {
                ConditionsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ConfigrationModel>> call, Throwable t) {

            }
        });
    }





}

