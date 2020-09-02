package com.example.androidbt9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialerActivity extends AppCompatActivity implements View.OnClickListener {

    private final int[] numbers = new int[]{R.id.tv_zero, R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_five, R.id.tv_six, R.id.tv_seven, R.id.tv_eight, R.id.tv_nine};
    private TextView[] textViews;
    private TextView tvPhone, tvStart, tvSharp, tvCall, tvBackspace;
    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        initViews();
    }

    private void initViews() {
        textViews = new TextView[10];
        for (int i = 0; i < numbers.length; i++) {
            textViews[i] = findViewById(numbers[i]);
            textViews[i].setText(i + "");
            textViews[i].setOnClickListener(this);
        }
        tvStart = findViewById(R.id.tv_start);
        tvSharp = findViewById(R.id.tv_sharp);
        tvCall = findViewById(R.id.tv_call);
        tvBackspace = findViewById(R.id.tv_backspace);
        tvSharp.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvCall.setOnClickListener(this);
        tvBackspace.setOnClickListener(this);
        tvPhone = findViewById(R.id.tv_phone);
        tvPhone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_backspace:
                if (phone.isEmpty()) {
                    break;
                }
                phone = phone.substring(0, phone.length() - 1);
                tvPhone.setText(phone);
                break;
            case R.id.tv_call:
                if (phone.isEmpty()) {
                    break;
                }
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + phone));
                startActivity(call);
                break;
            default:
                TextView textView = ((TextView) view);
                this.phone += textView.getText();
                tvPhone.setText(this.phone);
                break;
        }

    }

}