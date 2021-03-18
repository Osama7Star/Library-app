package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

public class ConfigrationModel {

    @SerializedName("conditions")
    private String conditions ;
    @SerializedName("logo_url")

    private String imageUrl ;
    @SerializedName("text")

    private  String text;

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
}

