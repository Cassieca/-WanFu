package com.sunnyweather.test

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var groupList = ArrayList<String>()
    private var childList =  ArrayList<ArrayList<Map.Entry<String, String>>> ()
    val dbHelper = MyDatabaseHelper(this, "VisitRecord.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        if(childList.isEmpty()){
            dbHelper.writableDatabase
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "kk")
                put("phone", "15812222222")
                put("club", "希望工程")
            }
            db.insert("Visited", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "zz")
                put("phone", "15813332222")
                put("company", "梦想工程")
                put("IDNumber", "440222222222222229")
                put("sex", "女")
                put("carNo", "粤D8888")
            }
            db.insert("Visitor", null, values2) // 插入第二条数据
        }
        initData()
        val adapter = MyAdapter(this, groupList , childList)
        val expendableListview = findViewById<ExpandableListView>(R.id.expendableListview)
        expendableListview.setAdapter(adapter)
        val width = windowManager.defaultDisplay.width
        expendableListview.setIndicatorBounds(width - 40, width - 10)
    }

    private fun initData() {
        val db = dbHelper.writableDatabase
        // 查询Visited表中所有的数据

        val cursor = db.query("Visited", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val map = HashMap<String, String>()
                // 遍历Cursor对象，取出数据
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("phone"))
                val pages = cursor.getString(cursor.getColumnIndex("club"))
                map.put("被访人姓名","$name")
                map.put("被访人手机","$author")
                map.put("被访人部门","$pages")
                val item=ArrayList<Map.Entry<String, String>>()
                for(a in map){
                    item.add(a)
                }
                groupList.add("被访人信息")
                childList.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        val cursor2 = db.query("Visitor", null, null, null, null, null, null)
        if (cursor2.moveToFirst()) {
            do {
                val map = HashMap<String, String>()
                // 遍历Cursor对象，取出数据
                val name = cursor2.getString(cursor2.getColumnIndex("name"))
                val phone = cursor2.getString(cursor2.getColumnIndex("phone"))
                val company = cursor2.getString(cursor2.getColumnIndex("company"))
                val IDNumber = cursor2.getString(cursor2.getColumnIndex("IDNumber"))
                val sex = cursor2.getString(cursor2.getColumnIndex("sex"))
                val carNo = cursor2.getString(cursor2.getColumnIndex("carNo"))
                map.put("访客姓名","$name")
                map.put("访客手机","$phone")
                map.put("所在公司","$company")
                map.put("证件号码","$IDNumber")
                map.put("性别","$sex")
                map.put("车牌号码","$carNo")
                val item2=ArrayList<Map.Entry<String, String>>()
                for(a in map){
                    item2.add(a)
                }
                groupList.add("访客信息")
                childList.add(item2)
            } while (cursor2.moveToNext())
        }
        cursor2.close()
    }
}
