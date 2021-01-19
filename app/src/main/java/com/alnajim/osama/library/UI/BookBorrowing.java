package com.alnajim.osama.library.UI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Authentication.Login;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookBorrowing extends AppCompatActivity {
    private TextView startDate,endDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    LibraryViewModel libraryViewModel;
    LinearLayout llborrowbook,bookInfo;
    String ISBN,bookId ;
    ImageView imgBook;
    TextView bookName,nobook,tvReturn;
    CheckBox checkBox,checkBoxRead;
    LinearLayout llNoLogin;

    ProgressBar progressBar;
    Button giveBack,btnLogin;

    String startDateStr , endDateStr;
    int borrowingCount = 88 ;
    Context context = this;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_borrowing);

        imgBook      = findViewById(R.id.imgBook);
        bookName     = findViewById(R.id.tvBookName);
        llborrowbook = findViewById(R.id.llborrowbook);
        bookInfo     = findViewById(R.id.bookInfo);
        progressBar  = findViewById(R.id.progressbar);
        startDate    = findViewById(R.id.tvStartDate);
        endDate      = findViewById(R.id.tvEndDate);
        nobook       = findViewById(R.id.tvNoBook);
        giveBack     = findViewById(R.id.btnGiveBack);
        tvReturn     = findViewById(R.id.tvReturn);
        checkBox     = findViewById(R.id.checkBox);
        llNoLogin    = findViewById(R.id.llNoLogin);
        btnLogin     = findViewById(R.id.btnLogin);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        Intent intent = getIntent();
        ISBN = intent.getStringExtra("ISBN");

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn())
        {
            llNoLogin.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, Login.class));
                }
            });
        }
        else{
            GetData();
            CheckBookAvailability();
        }







    }

    public void BorrowBook(View view) throws ParseException {


    if (endDate.getText().toString().equals(""))
    {
        Toast.makeText(context, "الرجاء تحديد تاريخ الاعادة", Toast.LENGTH_SHORT).show();

    }
     else {
        //// GET CURRENT DATE
        Date c = Calendar.getInstance().getTime();

        final SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        startDateStr = df.format(c);
        startDate.setText(startDateStr);

        ///// LET USER CHOOSE THE END DATE
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BookBorrowing.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = dateFormat.parse(endDateStr);

        Log.i("endDate", date + " -- "+ endDateStr);
        int share = checkBox.isChecked()?1:0;


        progressBar.setVisibility(View.VISIBLE);
        libraryViewModel.addbookborrowing(bookId, sessionManager.GetUserId(),date,endDateStr,share);
        final Date finalDate = date;
        libraryViewModel.AddQuoteLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                try {
                    llborrowbook.setVisibility(View.GONE);
                    nobook.setVisibility(View.GONE);
                    nobook.setText("");
                    if (s.equals("1"))
                        if (s.equals("1"))
                            libraryViewModel.Updatebookstatus(bookId,"1", sessionManager.GetUserId(),borrowingCount+"");
                    libraryViewModel.SignUpLiveData.observe((LifecycleOwner) context , new Observer<String>() {
                        @Override
                        public void onChanged(String s)
                        {
                            if (s.equals("1"))
                            {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(context, "تم الاستعارة بنجاح ", Toast.LENGTH_SHORT).show();
                                llborrowbook.setVisibility(View.GONE);
                                tvReturn.setVisibility(View.VISIBLE);
                                tvReturn.setText(" تاريح إعادة الكتاب "+endDateStr);



                            }
                            else
                                Toast.makeText(context, "حدث خطأ ما الرجاء المحاولة مجدداً", Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (Exception e)
                {
                    Toast.makeText(BookBorrowing.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                    Log.i("ErroBorrowingbook", e.getMessage());
                }
            }
        });
    }
    }


    public void GetData()
    {
        libraryViewModel.GetBookByISBN(ISBN);
        libraryViewModel.BookInformationLiveData.observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels)
            {
                try{
                    if (bookModels.size()>0)
                    {

                        bookInfo.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        borrowingCount = bookModels.get(0).getBorrowingsCounts();
                        bookName.setText(bookModels.get(0).getBookName());
                        bookId = bookModels.get(0).getBookId();
                        Glide.with(getApplication())
                                .load(bookModels.get(0).getBookImage())
                                .error(R.drawable.defaultbook)
                                .into(imgBook);

                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        tvReturn.setVisibility(View.VISIBLE);
                        tvReturn.setTextColor(Color.RED);
                        tvReturn.setText("الكتاب غير موجود ! \n الرجاء التأكد من أنك أدخلت رقم الكتاب بشكل صحيح  ");
                        bookInfo.setVisibility(View.GONE);
                    }
                }
                catch (Exception e )
                {
                    progressBar.setVisibility(View.GONE);
                    tvReturn.setVisibility(View.VISIBLE);
                    tvReturn.setTextColor(Color.RED);
                    tvReturn.setText("الكتاب غير موجود ! \n الرجاء التأكد من أنك أدخلت رقم الكتاب بشكل صحيح  ");
                    bookInfo.setVisibility(View.GONE);

                }
            }
        });
    }



    /// CHECK IF THE BOOK IF AVAILABLE OR NOT
    public void CheckBookAvailability()
    {
   
        libraryViewModel.GetBookByISBN(ISBN);
        libraryViewModel.BookInformationLiveData.observe(this, new Observer<List<BookModel>>()
        {
            @Override
            public void onChanged(List<BookModel> bookModels) {

                if (bookModels.size()>0) {
                    String bookStatus = bookModels.get(0).getBookStatus();

              
                    if (bookStatus.equals("0")) {

                        bookId = bookModels.get(0).getBookId();
                        nobook.setVisibility(View.GONE);
                        llborrowbook.setVisibility(View.VISIBLE);

                        // GET THE BOOK DATA

                        //// GET CURRENT DATE
                        Date c = Calendar.getInstance().getTime();

                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        startDateStr = df.format(c);
                        startDate.setText(startDateStr);

                        ///// LET USER CHOOSE THE END DATE
                        endDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Calendar cal = Calendar.getInstance();
                                int year = cal.get(Calendar.YEAR);
                                int month = cal.get(Calendar.MONTH);
                                int day = cal.get(Calendar.DAY_OF_MONTH);

                                DatePickerDialog dialog = new DatePickerDialog(
                                        BookBorrowing.this,
                                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                        mDateSetListener,
                                        year, month, day);

                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show();
                            }
                        });

                        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;

                                endDateStr = day + "-" + month + "-" + year;

                                endDate.setText(endDateStr);
                                endDateStr = year + "-" + month + "-" + day;


                            }
                        };

                    }
                
                else {

                    /// CHECK IF THE BOOK IS BORROWED AND THE PERSON WHO BORROWED THE BOOK IS SCAN TO GIVE THE BOOK BACK

                    llborrowbook.setVisibility(View.GONE);
                    nobook.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    libraryViewModel.Getborrowedinfo(bookId);
                    libraryViewModel.UserInformationLiveData.observe(BookBorrowing.this, new Observer<List<UserModel>>() {
                        @Override
                        public void onChanged(List<UserModel> userModels)
                        {
                            Log.i("userID", userModels.get(0).getUserId() + " - "+sessionManager.GetUserId());

                            if (userModels.get(0).getUserId().equals( sessionManager.GetUserId()))//TODO
                            {
                                giveBack.setVisibility(View.VISIBLE);
                                tvReturn.setVisibility(View.VISIBLE);

                                tvReturn.setText("يبدو أّنك إستعرت هذا الكتاب من قبل \n هل تريد إرجاعه ؟ ");
                                giveBack.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        progressBar.setVisibility(View.VISIBLE);
                                        borrowingCount =borrowingCount+1;
                                        libraryViewModel.Updatebookstatus(bookId,"0","0",borrowingCount+"");
                                        libraryViewModel.SignUpLiveData.observe((LifecycleOwner) context , new Observer<String>() {
                                            @Override
                                            public void onChanged(String s)
                                            {
                                                if (s.equals("1"))
                                                {
                                                    progressBar.setVisibility(View.GONE);

                                                    Toast.makeText(context, "تمت الاعادة بنجاح ", Toast.LENGTH_SHORT).show();

                                                    giveBack.setBackgroundColor(Color.BLUE);
                                                    tvReturn.setTextColor(Color.BLUE);
                                                    tvReturn.setText("هل تريد تقييم الكتاب");
                                                    giveBack.setText("تقييم الكتاب");
                                                    giveBack.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent intent = new Intent(BookBorrowing.this,SingleBook.class);
                                                            Toast.makeText(BookBorrowing.this, bookId, Toast.LENGTH_SHORT).show();
                                                            intent.putExtra("bookId",bookId);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    });


                                                }
                                                else
                                                    Toast.makeText(context, "Bad", Toast.LENGTH_SHORT).show();

                                            }
                                        });                                    }
                                });
                            }
                            else{
                                nobook.setText("الكتاب غير متاح حالياً !\n تم إستعارته من قبل  "+userModels.get(0).getFullName()+"\n" +
                                        " حتى تاريخ  ["+ userModels.get(0).getEndDate()+"]");

                            }
                        }
                    });

                }
            }
            else {
                }}
              
        });
        

    }
    
    
    
   

    public void GoToBook(View view)
    {
        Intent intent = new Intent(BookBorrowing.this,SingleBook.class);
        intent.putExtra("bookId",bookId);
        startActivity(intent);
        finish();
    }

    public void Search(View view)
    {
        startActivity(new Intent(this,Search.class));
    }
    public void SuggestBook(View view) { startActivity(new Intent(this,SuggestBook   .class)); }
    public void Back(View view) { finish(); }


    public void printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
       // enddate.get

        Log.i("thedifference ", String.valueOf(different));


        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

         double milisecondsInOneDay   = 86400;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        Log.i("thedifference ", String.valueOf(different/milisecondsInOneDay));





        Toast.makeText(this, elapsedDays+" - ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}

