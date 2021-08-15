package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CenterGood(
    //商品剩余件数
    @SerializedName("amount")
    val amount: Int,
    //商品名称
    @SerializedName("id")
    val id: Int,
    //商品价格
    @SerializedName("price")
    val price: Int,
    //商品名称
    @SerializedName("title")
    val title: String,
    //商品类型 0实体，1虚拟
    @SerializedName("type")
    val type: Int,
    //商品图片
    @SerializedName("url")
    val url: String
) : Serializable
