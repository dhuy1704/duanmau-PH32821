package com.example.duan_ph32821;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan_ph32821.adapter.SachAdapter;
import com.example.duan_ph32821.dao.SachDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SachActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSach;
    private SachDAO sachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        recyclerViewSach = findViewById(R.id.recyclerViewSach);
        FloatingActionButton floatAdd = findViewById(R.id.floatAdd);
        sachDAO = new SachDAO(this);
        loadData();
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSach.setLayoutManager(linearLayoutManager);
        SachAdapter adapter = new SachAdapter(this, sachDAO.getDSSach());
        recyclerViewSach.setAdapter(adapter);
    }
}