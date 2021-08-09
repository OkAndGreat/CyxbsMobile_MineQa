package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.mine.network.model.stamp.ExChangeDetail
import com.mredrock.cyxbs.mine.network.model.stamp.GetChangeDetail

class StampChangeDetailRepository private constructor() {
    companion object {
        fun getInstance(): StampChangeDetailRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampChangeDetailRepository()
    }


    fun getExChangeDetails(): List<ExChangeDetail> {
        var exChangeDetails = ArrayList<ExChangeDetail>()
        exChangeDetails.add(ExChangeDetail("熊大", 10, "2021-8-7", "15:02", false, 24579268))
        exChangeDetails.add(ExChangeDetail("熊二", 10, "2021-8-7", "15:02", true, 245327968))
        exChangeDetails.add(ExChangeDetail("熊三", 10, "2021-8-7", "15:02", false, 249957968))
        exChangeDetails.add(ExChangeDetail("熊四", 10, "2021-8-7", "15:02", true, 245732968))
        exChangeDetails.add(ExChangeDetail("熊五", 10, "2021-8-7", "15:02", false, 23112768))
        exChangeDetails.add(ExChangeDetail("熊六", 10, "2021-8-7", "15:02", true, 212457968))
        return exChangeDetails
    }

    fun getGetChangeDetails(): List<GetChangeDetail> {
        var getChangeDetailsS = ArrayList<GetChangeDetail>()
        getChangeDetailsS.add(GetChangeDetail("熊大", 10, "2021-8-7"))
        getChangeDetailsS.add(GetChangeDetail("熊二", 10, "2021-8-7"))
        getChangeDetailsS.add(GetChangeDetail("熊三", 10, "2021-8-7"))
        getChangeDetailsS.add(GetChangeDetail("熊四", 10, "2021-8-7"))
        getChangeDetailsS.add(GetChangeDetail("熊五", 10, "2021-8-7"))
        getChangeDetailsS.add(GetChangeDetail("熊六", 10, "2021-8-7"))
        return getChangeDetailsS
    }
}