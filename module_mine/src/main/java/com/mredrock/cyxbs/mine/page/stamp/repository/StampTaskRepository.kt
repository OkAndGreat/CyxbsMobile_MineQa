package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.mine.network.model.stamp.StampTask

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
        tasks.add(StampTask("任务名称", "任务描述", 15, 15, 0, "today"))
        tasks.add(StampTask("任务名称", "任务描述", 15, 15, 2, "today"))
        tasks.add(StampTask("任务名称", "任务描述", 15, 0, 0, "today"))
        tasks.add(StampTask("任务名称", "任务描述", 15, 0, 0, "today"))
        return tasks
    }

    fun getMoreTask(): List<StampTask> {
        var tasks: ArrayList<StampTask> = ArrayList()
        tasks.add(StampTask("任务名称", "任务描述", 15, 5, 5, "more"))
        tasks.add(StampTask("任务名称", "任务描述", 15, 2, 1, "more"))
        return tasks
    }

}