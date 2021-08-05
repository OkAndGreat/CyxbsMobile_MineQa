package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.R

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 因为后端数据暂时还没有拿到
 * 用来给viewpager2测试的adapter
 */
class GoodsDetailPicAdapter : RecyclerView.Adapter<GoodsDetailPicAdapter.PagerViewHolder>() {
    private var mList = listOf(1, 2, 3)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mine_list_item_goods_pic, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setList(list: List<Int>) {
        mList = list
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTextView: TextView = itemView.findViewById(R.id.tv_text)
        private var colors = arrayOf("#CCFF99", "#41F1E5", "#8D41F1", "#FF99CC")

        fun bindData(i: Int) {
            mTextView.text = i.toString()
            mTextView.setBackgroundColor(Color.parseColor(colors[i]))
        }
    }
}