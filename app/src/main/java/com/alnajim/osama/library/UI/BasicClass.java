package com.alnajim.osama.library.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

import com.alnajim.osama.library.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.InetAddress;

/**
 * Created by Osama Alnajm on 28-Jan-20.
 */
public class BasicClass
{
    public Context context ;
    private BottomNavigationView bottomNav;
    BasicClass (BottomNavigationView bottomNav, Context context)
    {
        this.context = context;
        this.bottomNav =bottomNav;
        this.bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    public void setNavigation()
    {
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {

                    switch (item.getItemId())
                    {
                        case R.id.nav_home:
                            context.startActivity(new Intent(context, MainActivity.class));

                            break;
                        case R.id.nav_activities:
                            context.startActivity(new Intent(context, UserActivities.class));

                            break;
                        case R.id.scan:
                            context.startActivity(new Intent(context, QrReader.class));

                            break;


                    }


                    return true;
                }
            };

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
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



}
