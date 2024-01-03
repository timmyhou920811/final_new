package com.example.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{ //繼承SQLiteOpenHelper類別
    private static final String name = "mdatabase.db"; //資料庫名稱
    private static final int version = 1; //資料庫版本
    //自訂建構子，只需輸入一個Context物件
    MyDBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建立
        db.execSQL("CREATE TABLE myTable(book text PRIMARY KEY, price integer NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
    }


}
