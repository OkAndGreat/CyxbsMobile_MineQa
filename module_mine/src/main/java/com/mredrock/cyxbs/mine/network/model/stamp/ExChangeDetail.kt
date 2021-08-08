package com.mredrock.cyxbs.mine.network.model.stamp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExChangeDetail(
    //兑换商品名称
    @SerializedName("ware_name")
    val wareName : String,
    //消耗数
    @SerializedName("cost_stamp")
    val amount : Int,
    //兑换时间
    @SerializedName("date")
    val date:String,
    //兑换时间
    @SerializedName("moment")
    val moment:String,
    //是否被兑换 虚拟商品默认为0
    @SerializedName("is_collected")
    val isCollected:Boolean,
    //订单编号
    @SerializedName("order_id")
    val orderId :Int
): Serializable
