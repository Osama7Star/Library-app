package com.alnajim.osama.library.UI.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.Models.ConfigrationModel;
import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.BasicClass;
import com.alnajim.osama.library.UI.MainActivity;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.Utilites.SqliteHandler;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class Login extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    EditText email,password;
    ProgressBar progressBar ;
    TextView tvActivationMessage;
    private SessionManager session;
    private SqliteHandler db;
    Button btnToMan;
    private String testtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        db = new SqliteHandler(getApplicationContext());
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        progressBar = findViewById(R.id.progressbar11);
        btnToMan    = findViewById(R.id.btnToMan);
        tvActivationMessage = findViewById(R.id.tvActivationMessage);
        GetConfigration ( );
        // Session manager
        session = new SessionManager(getApplicationContext());

        try{
            Intent intent = getIntent();
            String userNameStr = intent.getStringExtra("userName");
            String passwordStr = intent.getStringExtra("password");


            email.setText(userNameStr);
            password.setText(passwordStr);
        }
        catch (Exception e )
        {}
    }


    public void LogIn(View view) {
        BasicClass.closeKeyboard(this);
        String userNameStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if (userNameStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            libraryViewModel.LogIn(userNameStr, passwordStr)    ;

            libraryViewModel.UserInformationLiveData.observe(this, new Observer<List<UserModel>>() {
                @Override
                public void onChanged(List<UserModel> userModels) {
                    try {
                      //  db.addUser(userModels.get(0).getUserId());
                      if (userModels.size()>0)
                      {
                          if (userModels.get(0).getActive().equals("1"))
                          {
                              String userId    = userModels.get(0).getUserId();
                              String userName  =  userModels.get(0).getUserName();
                              session.setLogin(true,userId,userName);

                              startActivity(new Intent(Login.this, MainActivity.class));
                              finish();
                          }
                          else {
                              Toast.makeText(Login.this, "الرجاء الإنتظار حتى يقوم الأدمن بتفعيل الإيميل ", Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.GONE);
                              session.setLogin(false,"userId","userName");
                              tvActivationMessage.setText(testtest);}

                      }
                      else  {
                          Toast.makeText(Login.this,
                                  "خطأ في كلمة السر او إسم المستخدم", Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        Toast.makeText(Login.this,
                                "خطأ في كلمة السر او إسم المستخدم", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Log.i("loginerror", "onChanged: "+e.getMessage());



                    }
                }
            });
        }
    }

    public void BackToRegister(View view){startActivity(new Intent(this, Signup.class));}
    public void GoToMain(View view ){startActivity(new Intent(this, MainActivity.class));}




    private void GetConfigration ( )
    {
        libraryViewModel.GetConditions();
        libraryViewModel.ConditionsLiveData.observe(this, new Observer<List<ConfigrationModel>>() {
            @Override
            public void onChanged(List<ConfigrationModel> strings) {
                progressBar.setVisibility(View.GONE);


                testtest = strings.get(0).getLoginMessage();
            }
        });
    }
}
