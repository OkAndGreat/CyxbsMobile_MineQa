package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.extensions.doOnErrorWithDefaultErrorHandler
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.TestRetrofit
import com.mredrock.cyxbs.mine.network.ApiService
import com.mredrock.cyxbs.mine.network.model.stamp.ExChangeDetail
import com.mredrock.cyxbs.mine.network.model.stamp.GetChangeDetail
import com.mredrock.cyxbs.mine.util.apiService

/**
 * 明细界面仓库
 */
class StampChangeDetailRepository private constructor() {
    companion object {
        fun getInstance(): StampChangeDetailRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampChangeDetailRepository()
    }

    fun getExChangeDetails(function: (List<ExChangeDetail>) -> Unit) = TestRetrofit.testRetrofit
        .getExChangeInfo()
        .setSchedulers()
        .doOnErrorWithDefaultErrorHandler { true }
        .safeSubscribeBy(
            onNext = {
                println("${it.data[1].amount} 更新成功")
                function.invoke(it.data)
            },
            onError = {
                BaseApp.context.toast("请求异常:${it}")
            }
        )


    fun getGetChangeDetails(page:Int,function: (List<GetChangeDetail>) -> Unit) =
        TestRetrofit.testRetrofit.getGetChangeInfo(page, 15)
            .setSchedulers()
            .doOnErrorWithDefaultErrorHandler { true }
            .safeSubscribeBy(
                onNext = {
                    function.invoke(it.data)
                },
                onError = {
                    BaseApp.context.toast("没有更多了")
                }
            )

}