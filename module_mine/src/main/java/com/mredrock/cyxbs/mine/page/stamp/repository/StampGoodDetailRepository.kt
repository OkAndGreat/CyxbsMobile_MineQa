package com.mredrock.cyxbs.mine.page.stamp.repository

import android.util.Log
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.extensions.doOnErrorWithDefaultErrorHandler
import com.mredrock.cyxbs.common.utils.extensions.safeSubscribeBy
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.common.utils.extensions.toast
import com.mredrock.cyxbs.mine.TestRetrofit
import com.mredrock.cyxbs.mine.network.model.stamp.BuyGoodBack
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.util.apiService

class StampGoodDetailRepository private constructor() {
    companion object {
        fun getInstance(): StampGoodDetailRepository {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = StampGoodDetailRepository()
    }

    fun getGoodDetail(id: Int, good: (StampGood) -> Unit) {
        Log.d("StampGoodDetailReposit", id.toString())
        TestRetrofit.testRetrofit.getGoodDetail(id)
            .setSchedulers()
            .doOnErrorWithDefaultErrorHandler { true }
            .safeSubscribeBy(
                onNext = {
                    good.invoke(it.data)
                },
                onError = {
                    BaseApp.context.toast("请求异常:${it}")
                }
            )
    }


    fun buyGood(id: Int, buyGoodBackOk: (BuyGoodBack) -> Unit,buyGoodBackError:(String) ->Unit) {
        TestRetrofit.testRetrofit.buyGoodById(id)
            .setSchedulers()
            .doOnErrorWithDefaultErrorHandler { true }
            .safeSubscribeBy(
                onNext = {
                    if (it.status == 10000) {
                        buyGoodBackOk.invoke(it.data)
                    } else {
                        it.info?.let { it1 -> buyGoodBackError.invoke(it1) }
                    }
                },
                onError = {

                }
            )
    }
}
