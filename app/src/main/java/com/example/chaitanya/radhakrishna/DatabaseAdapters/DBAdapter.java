package com.example.chaitanya.radhakrishna.DatabaseAdapters;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chaitanya.radhakrishna.Model.ItemsPojo;
import com.example.chaitanya.radhakrishna.Model.MenuDetails;
import com.example.chaitanya.radhakrishna.Model.SubMenuResponseBean;

import java.util.ArrayList;
import java.util.HashMap;

public class DBAdapter extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 19;
    private static final String DATABASE_NAME = "Registration";
    private static final String ITEMS_TABLE = "items_table";
    //private static int ID;
    private static final String ID1 = "id";
    private static final String ITEM_NAME = "item_name";
    private static final String PARENT_ID = "parent_id";
    private static final String SUB_ITEM_NAME = "sub_item_name";
    private static final String PRICE_PER_SUB_ITEM = "price_per_sub_item";
    private static final String QUANTITY = "quantity";
    private static final String TOTAL = "total";
    private static final String ISSelected = "is_selected";


    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE "
            + ITEMS_TABLE + "(" + ID1 + " INTEGER PRIMARY KEY,"
            + ITEM_NAME + " TEXT,"
            + PARENT_ID + " TEXT,"
            + SUB_ITEM_NAME + " TEXT,"
            + PRICE_PER_SUB_ITEM + " TEXT,"
            + QUANTITY + " TEXT,"
            + ISSelected + " TEXT,"
            + TOTAL + " TEXT" + ")";

    SQLiteDatabase db;
    Context mcontext;

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcontext = context;
        openWritable();
    }

    public DBAdapter openWritable() throws SQLException {

        db = this.getWritableDatabase();
        return this;
    }

    public DBAdapter openReadable() throws SQLException {

        db = this.getReadableDatabase();
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
           /* private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
                    + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
                    + KEY_CREATED_AT + " DATETIME" + ")";*/
            db.execSQL(CREATE_ITEMS_TABLE);
            Log.d(" table crated ",CREATE_ITEMS_TABLE);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);

    }

    public long insertIntoItemsTable(MenuDetails list) {
         db = this.getWritableDatabase();
        long l=0;
        ContentValues values = new ContentValues();
            values.put(ITEM_NAME, list.getItemName());
            values.put(PARENT_ID, list.getParentId());
            values.put(SUB_ITEM_NAME,  list.getSubItemName());
            values.put(PRICE_PER_SUB_ITEM,  list.getPrice());
            values.put(QUANTITY,  list.getQuantity());
            values.put(ISSelected,  list.getIsSelected());
            values.put(TOTAL,  list.getTotal());

            // Inserting Row
            //if(isRecordAlreadyPresent(list.get(i).getParentId(), list.get(i).getItemName(), list.get(i).getSubItemName()))
              try {
                  l = db.insert(ITEMS_TABLE, null, values);
              }catch (Exception e){
                  e.printStackTrace();
              }
        db.close(); // Closing database connection
        return l;
    }
    public boolean isRecordAlreadyPresent()
    {
        boolean b=false;
        String sqlQuery="";
        Cursor cursor=null;
        SQLiteDatabase db= getReadableDatabase();

        sqlQuery="select * from "+ITEMS_TABLE;
        cursor=db.rawQuery(sqlQuery, null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();

                b=true;

        }




        return b;
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

    public ArrayList<MenuDetails> getAllListOfAllSubItems(String ClickedItemName)
    {
        ArrayList<MenuDetails> list = new ArrayList<>();
        MenuDetails bean;
        SQLiteDatabase db= getReadableDatabase();
        String sqlQuery="";
        Cursor cursor=null;
        String pid="0";

        sqlQuery= "select * from "+ITEMS_TABLE+" where "+ITEM_NAME+"='"+ClickedItemName+"' and "+PARENT_ID+"<>'"+pid+"'";

        cursor=db.rawQuery(sqlQuery, null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do{
                bean= new MenuDetails();
                bean.setId(cursor.getString(cursor.getColumnIndex(ID1)));
                bean.setItemName(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                bean.setSubItemName(cursor.getString(cursor.getColumnIndex(ITEM_NAME)));
                bean.setQuantity(cursor.getString(cursor.getColumnIndex(QUANTITY)));
                bean.setTotal(cursor.getString(cursor.getColumnIndex(TOTAL)));
                bean.setPrice(cursor.getString(cursor.getColumnIndex(PRICE_PER_SUB_ITEM)));
                bean.setParentId(cursor.getString(cursor.getColumnIndex(PARENT_ID)));
                bean.setIsSelected(cursor.getString(cursor.getColumnIndex(ISSelected)));

                list.add(bean);


            }while (cursor.moveToNext());
        }

        return list;
    }

    public String getItemNameFromPos(String id)
    {
        String parentId="";
        SQLiteDatabase db= getReadableDatabase();
        String sqlQuery="";
        Cursor cursor=null;
        try {

            sqlQuery = "select " + ITEM_NAME + " from " + ITEMS_TABLE + " where " + ID1 + "='" + id + "'";

            cursor = db.rawQuery(sqlQuery, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                parentId = cursor.getString(cursor.getColumnIndex(ITEM_NAME));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return parentId;

    }

    public void deleteItemsTable()
    {
        long l=0;
        SQLiteDatabase db= getReadableDatabase();


        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);


    }
}
