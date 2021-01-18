package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.AuthorAdapter;
import com.alnajim.osama.library.Models.AuthorModel;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class AllAuthors extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    BottomNavigationView bottom_navigation;
    LibraryViewModel libraryViewModel;
    AuthorAdapter authorAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText etSearch ;
    TextView tvNotFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);

        recyclerView = findViewById(R.id.rvAllAuthors);
        progressBar  = findViewById(R.id.progressbar);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        tvNotFound = findViewById(R.id.tvNotFound);
        etSearch = findViewById(R.id.etSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         authorAdapter   =   new AuthorAdapter(this);
        recyclerView.setAdapter(authorAdapter);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        swipeRefreshLayout.setOnRefreshListener(this);



        GetData();

        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;
                tvNotFound.setVisibility(View.GONE);
                //llNoBook.setVisibility(View.GONE);

                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    recyclerView.setVisibility(View.GONE);
                    if (event.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final String text = etSearch.getText().toString().trim();
                        recyclerView.setVisibility(View.GONE);
                        if(!text.equals(""))
                            SearchBook(text);


                    }
                }

                return false;
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    progressBar.setVisibility(View.VISIBLE);

                    SearchBook(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

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

        libraryViewModel.GetAuthors("1000");

        libraryViewModel.AuthorsLiveData.observe(this, new Observer<List<AuthorModel>>() {
            @Override
            public void onChanged(List<AuthorModel> authorModels) {
                authorAdapter.setList(authorModels);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void SearchBook(String text){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvNotFound.setVisibility(View.GONE);
    //    recyclerView.setVisibility(View.GONE);
        libraryViewModel.SearchAuthors(text);
        libraryViewModel.AuthorsLiveData.observe(AllAuthors.this, new Observer<List<AuthorModel>>() {
            @Override
            public void onChanged(List<AuthorModel> authorModels) {

                //closeKeyboard(Search.this);

                try {
                    if (authorModels.size() >0)
                    {
                        BasicClass.closeKeyboard(AllAuthors.this);
                        authorAdapter.setList(authorModels);
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                      //  llNoBook.setVisibility(View.GONE);
                    }
                    else {
                       // llNoBook.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);
                        tvNotFound.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                   // llNoBook.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvNotFound.setVisibility(View.VISIBLE);
                    //  Toast.makeText(Search.this, "Test", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
