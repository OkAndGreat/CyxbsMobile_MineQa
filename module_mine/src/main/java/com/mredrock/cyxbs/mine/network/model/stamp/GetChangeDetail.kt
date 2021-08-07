package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetChangeDetail(
    //完成任务名称
    @SerializedName("task_name")
    val taskName : String,
    //获得邮票数量
    @SerializedName("gain_stamp")
    val gainStamp : Int,
    //兑换时间
    @SerializedName("date")
    val date:String
): Serializable