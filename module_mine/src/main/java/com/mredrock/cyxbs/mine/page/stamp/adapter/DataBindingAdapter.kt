package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.mine.R

object DataBindingAdapter {


    //加载图片
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
            imageView.setImageFromUrl(url)
    }

    //设置背景
    @BindingAdapter("changeAllBackGround")
    @JvmStatic
    fun setChangeBackGround(view: View, boolean: Boolean) {
        if (boolean) {
            view.setBackgroundResource(R.drawable.mine_shape_exchange_detail_cv_got)
        } else {
            view.setBackgroundResource(R.drawable.mine_shape_exchange_detail_cv_normal)
        }
    }
    //设置背景
    @BindingAdapter("changeCircleBackGround")
    @JvmStatic
    fun setChangeCircleBackGround(view: View, boolean: Boolean) {
        if (boolean) {
            view.setBackgroundResource(R.drawable.mine_vector_stamp_exchange_detail_icon_collected)
        } else {
            view.setBackgroundResource(R.drawable.mine_vector_stamp_exchange_detail_icon)
        }
    }

    //设置任务完成状态
    @BindingAdapter(value = ["taskChangeDone","taskChangeTotal"],requireAll = true)
    @JvmStatic
    fun setTaskChange(view: Button, taskChangeDone:Int, taskChangeTotal:Int){
        if (taskChangeTotal != 0 && taskChangeDone < taskChangeTotal) {
            view.text = "去签到"
        }else{
            view.text = "已完成"
            view.isEnabled = false
            view.setBackgroundResource(R.drawable.mine_shape_task_btn_finish)
        }
    }
}