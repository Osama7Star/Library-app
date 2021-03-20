package com.alnajim.osama.library.UI.Authentication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.BasicClass;
import com.alnajim.osama.library.UI.Conditions;
import com.alnajim.osama.library.UI.MainActivity;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

public class Signup extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    private SessionManager session;
    ProgressBar progressBar ;
    Context context ;
    Button register ,btnToMan;
    SessionManager sessionManager;
    TextView tvCondition;

    EditText fullName,email,password,univeristyName,collageName,bio,imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        progressBar = findViewById(R.id.progressbar);
        register = findViewById(R.id.btnRegister);
        tvCondition =findViewById(R.id.tvConditions);
        btnToMan = findViewById(R.id.btnToMan);
        context = this;
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        session = new SessionManager(getApplicationContext());
        InitView();
        sessionManager = new SessionManager(this);
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        sessionManager.SetFirstTime();

        tvCondition.setPaintFlags(tvCondition.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


    }



    public void SignUp(View view)
    {

        register.setEnabled(false);
        final String fullNameStr = fullName.getText().toString().toLowerCase();
        String emailStr1 = email.getText().toString();
        final String emailStr = emailStr1.replace(" ", "");
        final String passwordStr = password.getText().toString();
        final String univsersityNameStr = univeristyName.getText().toString();
        final String collageNameStr = collageName.getText().toString();
        final String bioStr = bio.getText().toString();

        if (fullNameStr.equals("") || emailStr.equals("") || passwordStr.equals("") || univsersityNameStr.equals("") ||collageNameStr.equals(""))
        {
            Toast.makeText(this, "الرجاء ملىْ كل الحقول", Toast.LENGTH_SHORT).show();
            register.setEnabled(true);

        }
        else{
            register.setEnabled(true);

            if (!BasicClass.isEmailValid(emailStr))
            {
                Toast.makeText(context, "الرجاء إدخال إيميل صحيح ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressBar.setVisibility(View.VISIBLE);
                libraryViewModel.Checkusername(emailStr);
                libraryViewModel.UserNameLiveData.observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("1")) {
                            Toast.makeText(Signup.this, "الإيميل موجود مسبقاً!\n الرجاء المحاولة بإيميل آخر ", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                                    finish();
                                    return ;

                        }
                        else
                        {

                            libraryViewModel.SignUp(fullNameStr,emailStr,passwordStr,univsersityNameStr,collageNameStr,bioStr);
                            libraryViewModel.SignUpLiveData.observe((LifecycleOwner) context, new Observer<String>() {
                                @Override
                                public void onChanged(String s)
                                {

                                    try
                                    {

                                        //session.setLogin(true);
                                        Toast.makeText(Signup.this, "تم تسجيل الحساب بنجاح", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Signup.this, Login.class);
                                        intent.putExtra("userName",emailStr);
                                        intent.putExtra("password",passwordStr);

                                        startActivity(intent);

                                        finish();

                                    }
                                    catch (Exception e )
                                    {
                                        progressBar.setVisibility(View.GONE);

                                        Toast.makeText(Signup.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    }
                });
            }

        }
    }


    public void goToLogin(View view ){startActivity(new Intent(this, Login.class));}
   // public void GoToMain(View view ){startActivity(new Intent(this, MainActivity.class));}





    public void InitView()
    {
        fullName = findViewById(R.id.etFullName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        univeristyName = findViewById(R.id.etUserUniversity);
        collageName = findViewById(R.id.etCollage);
        bio         = findViewById(R.id.etBio);
    }

    public void GoToConditionsPage(View view)
    {
        startActivity(new Intent(this, Conditions.class));
    }
}
