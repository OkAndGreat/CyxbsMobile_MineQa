package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.mine.network.model.stamp.StampTask

/**
 * 任务仓库
 */
class StampTaskRepository private constructor() {

    companion object {
        fun getInstance(): StampTaskRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampTaskRepository()
    }


    fun getTodayTask(): List<StampTask> {
        var tasks: ArrayList<StampTask> = ArrayList()
        tasks.add(StampTask("任务名称", "任务描述 +10", 15, 0, "base"))
        tasks.add(StampTask("任务名称", "任务描述 +20", 15, 2, "base"))
        tasks.add(StampTask("任务名称", "任务描述 +30", 0, 0, "base"))
        tasks.add(StampTask("任务名称", "任务描述", 0, 0, "v"))
        return tasks
    }

    fun getMoreTask(): List<StampTask> {
        var tasks: ArrayList<StampTask> = ArrayList()
        tasks.add(StampTask("任务名称", "任务描述", 5, 5, "more"))
        tasks.add(StampTask("任务名称", "任务描述", 2, 1, "more"))
        return tasks
    }

}