package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alnajim.osama.library.R;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ErrorQrReader extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    EditText bookId;
    TextView tvNologin;
    ProgressBar progressBar ;
    SessionManager sessionManager ;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_qr_reader);

        bottom_navigation  = findViewById(R.id.bottom_navigation);
        tvNologin   = findViewById(R.id.tvNologin);
        btnEnter=  findViewById(R.id.btnEnter);
        bookId = findViewById(R.id.bookId);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
        sessionManager = new SessionManager(this);
        if(bookId.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        if (!sessionManager.isLoggedIn())
        {
            tvNologin.setVisibility(View.VISIBLE);
            bookId.setEnabled(false);
            tvNologin.setVisibility(View.VISIBLE);
            btnEnter.setEnabled(false);

        }

    }


    public void EnterBookId(View view )
    {
        if (!bookId.getText().toString().equals(""))
        {
            progressBar.setVisibility(View.VISIBLE);
            String bookIdStr = bookId.getText().toString();

            Intent intent = new Intent(this , BookBorrowing.class);
            intent.putExtra("ISBN",bookIdStr);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "الرجاء إدخال رمز الكتاب", Toast.LENGTH_SHORT).show();
    }


    ////////
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }
}
