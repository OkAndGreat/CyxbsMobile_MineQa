package com.mredrock.cyxbs.mine.page.stamp.viewModel

import android.util.Log
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
    private val getChangeDetailList = ArrayList<GetChangeDetail>()


    //更新获取明细
    fun loadGetChangeDetails(page:Int) {
        repository.getGetChangeDetails(page) {
            getChangeDetailList.addAll(it)
            _getChangeDetails.postValue(getChangeDetailList)
        }

    }

    //更新兑换明细
    fun loadExChangeDetails() {
        repository.getExChangeDetails {
            _exChangeDetails.postValue(it)
        }

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
//        Log.d("StampChangeViewModel","$id")
        exChangeDetails.value?.forEach {
            if (it.orderId == id) {
                Log.d("StampChangeViewModel", it.amount.toString())
                return it
            }
        }
        return ExChangeDetail("未查找到", 0, 0, false, 0)
    }

}