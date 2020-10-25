package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.R;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddQuote extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    EditText quote ;
    BottomNavigationView bottom_navigation;

    Button addQuoteBtn ;
    ProgressBar progressBar ;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        quote = findViewById(R.id.tvquote);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        addQuoteBtn        = findViewById(R.id.btnAddQuote);
        progressBar = findViewById(R.id.progressbar);
        quote.requestFocus();

        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();


        sessionManager = new SessionManager(this);
        if(quote.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


    }

    public void AddQuote1(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        String quoteStr = quote.getText().toString();
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       String startDateStr = df.format(c);
            if(!quoteStr.equals("")) {
            libraryViewModel.AddQuote(quoteStr,  sessionManager.GetUserId(), 0,startDateStr);
            libraryViewModel.AddQuoteLiveData.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    try {
                        if (s.equals("1")) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(AddQuote.this, "تم إضافة الاقتباس بنجاح", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AddQuote.this, "حدث خطأ , الرجاء المحاولة مجددا", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }

    @Override
    public void onRefresh() {

    }
}
