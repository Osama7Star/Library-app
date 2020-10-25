package com.alnajim.osama.library.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Fragments.BookReadFragment;
import com.alnajim.osama.library.UI.Fragments.QoutesFragment;
import com.alnajim.osama.library.UI.Fragments.ReviewsFragment;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    TextView userName ,fullName, userUniversity,userCollage,userBio ;
    BottomNavigationView bottom_navigation;
    ProgressBar progressbar,progressbar1;
    CircleImageView profile_image;
    LinearLayout llUserInfo;
    String    userNameStr,fullNameStr,userUnivesityStr,userCollageStr,userBioStr;
    String userId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initView();

        Intent intent = getIntent();
        userId    = intent.getStringExtra("userId");
        final Context context = this ;
        if (userId!=null)
        {
            LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
            libraryViewModel.GetUserInformation(userId);
            libraryViewModel.UserInformationLiveData.observe(this, new Observer<List<UserModel>>() {
                        @Override
                        public void onChanged(List<UserModel> userModels)
                        {
                            llUserInfo.setVisibility(View.VISIBLE);
                            userName.setText("@"+userModels.get(0).getUserName());
                            fullName.setText(userModels.get(0).getFullName());
                            userUniversity.setText(userModels.get(0).getUniversityName());
                            userCollage.setText(userModels.get(0).getCollageName());
                            userBio.setText(userModels.get(0).getBio());
                            String imagePath = userModels.get(0).getImageUrl();
                            Glide.with(context)
                                    .load(imagePath)
                                    .error(R.drawable.defultuser)
                                    .into(profile_image);

                            // HIDE PROGRESSBAR WHEN THE DATA IS LOADED
                            progressbar.setVisibility(View.GONE);

                        }
                    }
            );

        }
        else
            Toast.makeText(context, "Not from another activity ", Toast.LENGTH_SHORT).show();




        BottomNavigationView bottomNav = findViewById(R.id.fragmentNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null)
        {

//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new BookReadFragment()).commit();


        }

        ReceiveIntent(intent);



        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.Readbooks: {

                            selectedFragment = new BookReadFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("userId", userId);
                            selectedFragment.setArguments(bundle);


                            break;

                        }
                        case R.id.reviews: {
                            selectedFragment = new ReviewsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("userId", userId);
                            selectedFragment.setArguments(bundle);

                            break;
                        }
                        case R.id.quotes: {
                            selectedFragment = new QoutesFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("userId", userId);
                            selectedFragment.setArguments(bundle);

                            break;
                        }


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();


                    return true;
                }
            };



    public  void initView()
    {

        userName       = findViewById(R.id.tvUserName1);
        fullName       = findViewById(R.id.tvFullName);
        userUniversity = findViewById(R.id.tvUserUniversity);
        userCollage    = findViewById(R.id.tvUserCollage);
        userBio        = findViewById(R.id.tvUserBio);
        progressbar    = findViewById(R.id.progressbar);
        progressbar1   = findViewById(R.id.progressbar1);
        profile_image  = findViewById(R.id.profile_image);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        llUserInfo  = findViewById(R.id.llUserInfo);
    }

    public void ReceiveIntent(Intent intent)
    {


        try{
            if(intent!=null)
            {
//                userNameStr         = intent.getStringExtra("userName");
//                fullNameStr         = intent.getStringExtra("fullName");
//                userUnivesityStr    = intent.getStringExtra("userUniversity");
//                userCollageStr      = intent.getStringExtra("userCollage");
//                userBioStr          = intent.getStringExtra("userBio");



//                userName.setText("@"+userNameStr+"--");
//                fullName.setText(fullNameStr+"--");
//                userUniversity.setText(userUnivesityStr);
//                userCollage.setText(userCollageStr);
//                userBio.setText(userBioStr);
            }
            else {

            }

        }
        catch (Exception e){

        }



    }
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }

}
