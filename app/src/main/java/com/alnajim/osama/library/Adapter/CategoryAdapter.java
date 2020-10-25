package com.alnajim.osama.library.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alnajim.osama.library.Models.CategoryModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.CategoryBooks;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.PostViewHolder> {
    private List<CategoryModel> categoryList = new ArrayList<>();
    private Context context ;
    public CategoryAdapter(Context context){this.context = context;}
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
       final String cateogryName = categoryList.get(position).getGetCategoryName().trim();
        holder.categoryName.setText(cateogryName);
       // holder.categoryBookNumbers.setText("20 " );

        final String categoryId = categoryList.get(position).getCategoryId();
        holder.  itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            Intent intent = new Intent(context, CategoryBooks.class);
            intent.putExtra("categoryId",categoryId);
            intent.putExtra("categoryName",cateogryName);
            context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        try {
            return categoryList.size();

        }
        catch (Exception e )
        {
            return  0 ;
        }
    }

    public void setList(List<CategoryModel> moviesList) {
        this.categoryList = moviesList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;// categoryBookNumbers;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.tvCategoryName);
          //  categoryBookNumbers = itemView.findViewById(R.id.tvCategoryBookNumbers);



        }
    }


}

