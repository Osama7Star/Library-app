package com.alnajim.osama.library.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Osama Alnajm on 27-Jan-20.
 */
public class SliderModel
{
    @SerializedName("sliderImage")

    private String sliderImagePath ;
    private String sliderId ;
    public SliderModel(String sliderImagePath, String sliderId) {
        this.sliderImagePath = sliderImagePath;
        this.sliderId = sliderId;
    }



    public String getSliderImagePath() {
        return sliderImagePath;
    }

    public void setSliderImagePath(String sliderImagePath) {
        this.sliderImagePath = sliderImagePath;
    }

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }
}
