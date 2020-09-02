package com.example.androidbt9;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImagaActivity extends AppCompatActivity {

    public static final int REQUEST_IMGAE = 1;
    private ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
    }

    private void initViews() {
        ivMain = findViewById(R.id.iv_main);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Get image"), REQUEST_IMGAE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMGAE) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(this).load(data.getData()).into(ivMain);
            }
        }
    }
}