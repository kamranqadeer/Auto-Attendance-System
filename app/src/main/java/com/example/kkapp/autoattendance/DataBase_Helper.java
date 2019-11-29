package com.example.kkapp.autoattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kamran qadeer on 5/9/2018.
 */

public class DataBase_Helper extends SQLiteOpenHelper {
    DataBase_Helper dataBase_helper;
    private static final String DataBaseName="AutoAttendance_db1";//data base name is define
    private static final int DATABASE_VERSION=2;
    public DataBase_Helper(Context context) {
        super(context, DataBaseName, null,DATABASE_VERSION);
        //data base name is DataBaseName=AutoAttendance
       // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CreateTable1="create table "+Contracts.Teacher_Table.TABLE_NAME + "(" +
                Contracts.Teacher_Table._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Contracts.Teacher_Table.COLUMN_1 +" TEXT NOT NULL,"+
                Contracts.Teacher_Table.COLUMN_2 +" TEXT NOT NULL,"+
                Contracts.Teacher_Table.COLUMN_3 +" TEXT NOT NULL,"+
                Contracts.Teacher_Table.COLUMN_4 +" TEXT NOT NULL);";
        final String SQL_CreateTable2="create table "+Contracts.Student_Table.TABLE_NAME + "(" +
                Contracts.Student_Table._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Contracts.Student_Table.COLUMN_1 +" TEXT NOT NULL,"+
                Contracts.Student_Table.COLUMN_2 +" TEXT NOT NULL,"+
                Contracts.Student_Table.COLUMN_3 +" TEXT NOT NULL,"+
                Contracts.Student_Table.COLUMN_4 +" TEXT NOT NULL);";
        final String SQL_CreateTable3="create table "+Contracts.Student_Subjects.TABLE_NAME +
                "(" + Contracts.Student_Subjects._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ Contracts.Student_Subjects.COLUMN_1 +" TEXT NOT NULL);";

        final String SQL_CreateTable4="create table "+Contracts.Student_Subjects_Detail.TABLE_NAME +"("+
                Contracts.Student_Subjects_Detail._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Contracts.Student_Subjects_Detail.COLUMN_1+" TEXT NOT NULL,"+
                Contracts.Student_Subjects_Detail.COLUMN_2+" INTEGER,"+
                " FOREIGN KEY ("+Contracts.Student_Subjects_Detail.COLUMN_2+") REFERENCES "+Contracts.Student_Subjects_Detail.TABLE_NAME+"("+Contracts.Student_Subjects_Detail._ID+"));";
        final String SQL_CreateTable5="create table "+Contracts.Teacher_Subjects.TABLE_NAME +
                "(" + Contracts.Teacher_Subjects._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ Contracts.Teacher_Subjects.COLUMN_1 +" TEXT NOT NULL);";

        final String SQL_CreateTable6="create table "+Contracts.Teacher_Subjects_Detail.TABLE_NAME +"("+
                Contracts.Teacher_Subjects_Detail._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Contracts.Teacher_Subjects_Detail.COLUMN_1+" TEXT NOT NULL,"+
                Contracts.Teacher_Subjects_Detail.COLUMN_2+" INTEGER,"+
                " FOREIGN KEY ("+Contracts.Teacher_Subjects_Detail.COLUMN_2+") REFERENCES "+Contracts.Teacher_Subjects_Detail.TABLE_NAME+"("+Contracts.Teacher_Subjects._ID+"));";
          db.execSQL(SQL_CreateTable1);
          db.execSQL(SQL_CreateTable2);
          db.execSQL(SQL_CreateTable3);
          db.execSQL(SQL_CreateTable4);
          db.execSQL(SQL_CreateTable5);
          db.execSQL(SQL_CreateTable6);
          Log.d("Create","Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Teacher_Table.TABLE_NAME);
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Student_Table.TABLE_NAME);
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Teacher_Subjects.TABLE_NAME);
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Teacher_Subjects_Detail.TABLE_NAME);
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Student_Subjects.TABLE_NAME);
          db.execSQL("DROP TABLE IF EXISTS " + Contracts.Student_Subjects_Detail.TABLE_NAME);
          onCreate(db);
    }
    public boolean Teacher_Information(String name,String ID,String education,String phoneNo)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contracts.Teacher_Table.COLUMN_1,name);
        contentValues.put(Contracts.Teacher_Table.COLUMN_2,ID);
        contentValues.put(Contracts.Teacher_Table.COLUMN_3,education);
        contentValues.put(Contracts.Teacher_Table.COLUMN_4,phoneNo);
        long result=sqLiteDatabase.insert(Contracts.Teacher_Table.TABLE_NAME,null,contentValues);
        if(result==-1)
         return false;
        else
            return true;
    }
    public Cursor GetTeacherData()
    {
        SQLiteDatabase db = dataBase_helper.getWritableDatabase();
        Cursor res=db.rawQuery("Select * FROM "+Contracts.Student_Table.TABLE_NAME,null);
        return res;
    }
    public boolean Student_Information(String name,String ID,String education,String phoneNo)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Contracts.Student_Table.COLUMN_1,name);
        contentValues.put(Contracts.Student_Table.COLUMN_2,ID);
        contentValues.put(Contracts.Student_Table.COLUMN_3,education);
        contentValues.put(Contracts.Student_Table.COLUMN_4,phoneNo);
        long result=sqLiteDatabase.insert(Contracts.Student_Table.TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor GetStrudentData()
    {
        SQLiteDatabase db = dataBase_helper.getWritableDatabase();
        Cursor res=db.rawQuery("Select * FROM "+Contracts.Student_Table.TABLE_NAME,null);
        return res;
    }

}
