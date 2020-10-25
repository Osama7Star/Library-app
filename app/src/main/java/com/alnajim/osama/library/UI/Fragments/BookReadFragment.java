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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.BooksAdapter;
import com.alnajim.osama.library.Models.BookModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

import java.util.List;

public class BookReadFragment extends Fragment {
    private BooksAdapter readBooks;
    private ProgressBar progressBar1;
    private TextView tvEmpty;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        {


            View view = inflater.inflate(R.layout.activity_quotes, container, false);
            tvEmpty = view.findViewById(R.id.tvEmpty);

            RecyclerView recyclerView = view.findViewById(R.id.rvQoutes);
            progressBar1 = view.findViewById(R.id.progressbar1);
            LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
            readBooks = new BooksAdapter(getActivity(),true,libraryViewModel);
            //assert getArguments() != null;
            String userId  = getArguments().getString("userId");
            recyclerView.setAdapter(readBooks);
            libraryViewModel.Getuserrebooks(userId);
            libraryViewModel.UserBooksLiveData.observe(this, new Observer<List<BookModel>>() {
                @Override
                public void onChanged(List<BookModel> bookModels) {
                    try {

                        if (bookModels.size()>0) {
                            readBooks.setList(bookModels);
                            progressBar1.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText("\n  المستخدم لم يقرأ كتب حتى الآن ");
                        progressBar1.setVisibility(View.GONE);

                    }


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
