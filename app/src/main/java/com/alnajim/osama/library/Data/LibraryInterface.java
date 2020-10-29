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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LibraryInterface
{


    @GET("getbooks?access-token=test")
    Call<List<BookModel>> GetBooks();

    @GET("getmostratedbooks?access-token=test")
    Call<List<BookModel>> GetMostRatedBooks();

    @GET("getauthors?access-token=test")
    Call<List<AuthorModel>> GetAuthors(@Query("numberOfAuthors") String numberOfAuthors);

    @GET("getauthorbooks1?access-token=test&authorid=")
    Call<List<BookModel>> GetAuthorBooks(@Query("authorid") String authorid);

    @GET("getcategories?access-token=test")
    Call<List<CategoryModel>> GetCategories(@Query("numbercategories") String numbercategories);

    @GET("getbookbyid?access-token=test&bookId=")
    Call<List<BookModel>> GetBookById(@Query("bookId") String ISBN);

    @GET("getbookbyisbn?access-token=test&ISBN=")
    Call<List<BookModel>> GetBookByISBN(@Query("ISBN") String ISBN);

    @GET("getcategoryname?access-token=test&categoryId=")
    Call<List<CategoryModel>> GetCategoryName(@Query("categoryId") String cateogryId);


    @GET("getbookreview1?access-token=test&bookid=")
    Call<List<ReviewsModel>> GetBookReviews(@Query("bookid") String bookid);


    @GET("getcategorybook?access-token=test&categoryid=")
    Call<List<BookModel>> GetCategoryBooks(@Query("categoryid") String categoryid);

    @GET("getcategory1books?access-token=test&")
    Call<List<BookModel>> GetCategory1Books();
    @GET("getcategory2books?access-token=test&")
    Call<List<BookModel>> GetCategory2Books();
    @GET("getcategory3books?access-token=test&")
    Call<List<BookModel>> GetCategory3Books();



    @GET("getuserinformation?access-token=test&userid=")
    Call<List<UserModel>> GetuUserInformation(@Query("userid") String userid);

    @GET("getsearchbook?access-token=test&text=")
    Call<List<BookModel>> Getsearchbook(@Query("text") String text);

    @GET("getquotes?access-token=test")
        Call<List<QuoteModel>> GetQuote();


    @GET("getsuggestedbook?access-token=test")
    Call<String> BookSuggestion(@Query("bookName") String bookName, @Query("authorName") String authorName, @Query("note") String note, @Query("userId") String userId);




    @GET("slider?access-token=test")
    Call<List<SliderModel>> GetSliderImages();

    @GET("getbooksborrowingrecords?access-token=test")
    Call<List<BooksActivitesModel>> getbooksborrowingrecords();

    @GET("getuserquotes?access-token=test&userId=")
    Call<List<QuoteModel>> Getuserquotes(@Query("userId") String userId);


    @GET("getuserreviews?access-token=test&userId=")
    Call<List<ReviewsModel>> Getuserreviews(@Query("userId") String userId);

    @GET("getuserrebooks?access-token=test&userId=")
    Call<List<BookModel>> GetUserBooks(@Query("userId") String userId);

    @GET("getborrowedinfo?access-token=test&bookId=")
    Call<List<UserModel>> Getborrowedinfo(@Query("bookId") String bookId);

    @GET("getborrowingdate?access-token=test")
    Call<List<BorrowingModel>> Getborrowingdate(@Query("userId") String userId, @Query("bookId") String bookId);

    @GET("searchbytag?access-token=test")
    Call<List<BookModel>> searchbytag(@Query("tag") String tag);

    @GET("getauthorname?access-token=test")
    Call<List<AuthorModel>> Getauthorname(@Query("bookId") String bookId);

    @GET("borrowBook?access-token=test")
    Call<List<AuthorModel>> BorrowBook(@Query("bookId") String bookId, @Query("userId") String userId);

    /////// ADD FUNCTION
//    @GET("addquote?access-token=test")
//    Call<String> AddRreview (@Query("review")String review,@Query("rate")int rate,@Query("bookId")int bookId ,@Query("userId")int userId );
    @GET("addquote?access-token=test")
    Call<String> AddQuote(@Query("quote") String quote, @Query("userId") String userId, @Query("likeNumbers") int likeNumbers, @Query("date") String date);

    @GET("addreview?access-token=test")
    Call<String> AddReview(@Query("review") String review, @Query("rate") String rate, @Query("bookId") String bookId, @Query("userId") String userId);

    @GET("signup?access-token=test")
    Call<String> SignUp(@Query("fullName") String fullName, @Query("userName") String userName, @Query("password") String password, @Query("universityName") String universityName, @Query("collageName") String collageName, @Query("bio") String bio);

    @GET("login?access-token=test")
    Call<ArrayList<UserModel>> LogIn(@Query("username") String userName, @Query("password") String password);

    @GET("checkusername?access-token=test")
    Call<String> Checkusername(@Query("userName") String userName);

    @GET("addbookborrowing?access-token=test")
    Call<String> addbookborrowing(@Query("bookId") String bookId, @Query("userId") String userId, @Query("startDate") Date startDate, @Query("endDate") String endDate, @Query("share") int share);

    @GET("updatebookstatus?access-token=test")
    Call<String> Updatebookstatus(@Query("bookId") String bookId, @Query("bookStatus") String bookStatus, @Query("userId") String userId,@Query("borrowingCount") String borrowingCount );

    @GET("checkuserreview?access-token=test")
    Call<List<String>> CheckUserReview(@Query("bookId") String bookId, @Query("userId") String userId);

    @GET("updatequotelikes?access-token=test")
    Call<String> UpdateQuoteLikes(@Query("quoteId") String quoteId, @Query("like") int likeNumbers);

    @GET("getbookreview?access-token=test")
    Call<List<ReviewsModel>>GetbookReview(@Query("bookId") String bookId);


    @GET("getendedbook?access-token=test")
    Call<List<BookModel>> GetEndedBook(@Query("userId") String userId);

}
