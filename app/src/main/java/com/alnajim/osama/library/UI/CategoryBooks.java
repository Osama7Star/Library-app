package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CategoryBooks extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    BooksAdapter booksAdapter;
    TextView CategoryName , booksNumbers;
    ProgressBar progress;
    BottomNavigationView bottom_navigation;
    SwipeRefreshLayout swipeRefresh;
    LinearLayout test;
    public String categoryId;
    LibraryViewModel libraryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_books);

         libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

         CategoryName = findViewById(R.id.tvCategoryName);
         booksNumbers = findViewById(R.id.tvBooksNumber);
         progress      = findViewById(R.id.progress);
         test           = findViewById(R.id.test);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        swipeRefresh = findViewById(R.id.swipeRefresh);
         swipeRefresh.setOnRefreshListener(this);
         Intent intent = getIntent();
         categoryId   = intent.getStringExtra("categoryId");
         String categoryName = intent.getStringExtra("categoryName");
        CategoryName.setText(categoryName);
        booksAdapter = new BooksAdapter(this,true,libraryViewModel);

        InitRecyclerView ();


        GetData(categoryId);

        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();


    }


    void InitRecyclerView ()
    {
        RecyclerView recyclerView = findViewById(R.id.rvCategoryBooks);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(booksAdapter);

    }

    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }

    @Override
    public void onRefresh() {
        GetData(categoryId);
    }

    public void GetData(String categoryId)
    {
        libraryViewModel.GetCategoryBooks(categoryId);
        libraryViewModel.CategoryBooks.observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels)
            {
                try {
                    booksAdapter.setList(bookModels);
                    progress.setVisibility(View.GONE);
                    test.setVisibility(View.VISIBLE);
                    booksNumbers.setText("["+bookModels.size()+"]");
                    swipeRefresh.setRefreshing(false);
                }
                catch (Exception e){

                }

            }
        });
    }
}
