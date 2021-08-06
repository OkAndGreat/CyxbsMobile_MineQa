package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.R

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票明细页面RecyclerView的adapter
 * 要根据是兑换记录页面的数据还是获取记录页面的数据改变
 * mine_tv_stamp_exchange_count 的颜色
 * 和mine_view_stamp_detail_navigate的可见
 */
class StampDetailRvAdapter : RecyclerView.Adapter<StampDetailRvAdapter.InnerHolder>() {
    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder =
        InnerHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mine_list_item_stamp_detail, parent, false)
        )

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {

    }

    override fun getItemCount(): Int = 50
}
