package com.example.detectgas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class sqlCaiDat extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String CUSTOMER_TEN = "CUSTOMER_TEN";
    public static final String CUSTOMER_DOAM = "CUSTOMER_DOAM";
    public static final String CUSTOMER_NHIETDO = "CUSTOMER_NHIETDO";

    Context context;
    public sqlCaiDat( Context context) {
        super(context, "CaiDatDuLieu1.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + CUSTOMER_TABLE + " (" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ CUSTOMER_NHIETDO + " INT, " + CUSTOMER_DOAM + " INT, " + CUSTOMER_TEN + " TEXT)" ;
        sqLiteDatabase.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addOne(objectCaiDat obcd){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NHIETDO, obcd.getNhietDo());
        cv.put(CUSTOMER_DOAM, obcd.getDoAm());
        cv.put(CUSTOMER_TEN, obcd.getTenRau());
        long insert = sql.insert(CUSTOMER_TABLE, null, cv);
        if(insert == -1){
            Toast.makeText(context, "Lỗi tạo dữ liệu", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Tạo dữ liệu thành công", Toast.LENGTH_LONG).show();

        }
    }
    public List<objectCaiDat> getEveryone() {
        List<objectCaiDat> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase dbListBook = this.getWritableDatabase();
        Cursor cursor = dbListBook.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            //Lap qua con tro(ket qua truy vấn) và tạo các đối tượng khách hàng mới. Đặt chúng vào danh sách trả về.
            do {
                int customerID = cursor.getInt(0);
                int customerNhietDo = cursor.getInt(1);
                int customerDoAm = cursor.getInt(2);
                String customerTen = cursor.getString(3);
                //Chuyển các biến input đầu vào thành dạng hướng đối tường trước khi cho vào danh sách
                objectCaiDat obBook = new objectCaiDat(customerID, customerNhietDo, customerDoAm, customerTen);
                //Thêm dữ liều đầu vào vừa chuyển sang hướng đói tượng vào danh sách
                returnList.add(obBook);
            } while (cursor.moveToNext());
        } else {
            // failure, do not add anything to the list

            return returnList;

        }
        cursor.close();
        dbListBook.close();
        return returnList;
    }
}
