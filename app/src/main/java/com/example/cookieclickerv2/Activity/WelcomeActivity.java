package com.example.cookieclickerv2.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookieclickerv2.Converter.CoinConverter;
import com.example.cookieclickerv2.Converter.MoneyConverter;
import com.example.cookieclickerv2.R;
import com.example.cookieclickerv2.Storage.Storage;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity {

    Storage storage;

    Button continued;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        continued = (Button) findViewById(R.id.continued);
        storage = new Storage(this);

        if (!storage.getIsFirstOpen()) {
            startActivity(new Intent(this, GameActivity.class));
            finishAffinity();
        } else {
            setVideoView();
            setContinuedButton();
        }
    }

    private void setContinuedButton() {
        continued.setOnClickListener(v -> {
            startActivity(new Intent(this, GameActivity.class));
            storage.setOpenApp();
            finishAffinity();
        });
    }

    private void setVideoView() {
        VideoView videoView = findViewById(R.id.viewView);
        Uri localUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.background);
        try {
            videoView.setVideoURI(localUri);
            videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
            videoView.start();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            ViewGroup.LayoutParams params = videoView.getLayoutParams();
            params.width = metrics.widthPixels;
            params.height = metrics.heightPixels;
            videoView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}