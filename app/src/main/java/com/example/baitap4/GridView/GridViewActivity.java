package com.example.baitap4.GridView;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap4.MonHoc;
import com.example.baitap4.R;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<MonHoc> monHocList;
    MonHocGridAdapter adapter;
    EditText editTextName, editTextDesc;
    Button btnThem, btnCapNhat;
    int vitri = -1;
    private static final int[] IMAGE_RESOURCES = {
            R.drawable.java, R.drawable.c, R.drawable.php
    };
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        // Ánh xạ
        gridView = findViewById(R.id.gridview1);
        editTextName = findViewById(R.id.editTextName);
        editTextDesc = findViewById(R.id.editTextDesc);
        btnThem = findViewById(R.id.btnThem);
        btnCapNhat = findViewById(R.id.btnCapNhat);

        // Khởi tạo dữ liệu
        monHocList = new ArrayList<>();
        monHocList.add(new MonHoc("Java", "Ngôn ngữ lập trình hướng đối tượng", R.drawable.java));
        monHocList.add(new MonHoc("C#", "Ngôn ngữ phát triển ứng dụng Windows", R.drawable.c));
        monHocList.add(new MonHoc("PHP", "Ngôn ngữ lập trình web", R.drawable.php));

        // Tạo và gán adapter
        adapter = new MonHocGridAdapter(this, R.layout.item_mon_hoc, monHocList);
        gridView.setAdapter(adapter);

        // Bắt sự kiện nhấp chuột
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MonHoc monHoc = monHocList.get(i);
                editTextName.setText(monHoc.getName());
                editTextDesc.setText(monHoc.getDesc());
                vitri = i;
                Toast.makeText(GridViewActivity.this, "Đã chọn: " + monHoc.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // Bắt sự kiện nhấn giữ
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                monHocList.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(GridViewActivity.this, "Đã xóa mục: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // Bắt sự kiện nhấn nút Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String desc = editTextDesc.getText().toString().trim();
                if (!name.isEmpty() && !desc.isEmpty()) {
                    int imageRes = IMAGE_RESOURCES[currentImageIndex % IMAGE_RESOURCES.length];
                    monHocList.add(new MonHoc(name, desc, imageRes));
                    adapter.notifyDataSetChanged();
                    editTextName.setText("");
                    editTextDesc.setText("");
                    currentImageIndex++;
                    Toast.makeText(GridViewActivity.this, "Đã thêm: " + name, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GridViewActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Bắt sự kiện nhấn nút Cập nhật
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String desc = editTextDesc.getText().toString().trim();
                if (vitri != -1 && !name.isEmpty() && !desc.isEmpty()) {
                    MonHoc monHoc = monHocList.get(vitri);
                    monHoc.setName(name);
                    monHoc.setDesc(desc);
                    adapter.notifyDataSetChanged();
                    editTextName.setText("");
                    editTextDesc.setText("");
                    vitri = -1;
                    Toast.makeText(GridViewActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GridViewActivity.this, "Chọn mục và nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}