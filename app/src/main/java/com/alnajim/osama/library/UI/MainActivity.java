/**
 * @author Osama Alnajm
 * @email osama.alnagem@gmail.com
 * @date January 2020
 *
 */

package com.alnajim.osama.library.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.alnajim.osama.library.Adapter.AuthorAdapter;
import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Adapter.CategoryAdapter;
import com.alnajim.osama.library.Adapter.ImageAdapter;
import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.Models.SliderModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Authentication.Login;
import com.alnajim.osama.library.UI.Authentication.Signup;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView category3Name,userName;
    RecyclerView rvMostRead ,rvCategories, rvAuthors,rvMostRated;
    RecyclerView rvCategory1,rvCategory2,rvCategory3,rvEndedDate;
    ImageView search ,backImage ;
    LinearLayout llmain,llNoInternet,llCurrentBook;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context ;
    BottomNavigationView bottom_navigation;
    String [] categoryId = new String[3];
    String [] categoryName= new String[3];


    String test;
    LibraryViewModel libraryViewModel;

     BooksAdapter booksAdapter;
     BooksAdapter mostRatedBooksAdapter,endedBookAdapter;
     BooksAdapter booksAdapter1     ;
     BooksAdapter booksAdapter2         ;
     BooksAdapter booksAdapter3         ;
     CategoryAdapter categoriesAdapter;
     AuthorAdapter authorAdapter;
     SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMostRead  = findViewById(R.id.rvMostRead);
        rvCategory1 = findViewById(R.id.rvCategory1);
        rvCategory2 = findViewById(R.id.rvCategory2);
        rvCategory3 = findViewById(R.id.rvCategory3);
        rvMostRated = findViewById(R.id.rvMostRated);
        rvEndedDate = findViewById(R.id.rvEndedDate);
        search      = findViewById(R.id.imgSearch);
        backImage   = findViewById(R.id.back);
        llmain      = findViewById(R.id.llmain);
        llCurrentBook = findViewById(R.id.llCurrentBook);
        progressBar = findViewById(R.id.progressbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        bottom_navigation  = findViewById(R.id.bottom_navigation);

        userName           = findViewById(R.id.tvUserName);
        llNoInternet       = findViewById(R.id.llNoInternet);
        backImage.setVisibility(View.GONE);

         libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        booksAdapter      =       new BooksAdapter(this,false,libraryViewModel);
         mostRatedBooksAdapter      =    new BooksAdapter(this,true,libraryViewModel);
        endedBookAdapter      =    new BooksAdapter(this,true,libraryViewModel);

         booksAdapter1     =       new BooksAdapter(this,false,libraryViewModel);
         booksAdapter2     =       new BooksAdapter(this,false,libraryViewModel);
         booksAdapter3     =       new BooksAdapter(this,false,libraryViewModel);

       categoriesAdapter   =   new CategoryAdapter(this);
        authorAdapter       =   new AuthorAdapter(this);
        CheckSessions();

    }

    public void CheckSessions(){
        context = this;

        sessionManager = new SessionManager(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        if(sessionManager.GetFirstTime())
        {
            startActivity(new Intent(context, Signup.class));
        }
        if (sessionManager.isLoggedIn())
        {
            userName.setText(sessionManager.GetUserName());
            userName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("هل تريد تسجيل الخروج ");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "نعم",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sessionManager.setLogin(false,null,null);
                                    startActivity(new Intent(context, Login.class));

                                }
                            });

                    builder1.setNegativeButton(
                            "لا",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

        }

        else{
            userName.setText("تسجيل الدخول");
            userName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, Login.class));
                }
            });


        }

        GetData();




        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
    }

    public void InitRecyclerViewCategories()
    {
        rvCategories =  findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this,2));
        rvCategories.setAdapter(categoriesAdapter);
    }
    public void InitRecyclerViewMostRated(RecyclerView recyclerView , BooksAdapter booksAdapter)
    {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(booksAdapter);
    }

    public void InitRecyclerViewAuthors()
    {
        rvAuthors =  findViewById(R.id.rvAuthors);
        rvAuthors.setLayoutManager(new GridLayoutManager(this,3));
        rvAuthors.setAdapter(authorAdapter);
    }

    public void InitRecyclerViewCategory(RecyclerView recyclerView, BooksAdapter adapter)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
    }

    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }


    public void GetData()
    {
        try {




            //GET THE MOST READ BOOKS
            libraryViewModel.GetMostReadBooks();
            libraryViewModel.MostReadBooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    booksAdapter.setList(bookModels);
                    llmain.setVisibility(View.VISIBLE);
                    llNoInternet.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);


                }
            });
            ////////////
            libraryViewModel.GetCategory1Books();
            libraryViewModel.Category1BooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    booksAdapter1.setList(bookModels);
                    //category1Name.setText("First");
