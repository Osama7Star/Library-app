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
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Authentication.Login;
import com.alnajim.osama.library.UI.Authentication.Signup;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView category1Name,category2Name,category3Name,userName,ShowAll1,ShowAll2,ShowAll3;
    RecyclerView rvMostRead ,rvCategories, rvAuthors,rvMostRated;
    RecyclerView rvCategory1,rvCategory2,rvCategory3,rvEndedDate , rvCategoryLastBooks;
    ImageView search ,backImage ;
    LinearLayout llmain,llNoInternet,llCurrentBook,rlNoInternet;
    ViewPager viewPager;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context ;
    BottomNavigationView bottom_navigation;
    String [] categoryId = new String[3];
    String [] categoryName= new String[3];




    LibraryViewModel libraryViewModel;

     BooksAdapter booksAdapter;
     BooksAdapter mostRatedBooksAdapter,endedBookAdapter;
     BooksAdapter booksAdapter1     ;
     BooksAdapter booksAdapter2         ;
     BooksAdapter booksAdapter3         ;
     BooksAdapter bookAdapterLastbooks         ;

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
        rvCategoryLastBooks = findViewById(R.id.rvCategoryLastBooks);
        rvMostRated = findViewById(R.id.rvMostRated);
        rvEndedDate = findViewById(R.id.rvEndedDate);
        search      = findViewById(R.id.imgSearch);
        backImage   = findViewById(R.id.back);
        llmain      = findViewById(R.id.llmain);
        rlNoInternet  = findViewById(R.id.rlNoInternet);
        llCurrentBook = findViewById(R.id.llCurrentBook);
        progressBar = findViewById(R.id.progressbar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        category1Name = findViewById(R.id.tvCategory1);
        category2Name = findViewById(R.id.tvCategory2);
        category3Name = findViewById(R.id.tvCategory3);
        ShowAll1      = findViewById(R.id.tvShowAll1);
        ShowAll2      = findViewById(R.id.tvShowAll2);
        ShowAll3      = findViewById(R.id.tvShowAll3);


        userName           = findViewById(R.id.tvUserName);
        llNoInternet       = findViewById(R.id.llNoInternet);
        backImage.setVisibility(View.GONE);

        libraryViewModel  = ViewModelProviders.of(this).get(LibraryViewModel.class);
        booksAdapter      =       new BooksAdapter(this,false,libraryViewModel);
        mostRatedBooksAdapter  =    new BooksAdapter(this,true,libraryViewModel);
        endedBookAdapter      =    new BooksAdapter(this,true,libraryViewModel);

         booksAdapter1     =       new BooksAdapter(this,false,libraryViewModel);
         booksAdapter2     =       new BooksAdapter(this,false,libraryViewModel);
         booksAdapter3     =       new BooksAdapter(this,false,libraryViewModel);
         bookAdapterLastbooks     = new BooksAdapter(this,false,libraryViewModel);

       categoriesAdapter   =   new CategoryAdapter(this);
        authorAdapter      =   new AuthorAdapter(this);
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
            GetData();
            BasicClass basicClass = new BasicClass(bottom_navigation,this);
            basicClass.setNavigation();
        }

        else{
            userName.setText("تسجيل الدخول");
            userName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, Login.class));
                    finish();
                }
            });
            startActivity(new Intent(context, Login.class));
            finish();

        }







    }

    public void InitRecyclerViewCategories()
    {
        rvCategories =  findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new GridLayoutManager(this,2));
        rvCategories.setAdapter(categoriesAdapter);
    }

    public void InitRecyclerViewLastBooks()
    {
        rvCategoryLastBooks =  findViewById(R.id.rvCategoryLastBooks);
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
        CheckUser();
      if (isNetworkConnected())
      {
          try {
              rlNoInternet.setVisibility(View.GONE);





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
                  public void onChanged(final List<BookModel> bookModels) {
                      final   String categoryId   = bookModels.get(0).getCategoryId();
                      final   String categoryName  = bookModels.get(0).getCategoryName();
                      booksAdapter1.setList(bookModels);
                      category1Name.setText(bookModels.get(0).getCategoryName());
                      category1Name.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {



                              GoToCategory(categoryId,categoryName);
                          }
                      });

                      ShowAll1.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              GoToCategory(categoryId,categoryName);

                          }
                      });
                  }
              });

              libraryViewModel.GetCategory2Books();
              libraryViewModel.Category2BooksLiveData.observe(this, new Observer<List<BookModel>>() {
                  @Override
                  public void onChanged(final List<BookModel> bookModels) {
                      final   String categoryId   = bookModels.get(0).getCategoryId();
                      final  String categoryName = bookModels.get(0).getCategoryName();
                      category2Name.setText(bookModels.get(0).getCategoryName());

                      booksAdapter2.setList(bookModels);
                      category2Name.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {



                              GoToCategory(categoryId,categoryName);
                          }
                      });

                      ShowAll2.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              GoToCategory(categoryId,categoryName);

                          }
                      });
                  }
              });

              /// GET LAST BOOKS
              libraryViewModel.GetLastBooks();
              libraryViewModel.LastBooksLiveData.observe(this, new Observer<List<BookModel>>() {
                  @Override
                  public void onChanged(final List<BookModel> bookModels) {

                       bookAdapterLastbooks.setList(bookModels);

                  }
              });

              libraryViewModel.GetCategory3Books();
              libraryViewModel.Category3BooksLiveData.observe(this, new Observer<List<BookModel>>() {
                  @Override
                  public void onChanged(final List<BookModel> bookModels) {
                      category3Name.setText(bookModels.get(0).getCategoryName());

                      final   String categoryId   = bookModels.get(0).getCategoryId();
                      final  String categoryName = bookModels.get(0).getCategoryName();
                      booksAdapter3.setList(bookModels);
                      category3Name.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {



                              GoToCategory(categoryId,categoryName);
                          }
                      });

                      ShowAll3.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              GoToCategory(categoryId,categoryName);

                          }
                      });

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
              libraryViewModel.GetCategories("8");
              libraryViewModel.CategoriesLiveData.observe(this, new Observer<List<CategoryModel>>() {
                  @Override
                  public void onChanged(List<CategoryModel> categoryModels) {
                      categoriesAdapter.setList(categoryModels);
                  }
              });


              libraryViewModel.GetAuthors("9");
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

                       viewPager = findViewById(R.id.viewPager);
                      ImageAdapter adapter = new ImageAdapter(context, sliderModels);
                      viewPager.setAdapter(adapter);
                      progressBar.setVisibility(View.GONE);

                  }
              });


              InitRecyclerViewCategories();
              InitRecyclerViewAuthors();
              InitRecyclerViewCategory(rvMostRead ,  booksAdapter);
              InitRecyclerViewCategory(rvCategory1, booksAdapter1);
              InitRecyclerViewCategory(rvCategory2, booksAdapter2);
              InitRecyclerViewCategory(rvCategory3, booksAdapter3);
              InitRecyclerViewCategory(rvCategoryLastBooks,bookAdapterLastbooks);
              InitRecyclerViewMostRated(rvMostRated, mostRatedBooksAdapter);
              InitRecyclerViewMostRated(rvEndedDate, endedBookAdapter);

          }
          catch (Exception e )
          {
              Log.i("Error", "GetData: ");
          }
      }
      else{
          llmain.setVisibility(View.GONE);
          rlNoInternet.setVisibility(View.VISIBLE);
          progressBar.setVisibility(View.GONE);

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


    public void GoToCategory(String categoryId,String categoryName)
    {
        Intent intent = new Intent(context, CategoryBooks.class);

        intent.putExtra("categoryId",categoryId);

        intent.putExtra("categoryName",categoryName);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    void CheckUser()
    {
        libraryViewModel.GetUserInformation(sessionManager.GetUserId());
        libraryViewModel.UserInformationLiveData.observe(this, new Observer<List<UserModel>>() {
                    @Override
                    public void onChanged(List<UserModel> userModels)
                    {
                       if (userModels.get(0).getActive().equals("0"))
                       {
                           Toast.makeText(context, "تم إيقاف حسابك , الرجاء التواصل مع الأدمن ", Toast.LENGTH_LONG).show();
                           startActivity(new Intent(context, Login.class));
                            finish();
                       }
                       else{

                       }

                    }
                }
        );
    }

}
