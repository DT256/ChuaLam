package com.example.databinding;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databinding.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ListUserAdapter.OnItemClickListener{
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        title.set("Ví dụ về DataBinding cho RecyclerView");
        binding.setHome(this);
        setData();

        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);
        listUserAdapter.setOnItemClickListener(this);

    }
    @Override
    public void itemClick(User user) {
        Toast.makeText(this, "bạn vừa click", Toast.LENGTH_SHORT).show();
    }
    private void setData() {
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setFirstName("Hữu " + i);
            user.setLastName("Trung " + i);
            userList.add(user);
        }

        listUserAdapter = new ListUserAdapter(userList);
    }
}

