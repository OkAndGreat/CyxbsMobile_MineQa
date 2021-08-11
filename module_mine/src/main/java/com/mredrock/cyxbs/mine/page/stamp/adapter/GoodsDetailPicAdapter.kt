package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 因为后端数据暂时还没有拿到
 * 用来给viewpager2测试的adapter
 */
class GoodsDetailPicAdapter(private val pic:List<String>) : RecyclerView.Adapter<GoodsDetailPicAdapter.PagerViewHolder>() {
    private var mList = pic
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mine_list_item_goods_pic, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setList(list: List<String>) {
        mList = list
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mImageView: ImageView = itemView.findViewById(R.id.mine_stamp_detail_iv)
        fun bindData(url: String) {
            println("设置成功")
            mImageView.setImageFromUrl(url)
        }
    }
}