package com.alnajim.osama.library.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.UserReviewsAdapter;
import com.alnajim.osama.library.Models.ReviewsModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

import java.util.List;

public class ReviewsFragment extends Fragment
{

    private ProgressBar progressBar1 ;
    private UserReviewsAdapter userReviewsAdapter;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        {


            View view = inflater.inflate(R.layout.activity_quotes, container, false);
            progressBar1 = view.findViewById(R.id.progressbar1);
            tvEmpty = view.findViewById(R.id.tvEmpty);

            RecyclerView recyclerView = view.findViewById(R.id.rvQoutes);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            userReviewsAdapter = new UserReviewsAdapter(getContext());
            recyclerView.setAdapter(userReviewsAdapter);

            LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
            String userId  ;
            userId  = getArguments().getString("userId");


            libraryViewModel.Getuserreviews(userId);
                libraryViewModel.UserReviewLiveData.observe(this, new Observer<List<ReviewsModel>>() {
                @Override
                public void onChanged(List<ReviewsModel> reviewsModels) {
                    userReviewsAdapter.setList(reviewsModels);


                    try {

                        if (reviewsModels.size()>0) {
                            userReviewsAdapter.setList(reviewsModels);
                            progressBar1.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("\n المستخدم لم ينشر مراجعات حتى الآن");
                        progressBar1.setVisibility(View.GONE);

                    }}
            });

            return view  ;
        }
        }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
