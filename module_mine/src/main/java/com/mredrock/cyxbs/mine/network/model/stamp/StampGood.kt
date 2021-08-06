package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StampGood(
    //商品名称
    @SerializedName("title")
    val title : String,
    //商品剩余件数
    @SerializedName("rest_count")
    val leftCount : Int,
    //商品描述
    @SerializedName("desc")
    val description:String,
    //商品价格
    @SerializedName("price")
    val price :Int,
    //商品图片
    @SerializedName("image_urls")
    val pic: List<String>
): Serializable
