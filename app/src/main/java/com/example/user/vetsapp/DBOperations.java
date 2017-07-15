package com.example.user.vetsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBOperations extends SQLiteOpenHelper {
   static String database = "registerationDetail";String table_name = "RegisterationPet";
    String coloum_1 = "ID";
    String coloum_2 = "NAME";String coloum_3 = "AGE";String coloum_4 = "BREED";String coloum_5 = "SEX";
    String coloum_6 = "OWNER";String coloum_7 = "ADDRESS";String coloum_8 = "TP";String coloum_9 = "PHOTO";

String tablevac = "VACCINATION" ;
String  coloumv_1= "ID";String coloumv_2 = "NAME";String coloumv_3 = "DATE";String coloumV_4 = "STATUS";
    public DBOperations(Context context) {
        super(context,database,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table RegisterationPet (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE TEXT,BREED TEXT,SEX TEXT,OWNER TEXT,ADDRESS TEXT,TP TEXT,PHOTO TEXT)");
        db.execSQL("create table VACCINATION (ID INTEGER,NAME TEXT,DATE TEXT,STATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS RegisterationPet");
        db.execSQL("DROP TABLE IF EXISTS VACCINATION");
        onCreate(db);
    }
    public boolean ConnetdatabasetoAdd(DatabaseHelper help){
        SQLiteDatabase db = this.getWritableDatabase();
        //onUpgrade(db,1,1);
        ContentValues contentValues = new ContentValues();
        contentValues.put(coloum_2,help.getName());
        contentValues.put(coloum_3,help.getAge());
        contentValues.put(coloum_4,help.getBreed());
        contentValues.put(coloum_5,help.getSex());
        contentValues.put(coloum_6,help.getOwner());
        contentValues.put(coloum_7,help.getAddress());
        contentValues.put(coloum_8,help.getTP());
        contentValues.put(coloum_9,help.getPhotoPath());

        long result = db.insert(table_name,null,contentValues);

        if(result==-1){return  false;}
        else{return true;}
    }
    public Cursor Get(String query){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+table_name+" WHERE "+coloum_8+"='"+query+"'",null);//SELECT * FROM CustomersWHERE Country='Mexico';

       // Toast.makeText(this,"cursr return",Toast.LENGTH_SHORT).show();
        return  result;
    }
    public Cursor getListvac(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tablevac+" WHERE "+coloumv_1+"="+id,null);
        return res;
    }
    public boolean addVaccine(vaccineEntity v){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(coloumv_1,v.getId());
        contentValues.put(coloumv_2,v.getVaccine());
        contentValues.put(coloumv_3,v.getDate());
        contentValues.put(coloumV_4,v.getStatus());

        long res = db.insert(tablevac,null,contentValues);

        if(res==-1){return  false;}
        else{return true;}
    }
    public Cursor sendSMS(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tablevac+" WHERE "+coloumv_3+"='"+date+"'",null);
        return res;
    }
    public Cursor connectTables(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+table_name+" WHERE "+coloum_1+"="+ID,null);//SELECT * FROM CustomersWHERE Country='Mexico';

        // Toast.makeText(this,"cursr return",Toast.LENGTH_SHORT).show();
        return  result;
    }
    public  void deleteRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE * FROM "+tablevac+" WHERE "+coloumv_1+"="+id,null);
    }
public  void edit(int id,vaccineEntity v){
    deleteRow(id);
    addVaccine(v);
}
}
