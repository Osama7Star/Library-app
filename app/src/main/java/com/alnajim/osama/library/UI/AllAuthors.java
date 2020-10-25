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

import com.alnajim.osama.library.Adapter.AuthorAdapter;
import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AllAuthors extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    BottomNavigationView bottom_navigation;
    LibraryViewModel libraryViewModel;
    AuthorAdapter authorAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);

        recyclerView = findViewById(R.id.rvAllAuthors);
        progressBar  = findViewById(R.id.progressbar);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         authorAdapter   =   new AuthorAdapter(this);
        recyclerView.setAdapter(authorAdapter);
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

    @Override
    public void onRefresh() {
        GetData();
    }

    public void GetData()
    {

        libraryViewModel.GetAuthors("20");

        libraryViewModel.AuthorsLiveData.observe(this, new Observer<List<AuthorModel>>() {
            @Override
            public void onChanged(List<AuthorModel> authorModels) {
                authorAdapter.setList(authorModels);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
