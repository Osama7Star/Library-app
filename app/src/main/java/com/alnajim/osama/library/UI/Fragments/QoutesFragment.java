package com.alnajim.osama.library.UI.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.QuoteAdapter;
import com.alnajim.osama.library.Models.QuoteModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

import java.util.List;

/**
 * Created by Osama Alnajm on 26-Jan-20.
 */
public class QoutesFragment  extends Fragment {
    private QuoteAdapter quoteAdapter;
    private ProgressBar progressBar1 ;
    private TextView tvEmpty;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        {


            View view = inflater.inflate(R.layout.activity_quotes, container, false);
            progressBar1 = view.findViewById(R.id.progressbar1);
            tvEmpty      = view.findViewById(R.id.tvEmpty);

            RecyclerView recyclerView = view.findViewById(R.id.rvQoutes);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            quoteAdapter = new QuoteAdapter(getContext());
            recyclerView.setAdapter(quoteAdapter);

            final LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

              try {
                  String userId  = getArguments().getString("userId");

                  if (!userId.equals("-1"))
                      libraryViewModel.Getuserquotes(userId);
                  libraryViewModel.UserQuotedLiveData.observe(this, new Observer<List<QuoteModel>>() {
                      @Override
                      public void onChanged(List<QuoteModel> quoteModels)
                      {
                          try {

                              if (quoteModels.size()>0) {
                                  quoteAdapter.setList(quoteModels);
                                  progressBar1.setVisibility(View.GONE);
                              }

                          } catch (Exception e) {
                              tvEmpty.setVisibility(View.VISIBLE);
                              tvEmpty.setText("\n المستخدم لم ينشر إقتباسات حتى الآن  ");
                              progressBar1.setVisibility(View.GONE);
                              Toast.makeText(getContext(), "asdasdasdasd", Toast.LENGTH_SHORT).show();
                          }


                      }
                  });



              }
              catch (Exception e )
              {
                  libraryViewModel.GetQuote();
                  Log.i("QuoteError", e.getMessage());
              }

            libraryViewModel.QuoteLiveData.observe(this, new Observer<List<QuoteModel>>() {
                @Override
                public void onChanged(List<QuoteModel> quoteModels) {

                    try {

                        if (quoteModels.size()>0) {
                            quoteAdapter.setList(quoteModels);
                            progressBar1.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("This user has no review yet");
                    }


                }
            });
            quoteAdapter.setOnItemClickListener(new QuoteAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String quoteId, int likesNumbers)
                {
                    libraryViewModel.UpdateQuoteLikes(quoteId,likesNumbers);
                    libraryViewModel.SignUpLiveData.observe((LifecycleOwner) getContext(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Log.i("LikeTest", s+" - ");
                        }
                    });
                }
            });
            return view  ;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
