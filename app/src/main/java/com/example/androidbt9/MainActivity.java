package com.example.androidbt9;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Runnable, View.OnClickListener {


    private TextView tvTime, tvTimeDay;
    private LinearLayout llDialer, llMessager, llCamera, llMusic, llContact, llGallery;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tvTime = findViewById(R.id.tv_time);
        tvTimeDay = findViewById(R.id.tv_time_day);
        llDialer = findViewById(R.id.ll_dialer);
        llMessager = findViewById(R.id.ll_messager);
        llCamera = findViewById(R.id.ll_camera);
        llContact = findViewById(R.id.ll_contact);
        llGallery = findViewById(R.id.ll_gallery);
        llMusic = findViewById(R.id.ll_music);

        setTime();

        llDialer.setOnClickListener(this);
        llGallery.setOnClickListener(this);
        llMusic.setOnClickListener(this);
        llContact.setOnClickListener(this);
        llCamera.setOnClickListener(this);
        llMessager.setOnClickListener(this);

        thread = new Thread(this);
        thread.start();
    }

    private void setTime() {
        DateFormat dateFormatAll = new SimpleDateFormat("HH:mm");
        DateFormat dateFormatHouse = new SimpleDateFormat("HH");
        Date date = new Date();
        String dateStr = dateFormatAll.format(date);
        int house = Integer.parseInt(dateFormatHouse.format(date));
        String timeDay = "";

        if (5 < house & house < 10) {
            timeDay = "Sáng";
        } else if (10 < house & house < 13) {
            timeDay = "Trưa";
        } else if (13 < house & house < 17) {
            timeDay = "Chiều";
        } else if (17 < house & house < 21) {
            timeDay = "Tối";
        } else {
            timeDay = "Đêm";
        }
        tvTimeDay.setText(timeDay);
        tvTime.setText(dateStr);
    }

    @Override
    public void run() {
        while (true) {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTime();
                    }
                });
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_dialer:
                intent = new Intent(this, DialerActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_camera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.ll_contact:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_CONTACTS);
                startActivity(intent);
                break;
            case R.id.ll_gallery:
                intent = new Intent(this, ImagaActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_music:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_MUSIC);
                startActivity(intent);
                break;
            case R.id.ll_messager:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(intent);
                break;

        }
    }

}