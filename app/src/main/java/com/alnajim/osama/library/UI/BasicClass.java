package com.alnajim.osama.library.UI;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

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




}
