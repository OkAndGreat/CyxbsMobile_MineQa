package com.mredrock.cyxbs.mine.page.stamp.repository

class StampCenterRepository private constructor() {
    companion object {
        fun getInstance(): StampCenterRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampCenterRepository()
    }
}