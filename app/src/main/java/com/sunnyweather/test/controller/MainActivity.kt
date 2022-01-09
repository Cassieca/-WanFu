package com.sunnyweather.test.controller

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.sunnyweather.test.MyAdapter
import com.sunnyweather.test.R
import com.sunnyweather.test.dao.MyDatabaseHelper
import com.sunnyweather.test.model.ListModel
import com.sunnyweather.test.model.qaa

class MainActivity : AppCompatActivity() {
    val dbHelper = MyDatabaseHelper(this, "VisitRecord.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //expendableListview展示数据
        val adapter = MyAdapter(this, ListModel.groupList, ListModel.childList)
        val expendableListview = findViewById<ExpandableListView>(R.id.expendableListview)
        expendableListview.setAdapter(adapter)
        //控制展开和关闭的箭头在右边
        val width = windowManager.defaultDisplay.width
        expendableListview.setIndicatorBounds(width - 40, width - 10)
    }
}
