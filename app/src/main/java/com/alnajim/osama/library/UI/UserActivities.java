package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alnajim.osama.library.Adapter.QuoteAdapter;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Fragments.ActivitesFragment;
import com.alnajim.osama.library.UI.Fragments.QoutesFragment;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivities extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    LibraryViewModel libraryViewModel;
    BottomNavigationView bottom_navigation;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    QuoteAdapter quoteAdapter ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activities);

//        final UserActivitiesAdapter userActivitiesAdapter = new UserActivitiesAdapter();
//        final RecyclerView recyclerView = findViewById(R.id.rvUserActivites);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
//
//        recyclerView.setAdapter(userActivitiesAdapter);
//        libraryViewModel.ReviewsLiveData.observe(this, new Observer<List<ReviewsModel>>() {
//            @Override
//            public void onChanged(List<ReviewsModel> Reviews) {
//                userActivitiesAdapter.setList(Reviews);
//
//            }
//        });
        SetFragments( savedInstanceState);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        progressBar        = findViewById(R.id.progressbar);
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SetFragments( savedInstanceState);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        /////////

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_qoute: {

                            selectedFragment = new QoutesFragment();
                            break;

                        }
                        case R.id.nav_activities1: {
                            selectedFragment = new ActivitesFragment();

                            break;
                        }


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }


    public void SetFragments(Bundle savedInstanceState)
    {

        BottomNavigationView bottomNav = findViewById(R.id.fragmentNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new QoutesFragment()).commit();
        }
    }


    @Override
    public void onRefresh() {

    }
}
