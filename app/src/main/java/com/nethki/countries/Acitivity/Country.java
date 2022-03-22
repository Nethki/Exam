package com.nethki.countries.Acitivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nethki.countries.Model.CountryName;
import com.nethki.countries.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class Country extends AppCompatActivity {

    List<CountryName> countryNameList;
    Toolbar mToolbar;
    ImageView mFlag;
    TextView mContinent,mRegion,mSubRegion,mCapital,mLanguages,mCurrencies,mPopulation,mTimeZone,mLatLng,mArea;
    String imageUrl;

    boolean isImageFitToScreen;
    int flagWidth,flagHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        //initialize xml
        mToolbar = findViewById(R.id.my_toolbar);
        mFlag = findViewById(R.id.img_flags);
        mContinent = findViewById(R.id.txt_continent);
        mRegion = findViewById(R.id.txt_region);
        mSubRegion = findViewById(R.id.txt_sub_region);
        mCapital = findViewById(R.id.txt_capital);
        mLanguages = findViewById(R.id.txt_language);
        mCurrencies = findViewById(R.id.txt_currency);
        mPopulation = findViewById(R.id.txt_population);
        mTimeZone = findViewById(R.id.txt_timezone);
        mLatLng = findViewById(R.id.txt_latlang);
        mArea = findViewById(R.id.txt_area);
        flagWidth = mFlag.getMaxWidth();
        flagHeight = mFlag.getMaxHeight();
        mFlag.setOnTouchListener(mTouchListener);

        countryNameList = new Gson().fromJson(getIntent().getStringExtra("data"),new TypeToken<List<CountryName>>(){}.getType());

        for(int i = 0 ; i < countryNameList.size(); i++){
            Log.e("COUNTRY ", countryNameList.get(i).getName().getCommon());
            mToolbar.setTitle(countryNameList.get(i).getName().getCommon());
            imageUrl = countryNameList.get(i).getFlags().getPng();
            Glide.with(this)
                    .load(countryNameList.get(i).getFlags().getPng())
                    .centerCrop()
                    .into(mFlag);
           String tempContinent = "";
            for(String continent : countryNameList.get(i).getContinents()){
                tempContinent += continent + ", ";
            }
            mContinent.setText(tempContinent.replaceAll(", $", ""));
            mRegion.setText(countryNameList.get(i).getRegion());
            mSubRegion.setText(countryNameList.get(i).getSubregion());

            String tempCapital = "";
            for(String capital : countryNameList.get(i).getCapital()){
                tempCapital += capital + ", ";
            }
            mCapital.setText(tempCapital.replaceAll(", $", ""));

            String tempLanguage = "";
            Log.e("TEST ", "here "+countryNameList.get(i).getLanguages());
            JSONObject jsonObject = new JSONObject(countryNameList.get(i).getLanguages());
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                tempLanguage+= jsonObject.optString(key) + ", ";
            }
            mLanguages.setText(tempLanguage.replaceAll(", $", ""));

            String tempCurrencies = "";
            JSONObject jsonObjectCurrency = new JSONObject(countryNameList.get(i).getCurrencies());
            Iterator<String> iteratorCurrency = jsonObjectCurrency.keys();
            while (iteratorCurrency.hasNext()) {
                String key = iteratorCurrency.next();
                tempCurrencies+= key + ", ";
            }
            mCurrencies.setText(tempCurrencies.replaceAll(", $", ""));

            mPopulation.setText(""+countryNameList.get(i).getPopulation());

            String tempTimeZone = "";
            for(String timezone : countryNameList.get(i).getTimezones()){
                tempTimeZone += timezone + ", ";
            }
            mTimeZone.setText(tempTimeZone.replaceAll(", $", ""));

            String tempLatLng = "";
            for(Double latlng : countryNameList.get(i).getLatlng()){
                tempLatLng += latlng + ", ";
            }
            mLatLng.setText(tempLatLng.replaceAll(", $", ""));

            mArea.setText(""+countryNameList.get(i).getArea());
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnTouchListener mTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Dialog builder = new Dialog(Country.this);
            builder.setCanceledOnTouchOutside(true);
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            builder.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;
                }
            });
            int a=v.getId();

            ImageView imageView = new ImageView(Country.this);
            imageView.setAdjustViewBounds(true);
            Glide.with(Country.this)
                    .load(imageUrl)
                    .fitCenter()
                    .into(imageView);           //set the image in dialog popup
            //below code fullfil the requirement of xml layout file for dialoge popup

            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            builder.show();
            return false;
        }
    };
}
