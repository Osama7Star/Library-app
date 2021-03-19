package com.alnajim.osama.library.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alnajim.osama.library.Models.ConfigrationModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;

import java.util.List;


public class Conditions extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    TextView conditions;
    ImageView imageView;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        conditions = findViewById(R.id.tvcondition);
        imageView  = findViewById(R.id.imgLogo);
        progressBar = findViewById(R.id.progress);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        libraryViewModel.GetConditions();
        libraryViewModel.ConditionsLiveData.observe(this, new Observer<List<ConfigrationModel>>() {
            @Override
            public void onChanged(List<ConfigrationModel> strings) {
                progressBar.setVisibility(View.GONE);
                conditions.setText(strings.get(0).getConditions());
                String imageUrl = strings.get(0).getImageUrl();
                Glide.with(getApplication())
                        .load(imageUrl)
                        .error(R.drawable.defaultbook)
                        .into(imageView);
                Log.i("after", strings.get(0).getConditions());


            }
        });
    }
}
