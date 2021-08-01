package com.mredrock.cyxbs.mine.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 邮票中心页面当前余额bean类
 */
data class CurMoney(
    @SerializedName("cur_money")
    val curMoney: String
) : Serializable
