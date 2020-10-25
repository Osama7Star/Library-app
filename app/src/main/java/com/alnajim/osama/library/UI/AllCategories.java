package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.CategoryAdapter;
import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AllCategories extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    BottomNavigationView bottom_navigation;
    SwipeRefreshLayout swipeRefreshLayout;
    LibraryViewModel libraryViewModel;
    CategoryAdapter categoriesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        recyclerView = findViewById(R.id.rvAllCategories);
        progressBar  = findViewById(R.id.progressbar);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesAdapter =   new CategoryAdapter(this);
        recyclerView.setAdapter(categoriesAdapter);

        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        swipeRefreshLayout.setOnRefreshListener(this);


        GetData();




        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
        }
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }

    public void GetData(){
        libraryViewModel.GetCategories("20");

        libraryViewModel.CategoriesLiveData.observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {
                categoriesAdapter.setList(categoryModels);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        GetData();

    }
}
