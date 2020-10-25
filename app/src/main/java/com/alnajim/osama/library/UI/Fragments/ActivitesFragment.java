package com.alnajim.osama.library.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Adapter.BooksActivitesAdapter;
import com.alnajim.osama.library.Models.BooksActivitesModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

import java.util.List;

/**
 * Created by Osama Alnajm on 26-Jan-20.
 */
public class ActivitesFragment  extends Fragment {
    private BooksActivitesAdapter booksActivitesAdapter;
    private ProgressBar progressBar1;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        {


            View view = inflater.inflate(R.layout.activity_quotes, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.rvQoutes);
            progressBar1 = view.findViewById(R.id.progressbar1);

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
            booksActivitesAdapter = new BooksActivitesAdapter(getActivity());

            recyclerView.setAdapter(booksActivitesAdapter);
            LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
            libraryViewModel.getbooksborrowingrecords();
            libraryViewModel.BooksActivitesLiveData.observe(this, new Observer<List<BooksActivitesModel>>() {
                @Override
                public void onChanged(List<BooksActivitesModel> booksActivitesModels) {
                    booksActivitesAdapter.setList(booksActivitesModels);
                    progressBar1.setVisibility(View.GONE);
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
