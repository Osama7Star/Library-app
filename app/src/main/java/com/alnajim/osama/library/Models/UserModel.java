package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel
{
    @SerializedName("userId")
    private String userId ;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("userName")
    private String userName ;
    @SerializedName("imageUrl")
    private String imageUrl ;
    @SerializedName("universityName")
    private String universityName ;
    @SerializedName("collageName")
    private String collageName ;
    @SerializedName("bio")
    private String bio   ;
    @SerializedName("endDate")
    private String endDate   ;


    public UserModel(String userId, String userName, String imageUrl, String universityName, String collageName, String bio) {
        this.userId = userId;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.universityName = universityName;
        this.collageName = collageName;
        this.bio = bio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCollageName() {
        return collageName;
    }

    public void setCollageName(String collageName) {
        this.collageName = collageName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
