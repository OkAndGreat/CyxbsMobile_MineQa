package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.mine.network.model.StampTask
import kotlin.collections.ArrayList

class StampTaskRepository {


    fun getTodayTask():List<StampTask>{
        var tasks: ArrayList<StampTask> = ArrayList()
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,5))
        tasks.add(StampTask("任务名称","任务描述",15,true,true,15,5))
        tasks.add(StampTask("任务名称","任务描述",15,true,true,15,10))
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,2))
        return tasks
    }
    fun getMoreTask():List<StampTask>{
        var tasks: ArrayList<StampTask> = ArrayList()
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,5))
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,5))
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,10))
        tasks.add(StampTask("任务名称","任务描述",15,false,true,15,2))
        return tasks
    }
}