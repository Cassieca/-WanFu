package com.sunnyweather.test.controller

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sunnyweather.test.R
import com.sunnyweather.test.dao.MyDatabaseHelper
import com.sunnyweather.test.model.ListModel
import com.sunnyweather.test.model.qaa

class LoginActivity : AppCompatActivity() {
    val dbHelper = MyDatabaseHelper(this, "VisitRecord.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val createDb: Button = findViewById(R.id.createDb)
        createDb.setOnClickListener {
            dbAddData()
            ListModelAddData()
        }
    }

    private fun dbAddData(){
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
    private fun ListModelAddData(){
        val db = dbHelper.writableDatabase
        // 查询Visited表中所有的数据
        val cursor = db.query("Visited", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("phone"))
                val pages = cursor.getString(cursor.getColumnIndex("club"))
                val item=ArrayList<qaa>()
                item.add(qaa("被访人姓名","$name"))
                item.add(qaa("被访人手机","$author"))
                item.add(qaa("被访人部门","$pages"))
                ListModel.groupList.add("被访人信息")
                ListModel.childList.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        val cursor2 = db.query("Visitor", null, null, null, null, null, null)
        if (cursor2.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据
                val name = cursor2.getString(cursor2.getColumnIndex("name"))
                val phone = cursor2.getString(cursor2.getColumnIndex("phone"))
                val company = cursor2.getString(cursor2.getColumnIndex("company"))
                val IDNumber = cursor2.getString(cursor2.getColumnIndex("IDNumber"))
                val sex = cursor2.getString(cursor2.getColumnIndex("sex"))
                val carNo = cursor2.getString(cursor2.getColumnIndex("carNo"))
                val item=ArrayList<qaa>()
                item.add(qaa("被访人姓名","$name"))
                item.add(qaa("访客手机","$phone"))
                item.add(qaa("所在公司","$company"))
                item.add(qaa("证件号码","$IDNumber"))
                item.add(qaa("性别","$sex"))
                item.add(qaa("车牌号码","$carNo"))
                ListModel.groupList.add("访客信息")
                ListModel.childList.add(item)
            } while (cursor2.moveToNext())
        }
        cursor2.close()
    }
}