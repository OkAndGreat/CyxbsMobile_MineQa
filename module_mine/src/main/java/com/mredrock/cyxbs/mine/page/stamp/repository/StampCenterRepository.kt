package com.mredrock.cyxbs.mine.page.stamp.repository

import android.util.Log
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.extensions.doOnErrorWithDefaultErrorHandler
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.TestRetrofit
import com.mredrock.cyxbs.mine.network.model.stamp.CenterGood
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.network.model.stamp.StampTask
import com.mredrock.cyxbs.mine.util.apiService

/**
 * 邮票中心以及详情页仓库
 */
class StampCenterRepository private constructor() {
    companion object {
        fun getInstance(): StampCenterRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampCenterRepository()
    }

    /**
     * 根据后端返回接口，0为虚拟商品，1为实体商品
     * @return List<List<CenterGood>>
     */
    fun getCenterGoodData(
        goods: (List<CenterGood>) -> Unit,
        decorations: (List<CenterGood>) -> Unit,
        todayTasks: (List<StampTask>) -> Unit,
        moreTasks: (List<StampTask>) -> Unit,
        userAccount: (Int) -> Unit,
        unGotGood: (Boolean) -> Unit
    ) {
        TestRetrofit.testRetrofit.getCenterInfo()
            .setSchedulers()
            .doOnErrorWithDefaultErrorHandler { true }
            .safeSubscribeBy(
                onNext = {

                    var centerGoodsList = ArrayList<CenterGood>()
                    val allCenterGoodList = ArrayList<ArrayList<CenterGood>>(2)
                    allCenterGoodList.add(ArrayList())
                    allCenterGoodList.add(ArrayList())
                    centerGoodsList.addAll(it.data.goods)
                    //通过type将他们分类
                    centerGoodsList.forEach {
                        when (it.type) {
                            0 -> {
                                allCenterGoodList[0].add(it)
                            }
                            1 -> {
                                allCenterGoodList[1].add(it)
                            }
                        }
                    }
                    goods.invoke(allCenterGoodList[0])
                    decorations.invoke(allCenterGoodList[1])

                    var centerTaskList = ArrayList<StampTask>()
                    val allCenterTaskList = ArrayList<ArrayList<StampTask>>(2)
                    allCenterTaskList.add(ArrayList())
                    allCenterTaskList.add(ArrayList())
                    centerTaskList.addAll(it.data.tasks)
                    //通过type将他们分类
                    centerTaskList.forEach {
                        when (it.type) {
                            "base" -> {
                                allCenterTaskList[0].add(it)
                            }
                            "more" -> {
                                allCenterTaskList[1].add(it)
                            }
                        }
                    }
                    todayTasks.invoke(allCenterTaskList[0])
                    moreTasks.invoke(allCenterTaskList[1])
                    userAccount.invoke(it.data.userAmount)
                    unGotGood.invoke(it.data.enterToday)
                },
                onError = {
                    Log.d("StampCenterRepository",it.toString())
                    BaseApp.context.toast("请求异常:${it}")
                }
            )
    }
}