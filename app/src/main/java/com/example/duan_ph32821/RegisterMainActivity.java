package com.example.duan_ph32821;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_ph32821.dao.NguoiDungDAO;
import com.example.duan_ph32821.model.NguoiDung;

public class RegisterMainActivity extends AppCompatActivity {
private NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        EditText edtRePass = findViewById(R.id.edtRePass);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtAddress = findViewById(R.id.edtAddress);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnBack = findViewById(R.id.btnBack);

        nguoiDungDAO = new NguoiDungDAO(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edtPass.getText().toString();
                String repass = edtRePass.getText().toString();

                if (!pass.equals(repass)){
                    Toast.makeText(RegisterMainActivity.this, "Mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                }else{
                    String user = edtUser.getText().toString();
                    String name = edtName.getText().toString();
                    String phone = edtPhone.getText().toString();
                    String address = edtAddress.getText().toString();

                    NguoiDung nguoiDung = new NguoiDung(name, phone, address, user, pass);
                    boolean check = nguoiDungDAO.dangkyTaiKhoan(nguoiDung);
                    if (check){
                        Toast.makeText(RegisterMainActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(RegisterMainActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}