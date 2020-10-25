package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.alnajim.osama.library.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ErrorQrReader extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    EditText bookId;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_qr_reader);

        bottom_navigation  = findViewById(R.id.bottom_navigation);
        bookId = findViewById(R.id.bookId);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();

    }


    public void EnterBookId(View view )
    {
        progressBar.setVisibility(View.VISIBLE);
        String bookIdStr = bookId.getText().toString();

        Intent intent = new Intent(this , BookBorrowing.class);
        intent.putExtra("ISBN",bookIdStr);
        startActivity(intent);
    }


    ////////
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }
}
