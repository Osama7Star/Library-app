package com.alnajim.osama.library.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.QuoteAdapter;
import com.alnajim.osama.library.Models.QuoteModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class UserQuotes extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        bottom_navigation  = findViewById(R.id.bottom_navigation);
        final QuoteAdapter quoteAdapter = new QuoteAdapter(this);
        final RecyclerView recyclerView = findViewById(R.id.rvQoutes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);


       // libraryViewModel.Getuserquotes();
        recyclerView.setAdapter(quoteAdapter);
        libraryViewModel.QuoteLiveData.observe(this, new Observer<List<QuoteModel>>() {
            @Override
            public void onChanged(List<QuoteModel> Reviews) {
                quoteAdapter.setList(Reviews);

            }
        });

        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
    }
}
