package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.R;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuggestBook extends AppCompatActivity {

    EditText bookName,bookAuthor,bookNote;
    BottomNavigationView bottom_navigation;
    String bookNameS;
    ImageView imgSuggestBook;
    SessionManager sessionManager;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_book);



        bookName = findViewById(R.id.etBookName);
        bookAuthor = findViewById(R.id.etBookAuthor);
        bookNote = findViewById(R.id.etBookNote);
        progressBar = findViewById(R.id.progressbar);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        imgSuggestBook     = findViewById(R.id.imgSuggestBook);
        Intent intent = getIntent();
        bookNameS = intent.getStringExtra("bookName");
        bookName.setText(bookNameS);
        imgSuggestBook.setVisibility(View.GONE);
        if(bookName.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
         sessionManager = new SessionManager(this);
    }

    public void SuggestBook1(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        bookNameS           = bookName.getText().toString();
        String bookAuthors  = bookAuthor.getText().toString();
        String bookNotes    = bookNote.getText().toString();
        if (bookNameS.isEmpty() || bookAuthors.isEmpty())
        {
            Toast.makeText(this, "الرجاء ملىء كل الحقول", Toast.LENGTH_SHORT).show();
            return ;
        }


        LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        libraryViewModel.BookSuggest(bookNameS,bookAuthors,bookNotes,sessionManager.GetUserId() );
        libraryViewModel.AddReviewLiveData.observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {

                try {
                    if (s.equals("1")) {
                        Toast.makeText(SuggestBook.this, "تم إضافة الاقتراح بنجاح", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }

                } catch (Exception e) {
                    Toast.makeText(SuggestBook.this, "حدث خطأ , الرجاء المحاولة مجددا", Toast.LENGTH_SHORT).show();

                }

            }
        });
        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void Back(View view) { finish(); }

}
