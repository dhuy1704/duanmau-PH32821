package com.example.duan_ph32821;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLoaiSach = findViewById(R.id.linearLoaiSach);
        LinearLayout linearSach = findViewById(R.id.linearSach);
        LinearLayout linearPM = findViewById(R.id.linearPM);
        LinearLayout linearThongKe = findViewById(R.id.linearThongKe);
        LinearLayout linearLSMS = findViewById(R.id.linearLSMS);

        //lấy role để lưu trong shared
        SharedPreferences sharedPreferences = getSharedPreferences("dataUser", MODE_PRIVATE);
        int role = sharedPreferences.getInt("role", -1);

        switch (role){
            case 1://người dùng
                linearLoaiSach.setVisibility(View.GONE);
                linearSach.setVisibility(View.GONE);
                linearThongKe.setVisibility(View.GONE);
                linearPM.setVisibility(View.GONE);
                break;
            case 2://thủ thư
                linearLoaiSach.setVisibility(View.GONE);
                linearSach.setVisibility(View.GONE);
                linearThongKe.setVisibility(View.GONE);
                linearLSMS.setVisibility(View.GONE);
                break;
            case 3://admin
                linearLSMS.setVisibility(View.GONE);
                break;
            default:
                linearLoaiSach.setVisibility(View.GONE);
                linearSach.setVisibility(View.GONE);
                linearThongKe.setVisibility(View.GONE);
                linearLSMS.setVisibility(View.GONE);
                linearPM.setVisibility(View.GONE);
                break;
        }

        linearLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoaiSachActivity.class));
            }
        });

        linearSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SachActivity.class));
            }
        });
    }
}