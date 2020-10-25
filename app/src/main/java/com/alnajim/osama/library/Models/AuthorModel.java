package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class AuthorModel
{   @SerializedName("authorId")
    private String authorId ;
    @SerializedName("authrorName")
    private String authorName ;
    @SerializedName("imageUrl")
    private String authorImageUrl ;
    @SerializedName("authorBio")
    private String authorBio ;


    public AuthorModel( String authorName, String authorImageUrl,String authorBio) {
        this.authorName = authorName;
        this.authorImageUrl = authorImageUrl;
        this.authorBio = authorBio;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }



    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public String getAuthorBio() {
        return authorBio;
    }

    public void setAuthorBio(String authorBio) {
        this.authorBio = authorBio;
    }
}
