package com.mredrock.cyxbs.mine.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 邮票明细bean类
 */
data class MoneyDetail(
    //任务名称
    @SerializedName("action_name")
    val ActionName:String,
    //做这个任务所消耗或得到的邮票数量
    @SerializedName("count")
    val count:Int,
    //这个任务是得到了邮票还是消耗了邮票
    @SerializedName("is_add")
    val IsAdd:Boolean,
    //时间
    @SerializedName("time")
    val time:String
) :Serializable
