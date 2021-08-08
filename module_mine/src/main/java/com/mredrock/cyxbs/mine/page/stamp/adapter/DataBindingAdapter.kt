package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.mine.R

object DataBindingAdapter {


    //加载图片
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImage(imageView: ImageView, url:String?){
        url?.let {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

    //
    @BindingAdapter("changeAllBackGround")
    @JvmStatic
    fun setChangeBackGround(view: View,boolean: Boolean){
        if (boolean){
            view.setBackgroundResource(R.drawable.mine_shape_exchange_detail_cv_got)
        }else{
            view.setBackgroundResource(R.drawable.mine_shape_exchange_detail_cv_normal)
        }
    }

    @BindingAdapter("changeCircleBackGround")
    @JvmStatic
    fun setChangeCircleBackGround(view: View,boolean: Boolean){
        if (boolean){
            view.setBackgroundResource(R.drawable.mine_vector_stamp_exchange_detail_icon_collected)
        }else{
            view.setBackgroundResource(R.drawable.mine_vector_stamp_exchange_detail_icon)
        }
    }
}