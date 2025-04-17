package com.example.baitap4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap4.GridView.GridViewActivity;
import com.example.baitap4.ListView.ListViewActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút
        Button btnListView = findViewById(R.id.btnListView);
        Button btnGridView = findViewById(R.id.btnGridView);

        // Mở ListViewActivity
        btnListView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        });

        // Mở GridViewActivity
        btnGridView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
            startActivity(intent);
        });
    }
}