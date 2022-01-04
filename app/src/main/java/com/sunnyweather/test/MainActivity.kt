package com.sunnyweather.test

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var groupList = ArrayList<String>()
    private var childList = ArrayList<ArrayList<Map.Entry<String, String>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        initData()

        val adapter = MyAdapter(this, groupList, childList)
        val expendableListview = findViewById<ExpandableListView>(R.id.expendableListview)
        expendableListview.setAdapter(adapter)
    }

    private fun initData() {
        addData("幼稚园同学", mapOf("Apple" to "1", "Banana" to "2", "Orange" to "3"))
        addData("1同学", mapOf("Apple" to "addd", "Banana" to "o", "Orange" to "3"))
        addData("2同学", mapOf("Apple" to "gfbs", "Banana" to "2", "Orange" to "3"))
        addData("3同学", mapOf("Apple" to "hhh", "Banana" to "2", "Orange" to "lalala"))
    }

    /**
     * 用来添加数据的方法
     */
    private fun addData(group: String, items:Map<String, String>) {
        groupList.add(group)
        val item=ArrayList<Map.Entry<String, String>>()
        for(a in items){
            item.add(a)
        }
        childList.add(item)
    }
}