package com.sunnyweather.test
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.sunnyweather.test.model.qaa

/**
 * WenyiFeng(xfsy2014@gmail.com)
 * 2017-09-06 09:59
 */
class MyAdapter(
    private var mContext : Context,
    /** group 数据 */
    private var groupList: ArrayList<String>,
    /** child 数据 */
    private var childList : ArrayList<ArrayList<qaa>>) : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val groupView = View.inflate(mContext, R.layout.header_view, null)

        /** 分组名字 */
        val groupName = groupView.findViewById<TextView>(R.id.groupTvName)
        groupName.text = groupList[groupPosition]
        /** 子元素个数 */
//        val count = groupView.findViewById<TextView>(R.id.groupTvCount)
//        count.text = "${childList[groupPosition].size} 个"

        return groupView
    }

    /**
     * 这个返回的一定要是对应的item里面的List集合的size
     */
    override fun getChildrenCount(groupPosition: Int): Int {
        return childList[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childList[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, view: View?, viewGroup: ViewGroup?): View {
        val childView = View.inflate(mContext, R.layout.content_view, null)
        val question = childView.findViewById<TextView>(R.id.question)
        question.text = "${childList[groupPosition][childPosition].question}  ："
        val answer = childView.findViewById<TextView>(R.id.answer)
        answer.text = childList[groupPosition][childPosition].answer
        return childView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return groupList.size
    }

}