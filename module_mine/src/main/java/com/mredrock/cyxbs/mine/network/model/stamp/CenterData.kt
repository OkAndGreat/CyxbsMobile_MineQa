package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CenterData(
    //任务列表
    @SerializedName("task")
    val tasks : List<StampTask>,
    //商品列表
    @SerializedName("shop")
    val goods : List<StampGood>,
    //账户余额
    @SerializedName("user_amount")
    val userAmount : Int,
    //是否有未领取的
    @SerializedName("enter_today")
    val enterToday : Boolean
): Serializable
