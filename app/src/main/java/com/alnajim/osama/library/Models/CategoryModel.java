package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryModel
{
    @SerializedName("categoryId")
    private String categoryId ;
    @SerializedName("categoryName")
    private String CategoryName ;


    public CategoryModel( String getCategoryName) {
        this.CategoryName = getCategoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getGetCategoryName() {
        return CategoryName;
    }

    public void setGetCategoryName(String getCategoryName) {
        this.CategoryName = getCategoryName;
    }



}
