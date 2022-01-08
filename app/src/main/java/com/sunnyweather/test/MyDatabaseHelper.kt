package com.sunnyweather.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table Visited (" +
                " id integer primary key autoincrement," +
                "name text," +
                "phone text," +
                "club text)")
        db.execSQL("create table Visitor (" +
                " id integer primary key autoincrement," +
                "name text," +
                "phone text," +
                "company text," +
                "IDNumber text," +
                "sex text," +
                "carNo text)")
//        db.execSQL("create table Examine (" +
//                " id integer primary key autoincrement," +
//                "Examiner text," +
//                "visitorId integer," +
//                "visitedId integer," +
//                "areaId integer" +
//                "14places text," +
//                "touchHistory text," +
//                "bodySituation text" +
//                "healthColor text" +
//                "comingReason text" +
//                "startTime text" +
//                "endTime text" +
//                "arrivalId integer)")
//        db.execSQL("create table AreaInfo (" +
//                " id integer primary key autoincrement," +
//                "park text," +
//                "detailArea text," +
//                "company text," +
//                "address text)")
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
