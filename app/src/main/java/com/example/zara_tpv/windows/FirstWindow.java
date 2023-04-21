package com.example.zara_tpv.windows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.zara_tpv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstWindow extends AppCompatActivity implements View.OnClickListener {
    VideoView vv;
    Context context;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_window);

        vv = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.initial_video);
        vv.setVideoURI(uri);
        vv.start();

        vv.setOnPreparedListener(mp -> mp.setLooping(true));

        FloatingActionButton button_spanish = findViewById(R.id.button_languageSpanish);
        FloatingActionButton button_english = findViewById(R.id.button_languageEnglish);
        button_spanish.setOnClickListener(this);
        button_english.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        FloatingActionButton fab = (FloatingActionButton) v;
        switch (fab.getId()) {
            case R.id.button_languageSpanish:
                setLocale("es");
                break;
            case R.id.button_languageEnglish:
                setLocale("en");
                break;
        }
    }

    @Override
    protected void onRestart() {
        vv.start();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        vv.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        vv.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        vv.stopPlayback();
        super.onDestroy();
    }

    public void setLocale(String language) {
        Locale myLocale = new Locale(language);
        Resources res = this.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, res.getDisplayMetrics());
        finish();
        startActivity(new Intent(this, ResumeShopWindow.class));
    }
}