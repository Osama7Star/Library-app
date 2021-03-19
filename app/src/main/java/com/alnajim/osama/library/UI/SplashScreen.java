package com.alnajim.osama.library.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Models.ConfigrationModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SplashScreen extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    LibraryViewModel libraryViewModel;

    ImageView imageView ;
    ProgressBar progressBar ;
    TextView tvSplash;

        int SPLASH_TIME = 2000; //This is 1 seconds
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
            imageView = findViewById(R.id.imgSplash);
            tvSplash = findViewById(R.id.tvSplash);
            progressBar = findViewById(R.id.progress);
            libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

            GetData();

            //Code to start timer and take action after the timer ends

        }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    @Override
    public void onRefresh() {
        GetData();
    }

    public void GetData()
    {
        if (!isNetworkConnected())
        {
            imageView.setImageResource(R.drawable.nointernet);
            tvSplash.setText("تحقق من إتصالك بالانترنيت");
            progressBar.setVisibility(View.GONE);


        }
        libraryViewModel.GetConditions();
        libraryViewModel.ConditionsLiveData.observe(this, new Observer<List<ConfigrationModel>>() {
            @Override
            public void onChanged(List<ConfigrationModel> strings) {
                progressBar.setVisibility(View.GONE);
                tvSplash.setText(strings.get(0).getText());
                String imageUrl = strings.get(0).getImageUrl();
                Glide.with(getApplication())
                        .load(imageUrl)
                        .error(R.drawable.defaultbook)
                        .into(imageView);

                SPLASH_TIME = strings.get(0).getSplashTime();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do any action here. Now we are moving to next page
                        Intent mySuperIntent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(mySuperIntent);

                        //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                        finish();

                    }
                }, SPLASH_TIME);


            }
        });
    }
}

