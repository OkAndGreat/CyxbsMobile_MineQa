package com.mredrock.cyxbs.mine.page.sign.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.R

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票明细页面RecyclerView的adapter
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
