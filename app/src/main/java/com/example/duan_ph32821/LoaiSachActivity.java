package com.example.duan_ph32821;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_ph32821.adapter.LoaiSachAdapter;
import com.example.duan_ph32821.dao.LoaiSachDAO;
import com.example.duan_ph32821.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachActivity extends AppCompatActivity {
    private RecyclerView rcvLoaiSach;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sach);
        //thiết kế giao diện (chính + giao diện item)
        rcvLoaiSach = findViewById(R.id.rcvLoaiSach);
        FloatingActionButton fltAdd = findViewById(R.id.fltAdd);

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hiển thị dialog thêm
                showDialogThem();
            }
        });

        //data
        loaiSachDAO = new LoaiSachDAO(this);

        //apdapter
        loadData();

    }

    private void loadData(){
        list = loaiSachDAO.getDSLoaiSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvLoaiSach.setLayoutManager(linearLayoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(this, list, loaiSachDAO);
        rcvLoaiSach.setAdapter(loaiSachAdapter);
    }
    private void showDialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loaisach, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        //ánh xạ
        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnLuu = view.findViewById(R.id.btnLuu);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edtTenLoai.getText().toString();

                if (tenLoai.equals("")){
                    Toast.makeText(LoaiSachActivity.this, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = loaiSachDAO.themLoaiSach(tenLoai);
                if (check){
                    Toast.makeText(LoaiSachActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    loadData();
                    alertDialog.dismiss();
                }
                else {
                    Toast.makeText(LoaiSachActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}