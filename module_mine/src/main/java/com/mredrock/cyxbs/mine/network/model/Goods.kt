package com.mredrock.cyxbs.mine.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 邮票中心商品bean类
 */
data class Goods(
    //商品图片
    @SerializedName("pic")
    val pic: String,
    //商品持续时间
    //约定 返回“无”为虚拟商品
    @SerializedName("last_time")
    val LastTime: String,
    //商品标题
    @SerializedName("title")
    val title : String,
    //商品剩余件数
    @SerializedName("rest_count")
    val RestCount : Int,
    //商品价格
    @SerializedName("price")
    val price :Int,
    @SerializedName("name")
    //商品名称
    val name:String,
    //商品描述
    @SerializedName("description")
    val description:String
) : Serializable
