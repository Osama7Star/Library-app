package com.alnajim.osama.library.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alnajim.osama.library.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditUserInformation extends AppCompatActivity {

    EditText userName , userUniversity,userCollage,userBio ;
    String    userNameStr,userUnivesityStr,userCollageStr,userBioStr;

    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);

        initView();

        Intent intent = getIntent();
        userNameStr         = intent.getStringExtra("userName");
        userUnivesityStr    = intent.getStringExtra("userUniversity");
        userCollageStr      = intent.getStringExtra("userCollage");
        userBioStr          = intent.getStringExtra("userBio");

        userName.setText(userNameStr);
        userUniversity.setText(userUnivesityStr);
        userCollage.setText(userCollageStr);
        userBio.setText(userBioStr);


        /////////
        BasicClass basicClass = new BasicClass(bottom_navigation,this);
        basicClass.setNavigation();
    }

    public void Save(View view)
    {
      userNameStr         = userName.getText().toString();
      userUnivesityStr    = userUniversity.getText().toString();
      userCollageStr      = userCollage.getText().toString();
      userBioStr          = userBio.getText().toString();

        Intent intent = new Intent(EditUserInformation.this, UserProfile.class);
        intent.putExtra("userName",userNameStr);
        intent.putExtra("userUnivesity",userUnivesityStr);
        intent.putExtra("userCollage",userCollageStr);
        intent.putExtra("userBio",userBioStr);


    }
    public  void initView()
    {

        userName = findViewById(R.id.tvUserName);
        userUniversity = findViewById(R.id.tvUserUniversity);
        userCollage = findViewById(R.id.tvUserCollage);
        userBio     = findViewById(R.id.tvUserBio);
        bottom_navigation  = findViewById(R.id.bottom_navigation);
    }
    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }

}
