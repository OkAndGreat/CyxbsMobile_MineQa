package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.model.stamp.ExChangeDetail
import com.mredrock.cyxbs.mine.network.model.stamp.GetChangeDetail
import com.mredrock.cyxbs.mine.page.stamp.repository.StampChangeDetailRepository

class StampChangeViewModel : BaseViewModel() {

    private val repository = StampChangeDetailRepository.getInstance()


    //兑换明细
    private var _exChangeDetails = MutableLiveData<List<ExChangeDetail>>()
    val exChangeDetails: LiveData<List<ExChangeDetail>>
        get() = _exChangeDetails

    //获取明细
    private var _getChangeDetails = MutableLiveData<List<GetChangeDetail>>()
    val getChangeDetails: LiveData<List<GetChangeDetail>>
        get() = _getChangeDetails

    //更新获取明细
    fun loadGetChangeDetails() {
        _getChangeDetails.postValue(repository.getGetChangeDetails())
    }

    //更新兑换明细
    fun loadExChangeDetails() {
        _exChangeDetails.postValue(repository.getExChangeDetails())
    }

    //跳转详情页面
    private var _toExChangePager = MutableLiveData<Int>()
    val toExChangePager: LiveData<Int>
        get() = _toExChangePager

    //跳转详情页面
    fun onClickForToExChangePager(i: Int) {
        println("点击了")
        _toExChangePager.postValue(i)
    }

    //兑换明细
    private var _exChangeDetail = MutableLiveData<ExChangeDetail>()
    val exChangeDetail: LiveData<ExChangeDetail>
        get() = _exChangeDetail

    fun getOneExChangeById(id: Int): ExChangeDetail {
        exChangeDetails.value?.forEach {
            if (it.orderId == id) {
                return it
            }
        }
        return ExChangeDetail("未查找到", 0, 0, false, 0)
    }

}