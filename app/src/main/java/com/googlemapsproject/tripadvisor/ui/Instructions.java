package com.googlemapsproject.tripadvisor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.googlemapsproject.tripadvisor.R;

import java.util.ArrayList;


public class Instructions extends AppCompatActivity  {

    String instctions;
    private TextView mDisplayText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        mDisplayText = (TextView) findViewById(R.id.instuctions_view);
        Intent intent = getIntent();
        instctions = intent.getStringExtra("instructions");
        mDisplayText.setText(instctions);
    }
}
