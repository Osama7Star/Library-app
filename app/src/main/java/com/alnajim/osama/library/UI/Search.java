package com.alnajim.osama.library.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Search extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
     BooksAdapter searchResult;
    RecyclerView recyclerView ;
    LinearLayout llNoBook;
    EditText etSearch ;
    ImageView imgSearch;
    ProgressBar progressBar;
    BottomNavigationView bottom_navigation;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rvSearchResult);
        progressBar  = findViewById(R.id.progressbar);
        etSearch     = findViewById(R.id.etSearch);
        llNoBook     = findViewById(R.id.llNoBook);
        imgSearch    = findViewById(R.id.imgSearch);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        imgSearch.setVisibility(View.GONE);

        libraryViewModel = ViewModelProviders.of(Search.this).get(LibraryViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResult   =   new BooksAdapter(this,true,libraryViewModel);
        recyclerView.setAdapter(searchResult);
        progressBar.setVisibility(View.GONE);



        try {
            Intent intent = getIntent();
            String tagName = intent.getStringExtra("tagName").toLowerCase();
            if (!intent.getStringExtra("tagName").isEmpty())
                SearchByTag(tagName);
            libraryViewModel.searchbytag(tagName);
            libraryViewModel.SearchBookLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels)
                {
                    try
                    {
                        searchResult.setList(bookModels);
                        progressBar.setVisibility(View.GONE);

                    }
                    catch (Exception e )
                    {

                    }

                }
            });
        }
        catch (Exception e )
        {}


        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;

                llNoBook.setVisibility(View.GONE);

                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    recyclerView.setVisibility(View.GONE);
                    if (event.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final String text = etSearch.getText().toString().trim();
                        recyclerView.setVisibility(View.GONE);
                        if(!text.equals(""))
                        Search(text);


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

                    Search(v.getText().toString());
                    return true;
                }
                return false;
            }
        });

        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();


    }


    // TO CLOSE THE KEYPAD AFTER SHOWING SEARCH RESULT
    private void closeKeyboard1() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void closeKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void SuggestBook(View view)
    {
        String bookName = etSearch.getText().toString();

        Intent intent = new Intent(this, SuggestBook.class);
        intent.putExtra("bookName",bookName);
        startActivity(intent);
        finish();

    }

    public void SearchByTag(String tagName)
    {
        progressBar.setVisibility(View.VISIBLE);

        this.closeKeyboard1();
        etSearch.setText("TAG: "+tagName);
        etSearch.setEnabled(false);
        libraryViewModel.GetMostReadBook();
        libraryViewModel.MostReadBooksLiveData.observe(Search.this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                searchResult.setList(bookModels);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void Back(View view) { finish(); }


    public void Search(String text){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        libraryViewModel.Getsearchbook(text);
        libraryViewModel.SearchBookLiveData.observe(Search.this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {

                closeKeyboard(Search.this);

                try {
                    if (bookModels.size() >0)
                    {

                        searchResult.setList(bookModels);
                        recyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        llNoBook.setVisibility(View.GONE);
                    }
                    else {
                        llNoBook.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                } catch (Exception e) {
                    llNoBook.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.INVISIBLE);
                  //  Toast.makeText(Search.this, "Test", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
