package com.mredrock.cyxbs.mine.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 邮票中心兑换记录与兑换详情bean类
 */
data class ExchangeInfo(
    //商品名称
    @SerializedName("name")
    val name:String,
    //商品价格
    @SerializedName("price")
    val price:Int,
    //商品交易时间 年月日信息
    @SerializedName("simple_time")
    val SimpleTime:String,
    //商品编号
    @SerializedName("code_num")
    val CodeNum:String,
    //商品状态
    @SerializedName("state")
    val state:Boolean,
    //商品交易时间 年月日时分信息
    @SerializedName("detail_name")
    val DetailTime:String
): Serializable
