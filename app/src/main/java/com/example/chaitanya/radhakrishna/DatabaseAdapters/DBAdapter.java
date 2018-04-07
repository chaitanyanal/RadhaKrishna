package com.example.chaitanya.radhakrishna.DatabaseAdapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chaitanya.radhakrishna.Model.ItemsPojo;
import com.example.chaitanya.radhakrishna.Model.MenuDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class DBAdapter extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "database";
    private static final String ITEMS_TABLE = "items_table";
    //private static int ID;
    private static final String ID = "id";
    private static final String ITEM_NAME = "item_name";
    private static final String PARENT_ID = "parent_id";
    private static final String SUB_ITEM_NAME = "sub_item_name";
    private static final String PRICE_PER_SUB_ITEM = "price_per_sub_item";
    private static final String QUANTITY = "quantity";
    private static final String TOTAL = "total";



    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ITEMS_TABLE = "CREATE TABLE IF NOT EXISTS " + ITEMS_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ITEM_NAME + " TEXT,"
                + PARENT_ID + " TEXT,"
                + SUB_ITEM_NAME + " TEXT,"
                + PRICE_PER_SUB_ITEM + " TEXT,"
                + QUANTITY + " TEXT,"
                + TOTAL + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    public long insertIntoItemsTable(ArrayList<MenuDetails> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        long l=0;

        for(int i=0; i<list.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(ITEM_NAME, list.get(i).getItemName());
            values.put(PARENT_ID, list.get(i).getParentId());
            values.put(SUB_ITEM_NAME,  list.get(i).getSubItemName());
            values.put(PRICE_PER_SUB_ITEM,  list.get(i).getPrice());
            values.put(QUANTITY,  list.get(i).getQuantity());
            values.put(TOTAL,  list.get(i).getQuantity());

            // Inserting Row
          l=  db.insert(ITEMS_TABLE, null, values);
        }
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return l;
    }

    public ArrayList<HashMap<String, String>> getListOfItems()
    {

        ArrayList<HashMap<String, String>> list= new ArrayList<>();
        HashMap<String, String> hashMap= new HashMap<>();
        String sqlQuery="";
        Cursor cursor=null;

        sqlQuery="select * from ";

        return list;
    }

}
