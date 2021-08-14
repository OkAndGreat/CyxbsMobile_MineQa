package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CenterData(
    //商品id
    @SerializedName("id")
    val id: Int,
    //任务列表
    @SerializedName("task")
    val tasks: List<StampTask>,
    //商品列表
    @SerializedName("shop")
    val goods: List<CenterGood>,
    //账户余额
    @SerializedName("user_amount")
    val userAmount: Int,
    //是否有未领取的
    @SerializedName("un_got_good")
    val enterToday: Boolean
) : Serializable
