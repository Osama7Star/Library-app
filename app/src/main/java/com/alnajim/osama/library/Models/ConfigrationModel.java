package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class ConfigrationModel {

    @SerializedName("conditions")
    private String conditions;
    @SerializedName("logo_url")
    private String imageUrl;
    @SerializedName("text")
    private String text;
    @SerializedName("splash_time")
    private int splashTime;
    @SerializedName("login_message")
    private String loginMessage;


    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSplashTime() {
        return splashTime;
    }

    public void setSplashTime(int splashTime) {
        this.splashTime = splashTime;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}

