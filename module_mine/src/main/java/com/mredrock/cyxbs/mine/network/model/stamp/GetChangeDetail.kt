package com.mredrock.cyxbs.mine.network.model.stamp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetChangeDetail(
    //完成任务名称
    @SerializedName("task_name")
    val taskName: String,
    //获得邮票数量
    @SerializedName("task_income")
    val gainStamp: Int,
    //兑换时间
    @SerializedName("date")
    val date: Long
) : Serializable
