package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName

data class BuyGoodBack(
    //任务列表
    @SerializedName("amount")
    val amount: Int,
    //商品列表
    @SerializedName("msg")
    val msg: String
)
