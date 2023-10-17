package com.example.duan_ph32821.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_ph32821.database.DbHelper;
import com.example.duan_ph32821.model.LoaiSach;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoaiSachDAO {
    private DbHelper dbHelper;
    public  LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //lấy danh sách loại sách
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1) ));
            }while (cursor.moveToNext());
        }
        return list;
    }
    //thêm loại sách
    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenLoai);

        long check = sqLiteDatabase.insert("LOAISACH", null, contentValues);

        return check != -1;
    }
    public boolean suaLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", loaiSach.getTenLoai());
        int check = sqLiteDatabase.update("LOAISACH", contentValues, "maloai = ?", new String[]{String.valueOf(loaiSach.getMaLoai())});
        return check != 0;
    }

    /*
    * -1: không xóa được do lỗi hệ thống
    * 0: không xóa được (ràng buộc khóa ngoại)
    * 1: xóa thành công
    */
    public int xoaLoaiSach(int maLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        //kiểm tra sự tồn tại của những cuốn sách trong bảng sách với thể loại
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maLoai = ?", new String[]{String.valueOf(maLoai)});
        if(cursor.getCount() > 0){
            return 0; //khong duoc xoa vi rang buoc khoa ngoai
        }else {
            int check = sqLiteDatabase.delete("LOAISACH", "maLoai = ?", new String[]{String.valueOf(maLoai)});
            if (check == 0){
                return -1; //không xóa được vi lỗi, không tìm thấy loại sách cần xóa
            }else{
                return 1;
            }
        }
    }
}
