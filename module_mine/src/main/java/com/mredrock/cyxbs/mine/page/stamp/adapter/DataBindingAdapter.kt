package com.mredrock.cyxbs.mine.page.stamp.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.customview.RollTextView
import com.mredrock.cyxbs.mine.util.getDateFromYMD
import com.mredrock.cyxbs.mine.util.toDateDayStr
import com.mredrock.cyxbs.mine.util.toDateStr
import java.text.SimpleDateFormat
import java.util.*

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
    @BindingAdapter(value = ["taskChangeDone", "taskChangeTotal"], requireAll = true)
    @JvmStatic
    fun setTaskChange(view: Button, taskChangeDone: Int, taskChangeTotal: Int) {
        if (taskChangeTotal != 0 && taskChangeDone < taskChangeTotal) {
            view.text = "去签到"
        } else {
            view.text = "已完成"
            view.isEnabled = false
            view.setBackgroundResource(R.drawable.mine_shape_task_btn_finish)
        }
    }

    //显示年月日
    @BindingAdapter("long2dayTime")
    @JvmStatic
    fun setDayTime(textView: TextView, time:Long) {
        val split = time.toDateDayStr().split("-")
        val text = "${split[0]}.${split[1]}.${split[2]}"
        textView.text = text
    }

    //显示具体时间
    @BindingAdapter("long2Time")
    @JvmStatic
    fun setTime(textView: TextView, time:Long) {
        val text = "交易时间：       ${time.toDateStr()}"
        textView.text = text
    }

    //设置center Account的值
    @BindingAdapter("roll_account")
    @JvmStatic
    fun setAccount(view: RollTextView,count:Int){
        view.initCount(count)
    }
}