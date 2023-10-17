package com.example.duan_ph32821.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_ph32821.database.DbHelper;
import com.example.duan_ph32821.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    private DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    //lấy dnah sách các cuốn sách
    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT s.masach, s.tensach, s.tacgia, s.giaban, s.maloai, s.tenloai FROM SACH s, LOAISACH l WHERE s.maloai = l.maloai", null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        return list;
    }
}
