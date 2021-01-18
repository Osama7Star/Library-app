package com.alnajim.osama.library.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Adapter.UserReviewsAdapter;
import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.BorrowingModel;
import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SingleBook extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ImageView bookImage ;
    TextView bookName , bookAuthor , tvNote,tvISBN, bookCategory , bookPageNumbers,bookSummary,bookStatus,BorrowerName,BorrowingStartDate,BorrowingEndDate ;
    LibraryViewModel libraryViewModel;
    RatingBar  ratingBar,ratingBarReview;
    BottomNavigationView bottom_navigation;
    ProgressBar progressBar,reviewProgressbar;
    LinearLayout llmain,llAddReview;
    Button tag1,tag2,tag3,btnAddReview;
    String bookId,ISBN;
    CardView cvUserReviews;
    EditText tvReview;
    final String[] categoryId = new String[1];
    final String[] userId = new String[1];


    public Context context = this;

    SwipeRefreshLayout swipeRefresh;
    SessionManager sessionManager;
    boolean firstTime = false ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book);

        InitView();
        ratingBar.setRating(1);

        Intent intent = getIntent();
        bookId =  intent.getStringExtra("bookId");
        swipeRefresh.setOnRefreshListener(this);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        sessionManager = new SessionManager(this);

        if (!sessionManager.isLoggedIn())
        {
            btnAddReview.setEnabled(false);
            tvReview.setText("يجب ان تقوم بتسجيل الدخول قبل إضافة مراجعة");
            tvReview.setTextColor(getResources().getColor(R.color.red));
            tvReview.setEnabled(false);
            ratingBarReview.setEnabled(false);

        }

         // CHECK IF THE USER ADD REVIEW FOR THE BOOK BEFORE
        //CheckUserReview ();

        GetData();
        GetBookReview(bookId);

        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();

    }
    void GetData()
    {
        try {
            llmain.setVisibility(View.GONE);
            reviewProgressbar.setVisibility(View.GONE);


            ////////////
            libraryViewModel.GetBookInformation(bookId);
            libraryViewModel.BookInformationLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    swipeRefresh.setRefreshing(false);
                    progressBar.setVisibility(View.GONE);
                    llmain.setVisibility(View.VISIBLE);
                    bookName.setText(bookModels.get(0).getBookName());
                    tvISBN.setText(bookModels.get(0).getISBN());
                    ISBN = bookModels.get(0).getISBN();
                    bookPageNumbers.setText(bookModels.get(0).getBookPages() + "");

                    String note =bookModels.get(0).getNote();
                    if (!note.equals("")) {
                        tvNote.setVisibility(View.VISIBLE);
                        tvNote.setText(note);
                    }
                    bookSummary.setText(bookModels.get(0).getBookSummary());
                    String imageUrl = bookModels.get(0).getBookImage();
                    Glide.with(getApplication())
                            .load(imageUrl)
                            .error(R.drawable.defaultbook)
                            .into(bookImage);
                    String categoryId = bookModels.get(0).getCategoryId();
                    libraryViewModel.GetCategoryName(categoryId);

                   // float rateValue = bookModels.get(0).getBookRate();
                    String tag1Str, tag2Str, tag3Str;
                    tag1Str = bookModels.get(0).getTag1();

                    tag2Str = bookModels.get(0).getTag2();
                    tag3Str = bookModels.get(0).getTag3();

                   // ratingBar.setRating(rateValue);
                    tag1.setText(tag1Str);
                    tag2.setText(tag2Str);
                    tag3.setText(tag3Str);


                    /// GET BOOK'S AUTHORS NAMES
                    //////////////////////////////////////////
                    libraryViewModel.Getauthorname(bookId);
                    libraryViewModel.AuthorsLiveData.observe((LifecycleOwner) context, new Observer<List<AuthorModel>>() {
                        @Override
                        public void onChanged(List<AuthorModel> authorModels) {
                            try {
                                bookAuthor.setText(authorModels.get(0).getAuthorName() );
                                final  String authorId = authorModels.get(0).getAuthorId();
                                bookAuthor.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        Intent intent = new Intent(context, AuthorBooks.class);
                                        intent.putExtra("authorId",authorId);
                                        startActivity(intent);
                                    }
                                });
//                                for (int i = 0; i < authorModels.size(); i++) {
//
//                                    if (!firstTime)
//                                    {
//
//                                        if (i==authorModels.size()-1)
//                                        {
//                                            bookAuthor.append(authorModels.get(i).getAuthorName() );
//
//                                        }
//                                        else {
//                                            bookAuthor.append(authorModels.get(i).getAuthorName()+" , " );
//
//                                        }
//                                    }
//
//                                }
                                firstTime = true ;

                            } catch (Exception e) {
                            }
                        }
                    });
                    /////////////////////////////////////////
                    String bookstatus = bookModels.get(0).getBookStatus();

                        if (bookstatus.equals("0")) {
                            bookStatus.setText("الكتاب متاح للاستعارة");
                            bookStatus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(context, BookBorrowing.class);
                                    intent.putExtra("ISBN",ISBN);
                                    startActivity(intent);
                                }
                            });
                        }
                        else
                            {
                                libraryViewModel.Getborrowedinfo(bookId);
                                libraryViewModel.UserInformationLiveData.observe((LifecycleOwner) context, new Observer<List<UserModel>>() {
                                    @Override
                                    public void onChanged(List<UserModel> userModels) {
                                        bookStatus.setText("الكتاب غير  متاح للاستعارة");
                                        BorrowerName.setVisibility(View.VISIBLE);
                                        BorrowerName.setText("تم إستعارته من قبل "+userModels.get(0).getFullName() + "\n" +
                                                " حتى تاريخ [" + userModels.get(0).getEndDate()+"]");
                                    final  String userId1 = userModels.get(0).getUserId();
                                        bookStatus.setTextColor((Color.RED));
                                        BorrowerName.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                Intent intent = new Intent(context, UserProfile.class);
                                                intent.putExtra("userId",userId1);
                                                startActivity(intent);
                                            }
                                        });
                                        userId[0] = userModels.get(0).getUserId();

                                        libraryViewModel.Getborrowingdate(userModels.get(0).getUserId(), bookId);
                                        libraryViewModel.BorrowingDateLiveData.observe((LifecycleOwner) context, new Observer<List<BorrowingModel>>() {
                                            @Override
                                            public void onChanged(List<BorrowingModel> borrowingModels)
                                            {
                                                try {
                                                    BorrowingStartDate.setVisibility(View.VISIBLE);
                                                    BorrowingEndDate.setVisibility(View.VISIBLE);
                                                    BorrowingStartDate.setText("من " + borrowingModels.get(0).getStartDate());
                                                    BorrowingEndDate.setText(" إلى " + borrowingModels.get(0).getEndDate());
                                                }
                                                catch (Exception e )
                                                {

                                                }

                                            }
                                        });
                                    }
                                });

                    }


                }
            });


            libraryViewModel.OneCategoryLiveData.observe(this, new Observer<List<CategoryModel>>() {
                @Override
                public void onChanged(List<CategoryModel> categoryModels) {
                    try {
                        bookCategory.setText(categoryModels.get(0).getGetCategoryName());
                        categoryId[0] = categoryModels.get(0).getCategoryId();
                        Log.i("testingagin", categoryId[0] + " ");
                        SimilarBooks(categoryId[0]);

                    } catch (Exception e) {

                    }

                }
            });

            UsersReviews();
        }
        catch (Exception e )
        {
            Log.i("Error", "GetData: "+e.getMessage());
        }

    }


