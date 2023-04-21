package com.example.zara_tpv.windows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.example.zara_tpv.R;

public class FirstWindow extends AppCompatActivity {
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_window);

        vv = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.initial_video);
        vv.setVideoURI(uri);
        vv.start();

        vv.setOnPreparedListener(mp -> mp.setLooping(true));
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

    public void launchMainActivity(View view) {
        startActivity(new Intent(this, MainWindow.class));
    }
}