//                    categoryId[0] = bookModels.get(0).getCategoryId();
//                    categoryName[0] = bookModels.get(0).getCategoryName();
//                    test = bookModels.get(0).getCategoryId();
                }
            });

            libraryViewModel.GetCategory2Books();
            libraryViewModel.Category2BooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    booksAdapter2.setList(bookModels);
                 //   category2Name.setText("");
                    categoryId[1] = bookModels.get(0).getCategoryId();
                    categoryName[1] = bookModels.get(0).getCategoryName();
                 //   category2Name.setText(categoryName[1]);
                }
            });

            libraryViewModel.GetCategory3Books();
            libraryViewModel.Category3BooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    booksAdapter3.setList(bookModels);
                    categoryId[2] = bookModels.get(0).getCategoryId();
                    categoryName[2] = bookModels.get(0).getCategoryName();
                //    category3Name.setText(categoryName[2]);

                }
            });

            //////////////////////////

            /// GET THE MOST RATED BOOKS IN THE LIBRARY
            libraryViewModel.GetMostRatedBooks();
            libraryViewModel.MostRatedBooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    mostRatedBooksAdapter.setList(bookModels);
                }
            });


            /// GET THE CATEGORIES
            libraryViewModel.GetCategories("6");
            libraryViewModel.CategoriesLiveData.observe(this, new Observer<List<CategoryModel>>() {
                @Override
                public void onChanged(List<CategoryModel> categoryModels) {
                    categoriesAdapter.setList(categoryModels);
                }
            });


            libraryViewModel.GetAuthors("6");
            libraryViewModel.AuthorsLiveData.observe(this, new Observer<List<AuthorModel>>() {
                @Override
                public void onChanged(List<AuthorModel> authorModels) {
                    authorAdapter.setList(authorModels);

                }
            });

            libraryViewModel.GetEndedBook(sessionManager.GetUserId());
            libraryViewModel.EndedBookLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {

                   if (bookModels.size()>0)
                   {
                       llCurrentBook.setVisibility(View.VISIBLE);
                       endedBookAdapter.setList(bookModels);
                   }

                }
            });


            libraryViewModel.GetSliderImages();
            libraryViewModel.SliderImagesLiveData.observe(this, new Observer<List<SliderModel>>() {
                @Override
                public void onChanged(List<SliderModel> sliderModels) {

                    ViewPager viewPager = findViewById(R.id.viewPager);
                    ImageAdapter adapter = new ImageAdapter(context, sliderModels);
                    viewPager.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);

                }
            });


            InitRecyclerViewCategories();
            InitRecyclerViewAuthors();
            InitRecyclerViewCategory(rvMostRead, booksAdapter);
            InitRecyclerViewCategory(rvCategory1, booksAdapter1);
            InitRecyclerViewCategory(rvCategory2, booksAdapter2);
            InitRecyclerViewCategory(rvCategory3, booksAdapter3);
            InitRecyclerViewMostRated(rvMostRated, mostRatedBooksAdapter);
            InitRecyclerViewMostRated(rvEndedDate, endedBookAdapter);

        }
        catch (Exception e )
        {
            Log.i("Error", "GetData: ");
        }
    }
    @Override
    public void onRefresh()
    {
        llmain.setVisibility(View.GONE);
        llNoInternet.setVisibility(View.GONE);
        CheckSessions();


    }

    public void GoAllCategory(View view)
    {
        Intent intent = new Intent(MainActivity.this, AllCategories.class);
        startActivity(intent);
    }

    public void GoAllAuthors(View view)
    {
        Intent intent = new Intent(MainActivity.this, AllAuthors.class);
        startActivity(intent);
    }

    public void AddQuote(View view )
    {
        startActivity(new Intent(this, AddQuote.class));
    }

    public void GoCateogry1(View view ){Intent intent = new Intent(this, CategoryBooks.class) ; intent.putExtra("categoryId",categoryId[0]);intent.putExtra("categoryName",categoryName[0]);startActivity(intent); }
    public void GoCateogry2(View view ){Intent intent = new Intent(this, CategoryBooks.class) ; intent.putExtra("categoryId",categoryId[1]);intent.putExtra("categoryName",categoryName[1]);startActivity(intent);}
    public void GoCateogry3(View view ){Intent intent = new Intent(this, CategoryBooks.class) ; intent.putExtra("categoryId",categoryId[2]);intent.putExtra("categoryName",categoryName[2]);startActivity(intent);}

    @Override
    public void onBackPressed() {
        finish();
    }
}
