package com.example.applicationlanch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        if (getIntent().hasExtra(MenuActivity.ORDER)) {
            TextView textView = findViewById(R.id.textview_order_report);
            String text = getIntent().getStringExtra(MenuActivity.ORDER);

            textView.setText(text);
        }
    }
}