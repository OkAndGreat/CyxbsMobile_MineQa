package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable



data class StampGood(
    //商品图片
    @SerializedName("urls")
    val pic: List<String>,
    //商品名称
    @SerializedName("title")
    val title : String,
    //商品剩余件数
    @SerializedName("amount")
    val amount : Int,
    //商品描述
    @SerializedName("description")
    val description:String,
    //时限
    @SerializedName("life")
    val life:String,
    //类型 0为实体，1为虚拟
    @SerializedName("type")
    val type:Int,
    //商品价格
    @SerializedName("price")
    val price :Int
): Serializable
