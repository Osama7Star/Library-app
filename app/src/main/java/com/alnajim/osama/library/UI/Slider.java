package com.alnajim.osama.library.UI;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.library.Models.SliderModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.List;


//////// FOR DRAWER

public class Slider extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    /// TOOLBAR
    Toolbar toolbar ;
    /// SLIDER
    private SliderLayout mDemoSlider;




    // ImageButton tvSubCategoryName2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        mDemoSlider = findViewById(R.id.slider);

        LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);


        libraryViewModel.GetSliderImages();
        libraryViewModel.SliderImagesLiveData.observe(this, new Observer<List<SliderModel>>() {
            @Override
            public void onChanged(List<SliderModel> sliderModels) {
                ImageSlider(sliderModels);
            }
        });











    }// End onCreate

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") .toString(), Toast.LENGTH_SHORT).show();

      //  Intent intent = new Intent(MainActivity.this, Product.class);
        //intent.putExtra("prodcutId",slider.getBundle().get("extra").toString() );
        //startActivity(intent);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("mSlider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    void ImageSlider(List<SliderModel> Slider )

    {





        for(SliderModel slider :Slider){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(slider.getSliderId() + " | ")
                    .image(slider.getSliderImagePath())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

           // Log.i("ImagePath",RetrofitClass.BASE_IMAGE_URL+slider.getImagePath());
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",slider.getSliderId());
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
//        ListView l = (ListView)findViewById(R.id.transformers);
//        l.setAdapter(new TransformerAdapter(this));
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
//                Toast.makeText(MainActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    //// INIT THE  VIEW


}



