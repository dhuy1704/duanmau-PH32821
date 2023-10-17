package com.example.duan_ph32821.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "QUANLYTHUVIEN", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tạo bảng loại sách
        String tLoaiSach = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
        sqLiteDatabase.execSQL(tLoaiSach);
        //data loai sach
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES(1, 'thiếu nhi'), (2, 'tình cảm'), (3, 'hành động')");

        //tạo bảng sách
        String tSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, tacgia text, giaban integer, maloai integer references LOAISACH(maloai))";
        sqLiteDatabase.execSQL(tSach);
        // data sách
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(1, 'Kể cho em nghe', 'Nguyễn Nhật Ánh', 15000, 1), (2, 'Trạng Quỳnh', 'Kim Đồng', 5000, 1)");

        //tao bang  nguoi dung
        /*
        *role:
        * 1 - nguoi dung
        * 2 - thu thu
        * 3 - admin
        */
        String tNguoiDung = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement, tennd text, sdt text, diachi text, tendangnhap text, matkhau text, role integer)";
        sqLiteDatabase.execSQL(tNguoiDung);
        //data người dùng
        sqLiteDatabase.execSQL("INSERT INTO NGUOIDUNG VALUES(1, 'Nguyễn Văn Anh', '0987654321', 'Q12 TP.HCM', 'vananh01', '123456', 1)," +
                " (2, 'Trịnh Hòa Bình', '0987162323', 'Q9 TP.HCM', 'hoabinh01', '111222', 2)," +
                "(3, 'Lê Văn Hùng', '082318912', 'Q6 TP.HCM', 'hunglv01', '12341234', 3)");

        //bang phieu muon
        String tPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, ngaymuon text, ngaytra text, mand integer references NGUOIDUNG(mand))";
        sqLiteDatabase.execSQL(tPhieuMuon);
        //data phieu muon
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1, '20/9/2023', '26/9/2023', 1)");

        //bang chi tiet phieu muon
        String tCTPM = "CREATE TABLE CTPM(mactpm integer primary key autoincrement, mapm integer references PHIEUMUON(mapm), masach integer references SACH(masach), soluong integer)";
        sqLiteDatabase.execSQL(tCTPM);
        //data CTPM
        sqLiteDatabase.execSQL("INSERT INTO CTPM VALUES(1, 1, 1, 2), (2, 1, 2, 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CTPM");
            onCreate(sqLiteDatabase);
        }
    }
}
