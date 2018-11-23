package com.example.jp.reclwviewdemo.Sqlite_Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jp.reclwviewdemo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int db_version = 1;
    private static final String db_name = "FinalOrder";
    private static final String Table_name = "OrderDetails";
    private static final String Id = "id";
    private static final String ItemName = "itemname";
    private static final String ItemPrice = "itemprice";
    private static final String Quantity = "quantity";
    private static final String Total = "total";


    public Database(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st = " CREATE TABLE " + Table_name + " ( " + Id + " INTEGER PRIMARY KEY, " + ItemName + " TEXT, "
                + ItemPrice + " TEXT, " + Quantity + " TEXT," + Total + " TEXT" + ")";
        db.execSQL(st);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTs " + Table_name);
        onCreate(db);
    }

    public int insertdata(DataModel dm) {
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemName, dm.getItemName());
        values.put(ItemPrice, dm.getItemPrice());
        values.put(Quantity, dm.getQuantity());
        values.put(Total, dm.getTotal());

        int i = (int) d.insert(Table_name, null, values);
        Log.e("Row", ">>>" + i);
        d.close();

        return i;

    }

    public List<DataModel> getAllDatabase() {
        List<DataModel> data_models = new ArrayList<DataModel>();
      //  List<String>total=new ArrayList<String>();
        String selectquiry = "SELECT * FROM " + Table_name;
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor cursor = d.rawQuery(selectquiry, null);

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    DataModel dm = new DataModel();
                    dm.setId(Integer.parseInt(cursor.getString(0)));
                    dm.setItemName(cursor.getString(1));
                    dm.setItemPrice(cursor.getString(2));
                    dm.setQuantity(cursor.getString(3));
                    dm.setTotal(cursor.getString(4));
                  //  total.add(cursor.getString(4));
                    data_models.add(dm);
                } while (cursor.moveToNext());

            }
        }

        return data_models;
    }

    public void deletdata(int id) {
        SQLiteDatabase d = this.getWritableDatabase();
        d.delete(Table_name, Id + " = ?", new String[]{String.valueOf(id)});
        d.close();
    }
    public List<Integer> getTotal() {

        List<Integer> t = new ArrayList<Integer>();
        String selectquir = "SELECT * FROM " + Table_name;
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor cursor = d.rawQuery(selectquir, null);

        if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        t.add(Integer.parseInt(cursor.getString(4)));
                    } while (cursor.moveToNext());

                }

        }
        return t;

    }

    public void deletealldata() {
        SQLiteDatabase d = this.getWritableDatabase();
        d.execSQL("DELETE FROM "+Table_name);
        d.close();
    }
}