//  TO INITLIAZIE ALL THE VIEW IN THE XML FILE
    void InitView()
    {
        bookImage       = findViewById(R.id.imgImageBook);
        bookName        = findViewById(R.id.tvBookName);
        bookAuthor      = findViewById(R.id.tvAuthorName);
        bookCategory    = findViewById(R.id.tvBookCategory);
        tvNote          = findViewById(R.id.tvNote);
        bookPageNumbers = findViewById(R.id.tvBookPageNumbers);
        bookSummary     = findViewById(R.id.tvBookSummary);
        bookStatus      = findViewById(R.id.tvBookStatus);
        tvISBN          = findViewById(R.id.tvISBN);
        BorrowerName  = findViewById(R.id.tvBorrowerName);
        BorrowingStartDate = findViewById(R.id.tvBorrowingStartDate);
        BorrowingEndDate = findViewById(R.id.tvBorrowingEndDate);
        ratingBar       = findViewById(R.id.rating);
        ratingBarReview = findViewById(R.id.ratingBarReview);
        progressBar     = findViewById(R.id.progress);
        llmain          = findViewById(R.id.llmain);
        llAddReview     = findViewById(R.id.llAddReview);
        tvReview        = findViewById(R.id.tvReview);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        tag1 = findViewById(R.id.tvTag1);
        tag2 = findViewById(R.id.tvTag2);
        tag3 = findViewById(R.id.tvTag3);
        btnAddReview = findViewById(R.id.btnAddReview);
        reviewProgressbar = findViewById(R.id.reviewProgressbar);
        cvUserReviews = findViewById(R.id.cvUserReviews);

        swipeRefresh = findViewById(R.id.swipeRefresh);

    }

    /// GET USER'S REVIEWS FOR THE BOOKS
    public void UsersReviews ()
    {
        final UserReviewsAdapter userReviewsAdapter = new UserReviewsAdapter(this);
        final RecyclerView  recyclerView = findViewById(R.id.rvUserReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userReviewsAdapter);
        libraryViewModel.GetBookReviews(bookId);
        libraryViewModel.ReviewsLiveData.observe(this, new Observer<List<ReviewsModel>>() {
            @Override
            public void onChanged(List<ReviewsModel> Reviews) {
                userReviewsAdapter.setList(Reviews);
                if (Reviews==null)
                    cvUserReviews.setVisibility(View.GONE);


            }


        });

    }



    // GET THE SIMILAR BOOK // NOW WE GET THE BOOK IN THE SAME CATEGORY
    public void SimilarBooks(String categoryId)
    {
        final BooksAdapter similarAdapter = new BooksAdapter(this,false,libraryViewModel);
        final RecyclerView  recyclerView = findViewById(R.id.rvSimilarBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(similarAdapter);

        Log.i("asdasdasdasd", "categoryId is "+categoryId);
        libraryViewModel.GetCategoryBooks(categoryId);
        libraryViewModel.CategoryBooks.observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                similarAdapter.setList(bookModels);

            }
        });

    }

    public void SearchTag(View view)
    {
        Button b = (Button)view;
        String tagName = b.getText().toString();

        Intent intent = new Intent(SingleBook.this,Search.class);
        intent.putExtra("tagName",tagName);
        startActivity(intent);
    }

    public void GoToCategory(View view){
        Intent intent = new Intent (SingleBook.this, CategoryBooks.class);
        intent.putExtra("categoryId",categoryId[0]);
        startActivity(intent);
    }


    public void OpenUserProfile(View view ){
        Intent intent = new Intent (SingleBook.this, UserProfile.class);
        intent.putExtra("userId",userId[0]);
        Toast.makeText(context, "User Id is "+userId[0], Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }



    //WHEN USER SWIPE REFRESH
    @Override
    public void onRefresh() {
        GetData();
    }


    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this, SuggestBook.class)); }
    public void Back(View view) { finish(); }
    public void AddReview(View view)
    {
        final String reviewRate = String.valueOf(ratingBarReview.getRating());

        String review = tvReview.getText().toString();
     if ( !reviewRate.equals("0.0") )
     {
         reviewProgressbar.setVisibility(View.VISIBLE);
         libraryViewModel.AddReview(review,reviewRate,bookId , sessionManager.GetUserId());
         libraryViewModel.AddReviewLiveData.observe(this, new Observer<String>() {
             @Override
             public void onChanged(String s) {
                 try {
                     if (s.equals("1"))
                         reviewProgressbar.setVisibility(View.GONE);

                         Toast.makeText(SingleBook.this, "تمت إضافة التقييم بنجاح ", Toast.LENGTH_SHORT).show();


                     Intent intent = new Intent(SingleBook.this, SingleBook.class);
                     intent.putExtra("bookId",bookId);
                     startActivity(intent);
                     finish();


                 } catch (Exception e) {
                     Toast.makeText(SingleBook.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                     Log.i("AddReview", e.getMessage());
                 }

             }
         });
     }
     else {
         Toast.makeText(context, "الرجاء إدخال التقييم كاملاً", Toast.LENGTH_SHORT).show();
     }
    }


    public void GetBookReview(String bookId)
    {
        libraryViewModel.GetBookReview(bookId);
        libraryViewModel.BookRateLiveData.observe(this, new Observer<List<ReviewsModel>>() {
            @Override
            public void onChanged(List<ReviewsModel> reviewsModels)
            {
                try {

                    int sum = 0;
                    for (int i = 0; i < reviewsModels.size(); i++)
                    {
                        sum += reviewsModels.get(i).getRate();
                    }
                    float rate = sum / reviewsModels.size();
                    if (rate>0) {
                        ratingBar.setVisibility(View.VISIBLE);
                        ratingBar.setRating(rate);
                    }


                }
                catch (Exception e )
                {
                }
            }
        });
    }

//    void CheckUserReview ()
//    {
//        libraryViewModel.CheckUserReview(bookId,"1");
//        libraryViewModel.ReviewLiveDate.observe(this, new Observer<List<String>>() {
//            @Override
//            public void onChanged(List<String> strings)
//
//                {
//                    try{
//                    if (strings.get(0).equals("0")) {
//                        Toast.makeText(SingleBook.this, "You Have Write A review", Toast.LENGTH_SHORT).show();
//                        llAddReview.setVisibility(View.GONE);
//                    }
//                }
//                    catch(Exception e )
//                    {
//                        Toast.makeText(SingleBook.this, "No Review Yey", Toast.LENGTH_SHORT).show();
//
//                    }
//            }
//
//
//        });
//    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
