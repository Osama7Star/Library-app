package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class AuthorBooks extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    BooksAdapter booksAdapter;

    TextView authorName ,authorBio,bookNumber,noBook ;
    ImageView authorImage;
    CardView cvAuthorBook;
    ProgressBar progressBar;
    BottomNavigationView bottom_navigation;
    SwipeRefreshLayout swipeRefreshLayout;
    LibraryViewModel libraryViewModel;

    String authorIdStr,authorNameStr,authorBioStr,imageUrlStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_books);

        authorName       = findViewById(R.id.tvAuthorName);
        authorBio        = findViewById(R.id.tvAuthorBio);
        authorImage      = findViewById(R.id.imgProfile);
        bookNumber       = findViewById(R.id.tvBooksNumber);
        cvAuthorBook     = findViewById(R.id.cvAuthorBook);
        progressBar      = findViewById(R.id.progressbar);
        noBook           = findViewById(R.id.tvNoBook);
        bottom_navigation  = findViewById(R.id.bottom_navigation);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);


        swipeRefreshLayout.setOnRefreshListener(this);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        booksAdapter = new BooksAdapter(this,true,libraryViewModel);
        Intent intent = getIntent();

        authorIdStr   = intent.getStringExtra("authorId");
        authorNameStr = intent.getStringExtra("authorName");
        authorBioStr  = intent.getStringExtra("authorBio");
        imageUrlStr   = intent.getStringExtra("authorImage");

        InitRecyclerView ();

        GetData();

        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();

    }


    void InitRecyclerView ()
    {
        RecyclerView recyclerView = findViewById(R.id.rvAuthorBooks);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(booksAdapter);

    }
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }


    public void GetData()
    {
        libraryViewModel.getAuthorBook(authorIdStr);
        libraryViewModel.AuthorBooksLiveData.observe(this, new Observer<List<BookModel>>()
        {
            @Override
            public void onChanged(List<BookModel> bookModels)
            {
                try {
                    if (bookModels.size()>0) {
                        booksAdapter.setList(bookModels);
                        bookNumber.setText( bookModels.size()+" كتب ");
                        progressBar.setVisibility(View.GONE);
                        cvAuthorBook.setVisibility(View.VISIBLE);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
                catch (Exception e )
                {
                    progressBar.setVisibility(View.GONE);
                    noBook.setVisibility(View.VISIBLE);


                }
            }
        });

        authorName.setText(authorNameStr);
        authorBio.setText(authorBioStr);
        Glide.with(this)
                .load(imageUrlStr)
                .placeholder(R.drawable.defaultbook)
                .error(R.drawable.defaultbook)
                .into(authorImage);


    }

    @Override
    public void onRefresh() {
        GetData();

    }
}
