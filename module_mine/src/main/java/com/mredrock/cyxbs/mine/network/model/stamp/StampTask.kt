package com.mredrock.cyxbs.mine.network.model.stamp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StampTask(
    //任务名称
    @SerializedName("title")
    val title: String,
    //任务描述
    @SerializedName("description")
    val description: String,
    //该任务完成后能获得的积分数量
    @SerializedName("reward_number")
    val rewardNumber : Int,
    //是否已经完成
    @SerializedName("is_finished")
    val isFinished : Boolean,
    //该任务是否有进度
    @SerializedName("is_progress")
    val isProgress :Boolean,
    //如果存在进度，该任务总的任务量
    @SerializedName("max_progress")
    val totalAmount:Int,
    //如果存在进度，该已经做的任务量
    @SerializedName("current_progress")
    val doneAmount:Int
) : Serializable
