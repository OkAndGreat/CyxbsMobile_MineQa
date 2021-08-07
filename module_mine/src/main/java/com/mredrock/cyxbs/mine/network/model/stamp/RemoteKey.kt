package com.mredrock.cyxbs.mine.network.model.stamp

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * paging帮助类
 * @property remoteName String
 * @property nextKey Int?
 * @constructor
 */
@Entity
data class RemoteKey(
    @PrimaryKey
    val remoteName: String,
    val nextKey: Int?
)
